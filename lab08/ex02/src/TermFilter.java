package ex02.src;

/**
 * The TermFilter class is a decorator class that filters terms from a reader.
 * It extends the BaseDecorator class and implements the ReaderInterface.
 */
public class TermFilter extends BaseDecorator {
    private String[] terms;
    private int index;

    /**
     * Constructs a TermFilter object with the specified reader.
     * It initializes the terms array with the terms from the reader's next line
     * and sets the index to 0.
     *
     * @param reader the reader to be decorated
     */
    public TermFilter(ReaderInterface reader) {
        super(reader);
        this.terms = super.next().split(" ");
        this.index = 0;
    }

    /**
     * Checks if there are more terms to be read.
     *
     * @return true if there are more terms, false otherwise
     */
    @Override
    public boolean hasNext() {
        return index < terms.length;
    }

    /**
     * Returns the next term.
     * If the current term is the last term in the array, it gets the next line from the reader
     * and splits it into terms.
     *
     * @return the next term
     */
    @Override
    public String next() {
        String word = terms[this.index];
        this.index++;
        if (this.index == terms.length) {
            if (super.hasNext()) {
                this.terms = super.next().split(" ");
                this.index = 0;
            }
        }
        return word;
    }
}
