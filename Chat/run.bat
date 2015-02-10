@echo off

cd build\classes

set CLASSPATH=.
start rmiregistry

::java -cp . -Djava.rmi.server.hostname=localhost server.Server

timeout 2 > NUL

START java server.Server

timeout 2 > NUL

java client.Client localhost
java client.Client localhost
java client.Client localhost

cd ..\..

@echo on