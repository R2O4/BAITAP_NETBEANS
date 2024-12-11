import java.io.*;
import java.net.*;

public class OanTuTi2_Server {
    private static final int PORT = 12345;
    private static final int PLAYER_COUNT = 3;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        Socket[] playerSockets = new Socket[PLAYER_COUNT];
        BufferedReader[] in = new BufferedReader[PLAYER_COUNT];
        PrintWriter[] out = new PrintWriter[PLAYER_COUNT];

        System.out.println("Oan Tu Ti Server is running...");

        for (int i = 0; i < PLAYER_COUNT; i++) {
            playerSockets[i] = serverSocket.accept();
            in[i] = new BufferedReader(new InputStreamReader(playerSockets[i].getInputStream()));
            out[i] = new PrintWriter(playerSockets[i].getOutputStream(), true);
            out[i].println("Welcome Player " + (i + 1) + "! Please wait for other players.");
            System.out.println("Player " + (i + 1) + " has joined.");
        }

        int[] choices = new int[PLAYER_COUNT];
        for (int i = 0; i < PLAYER_COUNT; i++) {
            out[i].println("All players are connected. Enter your choice (1 for Rock, 2 for Paper, 3 for Scissors):");
            choices[i] = Integer.parseInt(in[i].readLine());
        }

        determineWinner(choices, out);

        for (int i = 0; i < PLAYER_COUNT; i++) {
            playerSockets[i].close();
        }

        serverSocket.close();
    }

    public static void determineWinner(int[] choices, PrintWriter[] out) {
        String[] options = {"", "Rock", "Paper", "Scissors"};
        for (int i = 0; i < choices.length; i++) {
            out[i].println("Player " + (i + 1) + " chose: " + options[choices[i]]);
        }

        if (choices[0] == choices[1] && choices[1] == choices[2]) {
            for (PrintWriter writer : out) {
                writer.println("It's a tie! All players chose the same.");
            }
            return;
        }

        boolean player1BeatsPlayer2 = beats(choices[0], choices[1]);
        boolean player1BeatsPlayer3 = beats(choices[0], choices[2]);
        boolean player2BeatsPlayer1 = beats(choices[1], choices[0]);
        boolean player2BeatsPlayer3 = beats(choices[1], choices[2]);
        boolean player3BeatsPlayer1 = beats(choices[2], choices[0]);
        boolean player3BeatsPlayer2 = beats(choices[2], choices[1]);

        if (player1BeatsPlayer2 && player1BeatsPlayer3) {
            for (PrintWriter writer : out) {
                writer.println("Player 1 wins!");
            }
        } else if (player2BeatsPlayer1 && player2BeatsPlayer3) {
            for (PrintWriter writer : out) {
                writer.println("Player 2 wins!");
            }
        } else if (player3BeatsPlayer1 && player3BeatsPlayer2) {
            for (PrintWriter writer : out) {
                writer.println("Player 3 wins!");
            }
        } else {
            for (PrintWriter writer : out) {
                writer.println("It's a tie! No single player beats the other two.");
            }
        }
    }

    public static boolean beats(int choice1, int choice2) {
        return (choice1 == 1 && choice2 == 3) ||
               (choice1 == 2 && choice2 == 1) ||
               (choice1 == 3 && choice2 == 2);
    }
}
