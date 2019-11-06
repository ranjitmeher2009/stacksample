package stack.sample;

import java.util.Stack;


//Algorithm
//        1. Scan the infix expression from left to right.
//        2. If the scanned character is an operand, output it.
//        3. Else,
//        …..3.1 If the precedence of the scanned operator is greater than the precedence of the
//               operator in the stack(or the stack is empty or the stack contains a ‘(‘ ), push it.
//        …..3.2 Else, Pop all the operators from the stack which are greater than or equal to in
//               precedence than that of the scanned operator. After doing that Push the scanned operator to the stack. (If you encounter parenthesis while popping then stop there and push the scanned operator in the stack.)
//        4. If the scanned character is an ‘(‘, push it to the stack.
//        5. If the scanned character is an ‘)’, pop the stack and and output it until a ‘(‘ is encountered, and discard both the parenthesis.
//        6. Repeat steps 2-6 until infix expression is scanned.
//        7. Print the output
//       8. Pop and output from the stack until it is not empty.

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
