package lexer;

public class Node {
    Node next;
    Node prev;
    int  value;

    public Node (Node next, Node prev, int value )
    {
        this.next = next;
        this.prev = prev;
        this.value = value;
    }
}
