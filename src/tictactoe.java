import java.util.Scanner;

public class tictactoe {
    private char[] board;
    private final Scanner scanner = new Scanner(System.in);
    private int xCount;
    private int oCount;
    private boolean gameOver;

    public void play() {
        board = "__________".toCharArray();
        print();
        makeMove();
    }

    private void makeMove() {
        while (!gameOver) {
            System.out.print("Enter the coordinates: ");
            String coordinates = scanner.nextLine();
            if (!coordinates.matches("\\d\\s\\d")) {
                System.out.println("You should enter numbers!");
            } else if (!coordinates.matches("[1-3]\\s[1-3]")) {
                System.out.println("Coordinates should be from 1 to 3!");
            } else if (occupied(coordinates)) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                setCoordinates(coordinates);
                print();
                System.out.println(checkWinner());

            }
        }

    }

    private void setCoordinates(String coordinates) {
        String[] cor = coordinates.split("\\s");
        int y = Integer.parseInt(cor[0]);
        int x = Integer.parseInt(cor[1]);
        if (xCount == oCount) {
            xCount++;
            board[3 * y + x - 4] = 'X';
        } else {
            oCount++;
            board[3 * y + x - 4] = 'O';
        }

    }

    private boolean occupied(String coordinates) {
        String[] cor = coordinates.split("\\s");
        int y = Integer.parseInt(cor[0]);
        int x = Integer.parseInt(cor[1]);
        return board[3 * y + x - 4] != '_';
    }

    private String checkWinner() {
        if (!isPossible()) {
            return "Impossible";
        } else if (checkWinnerFor('X')) {
            return "X wins";
        } else if (checkWinnerFor('O')) {
            return "O wins";
        } else if (isComplete()) {
            return "Draw";
        } else {
            return "";
        }

    }

    private boolean checkWinnerFor(char c) {
        if (board[0] == c && board[1] == c && board[2] == c ||
                board[3] == c && board[4] == c && board[5] == c ||
                board[6] == c && board[7] == c && board[8] == c ||
                board[0] == c && board[3] == c && board[6] == c ||
                board[1] == c && board[4] == c && board[7] == c ||
                board[2] == c && board[5] == c && board[8] == c ||
                board[0] == c && board[4] == c && board[8] == c ||
                board[2] == c && board[4] == c && board[6] == c) {
            gameOver = true;
            return true;
        } else return false;
    }

    private boolean isPossible() {
        int xCount = 0;
        int oCount = 0;
        for (char c : board) {
            if (c == 'X') {
                xCount++;
            } else if (c == 'O') {
                oCount++;
            }
        }

        if (xCount - oCount > 1 || oCount - xCount > 1) {
            return false;
        }

        return !checkWinnerFor('X') || !checkWinnerFor('O');
    }

    private boolean isComplete() {
        for (char c : board) {
            if (c == '_') {
                return false;
            }
        }
        gameOver = true;
        return true;
    }

    private void print() {
        char[] cells = new String(board).replace("_", " ").toCharArray();
        System.out.println("---------");
        System.out.println("| " + cells[0] + " " + cells[1] + " " + cells[2] + " |");
        System.out.println("| " + cells[3] + " " + cells[4] + " " + cells[5] + " |");
        System.out.println("| " + cells[6] + " " + cells[7] + " " + cells[8] + " |");
        System.out.println("---------");
    }
}