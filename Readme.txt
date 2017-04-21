1) Spring boot is used to build the application.
2) Can find jar and war files in target folders(\password\target)

Two ways to run the application
1) As spring boot comes with embedded  tomcat server ,can run the application from the 
terminal or command prompt.
 java -jar password-0.0.1-SNAPSHOT.jar(to run on the default port 8080)
 java -jar -Dserver.port=8888 password-0.0.1-SNAPSHOT.jar(to run on different port).
2) Deploy the war(assword-0.0.1-SNAPSHOT.war) in target folder to Tomcat(webapps folder).

Inputs
The input to should be given in the JSON format as shown from the postman.
Befor ruuning from the postman in the header section set the Content-Type to application/json.

{
"userName":"rrrr",
"password":"abc1215"
}

URL:http://localhost:8080/password-0.0.1-SNAPSHOT/passwordvalidator/validate/credentials

We can overRide the context path in configuring server.context-path in application.propperties in resources folder


