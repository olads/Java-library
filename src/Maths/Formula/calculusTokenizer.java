package Maths.Formula;

import java.util.HashMap;

public class calculusTokenizer {
    public final HashMap<String,String> constants = new HashMap<>();

    public calculusTokenizer(){
        constants.put("cos","co");
        constants.put("tan","ta");
        constants.put("sin","si");
        constants.put("sec","se");
        constants.put("cosec","co");
        constants.put("log",".lo");
        constants.put("ln","ln");
    }

}
