package ex03.src;

import java.util.Collection;

public class AddCommand<E> implements Command {
    private E element;
    private Collection<E> collection;

    public AddCommand(Collection<E> collection, E element) {
        this.collection = collection;
        this.element = element;
    }

    @Override
    public void execute() {
        collection.add(element);
    }

    @Override
    public void undo() {
        collection.remove(element);
    }
}
