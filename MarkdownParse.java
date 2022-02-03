
// File reading code from:
// https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Stack;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();

        int currentIndex = 0;
        Stack<Character> bracketTracker = new Stack<>();
        boolean findLink = false;
        int start = 0;
        int end = 0;
        while (currentIndex < markdown.length()) {
            char curr = markdown.charAt(currentIndex);
            // If an escape char is found, skip it and the
            // character it is escaping
            if (curr == '\\') {
                currentIndex += 2;
                continue;
            }
            // If we are potentially looking at a link with []
            if (findLink) {
                // If there aren't any other brackets on the bracket tracker
                if (bracketTracker.isEmpty()) {
                    if (curr == '(') {
                        bracketTracker.push(curr);
                        start = currentIndex;
                    }
                    else { // Something else came after the ] that wasn't (
                        findLink = false;
                    }
                }

                else {
                    if (curr == ')') {
                        end = currentIndex;
                        toReturn.add(markdown.substring(start + 1, end));
                        bracketTracker.pop();
                        findLink = false;
                    }
                }
            }
            else {

                if (curr == '[') {
                    bracketTracker.push(curr);
                }
                else if (curr == ']') {
                    if (!bracketTracker.isEmpty()) {
                        bracketTracker.clear();
                        findLink = true;
                    }
                }
                else if (curr == '!') {
                    if (currentIndex < markdown.length() - 1
                            && markdown.charAt(currentIndex + 1) == '[') {
                        currentIndex += 2;
                    }
                }
            }
            // Move to next char
            currentIndex++;
        }

        return toReturn;

    }

    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}