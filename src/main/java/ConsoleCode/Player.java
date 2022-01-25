package ConsoleCode;

public class Player {
    //private instance variables
    private int money;
    private int category;

    //Constructor
    public Player() {
        this.money = 0;
        this.category = 14;
    }

    //Getter-method to get the current category
    public int getCategory() {
        return category;
    }

    //method to raise the category after every question
    public void raiseCategory() {
        category++;
    }

    //method to detect the current amount of money with help of the category
    public void switchMoney() {
        switch (category) { //category is being switched
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

    //method to print out the money that has been won
    public String printMoneyWon(boolean leave) {
        String moneyWon = "";
        if (leave) { //if contestant presses leave it prints out the current amount of money
            moneyWon = "Du hast " + money + " Euro gewonnen!";
        } else if (category < 6) { //if a question is answered wrong and the contestant was under category 6
            moneyWon = "Du gehst leider leer aus!";
        } else if (category < 11) { //if a question is answered wrong and the contestant was under category 11
            moneyWon = "Du hast 500 Euro gewonnen!";
        } else if(category < 16){ //if a question is answered wrong and the contestant was higher than category 11
            moneyWon = "Du hast 16000 Euro gewonnen!";
        }
        return moneyWon;
    }
}
