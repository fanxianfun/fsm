package com.heetian.example1;

import java.util.Objects;
import java.util.Scanner;

public class Binary {

    private Binary(String currentState) {
        this.currentState = currentState;
    }

    private String currentState;

    private void transition(String event) {

        switch (event) {
            case "1":
                // from [S2] on [0] to [S2]
                // from [S1] on [0] to [S1]
                break;
            case "0":
                if(Objects.equals(this.currentState,"S2")){
                    // from [S2] on [1] to [S1]
                    this.currentState = "S1";
                }else {
                    // from [S1] on [1] to [S2]
                    this.currentState = "S2";
                }
                break;
            default:
                System.out.println("Invalid input.");
                break;
        }
    }


    private String getCurrentState() {
        return currentState;
    }

    public static void main(String[] args) {

        Binary binary = new Binary("S1");

        String line;
        Scanner sc = new Scanner(System.in);

        while ((line = sc.nextLine()) != null){
            if(line.length() == 0){
                continue;
            }
            String[] words = line.split("");
            for (String word : words) {
                binary.transition(word);
                System.out.println(binary.getCurrentState());
            }

        }

    }
}
