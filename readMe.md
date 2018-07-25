 ```
 int it = 0;
    int y = 0;
    for (; y <= it && it < arr[0].length; y = 0) {
      for (int x = it; y < arr.length && x >= 0; x--, y++) {
        System.out.print(arr[y][x] + " ");
      }
      System.out.println();
      it++;
    }

    int it2 = 1;
    int y2 = 1;
    int x2;
    for (; y2 <= it2 && it2 < arr[0].length; y2 = it2) {
      x2 = 5;
      for (; y2 < arr.length && x2 >= 0; x2--, y2++) {
        System.out.print(arr[y2][x2] + " ");
      }
      System.out.println();
      it2++;
    }
```

# SIMPLE WEB SERVICE PROJECT
[![MIT Licensed](https://img.shields.io/badge/license-MIT-blue.svg)](https://raw.githubusercontent.com/fiskra/sample-web-service/master/LICENSE)
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
 * SOAP(Simple Object Access Protocol) - Not a web service, it is  XML-based web service protocols
 
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
Restful web services supports data format as **Plain text, HTML, XML, JSON etc.** . While building a restful web service, the first thing we should think about is deciding resource format. Sample JSON and XML format for **Person** entity.

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

**HTTP Request:**

<table>
  <tr>
    <td>Verb</td>
    <td>Uri</td>
    <td>HTTP Version</td>
  </tr>
  <tr>
    <td colspan="3">Request Header</td>
  </tr>
  <tr>
    <td colspan="3">Request Body</td>
  </tr>
</table>


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
**HTTP Response:** 

<table>
  <tr>
    <td>HTTP Version</td>
    <td>Response Code</td>
  </tr>
  <tr>
    <td colspan="2">Response Header</td>
  </tr>
  <tr>
    <td colspan="2">Response Body</td>
  </tr>
</table>

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
##### Media Types
The **Accept** and **Content-Type** HTTP headers can be used to describe the content being sent or requested within an HTTP request. 
When sending data **Content-Type to application/xml** is used. The client may set **Accept to application/json** if it is requesting a response in JSON.

#### Addressing Resources
Rest web services have at least one URI(Uniform Resource Identifier (URI) is a string of characters used to identify a resource). URI identifies resources. When building rest apis or whatever we should follow best practices as much as we can. There are some recommendations for well-structured URI:

* The URI should not say anything about the operation or action. The actual operation is determined by an HTTP verb. This enables us to perform different operations by calling same URI.
* Use plural nouns for naming resources
* Avoid using spacing, use underscore(_) or hypen(-)
* The URI is case **insensitive**
* You should have your own convention and it should be well-documented.
* Your URIs should not change but if you need to change it, you should not break the old one, redirecting a new URI and use HTTP 300 code would be a good solution.
* Avoid verbs for your resource like **http://MyService/FetchPerson/1** or **http://MyService/DeletePerson?id=1**
* By default, the URI variable must match the regular expression "[^/]+?". 

Ex : `@Path("/users/{username}")` with regex -> `@Path("users/{username: [a-zA-Z][a-zA-Z_0-9]*}")` if requested url doesn't fit this regex, 404 response will be sent to client.

##### Query Parameters in URI
Building URIs with query parameters is not recommended, you can but there are some disadvantages:

Ex: `http://MyService/Persons?id=1`

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

> No client session state on the server.

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


#### Beyond the so far discussed
So far so good but if we talk about building rest services as an advanced level, we should mention HATEOAS.
Clients must know the API a priori. Changes in the API break clients and they break the documentation about the service. Hypermedia as the engine of application state (a.k.a. HATEOAS) is one more constraint that addresses and removes this coupling. 
Example of hateoas rest service output:

```
{
  _links : {
    self: { href: "http://myhost/person/1" },
  },
  firstname : "Dave",
  lastname : "Matthews"
}

```  

### Building RESTful Web Services
There are many ways to build a restful web service. You can use Spring, JAX-RS or if you are brave and well-experienced on http methods, you can write your own servlets.
JAX-RS is just specification, there are some implementations over there as I mentioned below:

| JAX-RS Implementations |
| --- |
| **Apache CXF**, an open source Web service framework. |
| **Jersey**, the reference implementation from Sun (now Oracle) |
| **RESTeasy**, JBoss's implementation. |
| **Restlet** (old one) |

If we have java ee compliant application server, it has already jax-rs api for building rest web service. Another good example of specification and implementation is 
**JPA and Hibernate**.
**JAX-RS and Jersey**
**JAX-RS and RESTEasy** 

Before deciding on a server to use, we should look at what JAX-RS implementation (name, vendor, version and known bugs) it provides, at least for production environments. For instance, Glassfish comes with Jersey, while Wildfly or Jboss come with RESTEasy. 
	
Building a rest web service is easy with spring-boot. You can check it out here developing basic web service:
[spring-boot-rest](https://spring.io/guides/gs/rest-service/) 


[oracle-ws-certificate-doc](http://java.boot.by/ocewsd6-guide/)

## SOAP

Skeleton of SOAP message

```
<?xml version="1.0"?>

<soap:Envelope
xmlns:soap="http://www.w3.org/2003/05/soap-envelope/"
soap:encodingStyle="http://www.w3.org/2003/05/soap-encoding">

<soap:Header>
...
</soap:Header>

<soap:Body>
...
  <soap:Fault>
  ...
  </soap:Fault>
</soap:Body>

</soap:Envelope>
```

#### SOAP Binding 

The SOAP specification defines the structure of the SOAP messages, not how they are exchanged. This gap is filled by what is called "SOAP Bindings". SOAP bindings are mechanisms which allow SOAP messages to be effectively exchanged using a transport protocol.

* Most SOAP implementations provide bindings for common transport protocols, such as HTTP or SMTP.
* HTTP is synchronous and widely used. A SOAP HTTP request specifies at least two HTTP headers: Content-Type and Content-Length.
* SMTP is asynchronous and is used in last resort or particular cases.
* Java implementations of SOAP usually provide a specific binding for the JMS (Java Messaging System) protocol.


#### WSDL
WSDL represents a contract between a Web Service Consumer and Web Service Provider in which it specifies the exact message format, Internet protocol, Internet address of the Web service provider, etc.
JAX-RPC is one of technologies which can interpret a WSDL document to generate interfaces and network stubs for its described Web service.

* WSDL is used to describe web services
* WSDL is written in XML

WSDL describes the following critical pieces of data:

* Interface information describing all publicly available functions
* Data type information for all message requests and message responses
* Binding information about the transport protocol to be used
* Address information for locating the specified service

Simple WSDL example:  

```
<message name="getTermRequest">
  <part name="term" type="xs:string"/>
</message>

<message name="getTermResponse">
  <part name="value" type="xs:string"/>
</message>

<portType name="glossaryTerms">
  <operation name="getTerm">
    <input message="getTermRequest"/>
    <output message="getTermResponse"/>
  </operation>
</portType>

<binding type="glossaryTerms" name="b1">
   <soap:binding style="document"
   transport="http://schemas.xmlsoap.org/soap/http" />
   <operation>
     <soap:operation soapAction="http://example.com/getTerm"/>
     <input><soap:body use="literal"/></input>
     <output><soap:body use="literal"/></output>
  </operation>
</binding>

```
#### JAXB
The Java Architecture of XML Binding (JAXB) provides a free, fast and convenient way to bind XML schemas to Java representations, making it easy for Java developers to incorporate XML data and processing functions in Java applications without having to know much about XML itself.

## SOAP VS REST 
[rest vs soap comparison](http://maxivak.com/rest-vs-xml-rpc-vs-soap/)
 
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


