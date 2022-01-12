import java.util.Random;

public class Joker {
    private final int function; //1 - 50:50, 2 - Second Chance, 3 - Telephone Joker / bestimmt, welcher Joker was macht
    private boolean used; //gibt true - Joker bereits verwendet bzw. false - Joker noch nicht verwendet
    private char random; //zufälliges char aus a, b, c, d

    //Konstruktor der Joker Klasse
    public Joker(int function) {
        this.function = function; //den Membervariablen werden ihre Werte zugewiesen
        this.used = false; //Joker sind am Anfang alle noch nicht verwendet
        this.random = '\0'; //Zufallsvariable ist noch leer
    }

    public boolean isUsed(){
        return used;
    } //gibt true zurück, wenn Joker verwendet wurde

    public static char getRandomChar() { //wählt aus den 4 Antwortmöglichkeiten einen Buchstaben aus; Rückgabewert ist char nicht int
        Random random = new Random();
        char randomChar = (char) (random.nextInt(4) + 'a'); //aus 4 aufeinanderfolgenden chars a, b, c, d ein char zufällig ziehen
        return randomChar;
    }

    //FiftyFifty Joker
    public void changeQuestion(Question q1) { //Methode, um falsche Antwortmöglichkeit zufällig zu verbergen
        random = getRandomChar();
        if (random != q1.getCorrect()) { //überprüfen, ob gezogenes char nicht gleich dem char der richtigen Antwort ist
            q1.changeHide(random, this); //dann hide auf true setzen bei gezogenem char
        } else {
            changeQuestion(q1); //wenn random == correctAnswer -> Methode nochmal ausführen
        }
    }

    /*
Wahrscheinlichkeiten für Telefonjoker
- Kategorie 1 - 5 - 90 Prozent
- Kategorie 6 - 7 - 85 Prozent
- Kategorie 8     - 80 Prozent
- Kategorie 9     - 77 Prozent
- Kategorie 10    - 75 Prozent
- Kategorie 11    - 73 Prozent
- Kategorie 12    - 70 Prozent
- Kategorie 13    - 66 Prozent
- Kategorie 14    - 61 Prozent
- Kategorie 15    - 55 Prozent
 */
    //TELEFONJOKER
    //je höher die Kategorie, desto geringer wird die Wahrscheinlichkeit, die richtige Antwort zu bekommen
    //zufällige Zahl zwischen 1 und 100 generieren
    //ist diese Zahl kleiner gleich der Prozentzahl, wird vom Telefonjoker die richtige Antwort gesagt
    //ist diese Zahl größer als die Prozentzahl, wird eine der 3 falschen Antworten gesagt (generateRandomWrongAnswer)
    //Teammitglieder einbinden als Telefonjoker

    public static int generateRandomNumber() { //Zahl zwischen 1 und 100 ziehen
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        return randomNumber;
    }

    public char generateRandomWrongAnswer(Question q1){ //zufällig eine der 3 falschen Antworten ziehen
        random = getRandomChar();
        if (random != q1.getCorrect()) { //überprüfen ob gezogenes char mit correctAnswer nicht übereinstimmt
            random = getRandomChar(); //random ist also die Antwort des Telefonjokers
        } else {
            generateRandomWrongAnswer(q1); //wenn random == correctAnswer -> Methode nochmal ausführen
        }
        return random;
    }

