import javax.lang.model.util.ElementScanner6;

class Node {
    int key;
    Node left, right;

    public Node(int data){
        key = data;
        left = right = null;
    }
}

class BST_Class {
    //node class that defines BST node
    // BST root node 
    Node root;

    // Constructor for BST =>initial empty tree
    BST_Class(){
        root = null;
    }

    //delete a node from BST
    void deleteKey(int key) {
        Node current = root;
        Node parent = null;
        //if the key to be deleted exists
        while(current != null && current.key != key) {
            parent = current;
            // checks left,right subtree if key is less,more than current key 
            if (key < current.key) {
                current = current.left;
            }
            else {
                current = current.right;
            }
        }
        if(current == null)
            ;
        else {
            delete(current, parent);
        }
    }

    void delete(Node current, Node parent) {
        // if current node has 0 or 1 children
        if(current.left == null || current.right == null) {
            Node currentChild = null;
            if(current.left != null)
                currentChild = current.left;
            else
                currentChild = current.right;
            if(current == root)
                root = currentChild;
            else if(current.key < parent.key)
                parent.left = currentChild;
            else
                parent.right = currentChild;
        }
        else {
            Node replacementParent = current;
            Node replacement = current.right;
            while(replacement.left != null) {
                replacementParent = replacement;
                replacement = replacement.left;
            }
            current.key = replacement.key;
            delete(replacement, replacementParent);
        }
    }

    int minValue(Node root) {
        Node current = root;
        while(current.left != null)
            current = current.left;
        return current.key;
    }

    // insert a node in BST
    void insert(int key) {
        root = insert_Recursive(root, key);
    }

    //recursive insert function
    Node insert_Recursive(Node root, int key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }
        //traverse the tree
        if (key < root.key)     //insert in the left subtree
            root.left = insert_Recursive(root.left, key);
        else if (key > root.key)    //insert in the right subtree
            root.right = insert_Recursive(root.right, key);
        // return pointer
        return root;
    }

    boolean search(int key)  {
        Node current = root;
        return searchFor(current, key);
    }

    boolean searchFor(Node current, int key) {
        if(current == null)
            return false;
        // recursive until key is found
        if (key < current.key) {
            current = current.left;
            searchFor(current, key);
        }
        else if (key > current.key) {
            current = current.right;
            searchFor(current, key);
        }
        else if (key == current.key)
            return true;
        return false;
    }

    //PostOrder Traversal - Left:Right:rootNode (LRn)
    void postOrder(Node node)  {
        if (node == null)
            return;

        //first traverse left subtree recursively
        postOrder(node.left);

        //next traverse right subtree recursively
        postOrder(node.right);

        //then go for root node
        System.out.println(node.key + " ");
    }

    // InOrder Traversal - Left:rootNode:Right (LnR)
    void inOrder(Node node)  {
        if (node == null)
            return;

        //first traverse left subtree recursively
        inOrder(node.left);

        //then go for root node
        System.out.println(node.key + " ");

        //next traverse right subtree recursively
        inOrder(node.right);
    }

    //PreOrder Traversal - rootNode:Left:Right (nLR)
    void preOrder(Node node)  {
        if (node == null)
            return;
        
        //then go for root node
        System.out.println(node.key + " ");

        //first traverse left subtree recursively
        preOrder(node.left);

        //next traverse right subtree recursively
        preOrder(node.right);
    }

    // Wrappers for recursive functions
    void postOrder_traversal()  {
        postOrder(root);  }
    void inOrder_traversal() {
        inOrder(root);   }
    void preOrder_traversal() {
        preOrder(root);  }
}

class Main{
    public static void main(String[] args)  {
        //create a BST object
        BST_Class bst = new BST_Class();
        //insert data into BST
        bst.insert(45);
        bst.insert(10);
        bst.insert(7);
        bst.insert(12);
        bst.insert(90);
        bst.insert(50);
        //print the BST
        System.out.println("The BST Created with input data(Left-root-right):");
        bst.inOrder_traversal();

        //delete leaf node
        System.out.println("\nThe BST after Delete 12(leaf node):");
        bst.deleteKey(12);
        bst.inOrder_traversal();
        //delete the node with one child
        System.out.println("\nThe BST after Delete 90 (node with 1 child):");
        bst.deleteKey(90);
        bst.inOrder_traversal();

        //delete node with two children  
        System.out.println("\nThe BST after Delete 45 (Node with two children):");
        bst.deleteKey(45);
        bst.inOrder_traversal();
        //search a key in the BST
        boolean ret_val = bst.search (50);
        System.out.println("\nKey 50 found in BST: " + ret_val );
        ret_val = bst.search (12);
        System.out.println("Key 12 found in BST: " + ret_val );

        //construct a BST
        BST_Class tree = new BST_Class();
        tree.root = new Node(45);
        tree.root.left = new Node(10);
        tree.root.right = new Node(90);
        tree.root.left.left = new Node(7);
        tree.root.left.right = new Node(12);
        //PreOrder Traversal
        System.out.println("BST => PreOrder Traversal:");
        tree.preOrder_traversal();
        //InOrder Traversal
        System.out.println("\nBST => InOrder Traversal:");
        tree.inOrder_traversal();
        //PostOrder Traversal
        System.out.println("\nBST => PostOrder Traversal:");
        tree.postOrder_traversal();
    }
}