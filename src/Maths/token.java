package Maths;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
* class for holding the  data types
* */
public class token {
    /*
    * Regular expression for different type differientiating constants*/
    String x="[-]?[\\+]?[\\d]*[\\.]?[\\d]*x[\\^]?[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
    String cos="[-]?[\\+]?[\\d]*[\\.]?[\\d]*cos\\([0-9a-z]*\\)[\\^]?[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
    String sin="[-]?[\\+]?[\\d]*[\\.]?[\\d]*sin[\\^]?[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
    String tan="[-]?[\\+]?[\\d]*[\\.]?[\\d]*tan[\\^]?[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
    String sec="[-]?[\\+]?[\\d]*[\\.]?[\\d]*sec[\\^]?[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
    String cot="[-]?[\\+]?[\\d]*[\\.]?[\\d]*cot[\\^]?[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
    String cosec="[-]?[\\+]?[\\d]*[\\.]?[\\d]*cosec[\\^]?[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
    String log="[-]?[\\+]?[\\d]*[\\.]?[\\d]*log[\\^]?[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
    String e="[-]?[\\+]?[\\d]*[\\.]?[\\d]*e\\^[-]?[\\+]?[\\d]*[\\.]?[\\d]*";
    String cons="[-]?[\\+]?[\\d]+[\\.]?[\\d]*";
    String question;

    /*
    * data to hold the regex*/
    ArrayList<String> foundToken = new ArrayList<>();
    ArrayList<String> difpair =new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();

/*
*
* @params not nullable string a
* String to be tokenized for evaluation
*
* */
    token(String a){
        this.question = a;
        data.add(x);
       data.add(sin); data.add(cos); data.add(tan);
        data.add(e); data.add(sec); data.add(cosec);
        data.add(cot); data.add(cons); data.add(log);

        difpair.add("cos"); difpair.add("-sin"); difpair.add("sec^2");
        difpair.add(e); difpair.add("sec"); difpair.add(cosec);
        difpair.add(cot); difpair.add("") ;
        difpair.add("x");

    }

    /*
    * get the number of found token in the string passed in the constructor
    */
    public int getNumber(){
      int ans= 0;
      String temp = question;
      for(int i = 0;i <10;i++){

        Pattern p= Pattern.compile(data.get(i));
          Matcher match = p.matcher(temp);
         if(match.find()) {

             String hh= match.group(0);
             foundToken.add(hh);
             temp=temp.replace(hh,"");
             System.out.println(hh);
         }
      }
      return foundToken.size();

    }

}
