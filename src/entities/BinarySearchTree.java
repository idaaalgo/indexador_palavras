package entities;

public class BinarySearchTree {

    private Node root;
    private Integer size;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    public void insert(String word) {
        root = insertRec(root, word);
        size++;
    }

    private Node insertRec(Node root, String word) {
        if (root == null) {
            root = new Node(word);
            return root;
        }
        if (word.compareTo(root.getWord()) < 0) {
            root.setLeft(insertRec(root.getLeft(), word));
        } else if (word.compareTo(root.getWord()) > 0) {
            root.setRight(insertRec(root.getRight(), word));
        }
        return root;
    }

    public boolean search(String word) {
        return searchRec(root, word);
    }

    private boolean searchRec(Node root, String word) {
        if (root == null) {
            return false;
        }
        if (word.equals(root.getWord())) {
            return true;
        }
        if (word.compareTo(root.getWord()) < 0) {
            return searchRec(root.getLeft(), word);
        } else {
            return searchRec(root.getRight(), word);
        }
    }

    public void inorder() {
        inorderRec(root);
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.getLeft());
            System.out.println(root.getWord());
            inorderRec(root.getRight());
        }
    }

    public int size() {
        return size;
    }

    public void remove(String word) {
        root = removeRec(root, word);
        size--;
    }

    private Node removeRec(Node root, String word) {
        if (root == null) {
            return root;
        }
        if (word.compareTo(root.getWord()) < 0) {
            root.setLeft(removeRec(root.getLeft(), word));
        } else if (word.compareTo(root.getWord()) > 0) {
            root.setRight(removeRec(root.getRight(), word));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }
            root.setWord(minValue(root.getRight()));
            root.setRight(removeRec(root.getRight(), root.getWord()));
        }
        return root;
    }

    private String minValue(Node root) {
        String min = root.getWord();
        while (root.getLeft() != null) {
            min = root.getLeft().getWord();
            root = root.getLeft();
        }
        return min;
    }

    public boolean searchSubstring(String substring) {
        return searchSubstringRec(root, substring);
    }

    private boolean searchSubstringRec(Node root, String substring) {
        if (root == null){
            return false;
        }
        if (root.getWord().contains(substring)) {
            return true;
        }
        if (substring.compareTo(root.getWord()) < 0) {
            return searchSubstringRec(root.getLeft(), substring);
        } else {
            return searchSubstringRec(root.getRight(), substring);
        }
    }

    public void searchSubstringInorder(String substring) {
        searchSubstringInorderRec(root, substring);
    }

    private void searchSubstringInorderRec(Node root, String substring) {
        if (root != null) {
            searchSubstringInorderRec(root.getLeft(), substring);
            if (root.getWord().contains(substring)){
                System.out.println(root.getWord());
            }
            searchSubstringInorderRec(root.getRight(), substring);
        }
    }
}
