package stack.sample;

import java.util.Stack;

public class InfixToPostfix {

    public static int getPrecedence(char operator){
        int precedence = 0;
        switch (operator){
            case '^':
                precedence = 3;
                break;
            case '*':
            case '/':
                precedence = 2;
                break;
            case '+':
            case '-':
                precedence = 1;
                break;
            default:
                precedence = 0;
        }
        return precedence;
    }

    public static void main(String[] args) {
        String exp = "{A+(B*C)-C*{D-(E+F)*(X*Y)}}";

        Stack<Character> postStack = new Stack<Character>();

        String postExp = "";
        for(int i=0; i<exp.length(); i++){
            if(exp.charAt(i) == '[' || exp.charAt(i) == '{' || exp.charAt(i) == '('){
                postStack.push(exp.charAt(i));
            }else if(exp.charAt(i) == '+' || exp.charAt(i) == '-'
                    || exp.charAt(i) == '*' || exp.charAt(i) == '/' || exp.charAt(i) == '^'){
                while(!postStack.empty() && !isOpeningBrackets(postStack.peek())
                    && hasHigherPrecedenceOperator(postStack, exp.charAt(i))){
                    postExp = postExp + postStack.pop();
                }
                postStack.push(exp.charAt(i));
            }else if(exp.charAt(i) == ']' || exp.charAt(i) == '}' || exp.charAt(i) == ')'){
                while(!postStack.empty() && !isOpeningBrackets(postStack.peek())){
                    postExp = postExp + postStack.pop();
                }
                postStack.pop();
            }else{
                postExp = postExp + exp.charAt(i);
            }
        }

        while(!postStack.empty()){
            postExp = postExp + postStack.pop();
        }

        System.out.println(postExp);
    }
    private static boolean isOpeningBrackets(char top) {
        return top == '(' || top == '{' || top == '[';
    }

    /* private static boolean isOpeningBracketss(char top, char exp) {
        if((top == '(' && exp != ')') ||
                (top == '{' && exp != '}') ||
                (top == '[' && exp != ']')){
            return false;
        }
    }*/

    private static boolean hasHigherPrecedenceOperator(Stack<Character> postStack, char operator) {
        return getPrecedence(postStack.peek()) > getPrecedence(operator);
    }
}
