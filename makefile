path=.:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar

MarkdownParse.class: MarkdownParse.java
	javac -cp $(path) MarkdownParse.java

MarkdownParseTest.class: MarkdownParseTest.java
	javac -cp $(path) MarkdownParseTest.java

test: MarkdownParse.class MarkdownParseTest.class
	java -cp $(path) org.junit.runner.JUnitCore MarkdownParseTest
