package ex03.src;

import java.util.Collection;

public class RemoveCommand<E> implements Command {
    private E element;
    private Collection<E> collection;

    public RemoveCommand(Collection<E> collection, E element) {
        this.collection = collection;
        this.element = element;
    }

    @Override
    public void execute() {
        collection.remove(element);
    }

    @Override
    public void undo() {
        collection.add(element);
    }
}
