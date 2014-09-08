# Frontend

The frontend is based on [Angular.js](http://angularjs.org/), located in the subdirectory `html`. The Angular-application itself is structured using [ngbp](https://github.com/ngbp/ngbp), for the build system and for the structure.

## Getting started

After installing Node.js:

```sh
$ cd html
$ npm -g install grunt-cli karma bower
$ npm install
$ bower install
$ grunt watch
```

This will continously build the frontend, making it available for Nginx to serve locally.

Fire up dependencies with `fig up`. Make nginx ready with and then start nginx with `./start-nginx-localdocker.sh`. The frontend application is available on [localhost:3000](http://localhost:3000/).
