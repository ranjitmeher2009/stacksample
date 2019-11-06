package stack.sample;

import java.util.Stack;

public class InfixToPostfix {

    // The main method that converts given infix expression
    // to postfix expression.
    static String infixToPostfix(String exp)
    {
        // initializing empty String for result
        String result = "";

        // initializing empty stack
        Stack<Character> stack = new Stack<Character>();

        for (int i = 0; i<exp.length(); ++i)
        {
            char c = exp.charAt(i);

            // If the scanned character is an operand, add it to output.
            if (Character.isLetterOrDigit(c))
                result += c;

                // If the scanned character is an '(', push it to the stack.
            else if (c == '(')
                stack.push(c);

                //  If the scanned character is an ')', pop and output from the stack
                // until an '(' is encountered.
            else if (c == ')')
            {
                while (!stack.isEmpty() && !isOpeningBrackets(stack.peek()))
                    result += stack.pop();

                if (!stack.isEmpty() && !isOpeningBrackets(stack.peek()))
                    return "Invalid Expression"; // invalid expression
                else
                    stack.pop();
            }
            else // an operator is encountered
            {
                while (!stack.isEmpty() && getPrecedence(c) <= getPrecedence(stack.peek())
                && !isOpeningBrackets(stack.peek())){
                    result += stack.pop();
                }
                stack.push(c);
            }

        }

        // pop all the operators from the stack
        while (!stack.isEmpty() && !isOpeningBrackets(stack.peek())){
            result += stack.pop();
        }
        return result;
    }

    public static int getPrecedence(char operator){
        switch (operator){
            case '^':
                return 3;
            case '*':
            case '/':
                return 2;
            case '+':
            case '-':
                return 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        String exp = "((A+{B*C}-C*(D-(E+F)*(X*Y))))";

        String exp1 = "(A+(B*C)-C*(D-(E+F)*(X*Y)))";

        Stack<Character> postStack = new Stack<Character>();

        String postExp = "";
        for (int i = 0; i < exp.length(); ++i) {
            char expChar = exp.charAt(i);
            if (Character.isLetterOrDigit(expChar)) {
                postExp += expChar;
            } else if (isOpeningBrackets(expChar)) {
                postStack.push(expChar);
            } else if (isClosingBrackets(expChar)) {
                while (!postStack.empty() && !isOpeningBrackets(postStack.peek())) {
                    postExp += postStack.pop();
                }
                if (!postStack.isEmpty() && !isOpeningBrackets(postStack.peek()))
                    System.out.println("Invalid expression");
                else
                    postStack.pop();
            }else {
                while (!postStack.empty() && !isOpeningBrackets(postStack.peek())
                        && hasHigherPrecedenceOperator(postStack, expChar)) {
                    postExp +=  postStack.pop();
                }
                postStack.push(expChar);
            }
        }

        while (!postStack.empty() && !isOpeningBrackets(postStack.peek())) {
            postExp +=  postStack.pop();
        }

        System.out.println(postExp);
        System.out.println(infixToPostfix(exp1));
    }

    private static boolean isOpeningBrackets(char top) {
        return top == '(' || top == '{' || top == '[';
    }

    private static boolean isClosingBrackets(char top) {
        return top == ')' || top == '}' || top == ']';
    }

    private static boolean hasHigherPrecedenceOperator(Stack<Character> postStack, char operator) {
        return getPrecedence(operator) <= getPrecedence(postStack.peek());
    }
}
