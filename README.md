# Resource - Trafficsignal

This repo is a Spring Boot implementation of the Traffic Signal API resource. This implementation can be further extended into microservice based 
architecture using rest api by creating controllers and it can be further consumed by reactive UI in asynchronous mode.

# Problem Statement

### Coding problem: Traffic Signals

Write a program that controls the traffic signals for a four-way intersection. Initially, we consider traffic flowing in straight lines only, no turns. The four directions are S(outhbound) and N(orthbound) on Snell Rd; and W(estbound) and E(astbound) on Weaver Rd. The traffic lights should obey the following rules:

1. Cars arrive in each direction on both roads (Snell and Weaver) at the rate of 1 car per second. That is, 4 cars approach the intersection each second.

2. Only one road (Snell or Weaver) can have a "green" light at one time.

3. It is acceptable for both roads to have the "red" light at the same time. Of course, traffic backs up on both roads if this happens.

4. Start by turning on the traffic on Snell Rd "green" in both directions for 3 seconds; then turn it "red" for one second; then turn Weaver "green" for 3 seconds; and then red for one second.

5. When the light turns from red to green at any intersection, it takes the first car 2 seconds to start moving and cross the intersection. Subsequent cars take 1 second each.

6. At the instant the light turns from "green" to "red", a car may not start moving to cross the intersection; whether that car just arrived at the intersection or was waiting at that intersection.

7. The output should be the number of cars that are waiting at the intersection in each direction at each second. Do not make the program wait 20 seconds to produce the output: this is only a simulation, so print the output when it's ready.

8. Expected output
```
 	: N = 0; S = 0; E = 0; W = 0
 	: N = 0; S = 0; E = 1; W = 1
 	: N = 0; S = 0; E = 2; W = 2
 	: N = 0; S = 0; E = 3; W = 3
 	: N = 1; S = 1; E = 4; W = 4
 	: N = 2; S = 2; E = 5; W = 5
 	: N = 3; S = 3; E = 5; W = 5
 	: N = 4; S = 4; E = 5; W = 5
 	: N = 5; S = 5; E = 6; W = 6
```
 
# Compile/run code locally (For 1.0.0 release)
Following command can be run once code is checked out from repo. Apache Maven 3.3.9 is required to create the build (https://maven.apache.org/).
*Manual build process:
1. open git bash and run below command.
git clone https://github.com/codebarn4ajay/resource-trafficsignal.git
2. cd resource-trafficsignal from the working directory
3. run "mvn clean install" command to build.
4. "cd target" command to get the jar.
5. java -jar resource-trafficsignal-1.0.0.jar




## Run the Application Locally (Other options)

Run the application from STS/IntelliJ by right-clicking on the project root in Package Explorer and choosing the 
**Run As > Spring Boot App** menu option. Alternatively, open another terminal window, navigate to the root of this repo 
on your development workstation and use the following command:

    mvn spring-boot:run

# Run the Application on a Server (Without editor)

Build and deploy an executable JAR file, preferably as part of the Continuous Integration/Deployment process.

Run the application using the following command:

    java -jar resource-trafficsignal-{version}.jar

## Application Configuration

Some configuration options have been externalized to allow environment-based overrides. These configuration options and
their default values reside either in the `Application` class or in the `application.properties` file that is 
bundled within the JAR at build time.

### application.properties

Most of these properties should vary rarely if at all between environments.

* `logging.file` = Name of the application's log file(s).
* `server.port` - Specifies the port used to expose REST endpoints.

