This project is a Maven-based Java automation framework.

Prerequisites
Before you begin, make sure you have the following installed:

Java JDK (version 11 or higher recommended)

Maven (version 3.6.0 or higher)

Git (to clone the repository)

How to Set Up and Run Tests
Follow these steps to set up the project and execute the tests:

Clone the Repository

Open your terminal or command prompt and run:

git clone https://github.com/MazenElsherif/Btech.git

Navigate to the Project Directory

Change directory to the cloned project folder:

cd Btech
Run the Tests

Use Maven to run the test suite:

mvn test
This command compiles the project and executes all TestNG tests.

Notes
Ensure java and mvn commands are available in your terminal. You can check this with:

java -version

mvn -version

If you encounter issues with permissions or dependencies, try:

mvn clean install
mvn test
