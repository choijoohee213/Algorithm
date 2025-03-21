import java.io.*;
import java.util.*;

class Main {
    static class Node {
        int value;
        Node leftChild;
        Node rightChild;

        Node (int value) {
            this.value = value;
        }
    }
    static int size = 0;
    static int[] preOrderTree = new int[10001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while((line = br.readLine()) != null) {
            preOrderTree[size++] = Integer.parseInt(line);
        }

        Node rootNode = preOrder(0, size-1);
        printPostOrder(rootNode);
    }

    static Node preOrder(int start, int end) {
        if(start > end) {
            return null;
        }

        int val = preOrderTree[start];
        Node node = new Node(val);

        int left = start + 1;
        int right = start + 1;

        while(right <= end && preOrderTree[right] < val) {
            right++;
        }

        node.leftChild = preOrder(left, right-1);
        node.rightChild = preOrder(right, end);

        return node;
    }

    static void printPostOrder(Node node) {
        if(node == null) {
            return;
        }

        printPostOrder(node.leftChild);
        printPostOrder(node.rightChild);
        System.out.println(node.value);
    }
}