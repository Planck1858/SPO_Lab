package lexer;

import java.util.ArrayList;
import java.util.Stack;

public class Poliz {
    ArrayList<Data> data = new ArrayList<Data>();
    ArrayList<ListOfLexems> program = new ArrayList<ListOfLexems>();
    ArrayList<String> poliz = new ArrayList<String>();
    ArrayList<List> list = new ArrayList<List>();
    ArrayList<HashSet>  hash = new ArrayList<HashSet>();
    int iPol = 0;
    int inter = 0;

    public Poliz(ArrayList<ListOfLexems> program) {
        this.program = program;
    }


    public void For() {
        int i = poliz.size();

        poliz.add(program.get(iPol + 2).lexem);
        poliz.add(program.get(iPol + 4).lexem);
        poliz.add(program.get(iPol + 3).lexem);

        int k = poliz.size() - 3 + link(iPol) - iPol + 1; 
        if (Index(iPol) == program.size())
            k--;
        poliz.add(Integer.toString(k));

        poliz.add(program.get(iPol + 6).lexem);
        poliz.add(program.get(iPol + 8).lexem);
        poliz.add(program.get(iPol + 7).lexem);

        poliz.add("!F");

        poliz.add(program.get(iPol + 10).lexem);
        poliz.add(program.get(iPol + 12).lexem);
        poliz.add(program.get(iPol + 14).lexem);
        poliz.add(program.get(iPol + 13).lexem);
        poliz.add(program.get(iPol + 11).lexem);


        iPol += 17;
        while (!program.get(iPol).lexem.equals("}"))
            convert();

        poliz.add(Integer.toString(i + 3));
        poliz.add("!");
        iPol++;

    }


    public void If() {
        //   int i = iPol;
        int k = poliz.size() + link(iPol) - iPol + 1;
        int i = Index(iPol);
        if (i == program.size())
            k--;
        else if (i + 2 < program.size())
            if (program.get(i).lexem.equals("else") & program.get(i + 2).lexem.equals("}") & (i + 3) >= program.size())
                k--;
        poliz.add(Integer.toString(k));
        poliz.add(program.get(iPol + 2).lexem);
        poliz.add(program.get(iPol + 4).lexem);
        poliz.add(program.get(iPol + 3).lexem);
        poliz.add("!F");

        iPol += 7;

        while (!program.get(iPol).lexem.equals("}"))
            convert();

        int b = poliz.size();
        if (iPol + 1 < program.size()) {
            if (program.get(iPol + 1).lexem.equals("else")) {
                b = b + link(iPol + 1) - iPol;
                System.out.println(program.get(i + 2).lexem.equals("}"));
                if ((i + 3) < program.size() & (program.get(i + 2).lexem.equals("}") | Index(iPol + 1) < program.size()))
                    b++;
            } else b += 2;
        } else b++;
        poliz.add(Integer.toString(b));
        poliz.add("!");
        iPol++; 
        if (iPol < program.size() - 1)
            if (program.get(iPol).lexem.equals("else"))
                Else();
    }


    public void Else() {

        iPol += 2; 

        while (!program.get(iPol).lexem.equals("}"))
            convert();
        iPol++;

    }

    public void While() {
        int i = poliz.size();
        int k = poliz.size() + link(iPol) - iPol + 1;
        if (Index(iPol) == program.size())
            k--;
        poliz.add(Integer.toString(k));
        poliz.add(program.get(iPol + 2).lexem);
        poliz.add(program.get(iPol + 4).lexem);
        poliz.add(program.get(iPol + 3).lexem);
        poliz.add("!F");

        iPol += 7;
        while (!program.get(iPol).lexem.equals("}"))
            convert();

        poliz.add(Integer.toString(i));
        poliz.add("!");
        iPol++;
    }

    public int Index(int iPol) {
        int a = iPol;
        int brace = 0;
        do {
            if (program.get(a).lexem.equals("if")) {
                brace++;
            } else if (program.get(a).lexem.equals("while")) {
                brace++;
            } else if (program.get(a).lexem.equals("for")) {
                brace++;
            } else if (program.get(a).lexem.equals("else")) {
                brace++;
            } else if (program.get(a).lexem.equals("}")) {
                brace--;
            }
            a++;
        } while (brace != 0);
        return (a);
    }

