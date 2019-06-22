package spo.MyLinkedList;

public class MyLinkedList {
    private Wrapper first;
    private Wrapper last;
    private int size = 0;
    private Wrapper header;

    public MyLinkedList() {
        header = new Wrapper(last, first, null);
        first = header;
        last = header;
    }

    public void add(Object el) {
        if (size == 0) {
            last = new Wrapper(last, el);
            first = last;
        } else {
            last = new Wrapper(last, el);
        }
        last.setNext(header);
        header.setPrev(last);
        size++;
    }

    private Wrapper getWrapper(int index) {
        checkElementIndex(index);
        Wrapper x;
        int count;
        if (index < (size >> 1)) {
            x = first;
            count = 0;
            while (index != count) {
                x = x.getNext();
                count++;
            }
            return x;
        } else {
            x = last;
            count = size - 1;
            while (index != count) {
                x = x.getPrev();
                count--;
            }
            return x;
        }
    }

    public Object get(int index) {
        return getWrapper(index).getItem();
    }

    private void remove(int index) {
        Wrapper wrapper = getWrapper(index);
        Wrapper prev = wrapper.getPrev();
        Wrapper next = wrapper.getNext();
        if (prev == header) {
            first = next;
            header.setNext(first);
        } else {
            prev.setNext(next);
            wrapper.setPrev(null);
        }
        if (next == header) {
            last = prev;
            header.setPrev(last);
        } else {
            next.setPrev(prev);
            wrapper.setNext(null);
        }
        wrapper.setItem(null);
        size--;
    }

    public void remove(Object object) {
        remove(getObjectIndex(object));
    }

    public boolean contains(Object object) {
        return getObjectIndex(object) != -1;
    }

    public void set(int index, Object object) {
        getWrapper(index).setItem(object);
    }

    public int size() {
        return size;
    }

    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size;
    }

    private void checkElementIndex(int index) {
        if (!isElementIndex(index))
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
    public void replace(Object object){
        int index=getObjectIndex(object);
        set(index,object);
    }

    private int getObjectIndex(Object o) {
        int count = 0;
        Wrapper x = first;
        if(size()!=0) {
            while (!x.getItem().equals(o)) {
                x = x.getNext();
                count++;
                if (count == size) {
                    break;
                }
            }
        }
        return count < size ? count : -1;
    }
}
