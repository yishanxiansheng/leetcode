package com.example.testmaven;


import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * @author heshufan
 * @date 2020-12-26
 */
public class TestStack {

    public static void main(String[] args) {
        validateStackSequences(new int[]{1, 2, 3, 4, 5}, new int[]{4, 5, 3, 2, 1});
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

        public int top() throws Exception {
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

    /**
     * 栈的压入弹出序列
     *
     * @param pushed
     * @param popped
     * @return
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> deque1 = new ArrayDeque<>();
        int i = 0;
        for (int j = 0; j < pushed.length; j++) {
            deque1.push(pushed[j]);
            while (!deque1.isEmpty() && (int) deque1.peek() == popped[i]) {
                deque1.pop();
                i++;
            }
        }
        while (!deque1.isEmpty() && i < popped.length) {
            if ((int) deque1.peek() == popped[i]) {
                deque1.pop();
            }
            i++;
        }
        return deque1.isEmpty();
    }
}
