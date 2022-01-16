package ConsoleCode;
import com.google.gson.Gson;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

//Steuerung des Spiels

public class Game {
    //Instanzvariable und Konstruktor zum Einlesen der Eingabe von der Konsole
    private final Scanner scanIn;

    public Game() {
        this.scanIn = new Scanner(System.in);
    }

    //Einbinden der Fragen mittels der Gson-Methode fromJson(), Rückgabe eines Question[]
    /*private Question[] createQuestions() throws IOException {
        Gson gson = new Gson();
        String dirPath = new File("").getAbsolutePath();
        dirPath += "\\questions.json";
        Question[] questions = gson.fromJson(new FileReader(dirPath), Question[].class);
        return questions;
    }

     */
    public Question[] createQuestions() throws IOException {
        Gson gson = new Gson();
        Question[] questions = gson.fromJson(new FileReader("/Users/susikloss/IdeaProjects/TA_WerWirdMillion/questions.json", StandardCharsets.UTF_8), Question[].class);
        return questions;
    }

    //Rückgabe einer ArrayList<Question>, die jeweils die Fragen einer bestimmten Kategorie enthält
    //die Fragen kommen aus dem Question[]
    public static ArrayList<Question> getQuestionCategory(Question[] questions, int category) {
        ArrayList<Question> questionsCategory = new ArrayList<>();
        for (int i = 0; i < questions.length; i++) {
            if (questions[i].getCategory() == category) {
                questionsCategory.add(questions[i]);
            }
        }
        return questionsCategory;
    }

    //Rückgabe einer zufällig ausgewählten Frage einer bestimmten Kategorie aus der ArrayList<Question>
    public static Question getQuestionFromCategory(ArrayList<Question> questionsCategory) {
        Random random = new Random();
        int randomInt = random.nextInt(questionsCategory.size());
        Question q1 = questionsCategory.get(randomInt);
        return q1;
    }

    /*Überprüfung, ob der gewählte Joker bereits verwendet worden ist, jeder Joker darf nur 1x ausgewählt werden
    Wurde ein bereits verwendeter Joker nochmals gewählt, darf man nochmals einen oder keinen Joker wählen
     */
    public static void checkJoker(Player p1, Joker fiftyFifty, Joker secondChance, Joker help, Question q1, Scanner scanIn) {
        if (p1.getAnswer() != 'k') {
            switch (p1.getAnswer()) {
                case 'f':
                    if (!fiftyFifty.getUsed()) {
                        fiftyFifty.useJoker(q1);
                    } else {
                        System.out.println("Du hast diesen Joker bereits verwendet\n");
                        p1.chooseJoker(scanIn);
                        checkJoker(p1, fiftyFifty, secondChance, help, q1, scanIn);
                    }
                    break;
                case 's':
                    if (!secondChance.getUsed()) {
                        secondChance.useJoker(q1);
                    } else {
                        System.out.println("Du hast diesen Joker bereits verwendet\n");
                        p1.chooseJoker(scanIn);
                        checkJoker(p1, fiftyFifty, secondChance, help, q1, scanIn);
                    }
                    break;
                case 'h':
                    if (!help.getUsed()) {
                        help.useJoker(q1);
                    } else {
                        System.out.println("Du hast diesen Joker bereits verwendet\n");
                        p1.chooseJoker(scanIn);
                        checkJoker(p1, fiftyFifty, secondChance, help, q1, scanIn);
                    }
                    break;
                default:
                    System.out.println("Es ist ein Fehler passiert");
                    p1.chooseJoker(scanIn);
                    checkJoker(p1, fiftyFifty, secondChance, help, q1, scanIn);
            }
        }
    }

    //Steuerung des Spiels
    private void playGame(Player p1, Question[] questions) {
        Joker fiftyFifty = new Joker(1);
        Joker secondChance = new Joker(2);
        Joker help = new Joker(3);
        ArrayList<Question> gameQuestion;

        System.out.println("Willkommen bei Team Alphas 'Wer Wird Millionär'!");

        //Frage der passenden Kategorie wird angezeigt, solange Kategorie 16 noch nicht gewonnen worden ist (s. while)
        do {
            gameQuestion = getQuestionCategory(questions, p1.getCategory());
            Question q1 = getQuestionFromCategory(gameQuestion);
            q1.printQuestion();

            //Auswahl des Jokers
            p1.chooseJoker(scanIn);
            checkJoker(p1, fiftyFifty, secondChance, help, q1, scanIn);

            /* Ab Kategorie 2 kann man das Spiel beenden und erhält den bisher gewonnenen Geldbetrag
            Wegen Konsolenanwendung kann Spieler nur zu einem bestimmen Zeitpunkt Spiel beenden
             */
            if (p1.getCategory() > 1 && p1.leave(scanIn)) {
                q1.printCorrectAnswer();
                p1.printMoneyWon(true);
                break;
            }

            //Auswahl einer Antwort, Überprüfung auf Richtigkeit
            p1.makeGuess(scanIn);

            if (!q1.checkAnswer(p1)) {               //Antwort ist falsch
                if (q1.getSecondChance()) {          //SecondChance-Joker war gewählt worden, man darf nochmal wählen
                    System.out.println();
                    System.out.println("Das war leider nicht richtig. Probier's nochmal!");
                    p1.makeGuess(scanIn);
                    if (!q1.checkAnswer(p1)) {      //Wieder falsche Antwort, verloren
                        q1.printCorrectAnswer();
                        p1.printMoneyWon(false);
                        break;
                    } else {                        //Zweite Antwort war richtig
                        p1.raiseCategory();
                        System.out.println();
                        System.out.println("Bravo du hast die Frage richtig beantwortet!");
                        p1.printMoney();
                    }
                } else {                            //Antwort ist falsch ohne SecondChance Joker, verloren

                    q1.printCorrectAnswer();
                    p1.printMoneyWon(false);
                    break;
                }
            } else {                                //Antwort ist richtig, nächste Kategorie
                p1.raiseCategory();
                System.out.println();
                System.out.println("Bravo du hast die Frage richtig beantwortet!");
                p1.printMoney();
            }
        } while (p1.getCategory() != 16);

        if (p1.getCategory() == 16) {
            System.out.println();
            System.out.println("Gratulation du hast 1.000.000€ gewonnen. Du bist ein Millionär");
        }

        System.out.println("Das Spiel ist zu Ende!");
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
