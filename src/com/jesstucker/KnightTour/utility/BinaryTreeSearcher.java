package com.jesstucker.KnightTour.utility;

import com.jesstucker.KnightTour.model.BinaryTree;
import com.jesstucker.KnightTour.model.BinaryTreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTreeSearcher {
    static public void print(BinaryTree tree) {
        Queue<BinaryTreeNode> q = new LinkedList<>();
        q.add(tree.getRoot());

        while (!q.isEmpty()) {
            BinaryTreeNode node = q.peek();
            q.remove();

            if (node == null) {
                System.out.println("null");
            }
            else {
                System.out.println(node.getId());
                q.add(node.getLeft());
                q.add(node.getRight());
            }
        }
    }
}
