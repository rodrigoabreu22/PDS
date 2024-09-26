package ex01.src;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class VectorListIterator<T> implements ListIterator<T>{
    private VectorGeneric<T> vectorGeneric;
    private int index;
    

    public VectorListIterator(VectorGeneric<T> vectorGeneric) {
        this.vectorGeneric = vectorGeneric;
        this.index = 0;
    }

    public VectorListIterator(VectorGeneric<T> vectorGeneric, int index) {
        this.vectorGeneric = vectorGeneric;
        this.index = index;
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
    public boolean hasPrevious() {
        return index > 0;
    }

    @Override
    public T previous() {
        if (!hasPrevious())
            throw new NoSuchElementException();
        return vectorGeneric.getElem(--index);
    }

    @Override 
    public int nextIndex() {
        return index+1;
    }
    
    @Override
    public int previousIndex() {
        return index-1;
    }

    @Override
    public void remove() {
        if (index == 0)
            throw new IllegalStateException();
        vectorGeneric.removeElem(vectorGeneric.getElem(--index));
    }

    @Override
    public void set(T e) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(T e) {
        if (index == 0)
            throw new IllegalStateException();
        vectorGeneric.addElem(e);
    }
}
