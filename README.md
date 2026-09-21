# Campus Study Group Finder

**Developer:** Ayoola Morakinyo

Campus Study Group Finder is a web application that will help students find and organize study groups for their classes.

## Planned Features

- Create and manage a student account
- Search for groups by course
- Create and join study groups
- Schedule study sessions
- Send messages to group members
- Receive meeting reminders
- Manage members and inappropriate posts

## DevOps Plan

The project will use Git for version control, Docker for containerization, and GitHub Actions for automated testing and builds.

## Sample Project and CI

The `dev` branch holds a sample JavaFX project (a temperature converter) used to set up the build pipeline for Project 1.

- `mvn verify` runs Checkstyle (`devops_checks.xml`), the JUnit 5 tests, and the JaCoCo 90% line coverage check.
- `mvn javafx:run` starts the GUI.
- `.github/workflows/ci.yml` runs `mvn verify` on every push and pull request to `dev`, and on every pull request to `release`.

## Branches

- `dev` is the default branch where work happens.
- `release` only accepts changes through a pull request with one approving review and a passing CI run.
