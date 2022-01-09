import java.util.Random;

public class Joker {
    private final int function;
    private boolean used;
    private char random;

    public Joker(int function) {
        this.function = function;
        this.used = false;
        this.random = '\0';
    }

    public boolean isUsed(){
        return used;
    }

    public static int getRandomChar() {
        Random random = new Random();
        String setOfChars = "abcd";

        int randomInt = random.nextInt(setOfChars.length());
        char randomChar = setOfChars.charAt(randomInt);
        return randomChar;
    }

    public void changeQuestion(Question q1) {
        random = (char) getRandomChar();
        if (random != q1.getCorrect()) {
            q1.changeHide(random, this);
        } else {
            changeQuestion(q1);
        }
    }

    /*
Telefonjoker

- je niedriger die Kategorie, desto wahrscheinlicher ist es, die Frage richtig zu beantworten
- Kategorie 1 - 5 - 90 Prozent Wahrscheinlichkeit "Susi: 'Ich bin mir sicher, dass " + givenAnswer + " richtig ist.'"
- Kategorie 6 - 7 - 85 Prozent Wahrscheinlichkeit "Chrisi: 'Das ist doch einfach! Die richtige Antwort ist " + givenAnswer + ".'"
- Kategorie 8 -     80 Prozent Wahrscheinlichkeit "Sophia: 'Wenn mich nicht alles täuscht, sollte " + givenAnswer + " stimmen.'"
- Kategorie 9 -  77 Prozent "Taha: 'Das hab ich letztens erst wieder gelesen! " + givenAnswer + " ist richtig.'"
- Kategorie 10 - 75 Prozent "Sara: 'Ich glaube, dass " + givenAnswer + " die richtige Antwort ist.'"
- Kategorie 11 - 73 Prozent "Susi: 'Mit hoher Wahrscheinlichkeit ist " + givenAnswer + " die richtige Antwort.'"
- Kategorie 12 - 70 Prozent "Chrisi: 'Ich glaube, dass die richtige Antwort " + givenAnswer + " ist.'"
- Kategorie 13 - 66 Prozent "Sophia: 'Ich bin mir recht sicher, dass " + givenAnswer + " richtig ist."
- Kategorie 14 - 61 Prozent "Taha: 'Eine echt schwierige Frage, aber die richtige Antwort sollte " + givenAnswer + " sein.'"
- Kategorie 15 - 55 Prozent "Sara: 'Ich bin mir nicht ganz sicher, glaube aber, dass Antwort " + givenAnswer + " richtig ist.'"



 */
    public static int generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        return randomNumber;
    }

    public char generateRandomWrongAnswer(Question q1){
        Random r = new Random();
        char randomChar = 'd';
        while(randomChar == q1.getCorrect()) {
            randomChar = (char) (r.nextInt(4) + 'a');
        }
        return randomChar;
    }

    public void telephoneHelpMe(Question q1){
        char givenAnswer;
        if(q1.getCategory() < 6){
            if(generateRandomNumber() < 91){
                givenAnswer = q1.getCorrect();
                System.out.println("Susi: 'Ich bin mir zu 100 Prozent sicher, dass " + givenAnswer + " richtig ist.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Susi: 'Ich bin mir nicht sicher. Ich glaube die Antwort ist " + givenAnswer + ".'");
            }
        }

        else if(q1.getCategory() < 8){
            if(generateRandomNumber() < 86){
                givenAnswer = q1.getCorrect();
                System.out.println("Chrisi: 'Das ist einfach! Die richtige Antwort ist " + givenAnswer + ".'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Chrisi: 'Ganz schön schwierig für den Anfang! " + givenAnswer + " könnte stimmen.'");
            }
        }

        else if(q1.getCategory() == 8){
            if(generateRandomNumber() < 81){
                givenAnswer = q1.getCorrect();
                System.out.println("Sophia: 'Wenn mich nicht alles täuscht, sollte " + givenAnswer + " stimmen.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Sophia: 'Wissen tu ich es nicht, aber ich tendiere zu " + givenAnswer + ".'");
            }
        }

        else if(q1.getCategory() == 9){
            if(generateRandomNumber() < 78){
                givenAnswer = q1.getCorrect();
                System.out.println("Taha: 'Das hab ich letztens erst wieder gelesen! Antwort " + givenAnswer + " ist richtig.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Taha: 'Das ist eine gute Frage... Ich würde " + givenAnswer + " nehmen - ohne Garantie!'");
            }
        }

        else if(q1.getCategory() == 10){
            if(generateRandomNumber() < 76){
                givenAnswer = q1.getCorrect();
                System.out.println("Sara: 'Ich glaube, dass " + givenAnswer + " die richtige Antwort ist.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Sara: 'Ich weiß es leider nicht. Tippen würde ich aber auf " + givenAnswer + ".'");
            }
        }

        else if(q1.getCategory() == 11){
            if(generateRandomNumber() < 74){
                givenAnswer = q1.getCorrect();
                System.out.println("Susi: 'Mit hoher Wahrscheinlichkeit ist " + givenAnswer + " die richtige Antwort.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Susi: 'Schwierige Frage. Ich bin mir nicht sehr sicher, würde aber " + givenAnswer + " nehmen.'");
            }
        }

        else if(q1.getCategory() == 12){
            if(generateRandomNumber() < 71){
                givenAnswer = q1.getCorrect();
                System.out.println("Chrisi: 'Ich glaube, dass die richtige Antwort " + givenAnswer + " ist.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Chrisi: '" + givenAnswer + " wäre mein Tipp. Aber wissen tu ich es leider nicht.'");
            }
        }

        else if(q1.getCategory() == 13){
            if(generateRandomNumber() < 67){
                givenAnswer = q1.getCorrect();
                System.out.println("Sophia: 'Ich bin mir recht sicher, dass " + givenAnswer + " richtig ist.");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Sophia: '" + givenAnswer + " könnte die richtige Antwort sein. Ich bin mir aber leider nicht sicher.'");
            }
        }

        else if(q1.getCategory() == 14){
            if(generateRandomNumber() < 62){
                givenAnswer = q1.getCorrect();
                System.out.println("Taha: 'Eine echt schwierige Frage, aber die richtige Antwort sollte " + givenAnswer + " sein.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Taha: 'Ich weiß es nicht, würde aber auf " + givenAnswer + " tippen.'");
            }
        }

        else if(q1.getCategory() == 15){
            if(generateRandomNumber() < 56){
                givenAnswer = q1.getCorrect();
                System.out.println("Sara: 'Ich bin mir nicht ganz sicher, glaube aber, dass Antwort " + givenAnswer + " richtig ist.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Sara: 'Ich tippe zwar auf " + givenAnswer + ", würde aber an deiner Stelle lieber das Geld nehmen.'");
            }
        }
    }

    public void useJoker(Question q1) {
        switch (function) {
            case 1:
                changeQuestion(q1);
                changeQuestion(q1);
                this.used = true;
                q1.printQuestion();
                break;
            case 2:
                q1.changeSecondChance();
                this.used = true;
                break;
            case 3:
                telephoneHelpMe(q1);
                this.used = true;
                break;
        }
        //Funktionen der verschiedenen Joker
    }
}
