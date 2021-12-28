import javafx.application.Preloader;

import java.util.Scanner;

public class Player {
    private String name;
    private char answer;
    private int money;

    //Setter/Konstruktor
    public Player (String name){
        this.name = name;
        this.answer = '\0';
        this.money = 0;
    }

    //Getter
    public char getAnswer(){
        return answer;
    }

    public String getName(){
        return name;
    }

    public int getMoney(){
        return money;
    }

    //Methoden
    public char makeGuess(Scanner scanIn){
        while (answer != 'a' && answer != 'b' && answer != 'c' && answer != 'd'){
            System.out.print("Wähle eine Antwortmöglichkeit:");
            answer = scanIn.next().charAt(0);
        }
        return answer;
    }

    public char chooseJoker(Scanner scanIn){
        while (answer != 'f' && answer != 'h' && answer != 's' && answer != 'k'){
            System.out.print("Wähle einen Joker (f) - \"50:50 Joker\", (h) - \"Help\", (s) - \"Second Chance\" oder (k) - \"Keinen Joker verwenden\" :");
            answer = scanIn.next().charAt(0);
        }
        return answer;
    }

    public void leave(Scanner scanIn){
        while (answer != 'y' && answer != 'n'){
            System.out.print("Möchtest du das Spiel beenden? (y) - Ja, (n) - Nein:");
            answer = scanIn.next().charAt(0);
        }
    }

    public void printMoney(){
        System.out.println("Du hast " + money + "€ gewonnen!");
    }
}