    public int link(int iPol) {
        int a = iPol;
        int brace = 0;
        int minus = 0;
        do {
            if (program.get(a).lexem.equals("if")) {
                brace++;
                if (brace > 1) minus++;
            } else if (program.get(a).lexem.equals("while")) {
                brace++;
                if (brace > 1) minus++;
            } else if (program.get(a).lexem.equals("for")) {
                brace++;
                if (brace > 1) minus++;
            } else if (program.get(a).lexem.equals("else")) {
                brace++;
                minus -= 3;
            } else if (program.get(a).lexem.equals("}")) {
                brace--;
            } else if (program.get(a).lexem.equals(",") |program.get(a).lexem.equals(";") | program.get(a).lexem.equals("(") | program.get(a).lexem.equals(")") | program.get(a).lexem.equals(":")) {
                minus--;
            }

            a++;
        } while (brace != 0);
        a += minus;
        return (a);
    }

    public void Var() {
        Stack<String> stack = new Stack<String>();
        poliz.add(program.get(iPol).lexem);
        stack.push(program.get(iPol + 1).lexem);
        iPol += 2;

        while (!program.get(iPol).value.equals("Semicolon")) {
            if (program.get(iPol).value.equals("Var") | program.get(iPol).value.equals("Number")) {
                poliz.add(program.get(iPol).lexem);
                iPol++;
            } else if (program.get(iPol).value.equals("Operation") & stack.peek().equals("=") | program.get(iPol).lexem.equals("(")) {
                stack.push(program.get(iPol).lexem);
                iPol++;
            } else if (program.get(iPol).lexem.equals("+") | program.get(iPol).lexem.equals("-")) {
                while (!stack.empty())
                    if (stack.peek().equals("(") | stack.peek().equals("=")) {
                        stack.push(program.get(iPol).lexem);
                        iPol++;
                        //                       flag = true;
                        break;
                    } else
                        poliz.add(stack.pop());
            } else if (program.get(iPol).lexem.equals("*") | program.get(iPol).lexem.equals("/")) {
                if (stack.peek().equals("*") | stack.peek().equals("/")) {
                    poliz.add(stack.pop());
                    stack.push(program.get(iPol).lexem);
                    iPol++;
                } else {
                    stack.push(program.get(iPol).lexem);
                    iPol++;
                }

            } else if (program.get(iPol).lexem.equals(")")) {
                while (!stack.peek().equals("("))
                    poliz.add(stack.pop());
                stack.pop();
                iPol++;
            }
        }
        if (program.get(iPol).value.equals("Semicolon")) {
            while (!stack.empty())
                poliz.add(stack.pop());
        }
        iPol++;
    }

    public boolean eq(String dataName) {
        for (int k = 0; k < data.size(); k++) {
            if (data.get(k).name.equals(dataName)) {
                return (true);
            }
        }
        return (false);
    }

    public void initialization() {
        int i;
        if (program.get(0).value.equals("Var")) {
            if (!eq(program.get(0).lexem)) {
                Data variable = new Data(program.get(0).lexem, 0);
                data.add(variable);
            }
        }
        for (i = 1; i < program.size(); i++)
            if (program.get(i).value.equals("Var")) {
                if (!eq(program.get(i).lexem))
                if (!program.get(i-1).lexem.equals("List")&!program.get(i-1).lexem.equals("Add")&
                        !program.get(i-1).lexem.equals("remove")&!program.get(i-1).lexem.equals("getSize")&
                        !program.get(i-1).lexem.equals("getElement")&!program.get(i-1).lexem.equals("HashSet")
                        &!program.get(i-1).lexem.equals("HashAdd")&!program.get(i-1).lexem.equals("HashRemove")
                        &!program.get(i-1).lexem.equals("HashContain"))
                {
                    Data variable = new Data(program.get(i).lexem, 0);
                    data.add(variable);
                }
            }
		for(int k=0;k<data.size();k++)
		System.out.println(data.get(k).name+ "  " + data.get(k).value);

        System.out.println("\n");
    }

