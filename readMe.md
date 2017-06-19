# SIMPLE CRUD WEB SERVICE PROJECT

## Getting Started

### Infrastructure

##### Java 8
##### Maven 
##### Spring-Boot and other dependencies(data-jpa, test, jersey, security,web, web-service)
##### Angular.js
##### H2 in-memory db

### Prerequisites


## What is Web Service? 

Web service is a communication between two applications or machines over the world wide web(www). There are two kind of web service: 
 * REST(Representational State Transfer) 
 * SOAP(Simple Object Access Protocol)
 
## RESTful Web Services

REST stands for REpresentational State Transfer. A service based on REST is called a RESTful service. REST is not dependent on any protocol, but almost every RESTful service uses HTTP as its underlying protocol. We are gonna talk about RESTful web service with HTTP. 
### Features of a RESTful Services
The purpose of a service is to provide resources to clients's window which might be video files, pictures, business information or anything can be represented in computer-based system.In general, RESTful services should have following properties and features:

* Representations
* Messages
* URIs
* Uniform interface
* Stateless
* Links between resources
* Caching 

#### Representations
Restful web services supports data format as **Plain text, HTML, XML, JSON etc.** . While building a restful web service, the first thing we should think about is deciding resource format. Sample JSON and XML format for **Person** entity :
JSON representation:
```
{
    "ID": "1",
    "Name": "M Vaqqas",
    "Email": "m.vaqqas@gmail.com",
    "Country": "India"
}
```

XML representation:
```
<Person>
 
<ID>1</ID>
 
<Name>M Vaqqas</Name>
 
<Email>m.vaqqas@gmail.com</Email>
 
<Country>India</Country>
</Person>

```
In fact, you can use more than one representation type and decide which one to use for a response depending on customer or request parameters.

#### Messages
The client sends the request to the server and the server replies with a response. The request and response includes more information that is called metadata besides the message.

HTTP Request: 

| Verb | Uri | HTTP Version |
| - |
| <td colspan=3> Request Header |
| <td colspan=3> Request Body |

* <VERB> is one of the HTTP methods like GET, PUT, POST, DELETE, OPTIONS, etc
* <URI> is the URI of the resource on which the operation is going to be performed
* <HTTP Version> is the version of HTTP, generally "HTTP v1.1" .
* <Request Header> contains the metadata as a collection of key-value pairs of headers and their values. These settings contain information about the message and its sender like client type, the formats client supports, format type of the message body, cache settings for the response, and a lot more information.
* <Request Body> is the actual message content. In a RESTful service, that's where the representations of resources sit in a message.

A sample POST request: 

```
POST http://MyService/Person/
Host: MyService
Content-Type: text/xml; charset=utf-8
Content-Length: 123
<?xml version="1.0" encoding="utf-8"?>
<Person>
  <ID>1</ID>
  <Name>M Vaqqas</Name>
  <Email>m.vaqqas@gmail.com</Email>
  <Country>India</Country>
</Person>
```
A sample GET request:

```
GET http://www.w3.org/Protocols/rfc2616/rfc2616.html HTTP/1.1
Host: www.w3.org
Accept: text/html,application/xhtml+xml,application/xml; …
User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 …
Accept-Encoding: gzip,deflate,sdch
Accept-Language: en-US,en;q=0.8,hi;q=0.6
```
HTTP Response:
| <HTTP Version> | <Response Code> |  
| --- | --- |
| <Response Header> |
| <Response Body> |

* <Response Header> contains the metadata and settings about the response message.
* <Response Body> contains the representation if the request was successful.
* <Response Code> is a three digit HTTP status code like 200,400,404,500

Response format for GET request:

```
HTTP/1.1 200 OK
Date: Sat, 23 Aug 2014 18:31:04 GMT
Server: Apache/2
Last-Modified: Wed, 01 Sep 2004 13:24:52 GMT
Accept-Ranges: bytes
Content-Length: 32859
Cache-Control: max-age=21600, must-revalidate
Expires: Sun, 24 Aug 2014 00:31:04 GMT
Content-Type: text/html; charset=iso-8859-1
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns='http://www.w3.org/1999/xhtml'>
<head><title>Hypertext Transfer Protocol -- HTTP/1.1</title></head>
<body>
...
```

#### Addressing Resources
Rest web services have at least one URI(Uniform Resource Identifier (URI) is a string of characters used to identify a resource). URI identifies resources. When building rest apis or whatever we should follow best practices as much as we can. There are some recommendations for well-structured URI:

* The URI should not say anything about the operation or action. The actual operation is determined by an HTTP verb. This enables us to perform different operations by calling same URI.
* Use plural nouns for naming resources
* Avoid using spacing, use underscore(_) or hypen(-)
* The URI is case **insensitive**
* You should have your own convention and it should be well-documented.
* Your URIs should not change but if you need to change it, you should not break the old one, redirecting a new URI and use HTTP 300 code would be a good solution.
* Avoid verbs for your resource like **http://MyService/FetcthPerson/1** or **http://MyService/DeletePerson?id=1**

