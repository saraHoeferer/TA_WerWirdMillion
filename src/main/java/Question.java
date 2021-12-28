public class Question {
    private String question;
    private int category;
    private String theme;
    private String a;
    private String b;
    private String c;
    private String d;
    private char correct;
    private boolean hide; //unterschieden zwischen gezeigt/unsichtbar (50:50)

    public Question(String question, int category, String theme, String a, String b, String c, String d, char  correct, boolean hide){
        this.question = question;
        this.category = category;
        this.theme = theme;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.correct = correct;
        this.hide = hide;
    }

    //Getter
    public String getQuestion(){
        return question;
    }

    public void printQuestion(){
        //Ausgabe der Frage
        //              Frage
        // Antwort 1            Antwort 2
        // Antwort 3            Antwort 4

        //Frage :
        //a:
        //b:
        //etc.
    }

    public void printCorrectAnswer(){
        //Ausgabe der korrekten Antworten bzw. richtig/falsch
    }

    public boolean checkAnswer(Player p1){
        if (p1.getAnswer() == correct){
            return  true;
        } else {
            return false;
        }
    }

}