    public void Polish() {
        while (iPol < program.size())
            convert();

        for (int k = 0; k < poliz.size(); k++)
            System.out.print(poliz.get(k) + " ");
        System.out.println("\n");


         interpreter();
    }

    public void myHash ()
    {
        poliz.add(program.get(iPol).lexem);
        iPol++;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
    }
    public void myHashAdd ()
    {
        poliz.add(program.get(iPol).lexem);
        iPol++;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
    }
    public void myHashRemove ()
    {
        poliz.add(program.get(iPol).lexem);
        iPol++;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
    }
    public void myHashContain ()
    {
        poliz.add(program.get(iPol).lexem);
        iPol++;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
    }
    public void myList ()
    {
        poliz.add(program.get(iPol).lexem);
        iPol++;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
    }
    public void myAdd ()
    {
        poliz.add(program.get(iPol).lexem);
        iPol++;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
    }
    public void myRemove ()
    {
        poliz.add(program.get(iPol).lexem);
        iPol++;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
    }
    public void mySize ()
    {
        poliz.add(program.get(iPol).lexem);
        iPol++;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
    }
    public void myElements ()
    {
        poliz.add(program.get(iPol).lexem);
        iPol++;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
        poliz.add(program.get(iPol).lexem);
        iPol+=2;
    }

    public void convert() {

        if (program.get(iPol).value.equals("Var"))
            Var();
        else if (program.get(iPol).value.equals("KeyWordList"))
            myList ();
        else if (program.get(iPol).value.equals("KeyWordRemove"))
            myRemove ();
        else if (program.get(iPol).value.equals("KeyWordAdd"))
            myAdd ();
        else if (program.get(iPol).value.equals("KeyWordGetElement"))
            myElements ();
        else if (program.get(iPol).value.equals("KeyWordGetSize"))
            mySize ();
        else if (program.get(iPol).value.equals("KeyWordHashSet"))
            myHash();
        else if (program.get(iPol).value.equals("KeyWordHashAdd"))
            myHashAdd();
        else if (program.get(iPol).value.equals("KeyWordHashRemove"))
            myHashRemove ();
        else if (program.get(iPol).value.equals("KeyWordHashContain"))
            myHashContain();
        else if (program.get(iPol).lexem.equals("while"))
            While();
        else if (program.get(iPol).lexem.equals("if"))
            If();
        else if (program.get(iPol).lexem.equals("for"))
            For();
    }

    public void interpreter() {
        initialization();

        langInter();

        for(int k=0;k<data.size();k++)
            System.out.println(data.get(k).name+ "  " + data.get(k).value);
    }

