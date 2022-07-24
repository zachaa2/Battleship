#!/bin/bash
#Bash script to run the Java Test Class
cd ../src/test/java
javac -cp ../../../bin -d ../../../bin BattleshipTest.java
java -cp ../../../bin test.java.BattleshipTest
cd ../../../scripts
$SHELL