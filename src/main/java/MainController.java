import ConsoleCode.Game;
import ConsoleCode.Joker;
import ConsoleCode.Player;
import ConsoleCode.Question;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import java.io.IOException;

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
    private Label labelCat1;

    @FXML
    private Label labelCat2;

    @FXML
    private Label labelCat3;

    @FXML
    private Label labelCat4;

    @FXML
    private Label labelCat5;

    @FXML
    private Label labelCat6;

    @FXML
    private Label labelCat7;

    @FXML
    private Label labelCat8;

    @FXML
    private Label labelCat9;

    @FXML
    private Label labelCat10;

    @FXML
    private Label labelCat11;

    @FXML
    private Label labelCat12;

    @FXML
    private Label labelCat13;

    @FXML
    private Label labelCat14;

    @FXML
    private Label labelCat15;

    @FXML
    private Label labelOutput;

    @FXML
    private Button buttonLeave;

    private final Game currentGame = new Game();
    private Question[] questions;
    private Question currentQuestion;
    private final Player currentPlayer = new Player();
    private final Joker fiftyFifty = new Joker(1);
    private final Joker secondChance = new Joker(2);
    private final Joker telephone = new Joker(3);
    private boolean left;

    @FXML
    private void initialize() {
        try {
            questions = currentGame.createQuestions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        makeNextQuestion();
    }

    @FXML
    private void actionPerformed(ActionEvent e){
        String buttonName = ((Button)e.getSource()).getId();
        char fieldName = Character.toLowerCase(buttonName.charAt(buttonName.length()-1));
        if(fieldName == currentQuestion.getCorrect()){
            changeCategory(currentPlayer.getCategory());
            // ((Button) e.getSource()).setStyle("-fx-background-color: #36f546");
            if(currentQuestion.getSecondChance()){
                currentQuestion.chamgeSecondChanceBack();
            }
            if(currentPlayer.getCategory() == 15){
                labelCat14.setStyle("-fx-background-color: #ffffff");
                labelCat15.setStyle("-fx-background-color: #ffd447");
                labelQ.setText("Herzlichen Glueckwunsch! Du bist jetzt ein Millionaer."); //Umlaute funktionieren bei setText nicht...
                labelQ.setStyle("-fx-text-fill: #ff5900; -fx-font-weight: bold");
            }
            currentPlayer.raiseCategory();
            makeNextQuestion();
            if(true){
                labelOutput.setText("");
            }
            currentPlayer.switchMoney();
        }else{
            //((Button) e.getSource()).setStyle("-fx-background-color: #ff1414");
            if(currentQuestion.getSecondChance()){
                labelOutput.setText("Das war noch nicht richtig. Probier es nochmal.");
                currentQuestion.chamgeSecondChanceBack();
            }else {
                currentPlayer.printMoneyWon(false);
                currentPlayer.changeCategory(16); //SPIEL BEENDEN?
            }
        }
    }

    private void makeNextQuestion() {
        currentQuestion = currentGame.getQuestionFromCategory(currentGame.getQuestionCategory(questions, currentPlayer.getCategory()));
        labelQ.setText(currentQuestion.getQuestion());
        buttonA.setText("A: " + currentQuestion.getA());
        buttonB.setText("B: " + currentQuestion.getB());
        buttonC.setText("C: " + currentQuestion.getC());
        buttonD.setText("D: " + currentQuestion.getD());

    }

    @FXML
    void changeColorASAP(MouseEvent event){
        if(event.getSource().equals(buttonFiftyFifty)){
            if(!fiftyFifty.getUsed()) {
                buttonFiftyFifty.setStyle("-fx-background-color: #ffe9a1");
            }else
                buttonFiftyFifty.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
        }
        else if(event.getSource().equals(buttonTelephone)){
            if(!telephone.getUsed()) {
                buttonTelephone.setStyle("-fx-background-color: #ffe9a1");
            }else
                buttonTelephone.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
        }
        else if(event.getSource().equals(buttonSecondChance)){
            if(!secondChance.getUsed()) {
                buttonSecondChance.setStyle("-fx-background-color: #ffe9a1");
            }else
                buttonSecondChance.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
        }
        else if(event.getSource().equals(buttonLeave)){
            buttonLeave.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
        }
        /*else {
            String buttonName = ((Button)event.getSource()).getId();
            char fieldName = Character.toLowerCase(buttonName.charAt(buttonName.length()-1));
            if(fieldName == currentQuestion.getCorrect()) {
                ((Button) event.getSource()).setStyle("-fx-background-color: #36f546");
            }else{
                ((Button) event.getSource()).setStyle("-fx-background-color: #ff1414");
           }

        }

         */
    }

    @FXML
    void leaveGame(ActionEvent event) {
        currentPlayer.switchMoney();
        labelQ.setText("Das Spiel ist hiermit beendet. Du hast " + currentPlayer.getMoney() + " Euro gewonnen!");
        labelQ.setStyle("-fx-text-fill: black; -fx-font-weight: bold");
        left = true;
        currentPlayer.changeCategory(16);
    }

    @FXML
    void changeColor(MouseEvent event) {
        if(event.getSource().equals(buttonLeave)){
            if(!left){
                buttonLeave.setStyle("-fx-background-color: #ff9191");
            }
            else {
                buttonLeave.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        }
        else if(event.getSource().equals(buttonFiftyFifty)){
            if(!fiftyFifty.getUsed()) {
                buttonFiftyFifty.setStyle("-fx-background-color: #ffe9a1");
            }else {
                buttonFiftyFifty.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        }
        else if(event.getSource().equals(buttonTelephone)){
            if(!telephone.getUsed()) {
                buttonTelephone.setStyle("-fx-background-color: #ffe9a1");
            }else {
                buttonTelephone.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        }
        else if(event.getSource().equals(buttonSecondChance)){
            if(!secondChance.getUsed()) {
                buttonSecondChance.setStyle("-fx-background-color: #ffe9a1");
            }else {
                buttonSecondChance.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        }
        else {
            ((Button) event.getSource()).setStyle("-fx-background-color: #d1fffa");
        }
    }

    @FXML
    void changeColorBack(MouseEvent event) {
        if(event.getSource().equals(buttonLeave)){
            if(!left){
                buttonLeave.setStyle("-fx-background-color: #ff8787");
            }
            else {
                buttonLeave.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        }
        else if(event.getSource().equals(buttonFiftyFifty)){
            if(!fiftyFifty.getUsed()) {
                buttonFiftyFifty.setStyle("-fx-background-color: #ffe07a");
            }else {
                buttonFiftyFifty.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        }
        else if(event.getSource().equals(buttonTelephone)){
            if(!telephone.getUsed()) {
                buttonTelephone.setStyle("-fx-background-color: #ffe07a");
            }else {
                buttonTelephone.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        }
        else if(event.getSource().equals(buttonSecondChance)){
            if(!secondChance.getUsed()) {
                buttonSecondChance.setStyle("-fx-background-color: #ffe07a");
            }else {
                buttonSecondChance.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        }
        else {
            ((Button) event.getSource()).setStyle("-fx-background-color: #a2fff4");
        }
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
        } else {
            labelOutput.setText("Du hast diesen Joker bereits verwendet.");
            labelOutput.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    void useTelephone(ActionEvent e){
        if (!telephone.getUsed()){
            labelOutput.setStyle("-fx-text-fill: black; -fx-text-weight: bold");
            labelOutput.setText(telephone.telephoneHelpMe(currentQuestion));
        } else {
            labelOutput.setText("Du hast diesen Joker bereits verwendet.");
            labelOutput.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    void useSecondChance(ActionEvent event) {
        if(secondChance.getUsed()){
            labelOutput.setText("Du hast diesen Joker bereits verwendet.");
            labelOutput.setStyle("-fx-text-fill: red");
        }
        else{
            secondChance.changeUsed();
            currentQuestion.changeSecondChance();
            labelOutput.setText("Second Chance Joker ist jetzt aktiv!");
            labelOutput.setStyle("-fx-text-fill: #ff9f00");
        }
    }
    @FXML
    void changeCategory(int category){
        if (category == 1){
            labelCat1.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat1.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 2){
            labelCat2.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat2.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 3){
            labelCat3.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat3.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 4){
            labelCat4.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat4.setStyle("-fx-background-color: #ffffff;");
        }

       if (category == 5){
            labelCat5.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat5.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 6){
            labelCat6.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat6.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 7){
            labelCat7.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat7.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 8){
            labelCat8.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat8.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 9){
            labelCat9.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat9.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 10){
            labelCat10.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat10.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 11){
            labelCat11.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat11.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 12){
            labelCat12.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat12.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 13){
            labelCat13.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat13.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 14){
            labelCat14.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat14.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 15){
            labelCat15.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat15.setStyle("-fx-background-color: #b8ffec;");
        }
    }
}








