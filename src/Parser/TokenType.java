package Parser;

public enum TokenType {
    NUMBER,// число
    HEX_NUMBER,// число в 16-ти ричной системе (#)
    WORD,//переменные
    TEXT,//текст

    //ключевые слова - KW
    PRINT,// вывод
    IF,//если
    ELSE,//иначе
    WHILE,// цикл while
    FOR,// цикл for
    BREAK,// закончить
    CONTINUE,// продолжить

    PLUS,// +
    MINUS,// -
    STAR,// *
    SLASH,// /

    EQ,// := присвоить
    EQEQ,// = прировнять
    EXCL,// отрицание
    EXCLEQ,// != неравно
    LT,// < меньше
    LTEQ,// <= меньше или равно
    GT,// > больше
    GTEQ,// >= больше или равно

    BAR,// ИЛИ
    BARBAR,// логическое ИЛИ
    AMP,// И
    AMPAMP,// логическое ИЛИ

    LPAREN, // (
    RPAREN, // )
    LBRACE,// {
    RBRACE,// }
    COMMA,// ,

    EOF //конец файла
}