    public void langInter() {
        while (inter < poliz.size() - 1) {
            char[] chars = poliz.get(inter).toCharArray();
            if (poliz.get(inter).equals("List"))
                iList();
            else if (poliz.get(inter).equals("Add"))
                iAdd();
            else if (poliz.get(inter).equals("remove"))
                iRemove();
            else if ((poliz.get(inter).equals("getSize")))
                iSize();
            else if (poliz.get(inter).equals("getElement"))
                iElement();
            else if (poliz.get(inter).equals("HashSet"))
                iHash();
            else if (poliz.get(inter).equals("HashAdd"))
                iHashAdd();
            else if (poliz.get(inter).equals("HashRemove"))
                iHashRemove();
            else if (poliz.get(inter).equals("HashContain"))
                iHashContain();
            else if (Character.isLetter(chars[0]))
                VarInter();
            else if (Character.isDigit(chars[0])&poliz.get(inter+1).equals("!"))
               return;
            else if (Character.isDigit(chars[0]))
                LaraCircl();
            else return;
        }
    }
    public boolean existHash (String name)
    {
        for(int t=0;t<hash.size();t++)
            if (hash.get(t).name.equals(name))
                return true;
        return false;
    }
    public void iHash ()
    {  inter++;
        if (!existHash(poliz.get(inter)))
        {
            HashSet a = new HashSet(poliz.get(inter));
            hash.add(a);
        }

        else System.out.println("List doesn't exist");
        inter++;

    }
    public void iHashAdd ()
    {
        inter++;
        char[] chars = poliz.get(inter+1).toCharArray();
        if (existHash(poliz.get(inter)))
        {
            for (int s=0;s<hash.size();s++)
                if (poliz.get(inter).equals(hash.get(s).name))
                    if (Character.isDigit(chars[0])) {
                        hash.get(s).AddHash(Integer.parseInt(poliz.get(inter + 1)));
                    } else  if (Character.isLetter(chars[0]))
                        for (int h = 0; h < data.size(); h++)
                            if (data.get(h).name.equals(poliz.get(inter+1)))
                                hash.get(s).AddHash(data.get(h).value);

        }
        else System.out.println("This list isn't exist");
        inter+=2;
    }
    public void iHashRemove ()
    {  inter++;
        char[] chars = poliz.get(inter+1).toCharArray();
        if (existHash(poliz.get(inter)))
        {
            for (int s=0;s<hash.size();s++)
                if (poliz.get(inter).equals(hash.get(s).name))
                    if (Character.isDigit(chars[0])) {
                        hash.get(s).remove(Integer.parseInt(poliz.get(inter + 1)));
                    } else  if (Character.isLetter(chars[0]))
                        for (int h = 0; h < data.size(); h++)
                            if (data.get(h).name.equals(poliz.get(inter+1)))
                                hash.get(s).remove(data.get(h).value);

        }
        else System.out.println("This list isn't exist");
        inter+=2;

    }
    public void iHashContain ()
    {   inter++;
        char[] chars = poliz.get(inter+1).toCharArray();
        if (existHash(poliz.get(inter)))
        {
            for (int s=0;s<hash.size();s++)
                if (poliz.get(inter).equals(hash.get(s).name))
                    if (Character.isDigit(chars[0])) {
                        System.out.println("Existing this element("+poliz.get(inter + 1)+") is "+ hash.get(s).contain(Integer.parseInt(poliz.get(inter + 1))));
                    } else if (Character.isLetter(chars[0]))
                        for (int h = 0; h < data.size(); h++)
                            if (data.get(h).name.equals(poliz.get(inter + 1)))
                                System.out.println("Existing this element("+data.get(h).value+") is "+ hash.get(s).contain(Integer.parseInt(poliz.get(inter + 1))));

        }    else System.out.println("This list isn't exist");
        inter+=2;

    }
    public boolean existList (String a)
    {
        for(int t=0;t<list.size();t++)
            if (list.get(t).name.equals(a))
                return true;
        return false;
    }
    public  void  iList()
    {
        inter++;
        if (!existList(poliz.get(inter)))
        {
            List a = new List();
            a.name = poliz.get(inter);
            list.add(a);
        }

        else System.out.println("List doesn't exist");
        inter++;
    }
    public  void  iAdd()
    {
        inter++;
        char[] chars = poliz.get(inter+1).toCharArray();
        if (existList(poliz.get(inter)))
        {
            for (int s=0;s<list.size();s++)
                if (poliz.get(inter).equals(list.get(s).name))
                    if (Character.isDigit(chars[0])) {
                        list.get(s).addBack(Integer.parseInt(poliz.get(inter + 1)));
                    } else  if (Character.isLetter(chars[0]))
                        for (int h = 0; h < data.size(); h++)
                            if (data.get(h).name.equals(poliz.get(inter+1)))
                                list.get(s).addBack(data.get(h).value);

        }
        else System.out.println("This list isn't exist");
        inter+=2;
    }
    public  void  iRemove()
    {
        inter++;
        if (existList(poliz.get(inter)))
        {
            for (int s=0;s<list.size();s++)
                if (poliz.get(inter).equals(list.get(s).name))
                    list.get(s).removeBack();
        }
        else System.out.println("This list isn't exist");
        inter++;
    }
    public  void  iSize()
    {
        inter++;
        if (existList(poliz.get(inter)))
        {
            for (int s=0;s<list.size();s++)
                if (poliz.get(inter).equals(list.get(s).name))
                    System.out.println("Size is "+list.get(s).getSize());
        }
        else System.out.println("This list isn't exist");
        inter++;
    }
    public  void  iElement()
    {
        inter++;
        char[] chars = poliz.get(inter+1).toCharArray();
         if (existList(poliz.get(inter)))
    {
        for (int s=0;s<list.size();s++)
            if (poliz.get(inter).equals(list.get(s).name))
                if (Character.isDigit(chars[0])) {
                    System.out.println("Element is " + list.get(s).getElement(Integer.parseInt
                            (poliz.get(inter + 1))));
                } else if (Character.isLetter(chars[0]))
                    for (int h = 0; h < data.size(); h++)
                        if (data.get(h).name.equals(poliz.get(inter + 1)))
                            System.out.println("Element is " + list.get(s).getElement(data.get(h).value));

            }    else System.out.println("This list isn't exist");
        inter+=2;
    }

