package lexer;

public class List {
    Node first;
    Node last;
    int size = 0;
    String name;

    public int getSize()
    {
        return size;
    }

    public void addBack (int value)
    {
        size++;
        if (first==null)
        {
            first = new Node(null,null,value);
            last=first;
        } else
            {
                Node node = new Node(null,last,value);
                this.last.next=node;
                this.last=node;
            }
    }

    public String removeBack()
    {
        String element = "List is empty";
        if (first!=null)
        {
            size--;
            element = "You killed "+ last.value;
            if (last.prev != null)
            {
                last.prev.next = null;
                last = last.prev;
            }
            else
                {
                first = null;
                last = null;
                }
        }

    return (element);
    }
    public int getElement (int index)
    { int element = 0;
    if (index <size)
    { Node node = first;
    if (index==0) return (first.value);
    else
    while (index>0)
    {
        index--;
        node = node.next;
        element = node.value;
    }

    }
    else System.out.println("This element doesn't exist");
       return element;
    }

    public void removeElement (int element)
    {
       if (first!=null)
       {
           if (size==1&first.value==element)
           {
               first = null;
               last = null;
               size--;
               return;
           }
           else if (first.value==element)
           {
               first=first.next;
               size--;
               return;
           }
           else if (last.value==element)
           {
               last=last.prev;
               size--;
               return;
           }
           else {
               Node node = first.next;
               size--;
               while (node.next!=null){

                   if (node.value==element)
                   {
                       node.prev.next=node.next;
                       node.next.prev=node.prev;
                       return;
                   }
                       node=node.next;
               }
           }
       }
       else  System.out.println("Element isn't exist");

    }
}
