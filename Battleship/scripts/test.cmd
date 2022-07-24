@ECHO OFF
:: Batch Script to compile and run the custom test class
cd ../src/test/java
javac -cp ../../../bin -d ../../../bin BattleshipTest.java
java -cp ../../../bin test.java.BattleshipTest
cd ../../../scripts
PAUSE