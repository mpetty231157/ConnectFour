import java.util.Scanner;

public class ConnectFour {

    public static void main(String[] args) {

        boolean gameEnded = false;
        char player1 = 'y';
        char player2 = 'n';
        int columnChoice;
        int currRow;
        int numTurns = 0;

        Scanner sc = new Scanner(System.in);

        System.out.println("What would you like the height of the board to be?");
        int numColumns = sc.nextInt();
        System.out.println("What would you like the length of the board to be?");
        int numRows = sc.nextInt();
        char[][] array = new char[numRows][numColumns];
        initializeBoard(array);
        printBoard(array);
        System.out.println("Player 1: y");
        System.out.println("Player 2: n");
        int totalPossibleTurns = numRows * numColumns;

        while (gameEnded == false) {
            System.out.println();
            System.out.println("Player 1: Which column would you like to choose?");
            columnChoice = sc.nextInt();
            currRow = insertChip(array, columnChoice, player1);
            ++numTurns;
            printBoard(array);
            gameEnded = checkIfWinner(array, columnChoice, currRow, player1);
            if (gameEnded == true) {
                System.out.println("Player 1 won the game!");
                break;
            }
            if (numTurns == totalPossibleTurns) {
                System.out.println("Draw. Nobody wins.");
                break;
            }

            System.out.println();
            System.out.println("Player 2: Which column would you like to choose?");
            columnChoice = sc.nextInt();
            currRow = insertChip(array, columnChoice, player2);
            ++numTurns;
            printBoard(array);
            gameEnded = checkIfWinner(array, columnChoice, currRow, player2);
            if (gameEnded == true) {
                System.out.println("Player 2 won the game!");
                break;
            }
            if (numTurns == totalPossibleTurns) {
                System.out.println("Draw. Nobody wins.");
                break;
            }
        }
    }

    public static void initializeBoard(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = '-';
            }
        }
    }

    public static void printBoard(char[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int insertChip(char[][] array, int col, char chipType) {
        int i;
        for (i = array.length - 1; i >= 0; i--) {
            if (array[i][col] == 'y' || array[i][col] == 'n') {
                continue;
            } else {
                array[i][col] = chipType;
                break;
            }
        }
        return i;
    }
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType) {
        int inRow = 1;
        int inCol = 1;
        boolean rightSide = true;
        boolean leftSide = true;
        boolean upSide = true;
        boolean downSide = true;
        int opponentChip;

        if (chipType == 'y') {
            opponentChip = 'n';
        }
        else {
            opponentChip = 'y';
        }

        for (int y = 1; y < array.length; y++) {
            while (rightSide) {
                if (col + y < array.length && array[row][col + y] == chipType) {
                    ++inRow;
                }
                if (col + y < array.length && array[row][col + y] == opponentChip) {
                    rightSide = false;
                }
                break;
            }
            while (leftSide) {
                if (col - y >= 0 && array[row][col - y] == chipType) {
                    ++inRow;
                }
                if (col - y >= 0 && array[row][col - y] == opponentChip) {
                    leftSide = false;
                }
                break;
            }
            if (inRow == 4) {
                return true;
            }
        }
        for (int y = 1; y < array[col].length; y++) {
            while (upSide) {
                if (row + y < array[col].length && array[row + y][col] == chipType) {
                    ++inCol;
                }
                if (row + y < array[col].length && array[row + y][col] == opponentChip) {
                    upSide = false;
                }
                break;
            }
            while (downSide) {
                if (row - y >= 0 && array[row - y][col] == chipType) {
                    ++inCol;
                }
                if (row - y >= 0 && array[row - y][col] == opponentChip) {
                    downSide = false;
                }
                break;
            }
            if (inCol == 4) {
                return true;
            }
        }
        return false;
    }
}
