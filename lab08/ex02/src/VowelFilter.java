package ex02.src;

/**
 * The VowelFilter class is a decorator that filters out vowels from the input stream.
 * It extends the BaseDecorator class and overrides the next() method to remove all vowels from the returned string.
 */
public class VowelFilter extends BaseDecorator {
    
    /**
     * Constructs a new VowelFilter object with the specified reader.
     * 
     * @param reader the reader to be decorated
     */
    public VowelFilter(ReaderInterface reader) {
        super(reader);
    }

    /**
     * Checks if there is a next element in the input stream.
     * 
     * @return true if there is a next element, false otherwise
     */
    @Override
    public boolean hasNext() {
        return super.hasNext();
    }

    /**
     * Returns the next element in the input stream with all vowels removed.
     * 
     * @return the next element with vowels removed
     */
    @Override
    public String next() {
        return super.next().replaceAll("[aeiouAEIOU]", "");
    }
}
