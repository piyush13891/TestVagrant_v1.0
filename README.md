# TestVagrant_v1.0

How to execute test case:

1. Extract the Eclipse project and import in Eclipse
2. Project is a Maven Java project and uses TestNG as testing framework.
3. param.Properties file is a configuration file that holds few common configuration details like tolerance, base paths etc. User can make changes according to requirement in this file.
4. Tests are parametrized using testNG parameters - city parameter used for the city for which temperature details required.
5. Project can be executed multiple ways
	a. Right click on textng.xml file and run as TestNG test
	b. Right click POM.xml and execute as maven goal.
	c. Use cmd to execute testng or maven tests.
	d. Integrate with any CI tool like Jenkins/CircleCI and pass required commands.(Solution is CI ready and can be integrated with jobs with minimal efforts)
6. TestNG reports can be found under test-output folder. ITestListener interface of testNG is implemented here.


Few Points to note :

For UI tests PageObject pattern is used. New pages and Methods can be added and testing scope can be increased.
RestAssured is used as API testing tool.


	

