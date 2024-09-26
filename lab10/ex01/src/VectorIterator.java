package ex01.src;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class VectorIterator<T> implements Iterator<T>{
    private VectorGeneric<T> vectorGeneric;
    private int index;

    public VectorIterator(VectorGeneric<T> vectorGeneric) {
        this.vectorGeneric = vectorGeneric;
        this.index=0;
    }
    
    @Override
    public boolean hasNext() {
        return index < vectorGeneric.totalElem();
    }

    @Override
    public T next() {
        if (!hasNext())
            throw new NoSuchElementException();
        return vectorGeneric.getElem(index++);
    }
    
    @Override
    public void remove() {
        if (index == 0)
            throw new IllegalStateException();
        vectorGeneric.removeElem(vectorGeneric.getElem(--index));
    }
}
