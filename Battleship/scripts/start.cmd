@ECHO OFF
:: Batch script to compile and run the GameOfLife Application
cd ../src/main/java
javac -d ../../../bin *.java
ECHO.
ECHO Starting Application
ECHO.
java -cp ../../../bin main.java.Main
cd ../../../scripts
PAUSE