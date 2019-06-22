package spo.MyHashSet;

import spo.MyLinkedList.MyLinkedList;


public class MyHashSet {

    private int size = 16;

    private MyLinkedList table[];

    public MyHashSet() {
        table = new MyLinkedList[size];
    }


    private static int hash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    private static int indexFor(int h, int length) {
        return h & (length - 1);
    }

    private boolean changeTable(int index, Object object, String value) {
        if (table[index] != null) {
            for (int i = 0; i < table[index].size(); i++) {
                int hash = hash(table[index].get(i).hashCode());
                if (hash == hash(object.hashCode()) && table[index].contains(object)) {
                    switch (value) {
                        case "add":
                            table[index].replace(object);
                            return true;
                        case "remove":
                            table[index].remove(object);
                            return true;
                        case "contains":
                            return table[index].contains(object);
                        default:
                            break;
                    }
                }
            }
        }
        return false;
    }

    public void add(Object object) {
        int index = indexFor(hash(object.hashCode()), size);
        if (!changeTable(index, object, "add")) {
            table[index] = new MyLinkedList();
            table[index].add(object);
        }
    }

    public void remove(Object object) {
        int index = indexFor(hash(object.hashCode()), size);
        if (!changeTable(index, object, "remove")) {
            System.out.println("Object '" + object + "' is not found!!!");
        }
    }

    public boolean contains(Object object) {
        int index = indexFor(hash(object.hashCode()), size);
        return changeTable(index, object, "contains");
    }
}