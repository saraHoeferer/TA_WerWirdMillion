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
        //Initialisieren der Objekte --> main
        //Question q1 = new Question();
        Joker fiftyFifty = new Joker(1);
        Joker secondChance =  new Joker(2);
        Joker help = new Joker(3);

        //Spielablauf
        while (playing) {
            p1.makeGuess(scanIn);
            p1.chooseJoker(scanIn);
            //checkJoker(p1, fiftyFifty, secondChance, help, q1, scanIn);
            p1.leave(scanIn);
            p1.printMoney();
            playing = false;
        }
        //Spielablauf
        //Frage ausgeben --> leave/Joker?/leave --> Spieler Guess --> richtig/falsch? --> weiter/gewonnen oder verloren
        //Ausgabe, ob gewonnen oder verloren
    }

    public static void main (String[] args){
        //Initialisieren
        Game g1 = new Game();
        Player p1 = new Player("test");

        //Spiel aufrufen
        g1.playGame(p1);
    }

}
