# bash script can end early, if anything fails
# set -e
# set up CLASSPATH
CLASSPATH=.:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar
javac -cp $CLASSPATH *.java 
# One way of exiting the program early. This can become a lot if we have a very
# very long bash script.
# if [ $? != 0 ]
# then
#     echo "Files did not all compile"
#     exit 1
# fi
java -cp $CLASSPATH org.junit.runner.JUnitCore MarkdownParseTest