public class Question {
    private String question;
    private int category;
    private String theme;
    private String a;
    private String b;
    private String c;
    private String d;
    private char correct;
    private boolean hideA;
    private boolean hideB;
    private boolean hideC;
    private boolean hideD;//unterschieden zwischen gezeigt/unsichtbar (50:50)

    public Question(String question, int category, String theme, String a, String b, String c, String d, char  correct, boolean hideA, boolean hideB, boolean hideC, boolean hideD){
        this.question = question;
        this.category = category;
        this.theme = theme;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;

        this.hideA = hideA;
        this.hideB = hideB;
        this.hideC = hideC;
        this.hideD = hideD;
    }

    //Getter
    public String getQuestion(){
        return question;
    }

    public char getCorrect(){
        return correct;
    }


    public void changeHide(char answer, Joker fiftyFifty){
        switch (answer){
            case 'a':
                if (!hideA){
                    hideA = true;
                } else {
                    fiftyFifty.changeQuestion(this);
                }
                break;
            case 'b':
                if (!hideB){
                    hideB = true;
                } else {
                    fiftyFifty.changeQuestion(this);
                }
                break;
            case 'c':
                if (!hideC){
                    hideC = true;
                } else {
                    fiftyFifty.changeQuestion(this);
                }
                break;
            case 'd':
                if (!hideD){
                    hideD = true;
                } else {
                    fiftyFifty.changeQuestion(this);
                }
                break;
            default:
                break;
        }
    }
    public void printQuestion(){
        System.out.println(question);
        if (!hideA){
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

    public void printCorrectAnswer(){
        System.out.println("Die richtige Antwort war: " + correct);
    }

    public boolean checkAnswer(Player p1){
        if (p1.getAnswer() == correct){
            return  true;
        } else {
            return false;
        }
    }

}
