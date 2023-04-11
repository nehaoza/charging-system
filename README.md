**Electric Vehicle Charging System**
===
# **Problem Statement**
There is a bus depot that charges and discharges different electric vehicles. The management of the depot wants to be able to share resources efficiently across all types of vehicles at any time of day. A system is needed to decide what to do when various types of buses are connected to one of the chargers.

A method `getChargeDifferential()` manages the efficiency by doing the following:

Decides how much an EV should be charged or discharged, given the time of day, EV type and current charge

- Any EV connected with less than 50% charge is automatically charged to 80%; any EV with 50-60 % is charged to 70% any EV above 60% is discharged to 50%

- If a school bus is connected before 8am make sure to charge it to 90%; if it is connected after 8am but before 11am discharge to 50%, if the charge is above 50% and if the school bus is connected after 6pm and before 12am discharge to 30%.

- If a commuter bus is connected between 3am and 7am charge up to 95%; if connected after 11:15pm then discharge to 30%.

# **Technologies used**

- Java 17
- Cucumber
- Maven Wrapper
- Spotless
- Errorprone
- Jacoco

# **Build Requirements**
Before being able to build the code you need:
- Java 17

# **How to run the build locally?**
````
git clone https://github.com/nehaoza/charging-system.git

cd charging-system
mvnw.cmd clean test
````

# **Continuous Integration with GitHub Actions**

## **Manually triggering the build**
Navigate to `Actions` -> `Run Charge System Tests` -> on right and side corner click on `Run Workflow` and click on `Run Workflow` button to trigger the build.
It will trigger the build and execute unit tests and integration test. This workflow will also expose the cucumber reports which can be seen in the [GitHub Actions workflow logs](https://github.com/nehaoza/charging-system/actions/runs/4666902150/jobs/8262030963#step:5:277).

## **When it triggers?**
1. When committed to main branch
https://github.com/nehaoza/charging-system/actions/runs/4666641012/jobs/8261455499

2. When Merge request is raised against main branch
https://github.com/nehaoza/charging-system/pull/2

GitHub Actions flow -
https://github.com/nehaoza/charging-system/actions/workflows/maven.yml


## **What it does?**
1. Checks formatting of the code with [spotless plugin](https://github.com/diffplug/spotless) and [fails otherwise during build]()
2. Checks code quality with [error-prone plugin](https://github.com/google/error-prone) and [fails otherwise during build]()
3. Compiles and builds the code
4. Runs Unit tests and Integration tests inside GitHub Actions container
5. Publishes cucumber reports as a part of [GitHub Actions](https://github.com/nehaoza/charging-system/actions/runs/4666902150/jobs/8262030963#step:5:277). Sample collection can be [found here](https://reports.cucumber.io/report-collections/b5a28dca-3018-4a8e-87fb-b7764077f074).
6. Runs Jacoco code coverage to check 90% coverage and [fails otherwise during build](https://github.com/nehaoza/charging-system/actions/runs/4665965591/jobs/8259990082?pr=2)


# **Achieves**
1. Automated the Charging system with the help of JUnit and Cucumber test
2. Achieved 100% code coverage
3. Added CI pipeline with GitHUb Actions
4. Project is built with zero bugs and Vulnerabilities with errorprone.
