
// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;

public class MarkdownParse {
    public static boolean containsSpace(String link) {
        return link.contains(" ");
    }

    /**
     * Parses a string of markdown for links and returns an {@link ArrayList} of
     * those links.
     * 
     * @param markdown file contents to search for links
     * @return ArrayList of links from {@code markdown} string
     */
    public static ArrayList<String> getLinks(String markdown) {
        // List of links found in markdown string
        ArrayList<String> links = new ArrayList<>();

        // Used for tracking brackets in a link
        Stack<Character> bracketTracker = new Stack<>();

        // Index trackers for the current character index, start of a potential
        // link, and end of a potential link
        int currIndex = 0;
        int start = 0;
        int end = 0;

        // Used to track when we are ready to check out a potential link
        boolean findLink = false;

        // Iterate over the text from the markdown file
        while (currIndex < markdown.length()) {
            // Store current character
            char curr = markdown.charAt(currIndex);

            // If an escape char is found, skip it and the character it is
            // escaping
            if (curr == '\\') {
                currIndex += 2;
                continue;
            }

            // If we are potentially looking at a link after finding []
            if (findLink) {
                // If there aren't any other brackets on the bracket tracker we
                // should check what we are looking at and if it is an open
                // bracket and mark the current index as the start of a link
                if (bracketTracker.isEmpty()) {
                    if (curr == '(') {
                        bracketTracker.push(curr);
                        start = currIndex;
                    }
                    else {
                        // Something else came after the ] that wasn't (
                        findLink = false;
                    }
                }
                else {
                    // If curr is ), look at enclosed link and add it to the
                    // list accordingly
                    if (curr == ')') {
                        // Update end index and store potential enclosed link
                        end = currIndex;
                        String link = markdown.substring(start + 1, end);

                        // Add the link if it does not contain any spaces
                        if (!containsSpace(link)) {
                            links.add(link);
                        }

                        // Pop ( from the bracketTracker and update findLink
                        bracketTracker.pop();
                        findLink = false;
                    }
                }
            }
            else {
                // Looking for next potential link

                // If we found a [, add it to bracketTracker
                if (curr == '[') {
                    bracketTracker.push(curr);
                }
                else if (curr == ']') {
                    // If we found a ] and the tracker isn't empty, we have
                    // potentially found a new link.
                    // If this is true, we should clear bracketTracker and
                    // update findLink to look for that next link
                    if (!bracketTracker.isEmpty()) {
                        bracketTracker.clear();
                        findLink = true;
                    }
                }
                else if (curr == '!') {
                    // If we found a potential image tag, skip over the next
                    // bracket to avoid reading it as a link
                    if (currIndex < markdown.length() - 1
                            && markdown.charAt(currIndex + 1) == '[') {
                        currIndex += 2;
                    }
                }
            }

            // Progress currIndex
            currIndex++;
        }

        // Once we exit the while loop, we are ready to return our list of links
        return links;

    }

    public static void main(String[] args) throws IOException {
        // Store first command line argument as a path
        Path fileName = Path.of(args[0]);

        // Store contents of file at path as a string
        String contents = Files.readString(fileName);

        // Use getLinks to parse all of the links from the file
        ArrayList<String> links = getLinks(contents);

        // Output list of links found
        System.out.println(links);
    }
}