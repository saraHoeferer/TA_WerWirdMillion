import java.util.Scanner;

public class Player {
    private String name;
    private char answer;
    private int money;
    private int kategorie;

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
        answer = '\0';
        while (answer != 'a' && answer != 'b' && answer != 'c' && answer != 'd'){
            System.out.print("Wähle eine Antwortmöglichkeit:");
            answer = scanIn.next().charAt(0);
        }
        return answer;
    }

    public char chooseJoker(Scanner scanIn){
        answer = '\0';
        while (answer != 'f' && answer != 'h' && answer != 's' && answer != 'k'){
            System.out.print("Wähle einen Joker (f) - \"50:50 Joker\", (h) - \"Help\", (s) - \"Second Chance\" oder (k) - \"Keinen Joker verwenden\" :");
            answer = scanIn.next().charAt(0);
        }
        return answer;
    }

    public boolean leave(Scanner scanIn){
        answer = '\0';
        while (answer != 'y' && answer != 'n'){
            System.out.print("Möchtest du das Spiel beenden? (y) - Ja, (n) - Nein:");
            answer = scanIn.next().charAt(0);
        }
        if(answer == 'y'){
            return true;
        } else {
            return false;
        }
    }

    public void raiseMoney(){
        kategorie++;
    }

    public void switchMoney(){
        switch(kategorie){
            case 1:
                money = 50;
                break;
            case 2:
                money = 100;
                break;
            case 3:
                money = 200;
                break;
            case 4:
                money = 300;
                break;
            case 5:
                money = 500;
                break;
            case 6:
                money = 1000;
                break;
            case 7:
                money = 2000;
                break;
            case 8:
                money = 4000;
                break;
            case 9:
                money = 8000;
                break;
            case 10:
                money = 16000;
                break;
            case 11:
                money = 32000;
                break;
            case 12:
                money = 64000;
                break;
            case 13:
                money = 125000;
                break;
            case 14:
                money = 500000;
                break;
            case 15:
                money = 1000000;
                break;
            default:
                money = 0;
                break;
        }
    }

    public void printMoney(){
        this.switchMoney();
        System.out.println("Du stehst bei " + money + "€!");
    }

    public void printMoneyWon(){
        if (kategorie < 5){
            System.out.println("Du hast 0€ gewonnen!");
        } else if (kategorie < 10){
            System.out.println("Du hast 500€ gewonnen!");
        } else if (kategorie > 10){
            System.out.println("Du hast 16000€ gewonnen!");
        }
    }
}
