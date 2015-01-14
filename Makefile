JFLAGS = -g -Xlint:unchecked
JC = javac
SOURCES = $(wildcard *.java)
CLASSES = $(SOURCES:.java=.class)
EXECUTABLE = InventoryGes.jar
MANIFEST = MANIFEST.MF
RESOURCES = $(wildcard resources/*)
.PHONY: clean test all

%.class:	%.java
	$(JC) $(JFLAGS) $*.java

$(EXECUTABLE):	$(MANIFEST) $(CLASSES) $(RESOURCES)
	jar cmf $(MANIFEST) $(EXECUTABLE) $(CLASSES) $(RESOURCES)


all:	$(EXECUTABLE)

clean:
	rm -rf $(EXECUTABLE) $(CLASSES)

test:	$(EXECUTABLE)
	java -jar $(EXECUTABLE)
