import java.util.Iterator;

public interface RandomizedQueueInterface<Item> {
    boolean isEmpty();

    int size();

    void enqueue(Item item);

    Item dequeue();

    Item sample();

    Iterator<Item> iterator();
}
