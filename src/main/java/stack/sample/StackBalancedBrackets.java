package stack.sample;

import java.util.Stack;

public class StackBalancedBrackets {
    public static void main(String[] args) {
        String exp = "{A+(B*C)+[C*{D+(E+F)*(X*Y)}]}";
        System.out.println(checkBalancedParenthesis(exp));
    }

    private static boolean checkBalancedParenthesis(String exp) {
        Stack<Character> balacedBracketStack = new Stack();
        for(int i =0; i < exp.length(); i++){

            if(exp.charAt(i) == '[' || exp.charAt(i) == '{' || exp.charAt(i) == '('){
                balacedBracketStack.push(exp.charAt(i));
            }

            if(exp.charAt(i) == ']' || exp.charAt(i) == '}' || exp.charAt(i) == ')'){
                if(balacedBracketStack.empty()){
                    return false;
                }

                Character top = balacedBracketStack.pop();

                if((top == '(' && exp.charAt(i) != ')') ||
                        (top == '{' && exp.charAt(i) != '}') ||
                        (top == '[' && exp.charAt(i) != ']')){
                    return false;
                }
            }
        }

        return balacedBracketStack.empty();
    }
}
