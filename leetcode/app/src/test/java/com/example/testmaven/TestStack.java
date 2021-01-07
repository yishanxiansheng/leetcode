package com.example.testmaven;


import java.util.Stack;

/**
 * @author heshufan
 * @date 2020-12-26
 */
public class TestStack {

    public static void main(String[] args) {

    }


    /**
     * 最小栈的实现
     */
    public class MinestStack {
        private Stack<Integer> mainStack = new Stack<>();
        private Stack<Integer> assistStack = new Stack<>();


        public void push(Integer data) {
            mainStack.add(data);

            if (assistStack.isEmpty() || data < assistStack.peek()) {
                assistStack.push(data);
            }
        }

        public Integer pop() throws Exception {

            if (mainStack.isEmpty()) {
                throw new Exception("stack is empty");
            }

            int result = mainStack.pop();
            if (!assistStack.isEmpty() && result == assistStack.peek()) {
                assistStack.pop();
            }
            return result;
        }

        public Integer getMin() throws Exception {
            if (assistStack.isEmpty()) {
                throw new Exception("stack is empty");
            }
            return assistStack.peek();
        }

        public int top() throws Exception{
            if (mainStack.isEmpty()) {
                throw new Exception("stack is empty");
            }

            int result = mainStack.pop();
            if (!assistStack.isEmpty() && result == assistStack.peek()) {
                assistStack.pop();
            }
            return result;
        }
    }
}
