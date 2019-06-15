package lexer;

import java.util.ArrayList;

public class HashSet {
    ArrayList<List> HashList = new ArrayList<>();
    String name;
    public HashSet (String name)
    {
        this.name=name;
        List list1 = new List();
        List list2 = new List();
        List list3 = new List();
        HashList.add(list1);
        HashList.add(list2);
        HashList.add(list3);
    }

    public void AddHash (int element)
    {
        if (!contain(element))
            HashList.get(element%3).addBack(element);
        else System.out.println("This element already exist");
    }

    public void remove (int element)
    {
        if (contain(element))
            HashList.get(element%3).removeElement(element);
        else System.out.println("This element doesn't exist");
    }

    public boolean contain (int element)
    {

        for (int i = 0; i<HashList.get(element%3).getSize();i++)
        {
            if (HashList.get(element%3).getElement(i)==element)
                return true;
        }
        return false;
    }
}
