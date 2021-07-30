package com.jesstucker.KnightTour.model;

import com.jesstucker.KnightTour.utility.BoardUtility;

public class Board {
    private int board[][];
    private int moveCount = 0;

    public Board() {
        this.board = BoardUtility.getBoard(8);
    }

    public Board(int size) {
        this.board = BoardUtility.getBoard(size);
    }

    public void print() {
        System.out.println("Moves: " + moveCount);
        BoardUtility.printBoard(this.board);
        System.out.println("");
    }

    public int getLength() {
        return this.board.length;
    }

    public int getSize() {
        return this.board.length * this.board.length;
    }

    public int getValue(int x, int y) {
        return this.board[y][x];
    }

    public int setPositionAsUsed(Position position) {
        int x = position.getX();
        int y = position.getY();

        moveCount += 1;
        this.board[y][x] = moveCount;

        //System.out.println("Used position: " + position + " +1 " + moveCount);
        return moveCount;
    }

    public int setPositionAsOpen(Position position) {
        int x = position.getX();
        int y = position.getY();

        moveCount -= 1;
        this.board[y][x] = -1;

        //System.out.println("Open position: " + position + " -1 " + moveCount);
        return moveCount;
    }

    public int getMoveCount() {
        return this.moveCount;
    }

    public boolean isInRange(Position position) {
        int x = position.getX();
        int y = position.getY();

        if (x < 0)
            return false;
        if (y < 0)
            return false;
        if (x >= this.board.length)
            return false;
        if (y >= this.board.length)
            return false;

        return true;
    }

    public boolean isAvailable(Position position) {
        int x = position.getX();
        int y = position.getY();

        if (!isInRange(position))
            return false;

        if (this.board[y][x] > 0)
            return false;

        return true;
    }

    public boolean isValidMove(Position position) {
        int x = position.getX();
        int y = position.getY();
        //System.out.println("in range? " + x + " " + y + " - " + isInRange(position));
        //System.out.println("is avail? " + x + " " + y + " - " + isAvailable(position));
        if (isInRange(position) && isAvailable(position)) {
            return true;
        }

        return false;
    }
}
