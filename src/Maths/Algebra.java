package Maths;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Algebra {
    private String alpha ="[a-zA-Z]";
    private String alphaCoefficient = "[a-zA-Z]+";

    public String addSimilarCoeff(String ch) {

        Pattern pat2 = Pattern.compile(alphaCoefficient);
        ArrayList<String> ans = new ArrayList<>();
        Data b = new Data("");
        b.tokk(ch);
        int size = b.tokens.size();
        for(int i=size-1;i>0;i--){
            for (int j =size-1;j>0;j--){
                System.out.println("Method called!!!");
                if(b.tokens.size() == 1){
                    j= -1;
                    break;
                }
                boolean comp = compare(b.tokens.get(j),b.tokens.get(j-1));
                if(comp){
                    String an= addCoeff(b.tokens.get(j),b.tokens.get(j-1));
                    b.tokens.remove(j);
                    b.tokens.remove(j-1);
                    b.tokens.add(j-1,an);


                }
            }
        }
        System.out.println(b.tokens.get(0));
       /* boolean asd = compare(b.tokens.get(0),b.tokens.get(1));
        if(asd) {
            System.out.println("they can be added");
            System.out.println(addCoeff(b.tokens.get(0),b.tokens.get(1)));
        }
        else
            System.out.println("they cannot be added");*/
        return "";
    }
/**
 * compares two string if they can be added of not
 * returning true if they can be added else
 * false
 * @param a and b are strings to be compared*/
    public boolean compare(String a,String b){
        System.out.println("String a and b " + a +" and b "+b);
        Pattern pat = Pattern.compile(alpha);
        boolean ans = false;
        Matcher matcher = pat.matcher(a);
        while(matcher.find()) {
         String temp = matcher.group();
         System.out.println(temp);
            if(b.contains(temp)){
                //System.out.println()
                ans = true;
                //continue;
            }
            else{
                ans = false;
            }
        }
        return ans;
    }

    public String addCoeff(String a,String b){
        Pattern pp = Pattern.compile(alphaCoefficient);
        Matcher m1 = pp.matcher(a);
        Matcher m2 = pp.matcher(b);
        m1.find();
        m2.find();
        Eval calc = new Eval();
        System.out.println(m1.group() + " -> the coeff of 1 coeff of 2 -> "+m2.group());
        String fir = check(a.replace(m1.group(),""));
        String sec = check(b.replace(m2.group(),""));
        int ab = Integer.parseInt(fir) + Integer.parseInt(sec);
        String aab = ab + m1.group();
        return aab;
    }

    public String check(String a){
        if(a.equals("") || a.equals(" ")){
            return "1";
        }
        else{
            return a;
        }
    }


}