             public void VarInter ()
        {
            Stack<String> stack = new Stack<String>();

            int a;
            int b;

            stack.push(poliz.get(inter));
            inter++;

            while (!poliz.get(inter).equals("="))
            {
                char[] chars = poliz.get(inter).toCharArray();


                if (Character.isLetter(chars[0])) {
                    for (int h = 0; h < data.size(); h++)
                        if (data.get(h).name.equals(poliz.get(inter)))
                            stack.push(Integer.toString(data.get(h).value));
                    inter++;
                } else if (Character.isDigit(chars[0])) {
                    stack.push(poliz.get(inter));
                    inter++;
                } else if (poliz.get(inter).equals("+") | poliz.get(inter).equals("-") | poliz.get(inter).equals("/")
                        | poliz.get(inter).equals("*"))
                {
                    b = Integer.parseInt(stack.pop());
                    a = Integer.parseInt(stack.pop());
                    if (poliz.get(inter).equals("+"))
                        stack.push(Integer.toString(a + b));
                    else if (poliz.get(inter).equals("-"))
                        stack.push(Integer.toString(a - b));
                    else if (poliz.get(inter).equals("*"))
                        stack.push(Integer.toString(a * b));
                    else if (poliz.get(inter).equals("/"))
                        stack.push(Integer.toString(a / b));
                    inter++;
                }
            } if (poliz.get(inter).equals("="))
            {
                b = Integer.parseInt(stack.pop());
                String word =stack.pop();
                for (int i = 0; i < data.size(); i++)
                    if (data.get(i).name.equals(word))
                    {
                        data.get(i).value = b;
                    }
                inter++;
            }
        }
    public void LaraCircl()
    {
        String p = poliz.get(inter);
        inter++;
        int m = 0;
        int n = 0;
        for (int i = 0; i < data.size(); i++)
            if (data.get(i).name.equals(poliz.get(inter)))
            {
                m =data.get(i).value ;
            } else  if (data.get(i).name.equals(poliz.get(inter+1)))
                n =data.get(i).value ;
        char[] chars1 = poliz.get(inter).toCharArray();
            if (Character.isDigit(chars1[0]))
                m = Integer.parseInt(poliz.get(inter));
        char[] chars2 = poliz.get(inter+1).toCharArray();
        if (Character.isDigit(chars2[0]))
            n = Integer.parseInt(poliz.get(inter+1));
        inter+=2;
        if (poliz.get(inter).equals(">")) {
            if (m <= n)
            {
                inter = Integer.parseInt(p);
                return;
            }
            else inter++;
        }
        else if (poliz.get(inter).equals("<"))
        {
            if (m>=n)
            {
                inter = Integer.parseInt (p);
                return;
            }
            else inter++;
        }
        else if (poliz.get(inter).equals("=="))
        {
            if (m!=n)
            {
                inter = Integer.parseInt (p);
                return;
            }
            else inter++;
        }
        else if (poliz.get(inter).equals("!="))
            if (m==n)
            {
                inter = Integer.parseInt (p);
                return;
            }
            else inter++;

        if (!poliz.get(inter).equals("!F"))
            System.out.println("Error in circl");

        inter++;

        while (!poliz.get(inter+1).equals("!"))
        langInter(); 

        inter = Integer.parseInt(poliz.get(inter));
    }
}
