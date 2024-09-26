package ex02.src;

/**
 * The BaseDecorator class is an implementation of the ReaderInterface interface.
 * It serves as a base class for decorator classes that add additional functionality to a ReaderInterface object.
 */
public class BaseDecorator implements ReaderInterface {
    ReaderInterface reader;

    /**
     * Constructs a BaseDecorator object with the specified ReaderInterface object.
     * 
     * @param reader the ReaderInterface object to be decorated
     */
    public BaseDecorator(ReaderInterface reader) {
        this.reader = reader;
    }

    @Override
    public boolean hasNext() {
        return reader.hasNext();
    }

    @Override
    public String next() {
        return reader.next();
    }   
}
