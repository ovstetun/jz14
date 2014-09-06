This is the demo code for my Javazone 2014 talk [Using Docker to streamline development!](http://2014.javazone.no/presentation.html?id=0264421f)

## Getting started

First, install the needed components. This setup has only been tested on OS X using [Homebrew](http://brew.sh/), but should work on Linux (no need for boot2docker). Windows-support will need native Docker-support for Windows, which isn't quite there yet...

Anyways, to install the basic components:

* [Docker](http://docker.io)

  ```brew install boot2docker docker```
  ```boot2docker init```
  ```boot2docker up```

  Then use `boot2docker ip` to get the IP of your boot2docker host, and add this to your hosts-file as `localdocker`. This hostname is used throughout the linking process later, and must be set correctly for the other components to work.

* [Fig](http://www.fig.sh/)

  ```brew install python``` (unsure if the python installation in OS X is OK, might try without this one)

  ```pip install fig```
  
* [Scala Build Tool](http://scala-sbt.org) (if working with the scala backend)
  
  ```brew install sbt```

* [Node.js](http://nodejs.org)
  To build the frontend in nginx

  ```brew install node```

* [nginx](http://nginx.org/)

  ```brew install nginx```

After installing, have a look at the Readme-files in each module for instructions to get started.
