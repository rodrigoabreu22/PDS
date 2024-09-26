package ex02.src;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * A class that implements the ReaderInterface and provides methods for reading text from a file.
 */
public class TextReader implements ReaderInterface {
    private Scanner input;

    /**
     * Constructs a TextReader object with the specified filename.
     *
     * @param filename the name of the file to be read
     * @throws FileNotFoundException if the specified file is not found
     */
    public TextReader(String filename) throws FileNotFoundException {
        File file = new File(filename);
        input = new Scanner(file);
    }

    /**
     * Checks if there is another line of text to be read from the file.
     *
     * @return true if there is another line of text, false otherwise
     */
    @Override
    public boolean hasNext() {
        return input.hasNextLine();
    }

    /**
     * Reads the next line of text from the file.
     *
     * @return the next line of text
     */
    @Override
    public String next() {
        return input.nextLine();
    }
}
