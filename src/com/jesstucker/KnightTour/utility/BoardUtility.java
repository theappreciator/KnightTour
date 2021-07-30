package com.jesstucker.KnightTour.utility;

import com.jesstucker.KnightTour.model.BinaryTree;
import com.jesstucker.KnightTour.model.BinaryTreeNode;
import com.jesstucker.KnightTour.model.Board;
import com.jesstucker.KnightTour.model.Position;
import javafx.geometry.Pos;

import java.util.*;

public class BoardUtility {
    static private int maxMoveCount = 0;
    static private long lastTimeMoveChanged = System.currentTimeMillis();
    static private long lastTimeMoveChangedDuration = 0;
    static private long largestBacktrack = 0;

    static private long timer = 0;
    static private long lastTimer = 0;
    static private long interval = 1000;
    static private long ticks = 0;
    static private long ticksBreak = 60;

    static public int[][] getBoard(int size) {
        int board[][] = new int[size][size];

        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                board[r][c] = -1;
            }
        }

        return board;
    }

    static public void printBoard(Board board) {
        board.print();
    }

    static public void printBoard(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board.length; c++) {
                System.out.print(String.format("%02d", board[r][c]) + " ");
            }
            System.out.println("");
        }
    }

    static public Queue<Position> getValidMovesFromPosition(Board board, Position position) {

        Queue<Position> validMoves = new LinkedList<>();

        Set<Integer> vals1 = new HashSet<>(Arrays.asList(-2, 2));
        Set<Integer> vals2 = new HashSet<>(Arrays.asList(-1, 1));

        vals1.forEach(v1 -> vals2.forEach(v2 -> {
            Position position1 = new Position(position.getX() + v1, position.getY() + v2);
            if (board.isValidMove(position1))
                validMoves.add(position1);

            Position position2 = new Position(position.getX() + v2, position.getY() + v1);
            if (board.isValidMove(position2))
                validMoves.add(position2);
        }));

        return validMoves;
    }

    static public Queue<Position> getNextMove(Queue<Position> currentMoves, Board board, Queue<Position> nextPositions) {

        System.out.println("Moves: " + currentMoves.size());

        while (!nextPositions.isEmpty() || currentMoves.size() < board.getSize()) {
            Position nextPosition = nextPositions.peek();
            nextPositions.remove();

            System.out.println("Checking " + nextPosition);

            if (nextPosition != null) {
                Queue<Position> positions = BoardUtility.getValidMovesFromPosition(board, nextPosition);

                currentMoves = getNextMove(currentMoves, board, positions);
            }
        }

        System.out.println("Moves size: " + currentMoves.size());
        return currentMoves;
    }

    static public boolean findSolutionUtil(Board board, Position currentPosition) {

        timer = System.currentTimeMillis();
        if (timer > lastTimer + interval) {
            ticks++;
            lastTimer = timer;
            System.out.print(".");

            if (ticks > ticksBreak) {
                ticks = 0;
                System.out.println("");
            }
        }

        if (board.getMoveCount() >= board.getSize()) {
            System.out.println("We think we have reached the max moves!");
            return true;
        }

        Queue<Position> nextMoves = BoardUtility.getValidMovesFromPosition(board, currentPosition);

//        if (nextMoves.size() == 0) {
//            System.out.println("No moves, backtracking for " + currentPosition);
//        }
        while (!nextMoves.isEmpty()) {
            Position nextPosition = nextMoves.peek();
            nextMoves.remove();

            //System.out.println("Checking: " + nextPosition + " " + board.getMoveCount() + " " + board.isValidMove(nextPosition) + " (MAX: " + maxMoveCount + " [" + largestBacktrack + "] [" + lastTimeMoveChangedDuration + "])");
            //board.print();

            if (board.isValidMove(nextPosition)) {
                board.setPositionAsUsed(nextPosition);
                if (board.getMoveCount() > maxMoveCount) {
                    largestBacktrack = 0;
                    maxMoveCount = board.getMoveCount();
                    lastTimeMoveChangedDuration = System.currentTimeMillis() - lastTimeMoveChanged;

                    System.out.println("");
                    System.out.println("New max moves: " + maxMoveCount);
                }
                if (findSolutionUtil(board, nextPosition)) {
                    //System.out.println("Returning true for current: " + currentPosition);
                    return true;
                }
                else {
                    board.setPositionAsOpen(nextPosition);

                    if ((maxMoveCount - board.getMoveCount()) > largestBacktrack) {
                        largestBacktrack = maxMoveCount - board.getMoveCount();

                        System.out.print("." + largestBacktrack);
                        System.out.println("");
                    }
                }
            }
        }

        //System.out.println("Returning false for current: " + currentPosition);
        return false;
    }

    static public void findSolution(Board board, Position startPosition) {

        int x = startPosition.getX();
        int y = startPosition.getY();

        board.setPositionAsUsed(startPosition);
        board.print();

        if (BoardUtility.findSolutionUtil(board, startPosition)) {
            System.out.println("Found a solution!");
        }
        else {
            System.out.println("Did not find a solution!");
        }

        board.print();
    }
}
