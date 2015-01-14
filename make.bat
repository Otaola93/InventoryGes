@echo off
del /s *.class
javac -g com\inventoryges\InventoryGes.java
jar cmf MANIFEST.MF InventoryGes.jar resources com
java -jar InventoryGes.jar
pause