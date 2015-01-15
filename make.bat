@echo off
del /s *.class
javac -g -classpath .;lib\jdatepicker-1.3.2.jar com\inventoryges\InventoryGes.java
jar cmf MANIFEST.MF InventoryGes.jar resources com
java -jar InventoryGes.jar
pause