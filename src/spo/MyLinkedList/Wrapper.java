package spo.MyLinkedList;

class Wrapper {
    private Object item;
    private Wrapper prev;
    private Wrapper next;

    Wrapper(Wrapper last, Object el){
        last.next = this;
        this.item = el;
        this.prev = last;
    }

    Wrapper(Wrapper last, Wrapper first, Object el){
        this.item = el;
        this.prev = last;
        this.next = first;
    }

    Object getItem(){
        return item;
    }

    Wrapper getPrev(){
        return prev;
    }
    Wrapper getNext(){
        return next;
    }

    void setNext(Wrapper next) {
        this.next = next;
    }

    void setPrev(Wrapper prev) {
        this.prev = prev;
    }

    void setItem(Object item) {
        this.item = item;
    }
}