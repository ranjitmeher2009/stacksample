package stack.sample;

import java.util.Stack;

public class EvalPostfix {
    public static void main(String[] args) {
        String exp = "23*54*+9-";

        Stack<String> postStack = new Stack<String>();
        int result = 0;
        for(int i = 0; i<exp.length(); i++){
            if(exp.charAt(i) != '+' && exp.charAt(i) != '*'
            && exp.charAt(i) != '-' && exp.charAt(i) != '/'){
                postStack.push(String.valueOf(exp.charAt(i)));
            }else {
                if(postStack.empty()){
                    return;
                }else{
                    int right = Integer.parseInt(postStack.pop());
                    int left = Integer.parseInt(postStack.pop());
                    switch (exp.charAt(i)){
                        case '+':
                            result = left + right;
                            break;
                        case '-':
                            result = left - right;
                            break;
                        case '*':
                            result = left * right;
                            break;
                        case '/':
                            result = left / right;
                            break;
                        default:
                            result = 0;
                    }
                    postStack.push(String.valueOf(result));
                }
            }
        }
        System.out.println(postStack.peek());
    }
}
