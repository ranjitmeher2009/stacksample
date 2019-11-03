package stack.sample;

import java.util.Stack;

public class StackAsLinkedList {

    StackNode root;
    static class StackNode {
        String data;
        StackNode next;

        public StackNode(String data) {
            this.data = data;
        }
    }

    public boolean isEmpty(){
        return root == null ? true : false;
    }

    public void push(String data){
        StackNode newNode = new StackNode(data);
        if(root == null){
            root = newNode;
        }else{
            newNode.next = root;
            root = newNode;
        }
    }

    public String pop(){
        String poppedData;
        if(root == null){
            return null;
        } else {
            poppedData = root.data;
            root = root.next;
        }
        return poppedData;
    }

    public String peek(){
        if(root == null){
            return null;
        } else {
            return root.data;
        }
    }

    public void display(){
        if(root == null){
            return;
        }else{
            while (!isEmpty()){
                System.out.println(root.data);
                root = root.next;
            }
        }
    }

    public static void main(String[] args) {
        StackAsLinkedList stack = new StackAsLinkedList();
        stack.push("Ravi");
        stack.push("Ram");
        stack.push("Ashok");
        stack.push("Rahul");
        stack.push("Sathi");

        stack.pop();

        System.out.println("Top Element"+ stack.peek());

        stack.display();

    }
}
