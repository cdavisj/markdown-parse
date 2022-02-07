
// JUnit imports
import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;

// For running tests on Windows
// javac -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" *.java
// java -cp ".;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar" org.junit.runner.JUnitCore MarkdownParseTest

public class MarkdownParseTest {
    @Test
    public void TestOne() throws IOException, NoSuchFileException {
        // Test with "group-test-file.md"
        List<String> correctOutput = List.of("https://something.com",
                "some-page.html");
        Path filePath = Path.of("test-files/group-test-file.md");

        // Read the file contents into a string
        String contents = Files.readString(filePath);

        // Run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(correctOutput, links);
    }

    @Test
    public void TestTwo() throws IOException, NoSuchFileException {
        // Test with "group-test-file2.md"
        List<String> correctOutput = List.of("https://google.com");
        Path filePath = Path.of("test-files/group-test-file2.md");

        // Read the file contents into a string
        String contents = Files.readString(filePath);

        // Run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(correctOutput, links);
    }

    @Test
    public void TestThree() throws IOException, NoSuchFileException {
        // Test with "group-test-file3.md"
        List<String> correctOutput = List.of("this is a link");
        Path filePath = Path.of("test-files/group-test-file3.md");

        // Read the file contents into a string
        String contents = Files.readString(filePath);

        // Run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(correctOutput, links);
    }

    @Test
    public void TestFour() throws IOException, NoSuchFileException {
        // Test with "group-test-file4.md"
        List<String> correctOutput = List.of("https://something.com", "");
        Path filePath = Path.of("test-files/group-test-file4.md");

        // Read the file contents into a string
        String contents = Files.readString(filePath);

        // Run getLinks on the contents of the file
        ArrayList<String> links = MarkdownParse.getLinks(contents);

        assertEquals(correctOutput, links);
    }
}
