Import the Maven Project - IL_UIAutomation
Execute below command from project folder.


UAT Environment -   mvn -DAUTO_ENV="UAT" test -Dcucumber.options="--tags @BVT
SIT Environment -   mvn -DAUTO_ENV="UAT" test -Dcucumber.options="--tags @BVT

# Mock Test - Test Automation Framework

The application consists of selenium automation framework using Java.

## UI Integration tests:
This project contains integration tests with TestNg automation framework.

###### Before start
* Install IntelliJ 
* Install Java JDK
* Set path and env variables

NB: Current app has been tested with:
java: "1.8.0_201"


Get the latest Source Code
Open Terminal or command line cd to the desired folder where the test automation source code needs to be checkout

Run command git clone https://github.com/Jaspreet23/CommbankChallenge OR Download from the zip provided
This will download the latest template source code

How to run tests from command line
> mvn -DAUTO_ENV="UAT" test -Dcucumber.options="--tags @SmokeTest

To run tests with debugger
> Right click on the scenario and run with debugger

