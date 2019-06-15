package lexer;

import java.util.ArrayList;

public class Parser {

    boolean flag = false;
    boolean flag2 = false;
    int brascets = 0;
    int i=-1;
    int error = 0;
    ArrayList<ListOfLexems> lexems;

    public Parser (ArrayList<ListOfLexems> lexems )
    {
        //this.i=i;
        this.lexems = lexems;
    }

    public boolean lang (ArrayList<ListOfLexems> lexems)
    {
        while (i<lexems.size()-1)
        {
            expr ();
        }

        System.out.println("\n\n error = " + error);
        return (error==0);}

    public void expr () {

        if (i<lexems.size()-1) {
            if (lexems.get(i+1).value=="Var")
            {
                expr_value ();
            }
            else if (lexems.get(i+1).value.equals("KW_List"))
            {myList ();}
            else if (lexems.get(i+1).value.equals("KW_Add"))
            {AddElement ();}
            else if (lexems.get(i+1).value.equals("KW_Remove"))
            {RemoveElement ();}
            else if (lexems.get(i+1).value.equals("KW_GetSize"))
            {getSize ();}
            else if (lexems.get(i+1).value.equals("KW_GetElement"))
            {getElement ();}
            else if (lexems.get(i+1).value.equals("KW_HashSet"))
            {myHash();}
            else if (lexems.get(i+1).value.equals("KW_HashAdd"))
            {myHashAdd();}
            else if (lexems.get(i+1).value.equals("KW_HashRemove"))
            {myHashRemove();}
            else if (lexems.get(i+1).value.equals("KW_HashContain"))
            {myHashContain();}
            else if (lexems.get(i+1).value.equals("KW_For"))
            {expr_for ();}
            else if (lexems.get(i+1).value.equals("KW_While"))
            {expr_wile ();}
            else if (lexems.get(i+1).value.equals("KW_If"))
            {expr_if ();}
            else if (true)
            {System.out.println("error near" + lexems.get(i).lexem); i++; error++; return;}
        }
    }

    public void myHash()
    {
        try {Hash ();}
        catch (Gach e) {}

        try {var ();}
        catch (Gach e) {}

        try {Semicolon ();}
        catch (Gach e) {}
    }

    public void myHashAdd()
    {
        try {HashAdd ();}
        catch (Gach e) {}

        try {var ();}
        catch (Gach e) {}

        try {Comma();}
        catch (Gach e) {}

        try {value();}
        catch (Gach e) {}

        try {Semicolon ();}
        catch (Gach e) {}
    }

    public void myHashRemove()
    {
        try {HashRemove ();}
        catch (Gach e) {}

        try {var ();}
        catch (Gach e) {}

        try {Comma();}
        catch (Gach e) {}

        try {value();}
        catch (Gach e) {}

        try {Semicolon ();}
        catch (Gach e) {}
    }

    public void myHashContain()
    {
        try {HashContain ();}
        catch (Gach e) {}

        try {var ();}
        catch (Gach e) {}

        try {Comma();}
        catch (Gach e) {}

        try {value();}
        catch (Gach e) {}

        try {Semicolon ();}
        catch (Gach e) {}
    }

    public void myList ()
    {
        try {mList ();}
        catch (Gach e) {}

        try {var ();}
        catch (Gach e) {}

        try {Semicolon ();}
        catch (Gach e) {}
    }

    public void AddElement ()
    {
        try {AddElements ();}
        catch (Gach e) {}

        try {var ();}
        catch (Gach e) {}

        try {Comma();}
        catch (Gach e) {}

        try {value();}
        catch (Gach e) {}

        try {Semicolon ();}
        catch (Gach e) {}
    }

    public void RemoveElement ()
    {
        try {Remove ();}
        catch (Gach e) {}

        try {var ();}
        catch (Gach e) {}

        try {Semicolon ();}
        catch (Gach e) {}
    }

    public void getSize ()
    {
        try {GetSize ();}
        catch (Gach e) {}

        try {var ();}
        catch (Gach e) {}

        try {Semicolon ();}
        catch (Gach e) {}
    }

