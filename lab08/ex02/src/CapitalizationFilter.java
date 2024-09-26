package ex02.src;

/**
 * The CapitalizationFilter class is a decorator that capitalizes the words returned by the underlying reader.
 * It extends the BaseDecorator class and implements the ReaderInterface.
 */
public class CapitalizationFilter extends BaseDecorator {
    
    public CapitalizationFilter(ReaderInterface reader) {
        super(reader);
    }

    @Override
    public boolean hasNext() {
        return super.hasNext();
    }

    @Override
    public String next() {
        String word = super.next();
        if (word.length() == 1) {
            return word.toUpperCase();
        } 
        else {
            return word.substring(0, 1).toUpperCase() + word.substring(1, word.length()-1).toLowerCase() + word.substring(word.length()-1).toUpperCase();
        }
    }
}
