import ConsoleCode.Game;
import ConsoleCode.Joker;
import ConsoleCode.Player;
import ConsoleCode.Question;
import com.google.gson.Gson;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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

    @FXML
    private Label money1;

    @FXML
    private Label money2;

    @FXML
    private Label money3;

    @FXML
    private Label money4;

    @FXML
    private Label money5;

    @FXML
    private Label money6;

    @FXML
    private Label money7;

    @FXML
    private Label money8;

    @FXML
    private Label money9;

    @FXML
    private Label money10;

    @FXML
    private Label money11;

    @FXML
    private Label money12;

    @FXML
    private Label money13;

    @FXML
    private Label money14;

    @FXML
    private Label money15;

    private Game currentGame = new Game();
    private Question[] questions;
    private Question currentQuestion;
    private Player currentPlayer = new Player();
    private Joker fiftyFifty = new Joker(1);
    private Joker telephone = new Joker(3);
    private PauseTransition delay = new PauseTransition(Duration.seconds(5));

    @FXML
    private void initialize() {
        try {
            questions = currentGame.createQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        makeNextQuestion();

//        ConsoleCode.Question q1 = new ConsoleCode.Question("Da es keinen wolligen Pelz trägt, hat es wenig Sinn, wenn ich das ...?", 5, "Hummer zange", "Fischer messer", "Geflügel schere","Eier löffel", 'c');
//        labelQ.setText(q1.getQuestion());
//        buttonA.setText("a: " + q1.getA());
//        buttonB.setText("b: " + q1.getB());
//        buttonC.setText("c: " + q1.getC());
//        buttonD.setText("d: " + q1.getD());
    }

    @FXML
    private void actionPerformed(ActionEvent e) throws InterruptedException {
        String buttonName = ((Button)e.getSource()).getId();
        char fieldName = Character.toLowerCase(buttonName.charAt(buttonName.length()-1));
        if(fieldName == currentQuestion.getCorrect()){
            changeCategory(currentPlayer.getCategory());
            ((Button)e.getSource()).setStyle("-fx-background-color: #9aff9a");
            currentPlayer.raiseCategory();
            makeNextQuestion();
        } else {
            ((Button)e.getSource()).setStyle("-fx-background-color: #EE3B3B");
            currentPlayer.switchMoney();
            labelQ.setText(currentPlayer.printMoneyWon(false));
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
        currentQuestion = currentGame.getQuestionFromCategory(currentGame.getQuestionCategory(questions, currentPlayer.getCategory()));
        labelQ.setText(currentQuestion.getQuestion());
        buttonA.setText("A: " + currentQuestion.getA());
        buttonB.setText("B: " + currentQuestion.getB());
        buttonC.setText("C: " + currentQuestion.getC());
        buttonD.setText("D: " + currentQuestion.getD());

    }

    private void printFiftyFiftyQuestion () {
        labelQ.setText(currentQuestion.getQuestion());
        if (!currentQuestion.isHideA()) {
            buttonA.setText("A: " + currentQuestion.getA());
        } else {
            buttonA.setText("A: ");
        }
        if (!currentQuestion.isHideB()) {
            buttonB.setText("B: " + currentQuestion.getB());
        }  else {
            buttonB.setText("B: ");
        }
        if (!currentQuestion.isHideC()) {
            buttonC.setText("C: " + currentQuestion.getC());
        }  else {
            buttonC.setText("C: ");
        }
        if (!currentQuestion.isHideD()) {
            buttonD.setText("D: " + currentQuestion.getD());
        }  else {
            buttonD.setText("D: ");
        }
    }

    //use 50 : 50 ConsoleCode.Joker
    //
    @FXML
    void useFiftyFifty(ActionEvent e) {
        if (!fiftyFifty.getUsed()) {
            fiftyFifty.useJoker(currentQuestion);
            printFiftyFiftyQuestion();
        }
    }

    @FXML
    void useTelephone(ActionEvent e){
        if (!telephone.getUsed()){
            labelQ.setText(telephone.telephoneHelpMe(currentQuestion));
        }
    }

    @FXML
    void changeCategory(int category){
        if (category == 1){
            money1.setStyle("-fx-background-color: #9aff9a");
        } else {
            money1.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 2){
            money2.setStyle("-fx-background-color: #9aff9a");
        } else {
            money2.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 3){
            money3.setStyle("-fx-background-color: #9aff9a");
        } else {
            money3.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 4){
            money4.setStyle("-fx-background-color: #9aff9a");
        } else {
            money4.setStyle("-fx-background-color: #ffffff;");
        }

       if (category == 5){
            money5.setStyle("-fx-background-color: #9aff9a");
        } else {
            money5.setStyle("-fx-background-color: #DBDBDB;");
        }

        if (category == 6){
            money6.setStyle("-fx-background-color: #9aff9a");
        } else {
            money6.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 7){
            money7.setStyle("-fx-background-color: #9aff9a");
        } else {
            money7.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 8){
            money8.setStyle("-fx-background-color: #9aff9a");
        } else {
            money8.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 9){
            money9.setStyle("-fx-background-color: #9aff9a");
        } else {
            money9.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 10){
            money10.setStyle("-fx-background-color: #9aff9a");
        } else {
            money10.setStyle("-fx-background-color: #DBDBDB;");
        }

        if (category == 11){
            money11.setStyle("-fx-background-color: #9aff9a");
        } else {
            money11.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 12){
            money12.setStyle("-fx-background-color: #9aff9a");
        } else {
            money12.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 13){
            money13.setStyle("-fx-background-color: #9aff9a");
        } else {
            money13.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 14){
            money14.setStyle("-fx-background-color: #9aff9a");
        } else {
            money14.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 15){
            money15.setStyle("-fx-background-color: #9aff9a");
        } else {
            money15.setStyle("-fx-background-color: #DBDBDB;");
        }
    }
}








