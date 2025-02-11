import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*

public class LexicalAnalyzer {
    public static void main(String[] args) throws IOException {
        String filePath = "C:\\Users\\Ahmad Abdullah\\Desktop\\file.txt";
        String outputPath = "C:\\Users\\Ahmad Abdullah\\Desktop\\fileOut.txt";

        // Java keywords stored in a HashSet for fast lookup
        HashSet<String> keywords = new HashSet<>(List.of(
                "abstract", "assert", "boolean", "break", "byte", "case", "catch", "char",
                "class", "const", "continue", "default", "do", "double", "else", "enum",
                "extends", "final", "finally", "float", "for", "goto", "if", "implements",
                "import", "instanceof", "int", "interface", "long", "native", "new",
                "package", "private", "protected", "public", "return", "short", "static",
                "strictfp", "super", "switch", "synchronized", "this", "throw", "throws",
                "transient", "try", "void", "volatile", "while"
        ));

        ArrayList<String> keywordFound = new ArrayList<>();
        ArrayList<String> lines;

        // Read all lines from the file
        try {
            lines = new ArrayList<>(Files.readAllLines(Paths.get(filePath)));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        // Extract and write comments to output file
        try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(outputPath))) {
            for (String line : lines) {
                if (line.trim().startsWith("//")) {  // Extract comments
                    fileWriter.write(line.trim());
                    fileWriter.newLine();
                    System.out.println(line.trim());
                }
            }
            System.out.println("Comments successfully written to " + outputPath);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        // Find Java keywords in the file
        for (String line : lines) {
            String[] words = line.split("\\s+"); // Split line into words
            for (String word : words) {
                if (keywords.contains(word)) {
                    keywordFound.add(word);
                }
            }
        }

        // Print found Java keywords
        System.out.println("Java Keywords Found: " + keywordFound);
    }
}
