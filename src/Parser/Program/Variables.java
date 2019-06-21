package Parser.Program;

import java.util.HashMap;
import java.util.Map;

public class Variables {
    private static final NumberValue ZERO =  new NumberValue(0);
    private static final Map<String, Value> variables;

    static {
        variables = new HashMap<String, Value>();
        variables.put("PI", new NumberValue(Math.PI));
        variables.put("ПИ", new NumberValue(Math.PI));
        variables.put("pi", new NumberValue(Math.PI));
        variables.put("пи", new NumberValue(Math.PI));
        variables.put("E", new NumberValue(Math.E));
        variables.put("GOLDEN_RATIO", new NumberValue(1.618));//Золотое сечение

    }

    public static boolean isExists(String key){
        return variables.containsKey(key);
    }

    public static Value get(String key){
        if(!isExists(key)) return ZERO;
        return variables.get(key);
    }

    public static void set(String key, Value value){
        variables.put(key, value);
    }

}