##### Query Parameters in URI
Building URIs with query parameters is not recommended, you can but there are some disadvantages:

Ex: http://MyService/Persons?id=1

* Increased complexity and reduced readability, which will increase if you have more parameters
* Search-engine crawlers and indexers like Google ignore URIs with query parameters.

Use this `http://MyService/Persons/1/json/UTF8`  instead of this `http://MyService/Persons/1?format=xml&encoding=UTF8`

#### Uniform Interface

| **Method** | **Operation performed on server** |  **Quality** |
| --- | --- | --- |
| GET | Read a resource. | Safe |
| PUT | Insert a new resource or update an existing | Idempotent |
| POST | Insert a new resource or update an existing | N/A |
| DELETE  | Delete a resource . | Idempotent |
| OPTIONS | List the allowed operations on a resource. | Safe |
| HEAD | Return only the response headers and no response body. | Safe |
 
** Safe means there is no important how many times we call this operation it doesn't affect the resource. Idempotent means no important how many times is performed this operation it affects the resource.  

Option request:
```
OPTIONS http://MyService/Persons/1 HTTP/1.1
HOST: MyService
```
Response:
```
200 OK
Allow: HEAD, GET, PUT
```
##### What is the difference between PUT and POST?
They do the same job: insert or update existing resource. But first difference is PUT is idempotent while POST is not. No matter how many times you send PUT request the result will be same. Sending many requests may result in multiple resources getting created. PUT needs full URI while POST does not.

| Request | Operation |
| --- | --- |
| PUT  http://MyService/Persons/ | Won't work. PUT requires a complete URI |
| PUT http://MyService/Persons/1 | Insert a new person with PersonID=1 if it does not already exist, or else update the existing resource | 
| POST http://MyService/Persons/ | Insert a new person every time this request is made and generate a new PersonID. |
| POST http://MyService/Persons/1 | Update the existing person where PersonID=1 |  

#### Statelessness 
Rest web service is stateless which means there is no conversational state between client and server. A request is sent, and it is not related old requests or others.
HTTP is a stateless protocol by design and you need to do something extra to implement a stateful service using HTTP. But it is really easy to implement stateful services with current technologies.

* A stateless design looks like so. Each of these requests can be treated separately.
```
Request1: GET http://MyService/Persons/1 HTTP/1.1

Request2: GET http://MyService/Persons/2 HTTP/1.1
```

* A stateful design, on the other hand, looks like so:
```
Request1: GET http://MyService/Persons/1 HTTP/1.1

Request2: GET http://MyService/NextPerson HTTP/1.1 
```

Your application needs to keep last person id is called by client, if the client doesn't send request, this operation won't be performed. 

#### Caching
Caching is a great way of enhancing the service performance, but if not managed properly, it can result in client being served old results.
Caching can be controlled using these HTTP headers:

| Header | Application | 
| --- | --- |
| Date | Date and time when this representation was generated. |
| Last Modified | Date and time when the server last modified this representation. |
| Cache-Control | The HTTP 1.1 header used to control caching. |
| Expires | Expiration date and time for this representation. To support HTTP 1.0 clients. | 
| Age | Duration passed in seconds since this was fetched from the server. Can be inserted by an intermediary component. |


#### Documenting a RESTful Service
There is no policy between client and server in rest, whereas soap has a wsdl, we should have a good documentation to provide resource,actions and methods information for clients. 

| Resource | Methods | URI | Description |
| --- | --- | --- | --- |
| Person | GET,POST,PUT, DELETE | http://MyService/Persons/{PersonID} | Contains information about a person <br/> {PersonID} is optional <br/> Format: text/xml |
| Club | GET,POST,PUT | http://MyService/Clubs/{ClubID} | Contains information about a club. A club can be joined my multiple people <br/> {ClubID} is optional <br/>Format: text/xml |

[Rest Web Service Doc Ref](http://www.drdobbs.com/web-development/restful-web-services-a-tutorial/)

## SOAP

 
 
| SOAP | REST |
| --- | --- |
| SOAP is a protocol. | REST is an architectural style. |
| SOAP stands for Simple Object Access Protocol.| REST stands for REpresentational State Transfer. |
| SOAP can't use REST because it is a protocol. | REST can use SOAP web services because it is a concept and can use any protocol like HTTP, SOAP.|
| SOAP uses services interfaces to expose the business logic. | REST uses URI to expose business logic. |
| **JAX-WS** is the java API for SOAP web services. | **JAX-RS** is the java API for RESTful web services.|
| SOAP defines standards to be strictly followed. | REST does not define too much standards like SOAP. |
| SOAP requires **more bandwidth and resource** than REST. | REST requires **less bandwidth and resource** than SOAP.|
| SOAP defines its own security. | RESTful web services inherits security measures from the underlying transport. |
| SOAP permits **XML** data format only. | REST permits different data format such as **Plain text, HTML, XML, JSON etc.** |


