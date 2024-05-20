package entities;

public class Node {
    private String word;
    private Node left, right;

    public Node(String word) {
        this.word = word;
        left = right = null;
    }

    public Node() {
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
