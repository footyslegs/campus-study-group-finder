# Campus Study Group Finder

**Developer:** Ayoola Morakinyo

Campus Study Group Finder is a web application that will help students find and organize study groups for their classes.

## MVP Features

- Sign in with a name and student ID
- Browse study groups and filter them by course code
- Create a group with a course, title, location, meeting time, and capacity
- Open a group to see its members and open seats, then join or leave it (capacity is enforced)

Groups are kept in memory while the application runs.

## Planned Features

See the [product backlog](docs/product-backlog.md) for the full list, including messaging, reminders, moderation, and saving groups between runs.

## Documentation

- [Product backlog](docs/product-backlog.md)
- [Sprint backlog](docs/sprint-backlog.md) and [burndown spreadsheet](docs/sprint-backlog-burndown.xlsx)

## Running the Application

Requires JDK 17 or newer and Maven 3.9 or newer.

```sh
mvn javafx:run
```

## Build and Quality Checks

`mvn verify` compiles the code, runs Checkstyle (`devops_checks.xml`), runs the JUnit 5 tests, and enforces 95 percent line coverage with JaCoCo. The `Main` class and the `view.codebehind` package are excluded from coverage because they only start the application and wire controls to view models.

## Architecture

- `model`: Student, CourseCode, StudyGroup, StudyGroupManager. All rules and validation live here.
- `viewmodel`: one view model per window plus AppSession for shared state. They expose JavaFX properties and are fully unit tested.
- `view.codebehind` and `resources/.../view`: FXML windows (sign in, main, create group, group details) and thin codebehind classes with no logic.

## DevOps

The project uses Git for version control and GitHub Actions for automated checks.

- `.github/workflows/ci.yml` runs `mvn verify` on every push to `dev` and on every pull request to `dev` or `release`.

## Branches

- `dev` is the default branch where work happens. Feature branches are created from `dev` and merged back into it.
- `release` only accepts changes through a pull request with one approving review and a passing CI run.

## Project Site

The Node.js project site is in [`code/project-site`](code/project-site). It includes a landing page, documentation index, quickstart, current starter feature instructions with real screenshots, and clearly labeled planned-feature guides.

```sh
cd code/project-site
npm start
```

Open http://localhost:3000. Requires Node.js 18 or later; no dependencies need to be installed. Run `npm test` from the same folder to check local pages, links, assets, and server responses.
