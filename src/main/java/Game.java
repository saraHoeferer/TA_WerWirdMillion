import java.util.Random;
import java.util.Scanner;

public class Game {
    private static boolean playing; //checken ob Spiel gespielt wird
    private int save; //Sicherheitsstufe vom Spieler
    private Scanner scanIn;

    public Game (){
        this.playing = true;
        this.save = 0;
        this.scanIn = new Scanner(System.in);
    }

    public void createQuestions(){
        //Array mit Fragen
    }

    public static void checkJoker(Player p1, Joker fiftyFifty, Joker secondChance, Joker help, Question q1, Scanner scanIn){
        if (p1.getAnswer() != 'k'){
            switch(p1.getAnswer()){
                case 'f':
                    fiftyFifty.useJoker(q1);
                    break;
                case 's':
                    secondChance.useJoker(q1);
                    break;
                case 'h':
                    help.useJoker(q1);
                    break;
                default:
                    System.out.println("Es ist ein Fehler passiert");
                    p1.chooseJoker(scanIn);
            }
        }
    }

    public void playGame(Player p1){
        int cnt = 0;
        //Initialisieren der Objekte --> main
        Question q1 = new Question("In den Trockengebieten des vorderen Orients und Nordafirkas leben die ...?", 3, "Biologie & Medizin", "Frechenhoppelhasen", "Wildenhüpfhamster", "Keckenhopshunde", "Wüstenspringmäuse", 'd', false, false, false, false);
        Joker fiftyFifty = new Joker(1);
        Joker secondChance =  new Joker(2);
        Joker help = new Joker(3);

        //Spielablauf
        do{
            q1.printQuestion();
            if (cnt > 0) {
                if(p1.leave(scanIn)){
                    p1.printMoney();
                    break;
                }
            }

            p1.chooseJoker(scanIn);
            checkJoker(p1, fiftyFifty, secondChance, help, q1, scanIn);
            p1.makeGuess(scanIn);

            if (!q1.checkAnswer(p1)){
                q1.printCorrectAnswer();
                p1.printMoneyWon();
                break;
            } else {
                p1.raiseMoney();
                System.out.println("Bravo du hast die Frage richtig beantwortet!");
                p1.printMoney();
            }
            cnt++;
        } while (playing);
    }

    public static void main (String[] args){
        //Initialisieren
        Game g1 = new Game();
        Player p1 = new Player("test");

        //Spiel aufrufen
        g1.playGame(p1);
    }

}
