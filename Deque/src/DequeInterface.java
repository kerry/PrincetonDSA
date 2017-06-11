import java.util.Iterator;

public interface DequeInterface<Item> {
    boolean isEmpty();

    int size();

    void addFirst(Item item);

    void addLast(Item item);

    Item removeFirst();

    Item removeLast();

    Iterator<Item> iterator();
}