# Swagger

## Introduction

Swagger is an open-source software set of tools to design, build, document and use RESTful web services, developed by SmartBear Software.
It includes automated documentation, code generation and test case generation.
An OpenAPI specification is required to generate all the Swagger tools from. 

## OpenAPI

The OpenAPI specification is an interface for RESTful APIs that allows both humans and computers to understand the functionality of an API without having access to the source code.
This specification is generated in YAML or JSON, though to ensure smooth translation between these to formats it’s recommended to use YAML version 1.2.

## Swagger Tools

### SwaggerHub

Swagger hub is a design tool where multiple user can work on the same RESTful API design at the same time.
It features both an UI for implementing the OpenAPI specification and a generated view of that specification in the Swagger UI way.
Multiple users can be given access to work on the same project and updates can be checked real time.
When the design is complete SwaggerHub can automatically generate the basic RESTful code implementation of your design.

### Swagger Inspector

Swagger Inspector allows a user to easily test their RESTful API in their browser, easily seeing which requests can be made and make them with the click of a button.
The results of these requests can be viewed and all requests are logged.
A number of these requests can be specifically selected to generate a specification from, which can then be examined and edited in SwaggerHub to update the API.

### Swagger UI

Swagger UI is a Swagger tool that allows instant documentation generation of a RESTful API based on a valid specification.
The documentation is generated in HTML and has JSON support. This documentation is interactive and will show all possible end point, their parameters and results of any requests.
No more messing around with Postman or the console to see the request results!

## Sources

https://swagger.io/
https://swagger.io/specification/
https://swagger.io/tools/

