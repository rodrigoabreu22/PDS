package ex03.src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Document {
    private String[] content;

    public Document(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            StringBuilder sb = new StringBuilder();
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine());
                sb.append("\n");
            }
            content = sb.toString().split("\n");
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String[] getContent() {
        return content;
    }

    public String toString() {
        return content[0].substring(0, Math.min(20, content[0].length()-1)) + "...";
    }

}
