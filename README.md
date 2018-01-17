# Resource - Trafficsignal

This repo is a Spring Boot implementation of the Traffic Signal API resource. This implementation can be further extended into microservice based 
architecture using rest api by creating controllers and it can be further consumed by reactive UI in asynchronous mode.

# Compile code locally
Following command can be run once code is checked out from repo. Apache Maven 3.3.9 is required to create the build (https://maven.apache.org/).




## Run the Application Locally

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

