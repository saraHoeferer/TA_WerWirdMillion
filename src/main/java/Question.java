public class Question {
    // instanz Variablen für die Question Klasse.
    // Private instanz Variablen kann nur in dieselbe klasse verwenden.
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
    // Konstruktor für unsere Frage Klasse.
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

    //Getter -- für die Frage.
    public String getQuestion() {
        return question;
    }
    //Getter -- für die Kategorie.
    public int getCategory() {
        return category;
    }
    // Getter a-d -- die Antwortmöglichkeiten für die frage.
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
    // Getter -- für die richtige antwort.
    public char getCorrect() {
        return correct;
    }
    // Getter -- für das second Chance Joker.
    public boolean getSecondChance(){ return secondChance; }

    //Methode um das Second Chance Joker zu nutzen.
    public void changeSecondChance(){
        this.secondChance = true;
    }
    // Methode um fifty-fifty zu nutzen, wenn man den fifty-fifty Joker aufruft. Die Methode ist zweimal aufruft werden. See Method 'useJoker'.
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
    //Methode um die Antwort zu ausdrucken. Diese Methode wirkt auch, um die zwei falscher Antworten zu ausblenden, wenn man fifty-fifty Joker aufruft. Siehe Methode useJoker.
    public void printQuestion() {
        System.out.println();
        System.out.println(question);
        if (!hideA) {
            System.out.println("a: " + a);
        } else {
            System.out.println("a:");
        }
        if (!hideB) {
            System.out.println("b: " + b);
        } else {
            System.out.println("b:");
        }
        if (!hideC) {
            System.out.println("c: " + c);
        } else {
            System.out.println("c:");
        }
        if (!hideD) {
            System.out.println("d: " + d);
        } else {
            System.out.println("d:");
        }
        System.out.println();
    }
    // Methode, wenn man falsch beantwortet, dann druckt die richtige Antwort aus.
    public void printCorrectAnswer() {
        System.out.println("Die richtige Antwort war: " + correct);
    }
    // Methode, die überprüft, ob die Spieler hat die fragen richtig beantwortet.
    public boolean checkAnswer(Player p1) {
        return p1.getAnswer() == correct;
    }

}
