import java.util.Scanner;

public class Player {
    //private Klassenvariablen
    private char answer;
    private int money;
    private int kategorie;

    //Konstruktor
    public Player() {
        this.answer = '\0';
        this.money = 0;
        this.kategorie = 1;
    }

    //Gettermethode für Antwort
    public char getAnswer() {
        return answer;
    }

    //Gettermethode um Kategorie zu bekommen
    public int getKategorie(){
        return kategorie;
    }

    //Methode um eine Antwortmöglichkeit einzugeben
    public void makeGuess(Scanner scanIn) {
        answer = '\0';
        while (answer != 'a' && answer != 'b' && answer != 'c' && answer != 'd') {
            System.out.print("Wähle eine Antwortmöglichkeit:");
            answer = scanIn.next().charAt(0);
        }
    }


    //Methode zur Abfrage nach Joker
    public void chooseJoker(Scanner scanIn) {
        answer = '\0';
        while (answer != 'f' && answer != 'h' && answer != 's' && answer != 'k') {
            System.out.print("Wähle einen Joker (f) - \"50:50 Joker\", (h) - \"Help\", (s) - \"Second Chance\" oder (k) - \"Keinen Joker verwenden\" :");
            answer = scanIn.next().charAt(0);
        }
    }

    //Methode zur Abfrage ob man das Spiel verlassen möchte
    public boolean leave(Scanner scanIn) {
        answer = '\0';
        while (answer != 'y' && answer != 'n') {
            System.out.print("Möchtest du das Spiel beenden? (y) - Ja, (n) - Nein:");
            answer = scanIn.next().charAt(0);
        }
        return answer == 'y';
    }

    //Methode um die Kategorie nach jeder Frage um eins zu erhöhen
    public void raiseCategory() {
        kategorie++;
    }

    //Methode um aus Kategorie den Geldbetrag zu ermitteln
    public void switchMoney() {
        switch (kategorie) { //Kategorie wird geswitched
            case 2:
                money = 50;
                break;
            case 3:
                money = 100;
                break;
            case 4:
                money = 200;
                break;
            case 5:
                money = 300;
                break;
            case 6:
                money = 500;
                break;
            case 7:
                money = 1000;
                break;
            case 8:
                money = 2000;
                break;
            case 9:
                money = 4000;
                break;
            case 10:
                money = 8000;
                break;
            case 11:
                money = 16000;
                break;
            case 12:
                money = 32000;
                break;
            case 13:
                money = 64000;
                break;
            case 14:
                money = 125000;
                break;
            case 15:
                money = 500000;
                break;
            case 16:
                money = 1000000;
                break;
            default:
                money = 0;
                break;
        }
    }

    //Methode um das Geld nach jeder Frage bezüglich der Kategorie auf Konsole auszugeben
    public void printMoney() {
        this.switchMoney();
        System.out.println("Du stehst bei " + money + "€!");
    }

    //Methode um Geld nach Ende des Spiels auszugeben
    public void printMoneyWon(boolean leave) {
        System.out.println();
        if (leave){
            System.out.println("Du hast " + money + "€ gewonnen!");
        } else if (kategorie < 6) { //wenn du eine Frage falsch beantwortet hast und nicht weiter als Kategorie 4 gekommen bist
            System.out.println("Du hast 0€ gewonnen!");
        } else if (kategorie < 11) { //wenn du eine Frage falsch beantwortet hast und nicht weiter als Kategorie 9 gekommen bist
            System.out.println("Du hast 500€ gewonnen!");
        } else if (kategorie >= 11) { //wenn du eine Frage falsch beantwortet hast und höher als Kategorie 10 warst
            System.out.println("Du hast 16000€ gewonnen!");
        }
    }
}
