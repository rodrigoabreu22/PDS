package ex02.src;

/**
 * The ReaderInterface interface represents a reader that can iterate over a collection of strings.
 */
public interface ReaderInterface {
    /**
     * Checks if there is another string to read.
     * 
     * @return true if there is another string to read, false otherwise.
     */
    public boolean hasNext();

    /**
     * Returns the next string in the collection.
     * 
     * @return the next string in the collection.
     */
    public String next();
}
