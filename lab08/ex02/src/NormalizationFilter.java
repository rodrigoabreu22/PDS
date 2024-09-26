package ex02.src;

import java.text.Normalizer;

/**
 * The NormalizationFilter class is a decorator class that normalizes the text
 * obtained from the underlying reader by removing non-ASCII characters and
 * punctuation marks.
 */
public class NormalizationFilter extends BaseDecorator {

    /**
     * Constructs a NormalizationFilter object with the specified reader.
     *
     * @param reader the underlying reader to be decorated
     */
    public NormalizationFilter(ReaderInterface reader) {
        super(reader);
    }

    /**
     * Checks if there is more text to be read from the underlying reader.
     *
     * @return true if there is more text to be read, false otherwise
     */
    @Override
    public boolean hasNext() {
        return super.hasNext();
    }

    /**
     * Returns the next normalized text from the underlying reader.
     * Non-ASCII characters and punctuation marks are removed from the text.
     *
     * @return the next normalized text
     */
    @Override
    public String next() {
        // [^\\p{ASCII}] -> Matches any character that is not ASCII
        // \\p{Punct} -> Matches any punctuation character
        return Normalizer.normalize(super.next(), Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replaceAll("\\p{Punct}", "");
    }
}
