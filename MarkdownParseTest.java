
// importing junit libraries
import static org.junit.Assert.*;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;

public class MarkdownParseTest {

    // javac -cp ".;lib\junit-4.12.jar;lib\hamcrest-core-1.3.jar"
    // MarkdownParseTest.java
    // java -cp ".;lib/junit-4.12.jar;lib/hamcrest-core-1.3.jar"
    // org.junit.runner.JUnitCore MarkdownParseTest
    @Test
    public void failTest() {
        assertTrue(false);
    }

    @Test
    public void addition() {
        // Passes if 2 equals 1 + 1, fails if they are not equal
        assertEquals(2, 1 + 1);
    }

    @Test
    public void TestOne() throws IOException, NoSuchFileException {
        // passes if running Markdown parse returns the correct text for "test-file.md"
        List<String> correctOutput = List.of("https://something.com", "some-page.html");
        Path fileName = Path.of("Group-test-file.md");
        // read the file contents into a string
        String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }

    @Test
    public void TestTwo() throws IOException, NoSuchFileException {
        // passes if running Markdown parse returns the correct text for "test-file2.md"
        List<String> correctOutput = List.of("https://google.com");
        Path fileName = Path.of("Group-test-file2.md");
        // read the file contents into a string
        String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }

    @Test
    public void TestThree() throws IOException, NoSuchFileException {
        // passes if running Markdown parse returns the correct text for "test-file3.md"
        List<String> correctOutput = List.of("this is a link");
        Path fileName = Path.of("Group-test-file3.md");
        // read the file contents into a string
        String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }

    @Test
    public void TestFour() throws IOException, NoSuchFileException {
        // passes if running Markdown parse returns the correct text for "test-file4.md"
        List<String> correctOutput = List.of("https://something.com", "");
        Path fileName = Path.of("Group-test-file4.md");
        // read the file contents into a string
        String contents = Files.readString(fileName);
        // run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }
}
