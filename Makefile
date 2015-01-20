JFLAGS = -g -Xlint:unchecked
CLASSPATH = .:lib/jdatepicker-1.3.2.jar
JC = javac
SOURCES = $(shell find com -name *.java)
CLASSES = $(SOURCES:.java=*.class)
EXECUTABLE = InventoryGes.jar
MANIFEST = MANIFEST.MF
RESOURCES = $(wildcard resources/*)

.PHONY: clean test all

%.class:	%.java
	$(JC) -cp $(CLASSPATH) $(JFLAGS) $*.java

$(EXECUTABLE):	$(MANIFEST) $(CLASSES) $(RESOURCES)
	jar cmf $(MANIFEST) $(EXECUTABLE) $(CLASSES) $(RESOURCES)


all:	$(EXECUTABLE)

clean:
	rm -rf $(EXECUTABLE) $(CLASSES)

test:	$(EXECUTABLE)
	java -jar $(EXECUTABLE)
