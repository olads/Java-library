package Maths.algebra;

import Maths.Data;
import Maths.Evaluation.Evaluate;
import Maths.util.Format;
import Maths.util.Number;
import Maths.util.StringUtil;


import java.util.ArrayList;

public abstract class  BasicToken {

    protected String value;
    protected ArrayList<BasicToken > values = new ArrayList<>();
    private types typ;
    private String[] valuesForAdd =  new String[2];
    String coefficient = "";
    String printDecorator = "";


    public BasicToken(String val,types type){
        typ = type;
        value = val;
    }

    public BasicToken (BasicToken a,BasicToken b,types type){
        values = new ArrayList<>();
        values.add(a);
        values.add(b);
        typ = type;
    }

    /*public BasicToken (String val,String vval,types type){
        this(new BasicToken(val,types.VARIABLES),new BasicToken(vval,types.VARIABLES),type);
    }*/

    public void addValues(String a){
       switch(getType()){
           case FRACTION:
           case POWER:
           case MULTIPLICATION:
               if(values.size() <2){
                   values.add(new Variable(a));
               }
               break;
           case VARIABLES:
               break;
       }
    }
    public BasicToken multiply(BasicToken basicToken) {
        return new Divide(new Variable(getMultiplication(getValue(),basicToken.getNumerator().getValue())),basicToken.getDenominator());
    }

    public String getMultiplication(String a,String b){
        Evaluate eval = new Evaluate();
        String aa = "";
        try {
            int form = Format.formatInt(eval.eval(getNo(sepNum(a,true))+ "x"+ getNo(sepNum(b,true))));
            aa = form + "";
        }
        catch (InvalidOperationException e){
            aa = eval.eval(sepNum(a,true) + "x"+ sepNum(b,true))+"";
        }
       // System.out.println("the value of the coefficient is " + aa  + " the value obtained from a " + sepNum(a,true)+ " value obtained from b " + sepNum(b,true));
        String bb = aa + (sepNum(a,false)) + (sepNum(b,false));
        //System.out.println("answer raw " + bb);
        return bb;
    }



    public String sepNum(String str,boolean returnCoef){
        char[] strr = str.toCharArray();
        StringBuilder num = new StringBuilder();
       StringBuilder num2 = new StringBuilder();

        boolean foundNumberBefore = false;

        for(int i = 0; i<strr.length; i++ ){
            char curr = strr[i];
            if(i == 0 && (curr == '+' || curr == '-')){

               // num = num.insert(0,curr);
                str = new StringBuilder(str).deleteCharAt(0).toString();
                continue;

            }
            if(Character.isDigit(curr) || curr == '.'){
                //System.out.println("the num in loop " + curr);
                if(!foundNumberBefore)
                num.append(curr);

                else{
                    num2.append(curr);
                }
                str = str.replaceFirst(curr+"","");
                int j = i + 1;
                if(j<str.length() && (Character.isDigit(strr[j]) || strr[j] == '.')){
                    continue;
                }
                else{
                    if(foundNumberBefore){
                        Evaluate eval = new Evaluate();
                        try {
                            int form = Format.formatInt(eval.eval(num.toString() + "x" + num2.toString()));
                            num = new StringBuilder(form + "");
                        }
                        catch (InvalidOperationException e){
                           num = new StringBuilder(eval.eval(num.toString() + "x" + num2.toString())+"");
                        }
                       num2 = new StringBuilder();
                    }

                  foundNumberBefore = true;
                }
            }
            else if(curr == '-'){
                num.insert(0,curr);
            }
        }
        //System.out.println("Number to be returned is " + num.toString());
        return ((returnCoef) ? num.toString() : str);
    }

    public BasicToken add(BasicToken basicToken){
        Evaluate evaluate = new Evaluate();
        if(checkAddSub(basicToken)){
            String aa = getNo(valuesForAdd[0]) + "+" + getNo(valuesForAdd[1]);
            try {
                int form = Format.formatInt(evaluate.eval(aa));
                return new Variable((form+"") + coefficient);
                //return new BasicToken((form + "") + coefficient, types.VARIABLES);
            }
            catch (InvalidOperationException e){
                return new Variable((evaluate.eval(aa) + "") + coefficient);

            }
        }
        else{
            return new Parenthesis(getValue() + "+" + basicToken.getValue());
        }
    }


    public BasicToken subtract(BasicToken basicToken) {
        Evaluate evaluate = new Evaluate();
        if(checkAddSub(basicToken)){
            String aa = getNo(valuesForAdd[0]) + "-" + getNo(valuesForAdd[1]);
            try {
                int form = Format.formatInt(evaluate.eval(aa));
                return new Variable((form + "") + coefficient);

            }
            catch (InvalidOperationException e){
                return new Variable((evaluate.eval(aa) + "") + coefficient);

            }
        }
        else{
            return new Parenthesis(getValue() + "-" + basicToken.getValue());
        }
    }

