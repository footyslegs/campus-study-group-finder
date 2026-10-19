# Campus Study Group Finder: Project Site

COMP 4420 DevOps · Ayoola Morakinyo

## Run

Requires Node.js 18 or later. No third-party dependencies or install step.

```sh
npm start
```

Open http://localhost:3000. Use `PORT=3001 npm start` on macOS/Linux to choose another port. In PowerShell use `$env:PORT=3001; npm start`.

## Verify

```sh
npm test
```

## Pages

- `/`: landing page, project description, real screenshot, repository and documentation links.
- `/docs/`: documentation index linking to quickstart and every feature.
- `/quickstart/`: launch instructions for both this Node site and the current JavaFX application.
- `/features/converter/`: working starter features, verified steps, real screenshots.
- `/features/accounts/`, `/features/search/`, `/features/groups/`, `/features/sessions/`, `/features/messages/`, `/features/reminders/`, `/features/moderation/`: one guide for each planned capability.

## Project status and screenshots

The source repository currently implements a JavaFX temperature converter, not the planned campus web application. The site accurately distinguishes working features from planned workflows. Planned pages are not claims of implemented functionality. Complete implementation and replace those workflow descriptions with verified instructions and screenshots before claiming those features work.

Images in `public/assets/` were captured from the actual running JavaFX scene, using its existing FXML and controllers. The campus connection diagram on the landing page is labeled as an illustration. It is not an application screenshot.

## Repository placement and submission

Keep this folder at `code/project-site` in https://github.com/footyslegs/campus-study-group-finder.

From the repository's `code` directory:

```sh
zip -r campus-study-finder-project-site.zip project-site -x '*/node_modules/*' '*/.DS_Store' '*/.git/*'
```

Submit the ZIP containing the complete Node app. This app needs no node_modules directory.