    public void getElement ()
    {
        try {GetElement ();}
        catch (Gach e) {}

        try {var ();}
        catch (Gach e) {}

        try {Comma();}
        catch (Gach e) {}

        try {value();}
        catch (Gach e) {}

        try {Semicolon ();}
        catch (Gach e) {}
    }

    public void expr_value () {

        brascets = 0;

        try {var ();}
        catch (Gach e) {}

        try {Equally ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return;

        try {value_brasket ();}
        catch (Gach e) {}

        if ((i<lexems.size()-1))
            while (!lexems.get(i+1).value.equals("Semicolon"))
            {
                if (lexems.get(i+1).value =="Operation")
                    try {Operation();}
                    catch (Gach e) {}
                if (lexems.get(i).value =="Semicolon") return;
                
                try {value_brasket ();}
                catch (Gach e) {}
                if (lexems.get(i).value =="Semicolon") return;
            }

        if (lexems.size()!=i+1)
        try {Semicolon();}
        catch (Gach e) {}

        if (brascets > 0)
        {
            System.out.println("May be you forgot ')'  ");
            error++;
        }
        else if (brascets < 0)
        {
            System.out.println("May be you forgot '('  ");
            error++;
        }
    }

    public void expr_for ()
    {
        try {KW_For ();}
        catch (Gach e) {}

        try {Open_bracket ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {var ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {Equally ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {value ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {Colon ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {var ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {booleanOP();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {value ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {Colon ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {var ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {Equally ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {var ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {Operation();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {value ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {Close_bracket ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {Open_brace();}
        catch (Gach e) {}

        if ((i<lexems.size()-1))
            while (!lexems.get(i+1).value.equals("Close_brace"))
                if (!lexems.get(i+1).value.equals("Var")&!lexems.get(i+1).value.equals("KW_While")
                        &!lexems.get(i+1).value.equals("KW_For")&!lexems.get(i+1).value.equals("KW_If")
                        &!lexems.get(i+1).value.equals("KW_Else")
                        &!lexems.get(i+1).value.equals("KW_List")&!lexems.get(i+1).value.equals("KW_Add")
                        &!lexems.get(i+1).value.equals("KW_Remove")&!lexems.get(i+1).value.equals("KW_GetElement")
                        &!lexems.get(i+1).value.equals("KW_GetSize")
                &!lexems.get(i+1).value.equals("KW_HashSet")&!lexems.get(i+1).value.equals("KW_HashAdd")
                &!lexems.get(i+1).value.equals("KW_HashRemove")&!lexems.get(i+1).value.equals("KW_HashContain"))
                {
                    System.out.println("error near" + lexems.get(i).lexem);
                    break;
                }
                else
                    expr ();

        try {Close_brace();}
        catch (Gach e) {}
    }

    public void expr_wile ()
    {
        try {KW_Wile ();}
        catch (Gach e) {}

        try {Open_bracket ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {value ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {booleanOP ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {value ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {Close_bracket ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;
        
        try {Open_brace();}
        catch (Gach e) {}

        if ((i<lexems.size()-1))
            while (!lexems.get(i+1).value.equals("Close_brace"))
                if (!lexems.get(i+1).value.equals("Var")&!lexems.get(i+1).value.equals("KW_While")
                        &!lexems.get(i+1).value.equals("KW_For")&!lexems.get(i+1).value.equals("KW_If")
                        &!lexems.get(i+1).value.equals("KW_Else") &!lexems.get(i+1).value.equals("KW_List")&!lexems.get(i+1).value.equals("KW_Add")
                        &!lexems.get(i+1).value.equals("KW_Remove")&!lexems.get(i+1).value.equals("KW_GetElement")
                        &!lexems.get(i+1).value.equals("KW_GetSize")
                        &!lexems.get(i+1).value.equals("KW_HashSet")&!lexems.get(i+1).value.equals("KW_HashAdd")
                        &!lexems.get(i+1).value.equals("KW_HashRemove")&!lexems.get(i+1).value.equals("KW_HashContain"))
                {
                    System.out.println("error near" + lexems.get(i).lexem);
                    break;
                }
                else
                    expr ();


        try {Close_brace();}
        catch (Gach e) {}


    }

    public void expr_else ()
    {
        try {KW_Else ();}
        catch (Gach e) {}


        try {Open_brace();}
        catch (Gach e) {}


        while ((i<lexems.size()-1)&(!lexems.get(i+1).value.equals("Close_brace")))
            if (!lexems.get(i+1).value.equals("Var")&lexems.get(i+1).value.equals("KW_While")
                    &!lexems.get(i+1).value.equals("KW_For")&!lexems.get(i+1).value.equals("KW_If")
                    &!lexems.get(i+1).value.equals("KW_Else") &!lexems.get(i+1).value.equals("KW_List")&!lexems.get(i+1).value.equals("KW_Add")
                    &!lexems.get(i+1).value.equals("KW_Remove")&!lexems.get(i+1).value.equals("KW_GetElement")
                    &!lexems.get(i+1).value.equals("KW_GetSize")
                    &!lexems.get(i+1).value.equals("KW_HashSet")&!lexems.get(i+1).value.equals("KW_HashAdd")
                    &!lexems.get(i+1).value.equals("KW_HashRemove")&!lexems.get(i+1).value.equals("KW_HashContain"))
            {
                System.out.println("error near" + lexems.get(i).lexem);
                break;
            }
            else
                expr ();


        try {Close_brace();}
        catch (Gach e) {}
    }

    public void expr_if ()
    {
        try {KW_If ();}
        catch (Gach e) {}


        try {Open_bracket ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {value ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {booleanOP ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {value ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;

        try {Close_bracket ();}
        catch (Gach e) {}
        if (lexems.get(i).value.equals("Semicolon")) return ;
        
        try {Open_brace();}
        catch (Gach e) {}


        while ((i<lexems.size()-1)&(!lexems.get(i+1).value.equals("Close_brace")))
            if (!lexems.get(i+1).value.equals("Var")&!lexems.get(i+1).value.equals("KW_While")
                    &!lexems.get(i+1).value.equals("KW_For")&!lexems.get(i+1).value.equals("KW_If")
                    &!lexems.get(i+1).value.equals("KW_Else") &!lexems.get(i+1).value.equals("KW_List")&!lexems.get(i+1).value.equals("KW_Add")
                    &!lexems.get(i+1).value.equals("KW_Remove")&!lexems.get(i+1).value.equals("KW_GetElement")
                    &!lexems.get(i+1).value.equals("KW_GetSize")
                    &!lexems.get(i+1).value.equals("KW_HashSet")&!lexems.get(i+1).value.equals("KW_HashAdd")
                    &!lexems.get(i+1).value.equals("KW_HashRemove")&!lexems.get(i+1).value.equals("KW_HashContain"))
            {
                System.out.println("error near  " + lexems.get(i).lexem);
                break;
            }
            else
                expr ();


        try {Close_brace();}
        catch (Gach e) {}
        
        if (i+1<lexems.size()-1)
        if(lexems.get(i+1).lexem.equals("else"))
            expr_else ();
    }

    //Ошибки:
    public void brasket() throws Gach
    {
        if(!flag2)i++;
        flag=true;

        try {Open_bracket ();}
        catch (Gach e)
        {
            try {Close_bracket ();}
            catch (Gach ex)
            {
                if (!flag2)
                {
                    error++;
                    System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting value or brascet");
                }
                else throw  new Gach ("Waiting brasket");
            }
        }
        this.flag=false;
    }

    public void value_brasket () throws Gach
    {
        i++;
        this.flag2=true;
        try {value();}
        catch (Gach e)
        {
            try {brasket();}
            catch (Gach ex)
            {

                System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting value or brascet");
                error++;
            }
        }
        flag2=false;
    }

    public void value () throws Gach
    {
        if(!flag2)i++;
        flag=true;
        try {var();}
        catch (Gach e)
        {
            try {Number();}
            catch (Gach ex)
            {
                if (!flag2)
                {
                    error++;
                    System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting value");
                }
                else throw  new Gach ("Waiting value");
            }
        }
        flag=false;
    }

    public void var  () throws Gach
    {
        if (!flag)i++;
        if (!lexems.get(i).value.equals("Var"))
        {
            if(!flag)
            {
                error++;
                System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting Var");
            }
            throw  new Gach ("Waiting Var");
        }

    }

    public void Number () throws Gach
    {
        if(!flag) i++;
        if (!lexems.get(i).value.equals("Number"))
        {
            if(!flag) {
                error ++;
                System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting Number");
            }

            throw new Gach ("Waiting Number");
        }
    }

    public void Open_bracket () throws Gach
    {
        if(!flag) i++;
        if (!lexems.get(this.i).value.equals("Open_bracket"))
        {
            if (!flag)
            {
                error++;
                System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting Open_bracket");
            }
            throw  new Gach ("Waiting Open_bracket");
        } else brascets ++;
    }

    public void Close_bracket () throws Gach
    {
        if(!flag) i++;
        if (!lexems.get(this.i).value.equals("Close_bracket"))
        {
            if(!flag)
            {
                error++;
                System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting Close_bracket");
            }
            throw  new Gach ("Waiting Close_bracket");
        }  else brascets --;
    }

    public void Equally () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("Equally"))
        {
            error++;
            System.out.println("See  "+lexems.get(i).value+ " "+lexems.get(i).lexem+" but  waiting Equally");
            throw  new Gach ("Waiting Equally");
        }
    }

    public void Semicolon () throws Gach
    {
        i++;
        if (!lexems.get(i).value.equals("Semicolon"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting Semicolon");
            throw new Gach ("Waiting Semicolon");
        }
    }

    public void Operation() throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("Operation"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting Operation");
            throw  new Gach ("Waiting Operation");
        }
    }

    public void KW_Wile () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("KW_While"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting while");
            throw  new Gach ("Waiting while");
        }
    }

    public void KW_For () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("KW_For"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting for");
            throw  new Gach ("Waiting for");
        }
    }

    public void KW_If () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("KW_If"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting if");
            throw  new Gach ("Waiting if");
        }
    }

    public void KW_Else () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("KW_Else"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting else");
            throw  new Gach ("Waiting else");
        }
    }

    public void booleanOP() throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("booleanOP"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting booleanOP");
            throw  new Gach ("Waiting booleanOP");
        }
    }

    public void Open_brace() throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("Open_brace"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting Open_brace");
            throw  new Gach ("Waiting Open_brace");
        }
    }

    public void Close_brace() throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("Close_brace"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting Close_brace");
            throw  new Gach ("Waiting Close_brace");
        }
    }

    public void Colon () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("Colon"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting Colon");
            throw  new Gach ("Waiting Colon");
        }
    }

    public void mList () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("KW_List"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting KW_List");
            throw  new Gach ("Waiting KW_List");
        }
    }

    public void AddElements () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("KW_Add"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting KW_Add");
            throw  new Gach ("Waiting KW_Add");
        }
    }

    public void Remove () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("KW_Remove"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting KW_Remove");
            throw  new Gach ("Waiting KW_Remove");
        }
    }

    public void GetSize () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("KW_GetSize"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting KW_GetSize");
            throw  new Gach ("Waiting KW_GetSize");
        }
    }

    public void GetElement () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("KW_GetElement"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  waiting KW_GetElement");
            throw  new Gach ("Waiting KW_GetElement");
        }
    }

    public void Comma () throws Gach
    {
        i++;
        if (!lexems.get(this.i).value.equals("Comma"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  Comma");
            throw  new Gach ("Waiting Comma");
        }
    }

    public void Hash () throws Gach
    {
        i++;
        if (!lexems.get(this.i).lexem.equals("HashSet"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  HashSet");
            throw  new Gach ("Waiting HashSet");
        }
    }

    public void HashAdd () throws Gach
    {
        i++;
        if (!lexems.get(this.i).lexem.equals("HashAdd"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  HashAdd");
            throw  new Gach ("Waiting HashAdd");
        }
    }

    public void HashRemove () throws Gach
    {
        i++;
        if (!lexems.get(this.i).lexem.equals("HashRemove"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  HashRemove");
            throw  new Gach ("Waiting HashRemove");
        }
    }
    
    public void HashContain () throws Gach
    {
        i++;
        if (!lexems.get(this.i).lexem.equals("HashContain"))
        {
            error++;
            System.out.println("See  "+lexems.get(this.i).value+ " "+lexems.get(this.i).lexem+" but  HashContain");
            throw  new Gach ("Waiting HashContain");
        }
    }
}
