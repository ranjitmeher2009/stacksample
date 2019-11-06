package stack.sample;

import java.util.Stack;

import java.util.Stack;

public class InfixToPrefix {

    static Stack inputStack;

    static String output = "";

    public static void main(String[] args) {

        //String input = "(A-B/C)*(A/K-L)";
        String input = "1+2*4/5-7+3/6";
        int len = input.length();
        char[] charr = new char[len];
        for (int i = 0; i < len; i++) {
            charr[i] = input.charAt(i);
        }

        String reverseInput = reverse(input);

        System.out.println(infixToPrefix(reverseInput));

    }

    public static String infixToPrefix(String input) {

        inputStack = new Stack();

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (current == '+' || current == '-') {
                isOperator(current, 1);
            } else if (current == '*' || current == '/') {
                isOperator(current, 2);
            } else {
                output += current;
            }
        }
        while (!inputStack.isEmpty()) {
            char top = (Character) inputStack.pop();
            output += top;
        }
        output = reverse(output);
        return output;
    }

    public static void isOperator(char c, int prec) {
        while (!inputStack.isEmpty()) {
            char top = (Character) inputStack.pop();
            int topPrec = 0;
            if (top == '+' || top == '-') {
                topPrec = 1;
            } else {
                topPrec = 2;
            }

            if (topPrec >= prec) {
                output += top;
            } else {
                inputStack.push(top);
                break;
            }

        }
        inputStack.push(c);

    }

    public static String reverse(String input) {
        int len = input.length();
        String reverse = "";
        char[] charr = new char[len];
        for (int i = 0; i < len; i++) {
            charr[i] = input.charAt(i);
        }
        for (int i = 0; i < len / 2; i++) {
            char temp = charr[i];
            charr[i] = charr[len - i - 1];
            charr[len - i - 1] = temp;
        }
        for (int j = 0; j < len; j++) {
            reverse += charr[j];
        }
        return reverse;
    }
}