package org.bruh.checkers.game;

public enum Cell {

    EMPTY,
    BLACK_MAN,
    BLACK_KING,
    WHITE_MAN,
    WHITE_KING;

    public static boolean isEmpty(Cell cell) {
        return cell == EMPTY;
    }

    public static boolean isChecker(Cell cell) {
        return cell != EMPTY;
    }

    public static boolean isMan(Cell cell) {
        return cell == BLACK_MAN || cell == WHITE_MAN;
    }

    public static boolean isKing(Cell cell) {
        return cell == BLACK_KING || cell == WHITE_KING;
    }

    public static boolean isBlack(Cell cell) {
        return cell == BLACK_MAN || cell == BLACK_KING;
    }

    public static boolean isWhite(Cell cell) {
        return cell == WHITE_MAN || cell == WHITE_KING;
    }

    public static boolean isBlackMan(Cell cell) {
        return cell == BLACK_MAN;
    }

    public static boolean isBlackKing(Cell cell) {
        return cell == BLACK_KING;
    }

    public static boolean isWhiteMan(Cell cell) {
        return cell == WHITE_MAN;
    }

    public static boolean isWhiteKing(Cell cell) {
        return cell == WHITE_KING;
    }

    public static boolean isSame(Cell cell1, Cell cell2) {
        return cell1 == cell2;
    }

    public static boolean isSameColor(Cell cell1, Cell cell2) {
        return (isWhite(cell1) && isWhite(cell2)) || (isBlack(cell1) && isBlack(cell2));
    }

    public static boolean isSameType(Cell cell1, Cell cell2) {
        return (isMan(cell1) && isMan(cell2)) || (isKing(cell1) && isKing(cell2));
    }

    public static boolean isOpositeColor(Cell cell1, Cell cell2) {
        return ((isWhite(cell1) && isBlack(cell2)) || (isBlack(cell1) && isWhite(cell2)));
    }

    public static boolean isColor(Cell cell, Color color) {
        if (color == Color.BLACK) return isBlack(cell);
        if (color == Color.WHITE) return isWhite(cell);
        return false;
    }
}