    public void telephoneHelpMe(Question q1){
        char givenAnswer; //Antwortmöglichkeit, die der Telefonjoker als Antwort gibt
        if(q1.getCategory() < 6){ //Kategorie 1-5
            if(generateRandomNumber() < 91){ //90% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Susi: 'Ich bin mir zu 100 Prozent sicher, dass " + givenAnswer + " richtig ist.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Susi: 'Ich bin mir nicht sicher. Ich glaube die Antwort ist " + givenAnswer + ".'");
            }
        }

        else if(q1.getCategory() < 8){ //Kategorie 6-7
            if(generateRandomNumber() < 86){ //85% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Chrisi: 'Das ist einfach! Die richtige Antwort ist " + givenAnswer + ".'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Chrisi: 'Ganz schön schwierig für den Anfang! " + givenAnswer + " könnte stimmen.'");
            }
        }

        else if(q1.getCategory() == 8){ //Kategorie 8
            if(generateRandomNumber() < 81){ //80% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Sophia: 'Wenn mich nicht alles täuscht, sollte " + givenAnswer + " stimmen.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Sophia: 'Wissen tu ich es nicht, aber ich tendiere zu " + givenAnswer + ".'");
            }
        }

        else if(q1.getCategory() == 9){ //Kategorie 9
            if(generateRandomNumber() < 78){ //77% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Taha: 'Das hab ich letztens erst wieder gelesen! Antwort " + givenAnswer + " ist richtig.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Taha: 'Das ist eine gute Frage... Ich würde " + givenAnswer + " nehmen - ohne Garantie!'");
            }
        }

        else if(q1.getCategory() == 10){ //Kategorie 10
            if(generateRandomNumber() < 76){ //75% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Sara: 'Ich glaube, dass " + givenAnswer + " die richtige Antwort ist.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Sara: 'Ich weiß es leider nicht. Tippen würde ich aber auf " + givenAnswer + ".'");
            }
        }

        else if(q1.getCategory() == 11){ //Kategorie 11
            if(generateRandomNumber() < 74){ //73% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Susi: 'Mit hoher Wahrscheinlichkeit ist " + givenAnswer + " die richtige Antwort.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Susi: 'Schwierige Frage. Ich bin mir nicht sehr sicher, würde aber " + givenAnswer + " nehmen.'");
            }
        }

        else if(q1.getCategory() == 12){ //Kategorie 12
            if(generateRandomNumber() < 71){ //70% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Chrisi: 'Ich glaube, dass die richtige Antwort " + givenAnswer + " ist.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Chrisi: '" + givenAnswer + " wäre mein Tipp. Aber wissen tu ich es leider nicht.'");
            }
        }

        else if(q1.getCategory() == 13){ //Kategorie 13
            if(generateRandomNumber() < 67){ //66% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Sophia: 'Ich bin mir recht sicher, dass " + givenAnswer + " richtig ist.");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Sophia: '" + givenAnswer + " könnte die richtige Antwort sein. Ich bin mir aber leider nicht sicher.'");
            }
        }

        else if(q1.getCategory() == 14){ //Kategorie 14
            if(generateRandomNumber() < 62){ //61% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Taha: 'Eine echt schwierige Frage, aber die richtige Antwort sollte " + givenAnswer + " sein.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Taha: 'Ich weiß es nicht, würde aber auf " + givenAnswer + " tippen.'");
            }
        }

        else if(q1.getCategory() == 15){ //Kategorie 15
            if(generateRandomNumber() < 56){ //55% Wahrscheinlichkeit auf richtige Antwort
                givenAnswer = q1.getCorrect();
                System.out.println("Sara: 'Ich bin mir nicht ganz sicher, glaube aber, dass Antwort " + givenAnswer + " richtig ist.'");
            }
            else{
                givenAnswer = generateRandomWrongAnswer(q1);
                System.out.println("Sara: 'Ich tippe zwar auf " + givenAnswer + ", würde aber an deiner Stelle lieber das Geld nehmen.'");
            }
        }
    }

    //Funktionen der verschiedenen Joker abrufen
    //Methode, um Joker zu verwenden
    public void useJoker(Question q1) {
        switch (function) {
            case 1: //FiftyFifty
                changeQuestion(q1); //verbirgt eine falsche Antwortmöglichkeit
                changeQuestion(q1); //verbirgt eine weitere falsche Antwortmöglichkeit
                this.used = true; //used auf true setzen - wichtig, um Joker nur einmal verwenden zu können
                q1.printQuestion(); //gibt Frage ohne 2 falschen Antwortmöglichkeiten aus
                break;
            case 2: //Second Chance (2 mal raten bei einer Frage)
                q1.changeSecondChance(); //ändert secondChance auf true, ist somit aktiv
                this.used = true;
                break;
            case 3: //Telephone
                telephoneHelpMe(q1); //ruft Telefonjokermethode auf (oben genauer erklärt)
                this.used = true;
                break;
        }
    }
}
