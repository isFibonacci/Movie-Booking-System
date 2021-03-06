package com.MovieTheaterBooking;

import java.util.ArrayList;
import java.util.Map;

public class MovieTheater {
    private int rows = 10, cols = 20;
    private int numberOfSeats = rows*cols, numberOfAvailable = rows*cols;
    private int[][] seats = new int[rows][cols];
    private int[] remainingSeats = { 20, 20, 20, 20, 20, 20, 20, 20, 20, 20};

    public int booking(String input, Map<String, ArrayList<String>> results) {
        String[] request = input.split(" ");
        String requestNumber = request[0];
        int peopleCnt = Integer.parseInt(request[1]);

        if (peopleCnt <= 0) return -1;
        if (peopleCnt > numberOfSeats || peopleCnt > numberOfAvailable) return 1;

        while (peopleCnt > 20) {
            assign(requestNumber, 20, results);
            peopleCnt -= 20;
        }
        assign(requestNumber, peopleCnt, results);
        numberOfSeats -= peopleCnt;
        numberOfAvailable -= peopleCnt;
        return 0;
    }
    public void assign(String requestNumber, int numberOfBook,  Map<String, ArrayList<String>> results) {
        // Assign the customer in Central extension method
        int lRow = 5, rRow = 5, rowStep = 0, curRow = 0;  // even move left, odd move right
        int lCol = 10, rCol = 10, colStep = 0, curCol = 0; // odd move left, even move right
        while (numberOfBook > 0 && lRow >= 0 && rRow < 10) {
            if (rowStep % 2 == 0) {
                curRow = lRow; lRow--;
            } else {
                curRow = rRow; rRow++;
            }
            rowStep++;

            // To satisfy our customers, assign them seat together as we can
            if (remainingSeats[curRow] < numberOfBook) continue;
            curCol = 10;
            while (numberOfBook > 0 && remainingSeats[curRow] > numberOfBook) {
                if (colStep % 2 == 0) {
                    curCol = lCol; lCol--;
                } else {
                    curCol = rCol; rCol++;
                }
                colStep++;

                while (curCol < 20 && (seats[curRow][curCol] == 1 || seats[curRow][curCol] == 9)) curCol++;
                // if current row have 3 consecutive seat, simply assign at first
                lockTheSeat(curRow, curCol - 1);
                for (int cnt = 0; cnt < 3; cnt++) {
                    if (curCol >= 19 || (seats[curRow][curCol] == 1 || seats[curRow][curCol] == 9)) break;
                    seats[curRow][curCol++] = 1;
                    updateResult(requestNumber, curRow, curCol, results);
                    numberOfBook -= 1; numberOfSeats -= 1; remainingSeats[curRow] -= 1;
                }
            }
        }
    }
    public void lockTheSeat(int curRow, int curCol) {
        if (curRow >= 0 && curRow < 10 && curCol >= 0 && curCol < 20) {
            seats[curRow][curCol] = 9;
            numberOfSeats -= 1; remainingSeats[curRow] -= 1;
        }
    }
    public void updateResult(String requestNumber, int curRow, int curCol, Map<String, ArrayList<String>> results) {
        if (results.containsKey(requestNumber)) {
            results.get(requestNumber).add((char)(curRow+65) + Integer.toString(curCol));
        } else {
            ArrayList<String> list = new ArrayList<>();
            list.add((char)(curRow+65) + Integer.toString(curCol));
            results.put(requestNumber, list);
        }
    }
    public void printResult() {
        System.out.println("-----------------------------------" + "[[ SCREEN ]]" + "-----------------------------------");
        System.out.println();
        for (int r = 0; r < 10; r++) {
            System.out.printf("%-4c", (char)(r + 65));
            for (int c = 0; c < 20; c++)
                System.out.printf("%-4d", seats[r][c]);
            System.out.println();
        }
    }
}
