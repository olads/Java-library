package Maths;

import java.lang.reflect.Array;
import java.util.*;


public class alteval {
    public List<Character> exp=new ArrayList<>();
    char[] str;
    Stack<String> operands = new Stack<>();
    Stack<Character> operator = new Stack<>();
    Stack<String> simple = new Stack<>();
    Map<Character,Integer> operators = new HashMap<>();

    String currentString = "";
    public alteval(){
        operators.put('-',2);
        operators.put('+',1);
        operators.put('x',3);
        operators.put('/',4);
    }
    public void lex(String a){
        str = a.toCharArray();
        currentString = "";
        operands.push(0+"");
        for( int i = 0; i<str.length; i++) {
            char curr = str[i];
            System.out.println(curr);
            if(Character.isDigit(curr) || curr == '.'){
                currentString += curr;
                if((i+1)<str.length && (Character.isDigit(str[i+1]) || str[i+1] == '.')){
                    continue;
                }
                else {
                    operands.push(currentString);
                    currentString = "";
                }
            }
            else if(operators.containsKey(curr)){
                if(operator.isEmpty()){
                    operator.push(curr);

                    }
                    else{
                        char peek = operator.peek();
                        if(operators.get(curr) >= operators.get(peek)){
                            operator.push(curr);
                        }
                        else{
                            String a1 = operands.pop();
                            String a2 = operands.pop();
                            char ch = operator.pop();
                            double result = simpleEval(a2,ch,a1);
                            operands.push(result+"");
                            operator.push(curr);

                        }
                    }
            }
        }

        System.out.println(evaluateStack());
    }

    public double simpleEval(String b,Character a, String c){
        double ans = 0;
        switch(a){
            case '-':
                ans = parseToDouble(b) - parseToDouble(c);
                break;
                   case '+':
                ans = parseToDouble(b) + parseToDouble(c);
                break;
                   case 'x':
                ans = parseToDouble(b) * parseToDouble(c);
                break;
                   case '/':
                ans = parseToDouble(b) / parseToDouble(c);
                break;

        }
        return ans;
    }
    public double evaluateStack(){

        while(!operator.isEmpty()){
            String a1 = operands.pop();
            String a2 = operands.pop();
            char ch = operator.pop();
            double result = simpleEval(a2,ch,a1);
            System.out.println(result);
            operands.push(result+"");
        }
        return parseToDouble(operands.pop());
    }
    public double parseToDouble(String a){
        double ans = Double.parseDouble(a);
        if(Double.isNaN(ans)) return 0;
        else return ans;
    }
}
