package ConsoleCode;

import ConsoleCode.Joker;
import ConsoleCode.Player;

public class Question {
    //instance variables
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

    //constructor for Question object
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

    //getter -- for the question.
    public String getQuestion() {
        return question;
    }
    //Getter -- for the category.
    public int getCategory() {
        return category;
    }
    // Getter a-d -- the answer possibilities for the questions.
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
    // Getter -- for the Correct answer.
    public char getCorrect() {
        return correct;
    }
    // Getter -- for the second Chance Joker.
    public boolean getSecondChance() {
        return secondChance;
    }
    // Getter hideA/D -- for our fifty-fifty Joker
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

    //Method which allows us to use the Second Chance Joker.
    public void changeSecondChance() {
        this.secondChance = true;
    }
    //Method that renews the Second Chance Joker.
    public void changeSecondChanceBack() {this.secondChance = false;}
    // Method in which one wrong answer is hidden when the fifty-fifty Joker is called upon. Method is used twice. See Method 'useFiftyFiftyJoker'.
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

