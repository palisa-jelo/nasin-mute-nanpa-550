package Structures;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.security.spec.ECFieldFp;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Queue;
import java.util.function.Function;

public class MinHashHeapSet<E, K> implements Queue<E> {
    private E[] objects;
    private final Comparator<E> comp;
    private final Function<E, K> keyGetter;
    private final HashMap<K, Integer> map;
    int size;

    public MinHashHeapSet(Comparator<E> comparator, Function<E, K> keyGetter) {
        objects = (E[]) new Object[16];
        this.keyGetter = keyGetter;
        this.comp = comparator;
        map = new HashMap<>();
        size = 0;
    }

    public MinHashHeapSet(int init, Comparator<E> comparator, Function<E, K> keyGetter) {
        objects = (E[]) new Object[init];
        this.comp = comparator;
        this.keyGetter = keyGetter;
        map = new HashMap<>(init);
        size = 0;
    }

    public K getKey(E object) {
        return keyGetter.apply(object);
    }

    private int getLeft(int index) {
        return 2 * index + 1;
    }
        
    private int getRight(int index) {
        return 2 * index + 2;
    }

    private int getParent(int index) {
        return (index - 1) / 2;
    }

    private void bubbleDown(int i) {
        int left = getLeft(i);
        int right = getRight(i);
        int min;
        if(left < objects.length && objects[left] != null) {
            if(objects[right] == null || comp.compare(objects[left], objects[right]) < 0) {
                min = left;
            } else {
                min = right;
            }
            if(comp.compare(objects[i], objects[min]) > 0) {
                swap(i, min);
                bubbleDown(min);
            }
        }
    }

    private void swap(int a, int b) {
        E temp = objects[a];
        objects[a] = objects[b];
        objects[b] = temp;
        map.replace(getKey(objects[a]), a);
        map.replace(getKey(objects[b]), b);
    }
    
    private void bubbleUp(int i) {
        int parent = getParent(i);
        if(i == 0 || parent < 0) {
            return;
        }
        while(comp.compare(objects[i], objects[parent]) < 0) {
            swap(i, parent);
            i = parent;
            parent = getParent(i);
        }
    }

    private void grow() {
        int newCapacity = objects.length << 1;
        objects = Arrays.copyOf(objects, newCapacity);
    }

    private void shrink(){} //NEEDS IMPLEMENTATION

    @Override 
    public boolean add(E e) {
        // See if we should just override an existing entry
        if(map.containsKey(getKey(e))) {
            int loc = map.get(getKey(e));
            // Only replace if smaller
            E current = objects[loc];
            if((comp.compare(current, e)) > 0) {
                objects[loc] = e;
                bubbleUp(loc);
                bubbleDown(loc);
            }
        } else {
            // Insert new entry
            // System.out.println("\n\n" + this);
            if(objects.length - size == 0) {
                grow();
            }
            objects[size] = e;
            map.put(getKey(e), size);
            bubbleUp(size);
            size++;
        }
        return true;
    }

    @Override 
    public boolean offer(E object) {
        try {
            return this.add(object);
        } catch (RuntimeException e) {
            System.out.println(e);
            return false;
        }
    }

    @Override
    public E peek() {
        return objects[0] == null ? null : objects[0];
    }

    @Override
    public E element() {
        return peek();
    }

    @Override 
    public E remove() {
        E output = objects[0];

        size--;
        swap(0, size);
        map.remove(getKey(output));
        objects[size] = null;
        bubbleDown(0);

        return output;
    }

    @Override
    public boolean remove(Object e) {
        throw new UnsupportedOperationException("This method 'remove' is not implemented.");
    }

    @Override 
    public E poll() {
        try {
            return this.remove();
        } catch (RuntimeException e) {
            System.out.println(e);
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object e) {
        if(!e.getClass().equals(objects[0].getClass())) {
            throw new IllegalArgumentException("The object provided cannot be present in the queue");
        }
        return map.containsKey(getKey((E)e));
    }

    @Override
    public E[] toArray() {
        E[] array = (E[]) new Object[objects.length];
        Iterator<E> it = this.iterator();
        int i = 0;
        while(it.hasNext()) {
            array[i] = it.next();
            i++;
        }
        return array;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new UnsupportedOperationException("This method 'toArray' is not implemented.");
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        int count = 0;
        for(E e : objects) {
            if(c.contains(e)) {
                count++;
            }
        }
        return count == c.size();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for(Object e : c) {
            if(!e.getClass().equals(objects[0].getClass())) {
                throw new ClassCastException("One or more elements in the specified collection are incompatible with this collection.");
            } else {
                this.add((E)e);
            }
        }
        // This return operation diverges from documentation
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This method 'removeAll' is not implemented.");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("This method 'retainAll' is not implemented.");
    }

    @Override
    public void clear() {
        this.objects = (E[]) new Object[16];
        map.clear();
        size = 0;
    }
    
    @Override
    public Iterator<E> iterator() {
        return new MHHSIterator(this.objects);
    }

    class MHHSIterator implements Iterator<E> {
        private E[] heap;
        int cursor;
        int size;

        MHHSIterator(E[] objects) {
            this.heap = Arrays.copyOf(objects, objects.length);
            this.cursor = 0;
            this.size = objects.length;
        }

        @Override
        public boolean hasNext() {
            return (objects[0] != null);
        }

        @Override
        public E next() {
            E output = heap[0];
            heap[0] = heap[size];
            size--;
            heap[size] = null;
            this.bubbleDown(0);
            return output;
        }


        private void bubbleDown(int i) {
            int left = getLeft(i);
            int right = getRight(i);
            int min;
            if(left < heap.length && heap[left] != null) {
                if(heap[right] == null || comp.compare(heap[left], heap[right]) < 0) {
                    min = left;
                } else {
                    min = right;
                }
                if(comp.compare(heap[i], heap[min]) > 0) {
                    E temp = heap[i];
                    heap[i] = heap[min];
                    heap[min] = temp;
                    bubbleDown(min);
                }
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        for(E entry : objects) {
            if(entry != null) {
                output += (entry + " | ");
            }
        }
        return output;
    }
}


