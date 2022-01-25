package ConsoleCode;

import java.util.Random;

public class Joker {
    private boolean used; //returns true - ConsoleCode.Joker already or false - ConsoleCode.Joker not used yet
    private char random; //random char out of a, b, c or d

    //constructor for ConsoleCode.Joker class
    public Joker() {
        this.used = false; //ConsoleCode.Joker all set to 'not used' at start of the game
        this.random = '\0'; //random variable empty
    }

    public boolean getUsed() {
        return used; //returns true if ConsoleCode.Joker already used
    }

    public void changeUsed() {
        used = true;
    }

    //Second Chance and changeHide in Question class

    public static char getRandomChar() { //choose one char from 4 answer possibilities; return value is char (not int)
        Random random = new Random();
        char randomChar = (char) (random.nextInt(4) + 'a');
        // a - 97 + [range: 0 - 3]
        return randomChar;
    }

    //FiftyFifty ConsoleCode.Joker
    public void changeQuestion(Question q1) { //method to hide one wrong answer
        random = getRandomChar();
        if (random != q1.getCorrect()) { //check if random char does not equal correct answer
            q1.changeHide(random, this); //hide is set true and changeHide from Question class is called
        } else {
            changeQuestion(q1); //if random == correctAnswer -> run method again
        }
    }

    public void useFiftyFiftyJoker(Question q1) {
        changeQuestion(q1); //hides one wrong answer
        changeQuestion(q1); //hides another wrong answer
        this.used = true; //to not be used again!
    }

    /*
Probabilities for Telephone Joker
- Category 1 - 5 - 90 %
- Category 6 - 7 - 85 %
- Category 8     - 80 %
- Category 9     - 77 %
- Category 10    - 75 %
- Category 11    - 73 %
- Category 12    - 70 %
- Category 13    - 66 %
- Category 14    - 61 %
- Category 15    - 55 %
 */
    //TELEPHONE JOKER
    //the higher the category the lower the probability that the Joker tells you the correct answer
    //generate random int between 1 and 100
    //int <= probability -> givenAnswer == correct answer
    //int >= probability -> givenAnswer != correct answer (generateRandomWrongAnswer)
    //team members are implemented as telephone Jokers

    public static int generateRandomNumber() { //generate random int between 1 and 100
        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1;
        return randomNumber;
    }

    public char generateRandomWrongAnswer(Question q1) { //choose one random char from 3 wrong answers
        random = getRandomChar();
        if (random != q1.getCorrect()) { //check if char does not equal correctAnswer
            random = getRandomChar(); //random is answer from telephone Joker
        } else {
            generateRandomWrongAnswer(q1); //if random == correctAnswer -> run method again
        }
        return random;
    }

    public String telephoneHelpMe(Question q1) {
        String answer = "";
        char givenAnswer; //answer given from telephone Joker
        if (q1.getCategory() < 6) { //category 1-5
            if (generateRandomNumber() < 91) { //90% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Susi: 'Ich bin mir zu 100 Prozent sicher, dass " + givenAnswer + " richtig ist.'";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Susi: 'Ich bin mir nicht sicher. Ich glaube die Antwort ist " + givenAnswer + ".'";
            }
        } else if (q1.getCategory() < 8) { //category 6-7
            if (generateRandomNumber() < 86) { //85% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Chrisi: 'Das ist einfach! Die richtige Antwort ist " + givenAnswer + ".'";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Chrisi: 'Ganz sch\u00f6n schwierig f\u00fcr den Anfang! " + givenAnswer + " k\u00f6nnte stimmen.'";
            }
        } else if (q1.getCategory() == 8) { //category 8
            if (generateRandomNumber() < 81) { //80% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Sophia: 'Wenn mich nicht alles t\u00e4uscht, sollte " + givenAnswer + " stimmen.'";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Sophia: 'Wissen tu ich es nicht, aber ich tendiere zu " + givenAnswer + ".'";
            }
        } else if (q1.getCategory() == 9) { //category 9
            if (generateRandomNumber() < 78) { //77% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Taha: 'Das hab ich letztens erst wieder gelesen! Antwort " + givenAnswer + " ist richtig.'";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Taha: 'Das ist eine gute Frage... Ich w\u00fcrde " + givenAnswer + " nehmen - ohne Garantie!'";
            }
        } else if (q1.getCategory() == 10) { //category 10
            if (generateRandomNumber() < 76) { //75% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Sara: 'Ich glaube, dass " + givenAnswer + " die richtige Antwort ist.'";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Sara: 'Ich wei\u00df es leider nicht. Tippen w\u00fcrde ich aber auf " + givenAnswer + ".'";
            }
        } else if (q1.getCategory() == 11) { //category 11
            if (generateRandomNumber() < 74) { //73% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Susi: 'Mit hoher Wahrscheinlichkeit ist " + givenAnswer + " die richtige Antwort.'";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Susi: 'Schwierige Frage. Ich bin mir nicht sehr sicher, w\u00fcrde aber " + givenAnswer + " nehmen.'";
            }
        } else if (q1.getCategory() == 12) { //category 12
            if (generateRandomNumber() < 71) { //70% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Chrisi: 'Ich glaube, dass die richtige Antwort " + givenAnswer + " ist.'";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Chrisi: '" + givenAnswer + " w\u00e4re mein Tipp. Aber wissen tu ich es leider nicht.'";
            }
        } else if (q1.getCategory() == 13) { //category 13
            if (generateRandomNumber() < 67) { //66% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Sophia: 'Ich bin mir recht sicher, dass " + givenAnswer + " richtig ist.";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Sophia: '" + givenAnswer + " k\u00f6nnte die richtige Antwort sein. Ich bin mir aber leider nicht sicher.'";
            }
        } else if (q1.getCategory() == 14) { //category 14
            if (generateRandomNumber() < 62) { //61% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Taha: 'Eine echt schwierige Frage, aber die richtige Antwort sollte " + givenAnswer + " sein.'";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Taha: 'Ich wei\u00df es nicht, w\u00fcrde aber auf " + givenAnswer + " tippen.'";
            }
        } else if (q1.getCategory() == 15) { //category 15
            if (generateRandomNumber() < 56) { //55% probability for right answer
                givenAnswer = q1.getCorrect();
                answer = "Sara: 'Ich bin mir nicht ganz sicher, glaube aber, dass Antwort " + givenAnswer + " richtig ist.'";
            } else {
                givenAnswer = generateRandomWrongAnswer(q1);
                answer = "Sara: 'Ich tippe zwar auf " + givenAnswer + ", w\u00fcrde aber an deiner Stelle lieber das Geld nehmen.'";
            }
        }
        this.used = true; //used set to true
        return answer;
    }
}
