package lexer;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileReader;
import java.util.regex.Pattern;

class ListOfLexems {
    public String lexem;
    public String value;

    public ListOfLexems (String lexem, String value)
    {
        this.lexem = lexem;
        this.value = value;
    }
    public void setlexem(String lexem, String value)
    {
        this.lexem = lexem;
        this.value = value;
    }
    public String printLexem()
    {
        return(lexem);
    }
    public String printValue()
    {
        return(value);
    }
}

class LexerClass
{
    ArrayList<ListOfLexems> program = new ArrayList<ListOfLexems>();

    int i = 0;
    public LexerClass () {}
    public ArrayList<ListOfLexems> lexemSintex () throws Exception {
        FileReader file = new FileReader("c:\\Program.txt");
        Scanner scan = new Scanner (file);
        while (scan.hasNextLine())
        {
            String phrase = scan.nextLine();
            String[] words = phrase.split("[ \\t]");
            for(int j=0;j<words.length;j++)
            {

                if (Pattern.matches("^if$",words[j]))
                {
                    ListOfLexems KW_If = new ListOfLexems(words[j],"KW_If");
                    program.add(KW_If);
                }
                else if (Pattern.matches("^List$",words[j]))
                {
                    ListOfLexems KW_List = new ListOfLexems(words[j],"KW_List");
                    program.add(KW_List);
                }
                else if (Pattern.matches("^remove$",words[j]))
                {
                    ListOfLexems KW_Remove = new ListOfLexems(words[j],"KW_Remove");
                    program.add(KW_Remove);
                }
                else if (Pattern.matches("^Add$",words[j]))
                {
                    ListOfLexems KW_Add = new ListOfLexems(words[j],"KW_Add");
                    program.add(KW_Add);
                }
                else if (Pattern.matches("^getElement$",words[j]))
                {
                    ListOfLexems KW_GetElement = new ListOfLexems(words[j],"KW_GetElement");
                    program.add(KW_GetElement);
                }
                else if (Pattern.matches("^getSize$",words[j]))
                {
                    ListOfLexems KW_GetSize = new ListOfLexems(words[j],"KW_GetSize");
                    program.add(KW_GetSize);
                }

                else if (Pattern.matches("^while$",words[j]))
                {
                    ListOfLexems KW_While = new ListOfLexems(words[j],"KW_While");
                    program.add(KW_While);
                }

                else if (Pattern.matches("^HashAdd$",words[j]))
                {
                    ListOfLexems KW_HashAdd = new ListOfLexems(words[j],"KW_HashAdd");
                    program.add(KW_HashAdd);
                }
                else if (Pattern.matches("^HashRemove$",words[j]))
                {
                    ListOfLexems KW_HashRemove = new ListOfLexems(words[j],"KW_HashRemove");
                    program.add(KW_HashRemove);
                }
                else if (Pattern.matches("^HashContain$",words[j]))
                {
                    ListOfLexems KW_HashContain = new ListOfLexems(words[j],"KW_HashContain");
                    program.add(KW_HashContain);
                }
                else if (Pattern.matches("^HashSet$",words[j]))
                {
                    ListOfLexems KW_HashSet = new ListOfLexems(words[j],"KW_HashSet");
                    program.add(KW_HashSet);
                }

                else if (Pattern.matches("^for$",words[j]))
                {
                    ListOfLexems KW_For = new ListOfLexems(words[j],"KW_For");
                    program.add(KW_For);
                }
                else if (Pattern.matches("^else$",words[j]))
                {
                    ListOfLexems KW_Else = new ListOfLexems(words[j],"KW_Else");
                    program.add(KW_Else);
                }
                else for (int k =0; k<words[j].length();k++) {
                        char[] chars = words[j].toCharArray();

                        String 	Sem = new String();
                        Sem = String.valueOf(chars[k]);

                        if (chars[k]==';')
                        {
                            ListOfLexems Semicolon = new ListOfLexems(Sem,"Semicolon");
                            program.add(Semicolon);
                        }

                        else if (chars[k]=='+'|chars[k]=='-'|chars[k]=='*'|chars[k]=='/')
                        {
                            ListOfLexems Operation = new ListOfLexems(Sem,"Operation");
                            program.add(Operation);
                        }

                        else if (chars[k]=='>'|chars[k]=='<'|Pattern.matches("!=|==|>=|<=",words[j])) /// доисправить
                        {
                            ListOfLexems booleanOP = new ListOfLexems(Sem,"booleanOP");
                            program.add(booleanOP);
                        }

                        else if (chars[k]==':')
                        {
                            ListOfLexems Colon = new ListOfLexems(Sem,"Colon");
                            program.add(Colon);
                        }

                        else if (chars[k]=='{')
                        {
                            ListOfLexems Open_brace = new ListOfLexems(Sem,"Open_brace");
                            program.add(Open_brace);
                        }

                        else if (chars[k]=='}')
                        {
                            ListOfLexems Close_brace = new ListOfLexems(Sem,"Close_brace");
                            program.add(Close_brace);
                        }

                        else if (chars[k]==',')
                        {
                            ListOfLexems Comma = new ListOfLexems(Sem,"Comma");
                            program.add(Comma);
                        }

                        else if (chars[k]=='(')
                        {
                            ListOfLexems Open_bracket = new ListOfLexems(Sem,"Open_bracket");
                            program.add(Open_bracket);
                        }

                        else if (chars[k]==')')
                        {
                            ListOfLexems Close_bracket = new ListOfLexems(Sem,"Close_bracket");
                            program.add(Close_bracket);
                        }

                        else if (chars[k]=='=')
                        {
                            ListOfLexems Equally = new ListOfLexems(Sem,"Equally");
                            program.add(Equally);
                        }
                        
                        else if (Character.isDigit(chars[k])) 
                        {
                            ListOfLexems Number = new ListOfLexems(Sem,"Number");
                            program.add(Number);
                        }

                        else if (Character.isLetter(chars[k]))
                        {
                            ListOfLexems Var = new ListOfLexems(Sem,"Var");
                            program.add(Var);
                        }

                        else if (Pattern.matches(".+",words[j]))
                        {
                            ListOfLexems Error = new ListOfLexems(words[j],"Error");
                            program.add(Error);
                        }
                    }
            }


        }

        ArrayList<ListOfLexems> massivOfLexems = new ArrayList<ListOfLexems>();

        int i = 0;

        while(i<program.size())
        {
            if(i<program.size())
                if(program.get(i).value.equals("Var"))
                {
                    String Krya = "";
                    while(program.size()>i&program.get(i).value.equals("Var"))
                    {
                        Krya += program.get(i).lexem;
                        i++;
                    }
                    ListOfLexems Var = new ListOfLexems(Krya,"Var");
                    massivOfLexems.add(Var);
                } else if(program.get(i).value.equals("Number"))
                {
                    String Krya = "";
                    while(program.size()>i&program.get(i).value.equals("Number"))
                    {
                        //System.out.println(program.get(i).lexem);
                        Krya += program.get(i).lexem;
                        i++;
                    }
                    ListOfLexems Number = new ListOfLexems(Krya,"Number");
                    massivOfLexems.add(Number);
                } else
                {
                    ListOfLexems Other = new ListOfLexems(program.get(i).lexem,program.get(i).value);
                    massivOfLexems.add(Other);
                    i++;

                }


        }


        for(i=0;i<massivOfLexems.size();i++)
        {
            System.out.println(massivOfLexems.get(i).printLexem()+"  "+massivOfLexems.get(i).printValue());
        }
        file.close();
        scan.close();
        return(massivOfLexems);}

}
class Gach extends Exception{

    String str;
    Gach(){}
    Gach(String msg){
        str=msg;
    }
}

public class Lexer
{
    public static void main (String args[])
    {
        boolean parser;
        LexerClass MetodLexer = new LexerClass ();
        ArrayList<ListOfLexems> program = new ArrayList<ListOfLexems>();
        try {program = MetodLexer.lexemSintex();}
        catch (Exception e) {e.printStackTrace();}
        Parser pars = new Parser(program);
        parser = pars.lang(program);
		Poliz object1 = new Poliz(program);
        if (parser)
		object1.Polish();

    }
}
