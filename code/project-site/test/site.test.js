import test from 'node:test';
import assert from 'node:assert/strict';
import { readdir, readFile } from 'node:fs/promises';
import { server } from '../server.js';
import { once } from 'node:events';

test('all pages, local links, and assets resolve; unsupported requests fail safely', async () => {
  server.listen(0, '127.0.0.1');
  await once(server, 'listening');
  const base = `http://127.0.0.1:${server.address().port}`;
  try {
    const root = new URL('../public/', import.meta.url);
    const paths = await readdir(root, { recursive: true });
    const htmlFiles = paths.filter(p => p.endsWith('.html'));
    assert.equal(htmlFiles.length, 12);
    const links = new Set(['/']);
    for (const file of htmlFiles) {
      const html = await readFile(new URL(file, root), 'utf8');
      assert.match(html, /<html lang="en">/);
      for (const match of html.matchAll(/(?:href|src)="(\/[^"#]*)"/g)) links.add(match[1]);
    }
    for (const link of links) {
      const response = await fetch(base + link);
      assert.equal(response.status, 200, `Broken link: ${link}`);
      assert.ok((await response.arrayBuffer()).byteLength > 0);
    }
    assert.equal((await fetch(base + '/does-not-exist')).status, 404);
    assert.equal((await fetch(base + '/%00')).status, 404);
    assert.equal((await fetch(base + '/%2e%2e%2fserver.js')).status, 404);
    assert.equal((await fetch(base, { method: 'POST' })).status, 405);
    const head = await fetch(base, { method: 'HEAD' });
    assert.equal(head.status, 200);
    assert.equal(await head.text(), '');
    const landing = await (await fetch(base)).text();
    assert.match(landing, /https:\/\/github.com\/footyslegs\/campus-study-group-finder/);
    assert.match(landing, /assets\/to-celsius.png/);
    for (const slug of ['accounts','search','groups','sessions','messages','reminders','moderation']) {
      const html = await (await fetch(base + `/features/${slug}/`)).text();
      assert.match(html, /not available in the current build/);
      assert.match(html, /<ol class="steps">/);
    }
  } finally {
    server.close();
    server.closeAllConnections();
    await once(server, 'close');
  }
});
