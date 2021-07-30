package com.jesstucker.KnightTour;

import com.jesstucker.KnightTour.model.BinaryTree;
import com.jesstucker.KnightTour.model.Board;
import com.jesstucker.KnightTour.model.Position;
import com.jesstucker.KnightTour.utility.BinaryTreeSearcher;
import com.jesstucker.KnightTour.utility.BoardUtility;

class KnightTour {
    public static void main(String[] args) {
        System.out.println("Running Knight Tour!");

        Board board = new Board(8);

        BoardUtility.findSolution(board, new Position(0,0));
    }
}