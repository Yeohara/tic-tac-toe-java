package tictactoe;
import java.util.Scanner;

public class Main {
    static void OutputField(char[][] field) {
        String lines = "---------";
        System.out.println(lines);
        System.out.println("| " + field[0][0] + " " + field[0][1] + " " + field[0][2] + " |");
        System.out.println("| " + field[1][0] + " " + field[1][1] + " " + field[1][2] + " |");
        System.out.println("| " + field[2][0] + " " + field[2][1] + " " + field[2][2] + " |");
        System.out.println(lines);
    }
    static void GameTurn(char symbol, char[][] field) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int coord_x = scanner.nextInt();
            int coord_y = scanner.nextInt();
            if ((1 <= coord_x && coord_x < 4) && (1 <= coord_y && coord_y < 4)) {
                if (field[3 - coord_y][coord_x - 1] != 'X' && field[3 - coord_y][coord_x - 1] != 'O') {
                    field[3 - coord_y][coord_x - 1] = symbol;
                    break;
                } else {
                    System.out.println("This cell is occupied! Choose another one!");
                }
            } else {
                System.out.println("Coordinates should be from 1 to 3!");
            }
        }
    }
    static boolean CheckField(char[][] field) {
        int sumX = 0;
        int sumO = 0;
        int sum_ = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == 'X') {
                    sumX += 'X';
                }
                if (field[i][j] == 'O') {
                    sumO += 'O';
                }
                if (field[i][j] == '_' || field[i][j] == ' ') {
                    sum_++;
                }
            }
        }

        if (sumO == sumX || ((sumX - sumO) > ('X' * 2)) || ((sumO - sumX) > ('O'))) {
            System.out.println("Impossible");
            return true;
        }
        boolean winX = false, winO = false;
        for (int i = 0, L = 0, R = 1, G = 0, V = 0, sumDiagR = 0, sumDiagL = 0, sumG, sumV;
             i < 3;
             i++, G++, L++, R++, V++) {
            sumDiagR += field[i][i];
            sumDiagL += field[i][2-i];
            sumG = field[G][0] + field[G][1] + field[G][2];
            sumV = field[0][V] + field[1][V] + field[2][V];
            if (sumDiagR == 264 || sumDiagL == 264 || sumG == 264 || sumV == 264) {
                winX = true;
            }
            if (sumDiagR == 237 || sumDiagL == 237 || sumG == 237 || sumV == 237) {
                winO = true;
            }
        }
        if (winX && winO) {
            System.out.println("Impossible");
            return true;
        }

        if (winX) {
            System.out.println("X wins");
            return true;
        }

        if (winO) {
            System.out.println("O wins");
            return true;
        }

        if (sum_ == 0) {
            System.out.println("Draw");
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        char[][] field = new char[3][3];
        for(int i = 0, k = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++, k++)
                field[i][j] = ' ';
        }
        OutputField(field);
        System.out.println("Enter the coordinates: ");
        do {
            GameTurn('X', field);
            OutputField(field);
            if (CheckField(field)) {
                break;
            }
            GameTurn('O', field);
            OutputField(field);
            if (CheckField(field)) {
                break;
            }

        } while (true);
    }
}
