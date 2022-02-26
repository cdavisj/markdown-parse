import static org.junit.Assert.*;
import org.junit.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.*;

public class MarkdownParseTest {
    @Test
    public void TestGroupOne() throws IOException, NoSuchFileException {
        List<String> correctOutput = List.of("https://something.com",
                "some-page.html");
        Path fileName = Path.of("test-files/group-test-file.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }

    @Test
    public void TestGroupTwo() throws IOException, NoSuchFileException {
        List<String> correctOutput = List.of("https://google.com");
        Path fileName = Path.of("test-files/group-test-file2.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }

    @Test
    public void TestGroupThree() throws IOException, NoSuchFileException {
        ArrayList<String> correctOutput = new ArrayList<>();
        Path fileName = Path.of("test-files/group-test-file3.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput.size(), links.size());
    }

    @Test
    public void TestGroupFour() throws IOException, NoSuchFileException {
        List<String> correctOutput = List.of("https://something.com", "");
        Path fileName = Path.of("test-files/group-test-file4.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }

    @Test
    public void TestGroupFive() throws IOException, NoSuchFileException {
        ArrayList<String> correctOutput = new ArrayList<>();
        Path fileName = Path.of("test-files/group-test-file5.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput.size(), links.size());
    }

    @Test
    public void TestOneThroughEight() throws IOException, NoSuchFileException {
        ArrayList<String> correctOutput1 = new ArrayList<>();
        ArrayList<String> correctOutput2 = new ArrayList<>();
        ArrayList<String> emptyList = new ArrayList<>();

        correctOutput1.addAll(
                Arrays.asList("https://something.com", "some-page.html"));
        correctOutput2.addAll(Arrays.asList("https://something.com",
                "some-page.html", "https://something.com", "some-page.html"));

        assertEquals(correctOutput1, MarkdownParse.getLinks(
                Files.readString(Path.of("test-files/test-file.md"))));
        assertEquals(correctOutput2, MarkdownParse.getLinks(
                Files.readString(Path.of("test-files/test-file2.md"))));
        assertEquals(emptyList, MarkdownParse.getLinks(
                Files.readString(Path.of("test-files/test-file3.md"))));
        assertEquals(emptyList, MarkdownParse.getLinks(
                Files.readString(Path.of("test-files/test-file4.md"))));
        assertEquals(emptyList, MarkdownParse.getLinks(
                Files.readString(Path.of("test-files/test-file5.md"))));
        assertEquals(emptyList, MarkdownParse.getLinks(
                Files.readString(Path.of("test-files/test-file6.md"))));
        assertEquals(emptyList, MarkdownParse.getLinks(
                Files.readString(Path.of("test-files/test-file7.md"))));
        assertEquals(emptyList, MarkdownParse.getLinks(
                Files.readString(Path.of("test-files/test-file8.md"))));
    }

    @Test
    public void testMissingParen() {
        String contents = "[link](a.com";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testOpenBracketInTextOfImage() {
        String contents = "![l[ink](a.com)";
        List<String> expect = List.of("a.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testEscapedBracketInLinkText() {
        String contents = "[This\\]is](a.com)";
        List<String> expect = List.of("a.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testSnippet1() throws IOException, NoSuchFileException {
        ArrayList<String> correctOutput = new ArrayList<>();
        correctOutput
                .addAll(Arrays.asList("`google.com", "google.com", "ucsd.edu"));
        Path fileName = Path.of("test-files/snippet1.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }

    @Test
    public void testSnippet2() throws IOException, NoSuchFileException {
        ArrayList<String> correctOutput = new ArrayList<>();
        correctOutput
                .addAll(Arrays.asList("a.com", "a.com(())", "example.com"));
        Path fileName = Path.of("test-files/snippet2.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }

    @Test
    public void testSnippet3() throws IOException, NoSuchFileException {
        ArrayList<String> correctOutput = new ArrayList<>();
        correctOutput.addAll(Arrays.asList("https://www.twitter.com",
                "https://ucsd-cse15l-w22.github.io/", "https://cse.ucsd.edu/"));
        Path fileName = Path.of("test-files/snippet3.md");
        String contents = Files.readString(fileName);
        ArrayList<String> links = MarkdownParse.getLinks(contents);
        assertEquals(correctOutput, links);
    }
}
