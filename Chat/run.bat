@echo off

cd build\classes

set CLASSPATH=.
start rmiregistry

::java -cp . -Djava.rmi.server.hostname=localhost server.Server

timeout 2 > NUL

START java server.ServerMainConsole localhost 9999

::timeout 2 > NUL

::java client.ClientMainConsole localhost

cd ..\..

@echo on
