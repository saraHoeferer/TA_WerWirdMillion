import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MainController {
    @FXML
    private Button buttonA;

    @FXML
    private Button buttonB;

    @FXML
    private Button buttonC;

    @FXML
    private Button buttonD;

    @FXML
    private Label labelQ;

    @FXML
    private Button buttonFiftyFifty;

    @FXML
    private Button buttonSecondChance;

    @FXML
    private Button buttonTelephone;


    private Question[] questions;
    private int currentCategory;
    private Question currentQuestion;

    @FXML
    private void initialize() {
        try {
            questions = createQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentCategory = 1;
        makeNextQuestion();

//        Question q1 = new Question("Da es keinen wolligen Pelz trägt, hat es wenig Sinn, wenn ich das ...?", 5, "Hummer zange", "Fischer messer", "Geflügel schere","Eier löffel", 'c');
//        labelQ.setText(q1.getQuestion());
//        buttonA.setText("a: " + q1.getA());
//        buttonB.setText("b: " + q1.getB());
//        buttonC.setText("c: " + q1.getC());
//        buttonD.setText("d: " + q1.getD());
    }

    @FXML
    private void actionPerformed(ActionEvent e){
        String buttonName = ((Button)e.getSource()).getId();
        char fieldName = Character.toLowerCase(buttonName.charAt(buttonName.length()-1));
        if(fieldName == currentQuestion.getCorrect()){
            currentCategory++;
            makeNextQuestion();
        }
    }

    @FXML
    void changeColor(MouseEvent event) {
        ((Button)event.getSource()).setStyle("-fx-background-color: #d1fffa");
    }

    @FXML
    void changeColorBack(MouseEvent event) {
        ((Button)event.getSource()).setStyle("-fx-background-color: #a2fff4");
    }

    private void makeNextQuestion() {
        currentQuestion = getQuestionFromCategory(getQuestionCategory(questions, currentCategory));
        labelQ.setText(currentQuestion.getQuestion());
        buttonA.setText("A: " + currentQuestion.getA());
        buttonB.setText("B: " + currentQuestion.getB());
        buttonC.setText("C: " + currentQuestion.getC());
        buttonD.setText("D: " + currentQuestion.getD());
    }

    private Question[] createQuestions() throws IOException {
        Gson gson = new Gson();
        String dirPath = new File("").getAbsolutePath();
        dirPath += "\\questions.json";

        Question[] questions = gson.fromJson(new FileReader(dirPath, StandardCharsets.UTF_8), Question[].class);
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

    //use 50 : 50 Joker
    //

    @FXML
    void useFiftyFifty(ActionEvent e) {
        char random = getRandomChar();
        for(int i = 0; i <= 2; i++)
            if (random == currentQuestion.getCorrect()) {
                random = getRandomChar();
                i--;
            } else{
                switch(random){
                    case 'a':
                        buttonA.setText("A: ");
                        break;
                    case 'b':
                        buttonB.setText("B: ");
                        break;
                    case 'c':
                        buttonC.setText("C: ");
                        break;
                    case 'd':
                        buttonD.setText("D: ");
                        break;
                }

            }
    }

    public static char getRandomChar() { //wählt aus den 4 Antwortmöglichkeiten einen Buchstaben aus; Rückgabewert ist char nicht int
        Random random = new Random();
        char randomChar = (char) (random.nextInt(4) + 'a'); //aus 4 aufeinanderfolgenden chars a, b, c, d ein char zufällig ziehen
        return randomChar;
    }






}