    //divides the current token by
    public BasicToken divide(BasicToken basicToken) {
        String param = this.getValue();
        String param2 = basicToken.getValue();
        char[] chars = param.toCharArray();
        char[] chars2 = param2.toCharArray();
        String number = "";
        String num = getSecondValue(param2);
        param2 = param2.replace(num,"");
        for(int i = 0; i < chars.length; i++){
            char current = chars[i];
            if(Character.isDigit(current) || current == '.'){
                number += current;

            }

            else if(Character.isLetter(current)){
                if(param2.contains(current+"")){
                    param = param.replaceFirst(current+"","");
                    param2 =  param2.replaceFirst(current+"","");

                }
            }
        }
        param = param.replaceFirst(number,"");
        String[] frac = reduceFraction(getNo(number),getNo(num));
        frac[1] = (frac[1].equals("1")  && !param2.equals(""))? "":frac[1];
        frac[0] = (frac[0].equals("1") && !param.equals(""))? "":frac[0];
        return convertToFraction(frac[0]+param,frac[1]+param2);

    }

  /*  public String replace(String old,String rep){
       String
    }*/


    public BasicToken convertToFraction(String a,String b){
        if(a.equals("")){
            a = "1";
        }
        b = (b.equals("")) ? "1" : b ;
        if(b.equals("0")){
            throw new NumberFormatException("Attempt to divide by a zero ");
        }

        else if(a.equals("0")){
            return new Variable("0");
        }

        else {

            return new Divide(a,b);
        }

    }

    //reduces the fraction to the lowest multiple
    public String[] reduceFraction(String a,String b){
        int i = Integer.parseInt(a);
        int j = Integer.parseInt(b);

        int[] divisor = {2,3,5,7};
        for (int g = 0; g<divisor.length; g++ ){
            int gg = divisor[g];
            //System.out.println(gg);
            if((i%gg) == 0 && (j%gg) == 0){
                i = i/gg;
                j = j/gg;
                --g;
            }

        }

        String[] ans = {Integer.toString(i),Integer.toString(j)};
        return ans;
    }

    public boolean checkAddSub(BasicToken basic){
        String myVal = this.getValue();
        String param = basic.getValue();
        return check(myVal,param);
    }


    //checks if the two strings are addable
    public boolean check (String param, String param2){
        char[] chars = param.toCharArray();
        String result = param2;
        String number = "";
        coefficient = "";
        valuesForAdd[1] = getSecondValue(param2);
        for(int i =0; i<param.length(); i ++){
            char curr = chars[i];

            if(Character.isDigit(curr) || curr == '.' || curr == '-'){
                number += curr;
                int j = i + 1;
                if(j<param.length() && (Character.isDigit(chars[j]) || chars[j] == '.')){
                    continue;
                }
                else{
                    //System.out.println("the number gotten from the first variable is " + number);
                    valuesForAdd[0] = number;
                }
            }
            else if(Character.isLetter(curr)){
                coefficient += curr;
                if(result.contains(curr+"")){
                    result = result.replace(curr+"","");
                }
                else{
                    return false;
                }
            }

        }
        if(!result.equals("")){
            return (isNumber(result));
        }
        else{
            return true;
        }



    }

    //gets the second value for performing addition and subtraction operation
    public String getSecondValue(String a){
        char[] chars2 = a.toCharArray();
        String num = "";
        for(int k = 0; k< a.length(); k++) {
            char current = chars2[k];
            if (Character.isDigit(current) || current == '.') {
                num += current;
                int j = k + 1;
                if (j < a.length() && (Character.isDigit(chars2[j]) || chars2[j] == '.')) {
                    continue;
                } else {
                    //System.out.println("the number gotten from the second variable is " + num);
                    break;
                    //valuesForAdd[1] = num;
                }
            }
        }
        return num;
    }

    //an util function that is used to check if a string is a number or not
    public boolean isNumber(String a){
        try{
            Double.parseDouble(a);
        }
        catch (NumberFormatException num){
            return false;
        }
        return true;
    }


/*public  String getValue(){
        //System.out.println("get value in basicToken has been called ...");
        if(value == null && getType() == types.FRACTION) return getValue(getType());
        return value;
    }*/

public abstract String getValue();

    public String getValue(types typ){
        if(typ == types.FRACTION) {
           // System.out.println("Get value function is called here ");
            if (getDenominator().getValue().equals("1")) {
                //System.out.println((getNumerator() == null) + " is it that the get Numerator fucntion returns fnull");
                return (getNumerator().getValue());
            } else {
                return (getNumerator().getValue() + " / " + getDenominator().getValue());
            }
        }
        return null;

    }

    public ArrayList<BasicToken> getValues(){
        return values;
    }
    public types getType(){
        return typ;
    }

    enum types{
        CONSTANTS,
        VARIABLES,
        FUNCTION,
        PARENTHESIS,
        FRACTION,
        COMPLEX_OPERATOR,
        MULTIPLICATION,
        POWER
    }

    public void setValue(String val ){
        this.value = val;
    }


