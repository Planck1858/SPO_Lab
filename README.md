# SPO_Lab [My project for university]
## Description
**This is my programming language - Gach**

- Gach consists of 4 main parts:
  + Lexer
  + Parser
  + RPN
  + Stack Machine

## Features

- Gach has a minimal standard set of operators, cycles and conditions
- Supports two classes:
  + Hash Set
  + Linked List
- Supports printing in console

### Lexems
```Java
    CYCLE("CYCLE", Pattern.compile("^while"), 0),
    PRINT("PRINT", Pattern.compile("^print"), 0),
    TYPE_OP("TYPE_OP", Pattern.compile("^type"), 1),
    TYPE("TYPE", Pattern.compile("^Float|List|HashSet"), 0),
    GET("GET", Pattern.compile("^[.]get"), 5),
    METHOD("METHOD", Pattern.compile("^[.](add|get|remove|contains|set)"), 5),
    DIGIT("DIGIT", Pattern.compile("^0|[1-9][0-9]*[.]?[0-9]*|0[.][0-9]*"), 0),
    VAR("VAR", Pattern.compile("^[a-zA-Z]+[0-9]*[a-zA-Z]*"), 0),
    COMP_OP("COMP_OP",Pattern.compile("^==|<|>"),1),
    ASSIGN_OP("ASSIGN_OP", Pattern.compile("^:="), 2),
    OP_ADD_SUB("OP_ADD_SUB", Pattern.compile("^[+|-]"), 3),
    OP_DIV_MUL("OP_DIV_MUL", Pattern.compile("^[*|/]"), 4),
    SPACE("SPACE", Pattern.compile("^\\s"), 0),
    L_B("L_B", Pattern.compile("^[(]"), 0),
    J_C("J_C", Pattern.compile("^!F"), 0),
    JMP("JMP", Pattern.compile("^!"), 0),
    R_B("R_B", Pattern.compile("^[)]"), 0),
    L_F_B("L_F_B",Pattern.compile("^[{]"),0),
    R_F_B("R_F_B",Pattern.compile("^[}]"),0),
    END("END", Pattern.compile("^[;]"), 0),
    COMA("COMA", Pattern.compile("^[,]"), 0);
```
