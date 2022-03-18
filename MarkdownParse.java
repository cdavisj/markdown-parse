import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// CommonMark dependencies
import org.commonmark.node.*;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class MarkdownParse {
    /**
     * Creates a {@link Map} of all links from {@code dirOrFile}.
     * 
     * @param dirOrFile directory or file to parse links from
     * @return {@code Map} of links parsed from {@code dirOrFile}
     * @throws IOException
     */
    public static Map<String, List<String>> getLinks(File dirOrFile)
            throws IOException {
        // Map to store links
        Map<String, List<String>> files = new HashMap<>();

        // If we have a directory, call getLinks on all files in that directory
        // and add them to our files map
        if (dirOrFile.isDirectory()) {

            // Iterate over files in directory and add links to files map
            for (File file : dirOrFile.listFiles()) {
                files.putAll(getLinks(file));
            }

            // Return files map
            return files;
        }
        else {
            // This is an individual file (not a directory)
            Path file = dirOrFile.toPath();
            int lastDot = file.toString().lastIndexOf(".");

            // If this file does not have a "." for an extension, or if the
            // extension is not ".md", then return current files map without
            // adding
            if (lastDot == -1
                    || !file.toString().substring(lastDot).equals(".md")) {
                return files;
            }

            // Call getLinks(String) on file contents
            ArrayList<String> links = getLinks(Files.readString(file));
            // Add new links to links map
            files.put(dirOrFile.getPath(), links);

            // Return files map
            return files;
        }
    }

    /**
     * Parses {@code markdown} argument for links and returns an
     * {@link ArrayList} of those links.
     * 
     * @param markdown {@link String} of markdown file content to parse for
     *                 links
     * @return {@code ArrayList} of links from {@code markdown}
     */
    public static ArrayList<String> getLinks(String markdown) {
        Parser parser = Parser.builder().build();
        Node node = parser.parse(markdown);
        LinkVisitor visitor = new LinkVisitor();
        node.accept(visitor);
        return visitor.links;
    }

    /**
     * Driver for {@link MarkdownParse} program. Takes command links arguments
     * for a file or directory to parse for links. Only parses links from
     * {@code .md} extension files or directory containing {@code .md} files.
     * 
     * @param args file or directory to parse links from
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Map<String, List<String>> links = getLinks(new File(args[0]));
        System.out.println(links);
    }
}

/**
 * Visitor class for visiting markdown link nodes. Visits next link node before
 * visits child link nodes.
 */
class LinkVisitor extends AbstractVisitor {
    // Create ArrayList for links
    ArrayList<String> links = new ArrayList<>();

    /**
     * Overrides visit(Link) method to add links to an ArrayList as they are
     * visited.
     */
    @Override
    public void visit(Link link) {
        // Add link to list
        links.add(link.getDestination());

        // Descend into children
        visitChildren(link);
    }
}