    /**
     * A function for getting the coefficient of a token in a math context
     * @param param a string param of token to get the coefficient
     * @return the string representation of the coefficient
     */
    public String getNo(String param){
        if(param == null){
            return "1";
        }
        if(param.length() == 1 ){
            if (param.charAt(0) == '-')
            return "-1";
            else if(param.charAt(0) == '+') return "1";
        }
       // System.out.println(param + " the coefficient");
        param = param.trim();
        if(param.equals("")){
            return "1";
        }
        if(param.charAt(0) == '+'){
            return param.substring(1,param.length());
        }
        else if(Character.isLetter(param.charAt(param.length()-1))){
            String ans = "";
            for(char a: param.toCharArray()){
                if(Character.isDigit(a) || a == '.'){
                    ans += a;
                }
                else
                    return getNo(ans);

            }
        }

            return param;
    }

    //get the formatted output string that is easy to read
    public String  parse(){

       switch(getType()) {
           case FRACTION:
               ArrayList<BasicToken> array = getValues();
               String str = array.get(1).getValue();
               if (str.equals("1")) {
                   return ((array.get(0)).getValue());
               } else {
                   return ((array.get(0)).parse() + " / " + array.get(1).parse());
               }

           case MULTIPLICATION:
           case VARIABLES:
               return parseMult(getValue());

       }
       return "";

    }

    //The function parses the String to convert Strings like aa to a^2
    public String parseMult(String ann){
        String ans = "";
        for(char a : ann.toCharArray()) {
            if (!ans.contains(a + "")) {
                int count = countChars(ann, a);
                if (count > 1) {
                    ans += "(" + a + "^" + count + ")";
                } else
                    ans += a;
            }
        }
        return ans;
    }

    public int countChars(String a,char chr){
        int count = 0;
        for(char ch : a.toCharArray()){
           if(ch == chr && !Character.isDigit(ch))
               ++count;

        }
        return count;
    }

    //returns the numerator or the value
    public BasicToken getNumerator(){
        switch(getType()){
            case FRACTION:
            case MULTIPLICATION:
                return getValues().get(0);
            default:
                return this;
        }
    }

    //returns the denominator of the current token
    // it returns 1 if the token is of type that is not fraction
    public BasicToken getDenominator(){
        switch(getType()){
            case FRACTION:
            case MULTIPLICATION:
                return getValues().get(1);

            default:
                return new Variable("1");
        }
    }

    // returns the factor between two Strings
    public BasicToken getFactor(BasicToken basicToken){

        return getFactor(getValue(),basicToken.getValue());
    }

    // returns the number factor of the two strings expression
    public BasicToken getFactor(String a,String b){
        String coefa = sepNum(a,true);
        String coefb = sepNum(b,true);
        String stripa = sepNum(a,false);
        String stripb = sepNum(b,false);
        //System.out.println("The no factor is " + getCommonNo(coefa,coefb) + " the common factor of the alphabet " + getCommon(stripa,stripb));
        return (new Variable(getCommonNo(coefa,coefb)+getCommon(stripa,stripb)));
    }

    /**
     * the method returns the common alphabet between the two Strings a and b
     * @param a
     * @param b
     * @return
     */
    public String getCommon(String a, String b){
        StringBuilder ans = new StringBuilder();

        for(char ch : a.toCharArray()){
            if(b.contains(ch + "")){
                ans.append(ch);
            }
        }
        return ans.toString();
    }

    //the method to factor out the common number
    public String getCommonNo(String a,String b){
        if(!(a.equals("")  || b.equals(""))) {
            int fac = Number.factor(Double.parseDouble(a), Double.parseDouble(b));
            //System.out.println("the fator is " + fac);
            return Integer.toString(fac);
        }
        return "";
    }

    // Remove factor from the original token
    public BasicToken factorOut(BasicToken basicToken){
        return ( new Parenthesis(this.divide(basicToken).getValue())).setFactor(basicToken);

    }

    /**
     * the method checks if the param string a if a factor of the current basictoken object
     * @param a
     * @return
     */
    public boolean checkFactor(String a){

        if(StringUtil.equalsMath(getFactor(this.getValue(),a).getValue(),a))
            return true ;
        return false;
    }

    public BasicToken reduce(){
        return this;
    }
    @Override
    public String toString(){
        return parse();
    }

    public  ArrayList<Atom> getAtoms(){
        ArrayList<Atom> atoms = new ArrayList<>();
        String a = sepNum(value,false);
        final String finalA = a;
        String temp = a;
        String b = "";
        while(!a.equals("")) {
            if ((b = DataSource.getContain(a)) != null) {
                a = a.replaceFirst(b, "");
                Atom atm = new Atom(b);
                atoms.add(atm);

            }
            else break;
        }
        if(a.length() > 0){
            for(char c : a.toCharArray()){
                Atom atm = new Atom(String.valueOf(c));
                atoms.add(atm);
            }


        }

        atoms.sort((x,y) -> {
            int i = finalA.indexOf(x.getValue());
            int j = finalA.indexOf(y.getValue());
            if(i<j) return -1;
            if(i == j ) return 0;
             return 1;
        });

        return atoms;
    }



    }






