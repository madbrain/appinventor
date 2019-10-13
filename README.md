# Standalone AppInventor

This projet is a version of [AppInventor](https://github.com/mit-cml/appinventor-sources) which can run on a local server.
The API format was changed from Gwt RPC to JSON, which allows a better portability.
The dependency on Google App Engine has been replaced with a SpringBoot and PostgreSQL backend.

# How it works

- GWT frontend
- SpringBoot + PostgreSQL backend
- Build Server
- Rendez-vous Server

# How to build

To build this version, you first need to build the the original version from AppInventor.
Set the environment variable `AI_ROOT` with the path to the root of the original sources (the directory
which contains aimerger, aiplayer, etc.).

```
cd scripts
sh build.sh
```

# TODO

- Finalize the docker container for the main application
- Make a docker-compose deployment