<h1 align="center">HTTP Server</h1>


## Table of Contents
- [About](#about)
- [Getting Started](#getting_started)
    - [Requirements](#requirements)
    - [Installation](#installation)
    - [Linting](#linting)
    - [Testing](#testing)
- [Usage](#usage)
    - [Launching the Server](#launching)
    - [Instructions](#instructions)

## About <a name = "about"></a>
This HTTP server establishes a connection with the client using a low-level socket library. It was built according to SOLID principles and using a test-driven development approach.

No external dependencies have been incorporated, apart from those used for linting and testing purposes.

The following routes and methods are currently supported:

| URI                     |   Methods Allowed    |     Response Headers      | Response Body? |
|-------------------------|:--------------------:|:-------------------------:|:--------------:|
| /simple_get             |         GET          |           Allow           |       No       |    
| /simple_get_with_body   |         GET          | Allow,<br/>Content-Length |      Yes       |
 | /head_request           |   HEAD,<br>OPTIONS   |           Allow           |       No       |

The production server is located at [tbd]. Please see instructions below for guidance on installing and running the server locally on your own machine.

## Getting Started <a name = "getting_started"></a>

### Requirements <a name = "requirements"></a>

- <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">Java 17</a>
- <a href="https://www.ruby-lang.org/en/downloads/">Ruby</a> (for running acceptance tests)

### Installation <a name = "installation"></a>

Clone this repo from the terminal:
```
git clone https://github.com/fifikim/http-server.git
```

Navigate to program root directory:
```
cd http-server
```  

Build the project:
```
./gradlew build
```

Next, build the acceptance test suite. Navigate to its directory:
```
cd http-server-spec
```

Install dependencies:
```
bundle install
```

Automated linting and testing has been incorporated into the build process. However, each of these tasks may also be run individually if desired:

### Linting <a name = "linting"></a>

Run the linter for source code:
```
./gradlew checkstyleMain
```

Run the linter for the test suite:
```
./gradlew checkstyleTest
```

### Testing <a name = "testing"></a>

There are separate test suites for unit tests and acceptance tests.

Run unit tests from the project's root directory:
```
./gradlew test
```

Before running the acceptance tests, start the HTTP Server on port 5000. From a separate terminal, navigate to the directory containing acceptance tests:
```
cd http-server-spec
```

Then run:
```
rake test
```

## Usage <a name="usage"></a>

### Launch the server <a name = "launching"></a>

```
java -jar build/libs/http-server.jar
```

### Instructions

tbd
 
