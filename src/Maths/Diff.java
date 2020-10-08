package Maths;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Diff {
    public HashMap<String,String> temp;
    Pattern pat1=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*");
     Pattern pat2=Pattern.compile("cos\\(");
     Pattern pat3=Pattern.compile("sin\\(");
     Pattern pat4=Pattern.compile("tan\\(");
     Pattern pat5=Pattern.compile("x\\^");
     String num="[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
     String cos="cos\\(.*\\)";
     String sin="cos\\(.*\\)"; String tan="cos\\(.*\\)";
     String poly="x\\^[\\d]*";
    /* Pattern pat1=Pattern.compile("");
     Pattern pat1=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*");
     Pattern pat1=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*");
     Pattern pat1=Pattern.compile("[-]?[\\+]?[\\d]*[\\.]?[\\d]*");
    */
    Diff(){
        temp=new HashMap<String,String>();
        temp.put("cos","-sin");
        temp.put("sin","cos");
        temp.put("tan","1/cos");
        temp.put("x^","x^");

    }
   public String difx(String a){
        return "";
   }

   public String product(String a,String b){
    return "";
   }
   public String mult(String a,String b){
        return (a+b);
   }

   public String add(String a,String b){
        return "";
   }

   public String simpleest(String a){
       String ans="";
           if (a.contains("x^")) {
               String[] copo=a.split("x\\^");
               if(copo.length  ==1){
                   ans+=copo[0];
                   ans+="x^"+(Integer.parseInt(copo[0])-1);
                   return ans;
               }
               else{
                   ans+=(Integer.parseInt(copo[0]) * Integer.parseInt(copo[1]));
                   ans+="x^"+(Integer.parseInt(copo[0])-1);
                   return ans;
               }

           }
           else if(a.contains("x")){
               ans=a.split("x")[0];
               return ans;
           }
           else if(a.matches("[-]?[\\+]?[\\d]*[\\.]?[\\d]*cos\\(.*\\)")){
            
           }
       return ans;
   }

}
