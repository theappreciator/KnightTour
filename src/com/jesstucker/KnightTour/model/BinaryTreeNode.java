package com.jesstucker.KnightTour.model;

import com.sun.source.tree.BinaryTree;

public class BinaryTreeNode {
    private Position id;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode() {
        left = null;
        right = null;
    }

    public BinaryTreeNode(Position id) {
        left = null;
        right = null;

        this.id = id;
    }

    public void setLeft(BinaryTreeNode node) {
        this.left = node;
    }

    public BinaryTreeNode getLeft() {
        return this.left;
    }

    public void setRight(BinaryTreeNode node) {
        this.right = node;
    }

    public BinaryTreeNode getRight() {
        return  this.right;
    }

    public void setId(Position id) {
        this.id = id;
    }

    public Position getId() {
        return this.id;
    }
}
