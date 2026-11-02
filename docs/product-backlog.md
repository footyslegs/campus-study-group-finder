# Product Backlog

Campus Study Group Finder helps students find and organize study groups for their classes. Items are ordered by priority. The MVP is the first five stories.

| ID | User story | Priority | Status |
|----|------------|----------|--------|
| US-1 | As a student, I want to see a list of study groups and search it by course code so that I can find a group for my class. | High | Done (MVP) |
| US-2 | As a student, I want to identify myself with my name and student ID so that groups know who joined. | High | Done (MVP) |
| US-3 | As the system, I want groups to enforce their rules (valid course, capacity, no duplicate members) so that data stays correct. | High | Done (MVP) |
| US-4 | As a student, I want to create a group with a course, title, location, meeting time, and capacity so that others can join. | High | Done (MVP) |
| US-5 | As a student, I want to open a group, see its members and open seats, and join or leave it. | High | Done (MVP) |
| US-6 | As a student, I want my groups to survive restarting the app, so that I do not lose them. | Medium | Planned |
| US-7 | As a student, I want to send messages to the members of my group. | Medium | Planned |
| US-8 | As a student, I want reminders before a meeting starts. | Low | Planned |
| US-9 | As an organizer, I want to remove members and moderate posts. | Low | Planned |
| US-10 | As a student, I want a real account with a password. | Low | Planned |

## Acceptance criteria for the MVP

- US-1: Typing in the search box filters the list immediately. The search ignores case and spaces, so "comp4420" finds "COMP 4420". An empty search shows everything.
- US-2: The sign in window rejects a blank name or ID with a message and opens the main window otherwise.
- US-3: A course code is two to four letters and four digits. Capacity is between 2 and 50. A full group refuses new members, a student cannot join twice, and the organizer cannot leave.
- US-4: Invalid input leaves the group list unchanged and shows what is wrong. A valid group appears in the main list when the window closes.
- US-5: Join is disabled when the student is a member or the group is full. Leave is disabled for non-members and for the organizer.
