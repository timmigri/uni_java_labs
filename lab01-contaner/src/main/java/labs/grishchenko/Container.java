package labs.grishchenko;

/**
 * Container for storing elements of certain type
 */
public class Container<T> {
    /**
     * Item to store in Container 
     */
    class Item {
        T data;
        Item next;

        /**
         * Constructor
         * @param data element of certain type
         */
        Item(T data) {
            this.data = data;
        }
    }
    
    private Item m_first;
    private int m_size;

    /**
     * Insert element to the end of contaner
     * @param element element of certain type
     */
    public void insert(T element) {
        Item newItem = new Item(element);
        if (m_first == null) {
            m_first = newItem;
            m_size++;
            return;
        }

        Item currentItem = m_first;
        while (currentItem.next != null) {
            currentItem = currentItem.next;
        }
        currentItem.next = newItem;
        m_size++;
    }

    /**
     * Remove first element occurrence if it exists
     * @param element element of certain type
     * @return boolean - was element removed from container
     */
    public boolean remove(T element) {
        Item prevItem = null;
        Item currentItem = m_first;

        while (currentItem != null) {
            if (!currentItem.data.equals(element)) {
                prevItem = currentItem;
                currentItem = currentItem.next;
                continue;
            }
            if (prevItem == null) {
                m_first = currentItem.next;
            } else {
                prevItem.next = currentItem.next;
            }
            m_size--;
            return true;
        }

        return false;
    }

    /**
     * Return element at index
     * @param index index of element of certain type
     * @return element at index if index exists, otherwise - throws IndexOutOfBoundsException
     */
    public T get(int index) {
        if (index < 0 || index >= m_size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Item currentItem = m_first;
        for (int i = 0; i < index; i++) {
            currentItem = currentItem.next;
        }

        return currentItem.data;
    }

    /**
     * Return number of items in container
     * @return number of items in container
     */
    public int size() {
        return m_size;
    }
}
