package ConsoleCode;

import java.util.Scanner;

public class Player {
    //private Klassenvariablen
    private int money;
    private int category;

    //Konstruktor
    public Player() {
        this.money = 0;
        this.category = 1;
    }

    //Gettermethode um Kategorie zu bekommen
    public int getCategory() {
        return category;
    }

    public int getMoney() {return money;}

    public void changeCategory(int currentCategory){
        category = currentCategory; //refactored
    }

    //Methode um die Kategorie nach jeder Frage um eins zu erhöhen
    public void raiseCategory() {
        category++;
    }

    //Methode um aus Kategorie den Geldbetrag zu ermitteln
    public void switchMoney() {
        switch (category) { //Kategorie wird geswitched
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

    //Methode um Geld nach Ende des Spiels auszugeben
    public String printMoneyWon(boolean leave) {
        String moneyWon = "";
        if (leave) {
            moneyWon = "Du hast " + money + " Euro gewonnen!";
        } else if (category < 6) { //wenn du eine Frage falsch beantwortet hast und nicht weiter als Kategorie 4 gekommen bist
            moneyWon = "Du gehst leider leer aus!";
        } else if (category < 11) { //wenn du eine Frage falsch beantwortet hast und nicht weiter als Kategorie 9 gekommen bist
            moneyWon = "Du hast 500 Euro gewonnen!";
        } else if(category < 15){ //wenn du eine Frage falsch beantwortet hast und höher als Kategorie 10 warst
            moneyWon = "Du hast 16000 Euro gewonnen!";
        } else if(category == 15){
            moneyWon = "Gl\u00fcckwunsch! Du bist Million\u00e4r.";
        }
        return moneyWon;
    }
}
