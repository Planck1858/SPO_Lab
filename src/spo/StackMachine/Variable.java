package spo.StackMachine;

import spo.MyHashSet.MyHashSet;
import spo.MyLinkedList.MyLinkedList;


public class Variable {
    private float value;
    private String type;
    private MyLinkedList list;
    private MyHashSet set;

    Variable(String type, int value) {
        this.type = type;
        this.value = value;
    }

    Variable(String type, MyLinkedList list) {
        this.type = type;
        this.list = list;
    }

    Variable(String type, MyHashSet hashSet){
        this.type = type;
        this.set = hashSet;
    }

    float getValue() {
        return value;
    }

    MyLinkedList getList(){ return list; }

    public String getType() {
        return type;
    }

    void setValue(float value) { this.value = value; }

    MyHashSet getSet(){return set; }
}