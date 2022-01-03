import java.util.Random;

public class Joker {
    //ob eine eigene Klasse nötig
    private int function;
    private boolean used;
    private char random;

    public Joker(int function){
        this.function = function;
        this.used = false;
        this.random = '\0';
    }

    public static int getRandomChar(){
        Random random = new Random();
        String setOfChars = "abcd";

        int randomInt = random.nextInt(setOfChars.length());
        char randomchar = setOfChars.charAt(randomInt);
        return randomchar;
    }

    public void changeQuestion(Question q1){
        random = (char) getRandomChar();
        if (random != q1.getCorrect()) {
            q1.changeHide(random, this);
        } else {
            changeQuestion(q1);
        }
    }
    public void useJoker(Question q1){
        switch(function){
            case 1:
                changeQuestion(q1);
                changeQuestion(q1);
                q1.printQuestion();
                break;
            case 2:
                break;
            case 3:
                break;
        }
        //Funktionen der verschieden Joker
        //switch(function){}
    }
}
