path=.:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar

MarkdownParse.class: MarkdownParse.java
	javac -g -cp $(path) MarkdownParse.java

MarkdownParseTest.class: MarkdownParseTest.java MarkdownParse.class
	javac -g -cp $(path) MarkdownParseTest.java

test: MarkdownParseTest.class 
	java -cp $(path) org.junit.runner.JUnitCore MarkdownParseTest

debut-test: MarkdownParseTest.class
	jdb -classpath $(path) org.junit.runner.JUnitCore MarkdownParseTest
