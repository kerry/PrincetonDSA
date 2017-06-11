import java.util.Iterator;

public class RandomizedQueue<Item> implements RandomizedQueueInterface<Item>, Iterable<Item> {

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public void enqueue(Item item) {

    }

    @Override
    public Item dequeue() {
        return null;
    }

    @Override
    public Item sample() {
        return null;
    }

    @Override
    public Iterator<Item> iterator() {
        return null;
    }
}
