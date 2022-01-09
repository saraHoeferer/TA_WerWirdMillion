import com.google.gson.Gson;
import java.io.*;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private final Scanner scanIn;

    public Game() {
        this.scanIn = new Scanner(System.in);
    }

    private Question[] createQuestions() throws IOException {
        Gson gson = new Gson();
        Question[] questions = gson.fromJson(new FileReader("C:\\Users\\sarah\\IdeaProjects\\questions.json"), Question[].class); //Auf Pfad achten
        return questions;
    }

    public static Question[] getQuestionCategory(Question[] questions, int category){
        int cnt = 0;
        //Länge des Array herauszufinden mithilfe For-Schleife
        for(int i = 0; i < questions.length; i++){
            if(questions[i].getCategory() == category){
               cnt++;
            }
        }
        Question[] questionsCategory = new Question[cnt];
        cnt = 0;
        for(int i = 0; i < questions.length; i++){
            if(questions[i].getCategory() == category){
                questionsCategory[cnt] = questions[i];
                cnt++;
            }
        }
        return questionsCategory;
    }

    public static Question getQuestionFromCategory(Question[] questionsCategory){
        Random random = new Random();
        int randomInt = random.nextInt(questionsCategory.length);
        Question q1 = questionsCategory[randomInt];
        return q1;
    }

    public static void checkJoker(Player p1, Joker fiftyFifty, Joker secondChance, Joker help, Question q1, Scanner scanIn) {
        if (p1.getAnswer() != 'k') {
            switch (p1.getAnswer()) {
                case 'f':
                    if(!fiftyFifty.isUsed()) {
                        fiftyFifty.useJoker(q1);
                    } else {
                        System.out.println("Du hast diesen Joker bereits verwendet");
                        p1.chooseJoker(scanIn);
                    }
                    break;
                case 's':
                    if(!secondChance.isUsed()) {
                        secondChance.useJoker(q1);
                    } else {
                        System.out.println("Du hast diesen Joker bereits verwendet");
                        p1.chooseJoker(scanIn);
                    }
                    break;
                case 'h':
                    if(!help.isUsed()) {
                        help.useJoker(q1);
                    } else {
                        System.out.println("Du hast diesen Joker bereits verwendet");
                        p1.chooseJoker(scanIn);
                    }
                    break;
                default:
                    System.out.println("Es ist ein Fehler passiert");
                    p1.chooseJoker(scanIn);
            }
        }
    }

    private void playGame(Player p1, Question[] questions) {
        Joker fiftyFifty = new Joker(1);
        Joker secondChance = new Joker(2);
        Joker help = new Joker(3);
        Question[] gameQuestion;

        //Spielablauf
        do {
            gameQuestion = getQuestionCategory(questions, p1.getKategorie());
            Question q1 = getQuestionFromCategory(gameQuestion);
            q1.printQuestion();

            p1.chooseJoker(scanIn);
            checkJoker(p1, fiftyFifty, secondChance, help, q1, scanIn);

            //Wegen Konsolenanwendung kann Spieler nur zu einem bestimmen Zeitpunkt Spiel beenden
            if (p1.getKategorie() > 1) {
                if (p1.leave(scanIn)) {
                    p1.printMoneyWon(true);
                    break;
                }
            }
            p1.makeGuess(scanIn);

            if (!q1.checkAnswer(p1)){
                if (q1.getSecondChance()){
                    System.out.println("Das war leider nicht richtig. Probier's nochmal!");
                    p1.makeGuess(scanIn);
                    if (!q1.checkAnswer(p1)) {
                        q1.printCorrectAnswer();
                        p1.printMoneyWon(false);
                        break;
                    } else {
                        p1.raiseCategory();
                        System.out.println("Bravo du hast die Frage richtig beantwortet!");
                        p1.printMoney();
                    }
                } else {
                    q1.printCorrectAnswer();
                    p1.printMoneyWon(false);
                    break;
                }
            } else {
                p1.raiseCategory();
                System.out.println("Bravo du hast die Frage richtig beantwortet!");
                p1.printMoney();
            }
        } while (p1.getKategorie() != 16);
        if (p1.getKategorie() == 16){
            System.out.println("Gratulation du hast 1.000.000€ gewonnen. Du bist ein Millionär");
        }
    }

    public static void main(String[] args) throws IOException {
        //Initialisieren
        Game g1 = new Game();
        Question[] questions = g1.createQuestions();
        Player p1 = new Player();

        //Spiel aufrufen
        g1.playGame(p1, questions);
    }

}
