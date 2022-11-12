This small repo demonstrates a solution for well-known issues with JaCoCo + multi-project Gradle build + Sonar.

Core goals:

* in each project, a JacCoCo report should show all classes touched by tests, even if they are outside the module running the test. This is especially true for integration tests
  touching a number of modules.
* additionally there should be a single JaCoCo report showing __all__ coverage collected
* Sonar should show an aggregated coverage report (it can't aggregate coverage itself)

Solutions:

1. Cross-module report.<br>In a project, resolve all dependencies on any other local projects, including transitive ones. Add their source sets to JaCoCo scope.
    1. Note: suppose a client talks to backend. The client then may be independent of the backend in terms of build dependencies, but we would still like to collect coverage on
       backend. To collect coverage, include backend in the integration test as testImplementation dependency. On the other hand, how would you collect coverage in something you
       don't run in the test (i.e. run your backend in a Docker container)?
    2. Responsible files:
        1. [jacoco.gradle](gradle/config/jacoco.gradle)
        2. [core.gradle](gradle/config/core.gradle), just apply the former to subprojects.
2. Aggregated report.<br>Thankfully, there's now a plugin for that. You still need to provide source/class directories from your project. However, unlike the previous step,
   we'd like to see __all__ files, including those totally uncovered, in this report. Therefore, all local projects are added to this report, not just dependencies. Also,
   `testCodeCoverageReport` is skipped if its project has no source, so I placed in `:test`.
    1. Responsible files:
        1. [sonar_with_aggregated_report](gradle/config/sonar_with_aggregated_report.gradle), the `testCodeCoverageReport` section.
3. Sonar.<br>Sonar can't merge coverage, dot. Therefore, we must feed the aggregated report to Sonar.
    1. Responsible files:
        1. [sonar_with_aggregated_report](gradle/config/sonar_with_aggregated_report.gradle), the `sonar` section.

Usage: `./gradlew clean build testCodeCoverageReport sonar`. You will find the aggregated report
at [test/build/reports/jacoco/testCodeCoverageReport/html/index.html](test/build/reports/jacoco/testCodeCoverageReport/html/index.html)

Apparently, you can't run Sonar in GitHub without an account, so I provided a [setup](docker/docker-compose.yml) for a local SonarQube installation (arm64 only for now). Run
the containers, [login](http://localhost:9000), set up a project and set project name/token in [gradle.properties](gradle.properties).

PRs/improvements are much welcome!
