package Maths.Evaluation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class ConstantsFactory {
    public Map<String,Constants> constants = new HashMap<>();
    public ConstantsFactory(){

    }
    public static Constants getConstants(String a){
        if(a.equals("π")){
            return(new Constants((double) (22.0/7.0)));
        }

        else
            throw new ParameterException("Invalid Constant");
    }
}
