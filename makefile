path=lib/*;.

MarkdownParse.class: MarkdownParse.java
	javac -g -cp $(path) MarkdownParse.java

MarkdownParseTest.class: MarkdownParseTest.java MarkdownParse.class
	javac -g -cp $(path) MarkdownParseTest.java

test: MarkdownParseTest.class 
	java -cp $(path) org.junit.runner.JUnitCore MarkdownParseTest

debut-test: MarkdownParseTest.class
	jdb -classpath $(path) org.junit.runner.JUnitCore MarkdownParseTest

TryCommonMark.class: TryCommonMark.java
	javac -g -cp $(path) TryCommonMark.java
