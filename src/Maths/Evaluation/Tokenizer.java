package Maths.Evaluation;

import java.util.ArrayList;

public class Tokenizer {
    public ArrayList<MyCharacter> tokens;
    public Tokenizer(){
        tokens = new ArrayList<>();
    }

    public ArrayList<MyCharacter> tokenize(String a){
        tokens = new ArrayList<>();
        char[] str = a.toCharArray();
        String currentString = "";
        for( int i = 0; i<str.length; i++) {
            char curr = str[i];
            if (Character.isDigit(curr) || curr == '.') {
                currentString += curr;
                if ((i + 1) < str.length && (Character.isDigit(str[i + 1]) || str[i + 1] == '.')) {
                    continue;
                }
                else{
                    tokens.add(new MyCharacter(currentString,MyCharacter.type.NUMBER));
                    currentString = "";
                }

            }
            /**
             * more operators to be added later but for now that's
             * what came to head
             */
            else if(curr == '+' || curr == '-' || curr == 'x' || curr == '/' || curr == '^' || curr == '!'){
                tokens.add(new MyCharacter(curr+"",MyCharacter.type.OPERATOR));
            }

            else if (Character.isLetter(curr)){
                currentString += curr;
                if ((i + 1) < str.length && (Character.isLetter(str[i+1]))){
                    continue;
                }
                else{
                    tokens.add(new MyCharacter(currentString,MyCharacter.type.FUNCTION));
                    currentString = "";
                }
            }
            else if (curr == '(' || curr == ')'){
                tokens.add(new MyCharacter(curr+"",MyCharacter.type.PARENTHESIS));
            }


        }
        return tokens;
    }
}
