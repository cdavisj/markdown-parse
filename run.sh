# bash script can end early, if anything fails
set -e

# set up path
path=.:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar

# compile and run tests
javac -cp $path *.java 
java -cp $path org.junit.runner.JUnitCore MarkdownParseTest