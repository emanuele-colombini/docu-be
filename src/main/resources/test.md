# Satispay Signature Implementation - TEST
This folder contains the resolution of the interview task that requires to implement a Satispay HTTP Signature authentication.

It's composed by 3 subfolders:
* **dist**: contains the executables files that compose the demo
* **signature.implementation**: contains the maven project of the application that simulates a call to Satispay's endpoint with the authorization header well formed
* **signature.tester**: contains the maven project of a test application used to read request information (headers, query parameters and body) of each call and to simulate Satispay's response

---

## PlantUml demo
@startuml
Class01 <|-- Class02
Class03 *-- Class04
Class05 o-- Class06
Class07 .. Class08
Class09 -- Class10
@enduml

---

## Demo Execution
The **dist** folder contains the followings: 
* the main application _signature.implementation.jar_
* the private key refenced by the implementation _client-rsa-private-key.pem_
* the test server application _signature.tester.jar_

### Running the application
To execute the main application and call the Satispay's test API use the following script

```shell
java -jar signature.implementation.jar
```
If everything is fine, the output will be:
```
OUTPUT
GET - outcome=OK, role=DEVICE, signed string check=OK
POST - outcome=OK, role=DEVICE, signed string check=OK
PUT - outcome=OK, role=DEVICE, signed string check=OK
DELETE - outcome=OK, role=DEVICE, signed string check=OK
```
For each HTTP method:
* **outcome**: OK means that the call was a success (status = 200), otherwise KO
* **role**: the role read from the API's response (json path = _$.authentication_key.role_)
* **signed string check**: OK means that the signed string read from the API's response (json path = _$.signed_string_) is the same to the one sent to the server, otherwise KO
 
### Running the application in local/test mode
To start the test server, run the following script:
```shell
java -jar signature.tester.jar
```
> This server is a simple Spring Boot MVC Application that run a local server on port 8080, to use it be assured that there're not any other local application on this port

To configure the main application to point to the local tester set the _do-local-test_ property to **true**:
```shell
java -jar signature.implementation.jar --app.do-local-test=true
```

---

## Source explanation

### Signature.Implementation
The main application is a Commandline Spring Boot Application; in the main run method it executes the get/post/puth/delete methods of the _SatispayCallService_.

The core of the task is in the _SatispayCallService_ where the actual call to the endpoint is made. Intended as a utility service of a client's application, this service exposes an API for each method and a generic API. 

Those methods prepare the request to the endpoint adding the Authentication header calculated following the Signing HTTP Message authentication type.
The response is then parsed to retreive the **authentication_key.role** and the **signed_string** checking their values.

### Signature.Tester
This is a simple Spring Boot MVC application used to test the main application