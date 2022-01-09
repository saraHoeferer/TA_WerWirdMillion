public class Question {
    private String question;
    private int category;
    //private String theme;
    private String a;
    private String b;
    private String c;
    private String d;
    private char correct;
    private boolean hideA;
    private boolean hideB;
    private boolean hideC;
    private boolean hideD;
    private boolean secondChance;

    public Question(String question, int category, String a, String b, String c, String d, char correct) {
        this.question = question;
        this.category = category;
        //this.theme = theme;
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

    public boolean getHideA() {
        return hideA;
    }

    public boolean getHideB() {
        return hideB;
    }

    public boolean getHideC() {
        return hideC;
    }

    public boolean getHideD() {
        return hideD;
    }

    public boolean getSecondChance(){ return secondChance; }

    //Methoden
    public void changeSecondChance(){
        this.secondChance = true;
    }

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

    public void printQuestion() {
        System.out.println(question);
        if (!hideA) {
            System.out.println("a: " + a);
        }
        if (!hideB) {
            System.out.println("b: " + b);
        }
        if (!hideC) {
            System.out.println("c: " + c);
        }
        if (!hideD) {
            System.out.println("d: " + d);
        }
    }

    public void printCorrectAnswer() {
        System.out.println("Die richtige Antwort war: " + correct);
    }

    public boolean checkAnswer(Player p1) {
        return p1.getAnswer() == correct;
    }

}
