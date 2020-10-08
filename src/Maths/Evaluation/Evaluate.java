package Maths.Evaluation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Evaluate {

    public static Map<String,Operator> myOperator;
    public Map<String,Integer> priority;
    private Stack<MyCharacter> operands;
    private Stack<MyCharacter> operator;
    Tokenizer tokenizer = new Tokenizer();
    OperatorFactory factory = new OperatorFactory();



   public Evaluate (){
        operands = new Stack<>();
        operator = new Stack<>();
        priority = new HashMap<>();
        priority.put("-",2);
        priority.put("+",1);
        priority.put("x",3);
        priority.put("/",4);
        priority.put("!",6);
        priority.put("^",5);
        myOperator = factory.getMyOperators();
    }

    public double eval(String a){
       System.out.println(a);
       double ans = 0;
       Operator operate;
       boolean isFunctionParameter = false;
       MyCharacter function = null;
       ArrayList<MyCharacter> tokens = tokenizer.tokenize(a);
       ArrayList<MyCharacter> lazyEval = new ArrayList<>();
       MyCharacter lastCharacter = null;
       for(int i = 0; i<tokens.size(); i++){
           MyCharacter character = tokens.get(i);
           if(character.getType() == MyCharacter.type.NUMBER){
               lazyEval.add(character);
           }
           else if(character.getType() == MyCharacter.type.OPERATOR){
               operate = myOperator.get(character.getValue());
                if(operate.isCoefOperator ) {

                    if (lazyEval.isEmpty() || (tokens.get(i - 1) != null && tokens.get(i - 1).getType() == MyCharacter.type.OPERATOR && !myOperator.get(tokens.get(i-1).getValue()).isUnary)) {
                      /*  String anss = operate.solve("0",tokens.get(i + 1).getValue()) + "";
                        i++;
                        lazyEval.add(new MyCharacter(anss, MyCharacter.type.NUMBER));
                        continue;*/
                      if( i == 0){
                          return eval("0" + a);
                      }
                      try {
                          String anss = operate.solve("0", tokens.get(i + 1).getValue()) + "";
                          System.out.println("the result of the first " + anss);
                          lazyEval.add(new MyCharacter(anss,MyCharacter.type.NUMBER));
                          lastCharacter = character;
                          i++;
                          continue;
                      }
                      catch(NumberFormatException e){
                          return eval("0" + a);
                      }



                    }
                }



               if( lastCharacter == null ){
                       lastCharacter = character;
                       lazyEval.add(character);
                   }

               else{
                   String cur = character.getValue();
                   String prev = lastCharacter.getValue();

                   if(priority.get(cur) > priority.get(prev)){
                       lazyEval.add(character);
                       lastCharacter = character;
                   }
                   else{
                        int pos = lazyEval.lastIndexOf(lastCharacter);
                        //System.out.println(lastCharacter.getValue() + " this is the previous character followed by " + character.getValue());
                        operate = myOperator.get(prev);
                        if(operate.isUnary){
                            MyCharacter param;
                        if(operate.isRTL){
                            param = lazyEval.get(pos-1);
                        }
                        else{
                            param = lazyEval.get(pos+1);
                        }
                        String an = operate.solve(param.getValue())+"";
                        lazyEval.remove(lastCharacter);
                        lazyEval.remove(param);
                        lastCharacter = character;
                        lazyEval.add(new MyCharacter(an,MyCharacter.type.NUMBER));
                        lazyEval.add(character);

                        }
                        else{
                            MyCharacter param1;
                            MyCharacter param2;
                            if(operate.isRTL){
                                System.out.println(pos);
                                param1 = lazyEval.get(pos-1);
                                param2 = lazyEval.get(pos+1);

                            }
                            else{
                                param1 = lazyEval.get(pos+1);
                                param2 = lazyEval.get(pos-1);

                            }
                            String an = operate.solve(param1.getValue(),param2.getValue())+"";
                            //System.out.println(an + "this is the result after solving ");
                            lazyEval.remove(lastCharacter);
                            lazyEval.remove(param1);
                            lazyEval.remove(param2);
                            lazyEval.add(new MyCharacter(an,MyCharacter.type.NUMBER));
                            lazyEval.add(character);
                            lastCharacter = character;

                        }
                   }
               }
           }
           else if(character.getType() == MyCharacter.type.PARENTHESIS){
               String parenthesis = character.getValue();
               if(parenthesis.equals("(")){
                   lastCharacter = null;
                   System.out.println(i + ": index is ");
                   if((!lazyEval.isEmpty() && i>0) && !(pop(lazyEval).getType() == MyCharacter.type.PARENTHESIS || pop(lazyEval).getType() == MyCharacter.type.OPERATOR)){
                       lazyEval.add(new MyCharacter("x",MyCharacter.type.OPERATOR));
                       lastCharacter = pop(lazyEval);
                       lazyEval.add(character);
                   }
                   else
                   lazyEval.add(character);
               }
               else if(parenthesis.equals(")")){
                   int size = lazyEval.size();
                    MyCharacter myCharacter = lazyEval.get(--size);
                    try {
                        while (!myCharacter.getValue().equals("(")) {
                            if (myCharacter.getType() == MyCharacter.type.OPERATOR) {
                                solve(lazyEval, size);
                            }
                            myCharacter = lazyEval.get(--size);

                        }
                        lazyEval.remove(myCharacter);
                        if (isFunctionParameter && function != null) {
                            String anss = evalFunction(function,lazyEval.get(lazyEval.size()-1));
                            lazyEval.remove(lazyEval.size()-1);
                            lazyEval.add(new MyCharacter(anss,MyCharacter.type.NUMBER));
                            function = null;
                            isFunctionParameter = false;
                        }
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Hey left parenthesis is not found !!");
                    }
                   if(i < tokens.size()-1 && !(tokens.get(i+1).getType() == MyCharacter.type.PARENTHESIS || tokens.get(i+1).getType() == MyCharacter.type.OPERATOR)){
                       lazyEval.add(new MyCharacter("x",MyCharacter.type.OPERATOR));
                       lastCharacter = pop(lazyEval);
                   }

               }
           }
           else if (character.getType() == MyCharacter.type.FUNCTION){
               isFunctionParameter = true;
               function = character;
               if(i == tokens.size()-1 || !tokens.get(i+1).getValue().equals("(")){
                   throw new ParameterException("Missing opening parenthesis to call the function !!");
               }
               System.out.println(character.getValue());

           }
           else if(character.getType() == MyCharacter.type.CONSTANTS){
               if((!lazyEval.isEmpty() && i>0) && !(pop(lazyEval).getType() == MyCharacter.type.PARENTHESIS || pop(lazyEval).getType() == MyCharacter.type.OPERATOR)){
                   System.out.println(pop(lazyEval).getValue());
                   lazyEval.add(new MyCharacter("x",MyCharacter.type.OPERATOR));
                   lastCharacter = pop(lazyEval);

               }
              String aa = ConstantsFactory.getConstants(character.getValue()).getValue() +"";
               lazyEval.add(new MyCharacter(aa,MyCharacter.type.NUMBER));

               if(i < tokens.size()-1 && !(tokens.get(i+1).getType() == MyCharacter.type.PARENTHESIS || tokens.get(i+1).getType() == MyCharacter.type.OPERATOR)){
                   lazyEval.add(new MyCharacter("x",MyCharacter.type.OPERATOR));
                   lastCharacter = pop(lazyEval);
                   System.out.println(tokens.get(i+1).getValue());
               }
           }
       }
       if(lazyEval.isEmpty()){
           return 0;
       }
       else
       return calc(lazyEval);

    }
    public MyCharacter pop(ArrayList<MyCharacter> a){
       int size = a.size();
       return a.get(size - 1);
    }


    public String evalFunction(MyCharacter a,MyCharacter b){
        return (FunctionFactory.get(a.getValue()).solve(b.getValue())+"");
    }
/*

    public String evalParenthesis(){
       String operand = operands.pop().getValue();
       String operat = operator.pop().getValue();
       if(operat.equals("(")){
           return operand;
       }
       else{
           String operandd = operands.pop().getValue();
           double ans = myOperator.get(operat).operate(operandd,operand);
           return ans+"";
       }
    }
    public double evaluateStack(){
        if(operands.size() == 1){
            return parseToDouble(operands.pop().getValue());
        }
        while(!operator.isEmpty()){
            String a1 = operands.pop().getValue();
            String a2 = operands.pop().getValue();
            String ch = operator.pop().getValue();
            System.out.println("the first operand is :" + a2 + "the second operand is :" + a1);
            System.out.println(ch + "finds the operator in the myoperator map " + myOperator.containsKey(ch));
            double result = myOperator.get(ch).operate(a2,a1);
            System.out.println(result);
            operands.push(new MyCharacter(result+"",MyCharacter.type.NUMBER));
        }
        return parseToDouble(operands.pop().getValue());
    }
*/

    public double parseToDouble(String a){
        double ans = Double.parseDouble(a);
        if(Double.isNaN(ans)) return 0;
        else return ans;
    }
    public double fact(double a){
        if(a==1){
            return a;
        }
        else{
            return(a*fact(a-1));
        }
    }
/*

    public double evval(String a){
       double ans = 0;
       ArrayList<MyCharacter> myTokens = tokenizer.tokenize(a);
       ArrayList<MyCharacter> lazyEval = new ArrayList<>();
       MyCharacter lastOperator = null;
       int pos = 0;
       for(int k = 0;k<myTokens.size(); k++){
           MyCharacter ab = myTokens.get(k);
           if(ab.typ == MyCharacter.type.NUMBER){
               lazyEval.add(ab);
           }
           else if(ab.typ == MyCharacter.type.OPERATOR){
              if(lastOperator == null){
                  lastOperator = ab;
                  pos = k;
                  lazyEval.add(ab);
              }
              else{
                  String op = lastOperator.getValue();
                  String curOp = ab.getValue();
                  if(priority.get(curOp) >= priority.get(op)){
                      lastOperator = ab;
                      lazyEval.add(ab);
                  }
                  else{
                      pos = lazyEval.size()-1;
                      MyCharacter i = lazyEval.get(pos--),j = lazyEval.get(pos--),m = lazyEval.get(pos);
                      lazyEval.remove(i); lazyEval.remove(j) ; lazyEval.remove(m);
                      String res =  myOperator.get(j.getValue()).operate(m.getValue(),i.getValue()) + "";
                      lazyEval.add(new MyCharacter(res,MyCharacter.type.NUMBER));
                      lazyEval.add(ab);
                  }
              }

           }
       }
       return calc(lazyEval);
    }
    */


    public double calc(ArrayList<MyCharacter> a){
        int size = a.size()-1;
       while(size > 0){
          if(a.get(size).getType() == MyCharacter.type.OPERATOR){
              System.out.println(size);
              solve(a,size);
          }

          size--;
       }
       if(a.get(size).getType() == MyCharacter.type.PARENTHESIS){
            throw new ParameterException("Invalid use of parenthesis !!!");
        }
       return parseToDouble(a.get(0).getValue());
    }

    public void solve(ArrayList<MyCharacter> a, int pos){
        MyCharacter curr = a.get(pos);
        Operator operate = myOperator.get(curr.getValue());
        if(operate.isUnary){
            MyCharacter param;
            if(operate.isRTL){
                param = a.get(pos-1);
            }
            else{
                param = a.get(pos+1);
            }
            String an = operate.solve(param.getValue())+"";
            a.remove(curr);
            a.remove(param);
            a.add(new MyCharacter(an,MyCharacter.type.NUMBER));
        }

        else{
            MyCharacter param1;
            MyCharacter param2;
            if(operate.isRTL){
                param1 = a.get(pos-1);
                param2 = a.get(pos+1);

            }
            else{
                param1 = a.get(pos+1);
                param2 = a.get(pos-1);

            }
            String an = operate.solve(param1.getValue(),param2.getValue())+"";
           a.remove(curr);
            a.remove(param1);
            a.remove(param2);
            a.add(new MyCharacter(an,MyCharacter.type.NUMBER));
        }


    }





}
