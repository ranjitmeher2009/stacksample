package stack.sample;

import java.util.Stack;

public class DuplicateParenthesisStack {
    public static void main(String[] args) {
        String exp = "((A+B))";
        System.out.println(checkDuplicateParenthesis(exp));
    }

    private static boolean checkDuplicateParenthesis(String exp) {
        Stack<Character> duplicateBracketsStack = new Stack<Character>();

        for(int i = 0; i < exp.length(); i++){
            if(exp.charAt(i) != ')'){
                duplicateBracketsStack.push(exp.charAt(i));
            }else{
                if(duplicateBracketsStack.peek() == '('){
                    return true;
                }

                while(duplicateBracketsStack.peek() != '('){
                    duplicateBracketsStack.pop();
                }

                duplicateBracketsStack.pop();
            }
        }
        return false;
    }
}
