package ConsoleCode;

import ConsoleCode.Joker;
import ConsoleCode.Player;

public class Question {
    private final String question;
    private final int category;
    private final String a;
    private final String b;
    private final String c;
    private final String d;
    private final char correct;
    private boolean hideA;
    private boolean hideB;
    private boolean hideC;
    private boolean hideD;
    private boolean secondChance;

    public Question(String question, int category, String a, String b, String c, String d, char correct) {
        this.question = question;
        this.category = category;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;

        this.hideA = false;
        this.hideB = false;
        this.hideC = false;
        this.hideD = false;
        this.secondChance = false;
    }

    //Getter
    public String getQuestion() {
        return question;
    }

    public int getCategory() {
        return category;
    }

    public String getA() {
        return a;
    }

    public String getB() {
        return b;
    }

    public String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public char getCorrect() {
        return correct;
    }

    public boolean getSecondChance() {
        return secondChance;
    }

    public boolean isHideA (){
        return  hideA;
    }

    public boolean isHideB (){
        return hideB;
    }

    public boolean isHideC(){
        return hideC;
    }

    public boolean isHideD(){
        return hideD;
    }

    //Methoden
    public void changeSecondChance() {
        this.secondChance = true;
    }

    public void changeSecondChanceBack() {this.secondChance = false;}

    public void changeHide(char answer, Joker fiftyFifty) {
        switch (answer) {
            case 'a':
                if (!hideA) {
                    hideA = true;
                } else {
                    fiftyFifty.changeQuestion(this);
                }
                break;
            case 'b':
                if (!hideB) {
                    hideB = true;
                } else {
                    fiftyFifty.changeQuestion(this);
                }
                break;
            case 'c':
                if (!hideC) {
                    hideC = true;
                } else {
                    fiftyFifty.changeQuestion(this);
                }
                break;
            case 'd':
                if (!hideD) {
                    hideD = true;
                } else {
                    fiftyFifty.changeQuestion(this);
                }
                break;
            default:
                break;
        }
    }
}

