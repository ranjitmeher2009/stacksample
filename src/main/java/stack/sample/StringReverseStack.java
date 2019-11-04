package stack.sample;

import java.util.Stack;

public class StringReverseStack {

    public static void main(String[] args) {
        String[] stringArray = {"Ram", "Ravi", "Kalyani", "kalyani", "Reeta", "Ranjan", "Harshita", "Abhi", "Aakash", "Yak"};
        Stack<String> strStack = new Stack<String>();

        for(int i=0; i < stringArray.length; i++){
            strStack.push(stringArray[i]);
        }

        while(!strStack.empty()){
            System.out.print(strStack.pop()+ " | ");
        }
    }
}
