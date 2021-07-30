package com.jesstucker.KnightTour.model;

public class BinaryTree {
    private BinaryTreeNode root;

    public BinaryTree() {
        this.root = null;
    }

    public BinaryTree(BinaryTreeNode node) {
        this.root = node;
    }

    public void setRoot(BinaryTreeNode node) {
        this.root = node;
    }

    public BinaryTreeNode getRoot() {
        return this.root;
    }



//    static public BinaryTree getDummyData() {
//        BinaryTreeNode root = new BinaryTreeNode("1");
//        BinaryTreeNode left = new BinaryTreeNode("2");
//        BinaryTreeNode right = new BinaryTreeNode("3");
//        BinaryTreeNode leftLeft = new BinaryTreeNode("4");
//        left.setLeft(leftLeft);
//        root.setLeft(left);
//        root.setRight(right);
//
//        BinaryTree tree = new BinaryTree(root);
//
//        return tree;
//    }
}
