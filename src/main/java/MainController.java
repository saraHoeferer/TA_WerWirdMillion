import ConsoleCode.Game;
import ConsoleCode.Joker;
import ConsoleCode.Player;
import ConsoleCode.Question;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.swing.plaf.basic.BasicSliderUI;
import java.io.IOException;
import java.security.Key;
import java.time.Duration;


public class MainController {

    // possible answers
    @FXML
    private Button buttonA = new Button();

    // label for printing the question
    @FXML
    private Button buttonB = new Button();

    // joker - buttons
    @FXML
    private Button buttonC = new Button();

    // labels for displaying all categories
    @FXML
    private Button buttonD = new Button();

    // label for printing the question
    @FXML
    private Label labelQ = new Label();

    // joker - buttons
    @FXML
    private Button buttonFiftyFifty = new Button();

    @FXML
    private Button buttonSecondChance = new Button();

    @FXML
    private Button buttonTelephone = new Button();

    // labels for displaying all categories
    @FXML
    private Label labelCat1 = new Label();

    @FXML
    private Label labelCat2 = new Label();

    @FXML
    private Label labelCat3 = new Label();

    @FXML
    private Label labelCat4 = new Label();

    @FXML
    private Label labelCat5 = new Label();

    @FXML
    private Label labelCat6 = new Label();

    @FXML
    private Label labelCat7 = new Label();

    @FXML
    private Label labelCat8 = new Label();

    @FXML
    private Label labelCat9 = new Label();

    // label for displaying statements regarding use of jokers
    @FXML
    private Label labelCat10 = new Label();

    // button for leaving the game (and win money of current category)
    @FXML
    private Label labelCat11 = new Label();

    @FXML
    private Label labelCat12 = new Label();

    @FXML
    private Label labelCat13 = new Label();

    @FXML
    private Label labelCat14 = new Label();

    @FXML
    private Label labelCat15 = new Label();

    // label for displaying statements regarding use of jokers
    @FXML
    private Label labelOutput = new Label();

    // button for leaving the game (and win money of current category)
    @FXML
    private Button buttonLeave = new Button();

    // instance variables
    private final Game currentGame = new Game(); // new object of the Game class
    private Question[] questions; // array of questions
    private Question currentQuestion; // new object of the Question class
    private final Player currentPlayer = new Player(); // new object of the Player class
    private boolean left; // true when leaveButton is pressed
    private final Joker fiftyFifty = new Joker(); // new 50 : 50 Joker
    private final Joker secondChance = new Joker(); // new second chance Joker
    private final Joker telephone = new Joker();

    // initialize the game board
    @FXML
    private void initialize() {
        try {
            questions = currentGame.createQuestions(); // load new questions
        } catch (IOException e) {
            e.printStackTrace();
        }
        makeNextQuestion(); // printing first question on the game board
    }

    // switch from start window (in HelloFX) to main window (game)
    public void switchToGame() throws IOException { // called by button "Start"
        FXMLLoader fxmlLoader = new FXMLLoader(HelloFX.class.getResource("test.fxml")); // load belonging fxml file
        Scene scene = new Scene(fxmlLoader.load(), 800, 500); // create new scene and edit measurements of window
        HelloFX.stage.setTitle("Team Alphas");
        HelloFX.stage.setScene(scene);
        HelloFX.stage.setResizable(false); // not resizeable
        HelloFX.stage.show();

    }

    // switch from end of the game (end window) to start window
    public void switchToStart() throws IOException { // called by buttonEndGame
        FXMLLoader fxmlLoader = new FXMLLoader(HelloFX.class.getResource("start.fxml")); // load start window
        Scene scene = new Scene(fxmlLoader.load(), 800, 500);
        HelloFX.stage.setTitle("Team Alphas");
        HelloFX.stage.setScene(scene);
        HelloFX.stage.setResizable(false); // not resizeable
        HelloFX.stage.show();
    }



    @FXML
    private Label labelMoneyWon = new Label(); // print money won


    @FXML
    private Button buttonEndGame = new Button(); // switch to start window

    @FXML
    private Button buttonPlayAgain = new Button(); // switch to main window (shows directly first question again)


    public void switchAndClose() throws IOException {
        Stage endStage = (Stage) buttonEndGame.getScene().getWindow(); // button is a child of the Alert window hence why buttonEndGame.getScene().getWindow();
        // new Stage with source from button is created; button has to be defined outside
        switchToStart(); // calls method to switch (in this case) stage and scene
        endStage.close(); // close window which belongs to buttonEndGame
    }

    public void switchAndClose2() throws IOException { // used by buttonPlayAgain because we don't switch
        Stage endStage = (Stage) buttonEndGame.getScene().getWindow();
        endStage.close();
        switchToGame(); // starting a new game but stay in same window
    }



    // end window pops up
    public void switchToAlert() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloFX.class.getResource("end.fxml")); // load belonging fxml file
        Scene scene = new Scene(fxmlLoader.load(), 400, 250); // measurements half of main window
        Stage stage = new Stage();
        stage.setTitle("Team Alphas");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.initOwner(HelloFX.stage);
        stage.initModality(Modality.APPLICATION_MODAL);
        // after window opens main window cannot be used until this stage is closed

        stage.show();


        Label moneyLabel = (Label) scene.lookup("#labelMoneyWon");
        // new label connecting with scene label via fx:id


       if(left) { // if leaveGame button is pushed
            moneyLabel.setText(currentPlayer.printMoneyWon(true));
        }
       else{ // if answer is wrong
            moneyLabel.setText(currentPlayer.printMoneyWon(false));
        }

    }


    @FXML
    private void actionPerformed(MouseEvent e) throws InterruptedException, IOException {
        String buttonName = ((Button) e.getSource()).getId(); // get ID of the button that was pressed
        char fieldName = Character.toLowerCase(buttonName.charAt(buttonName.length() - 1)); // which answer is on different button
        if (fieldName == currentQuestion.getCorrect()) { // compare answer on the field that was pressed with correct answer
            changeCategory(currentPlayer.getCategory()); // call method changeCategory from Game class
            ((Button)e.getSource()).setStyle("-fx-background-color: #36f546"); // color correct answer green
            Thread.sleep(500); // delay - else it won't show green color
            changeColorBack(e); // field needs to be reset to origin color - else at next question answer still green

            if (currentQuestion.getSecondChance()) { // check if second chance joker was used
                currentQuestion.changeSecondChanceBack(); // second chance deactivated
            }
            if (currentPlayer.getCategory() == 15) { // if category 15 is reached (won game)
                labelCat14.setStyle("-fx-background-color: #ffffff"); // set background to origin background
                labelCat15.setStyle("-fx-background-color: #ffd447"); // move category pointer one up
                switchToAlert(); // open end window - choose if you want to play again or leave (switch to start window)
            }

            currentPlayer.raiseCategory(); // increase category by 1 - method from Player class
            makeNextQuestion(); // print next question
            if (true) {
                labelOutput.setText(""); // reset output label when creating new question
            }
            currentPlayer.switchMoney(); // money according to category ??? GEHÖRT NICHT printMoney Player class
        } else { // if answer is wrong
            ((Button)e.getSource()).setStyle("-fx-background-color: #ff1414"); // color wrong answer red
            if (currentQuestion.getSecondChance()) { // check if second chance joker is active
                labelOutput.setText("Das war noch nicht richtig. Probier es nochmal."); // try again
                currentQuestion.changeSecondChanceBack(); // deactivate second chance
            } else { // wrong answer - no second chance
                //currentPlayer.printMoneyWon(false); // print money won according to different category
                switchToAlert(); // open end window
                //labelMoneyWon.setText();

            }
        }
    }

    private void makeNextQuestion() { // print question and possible answers
        currentQuestion = currentGame.getQuestionFromCategory(currentGame.getQuestionCategory(questions, currentPlayer.getCategory()));
        labelQ.setText(currentQuestion.getQuestion());
        buttonA.setText("A: " + currentQuestion.getA());
        buttonA.setMouseTransparent(false);

        buttonB.setText("B: " + currentQuestion.getB());
        buttonB.setMouseTransparent(false);

        buttonC.setText("C: " + currentQuestion.getC());
        buttonC.setMouseTransparent(false);

        buttonD.setText("D: " + currentQuestion.getD());
        buttonD.setMouseTransparent(false);
    }

    // COLORS
    // change color when mouse is pressed (not clicked)
    @FXML
    void changeColorASAP(MouseEvent event){
        if (event.getSource().equals(buttonFiftyFifty)) {
            /*if (!fiftyFifty.getUsed()) { // call getUsed method from Joker class
                buttonFiftyFifty.setStyle("-fx-background-color: #ffe9a1"); // if 50:50 Joker is not used
            } else
             */
                buttonFiftyFifty.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;"); // if 50:50 Joker is used
        } else if (event.getSource().equals(buttonTelephone)) {
            /* if (!telephone.getUsed()) {
                buttonTelephone.setStyle("-fx-background-color: #ffe9a1"); // telephone Joker
            } else
             */
                buttonTelephone.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
        } else if (event.getSource().equals(buttonSecondChance)) { // second chance Joker
            /* if (!secondChance.getUsed()) {
                buttonSecondChance.setStyle("-fx-background-color: #ffe9a1");
            } else
            */
                buttonSecondChance.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
        } else if (event.getSource().equals(buttonLeave)) { // leave button
            buttonLeave.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
        } else {
            String buttonName = ((Button) event.getSource()).getId();
            char fieldName = Character.toLowerCase(buttonName.charAt(buttonName.length() - 1)); // get char of pushed answer button
            if (fieldName == currentQuestion.getCorrect()) { // check if pushed button is correct
                ((Button) event.getSource()).setStyle("-fx-background-color: #36f546"); // color correct field green
            } else
                ((Button) event.getSource()).setStyle("-fx-background-color: #ff1414"); // color wrong field red
        }
    }

    // method to leave game and get money from current category
    @FXML
    void leaveGame(ActionEvent event) throws IOException {
        currentPlayer.switchMoney(); // get money according to category
        left = true; // set referring boolean to true
        currentPlayer.changeCategory(16);
        switchToAlert();
        labelMoneyWon.setText("Das Spiel ist hiermit beendet. Du hast " + currentPlayer.getMoney() + " Euro gewonnen!");
        labelMoneyWon.setStyle("-fx-text-fill: black; -fx-font-weight: bold"); // geht nicht
    }

    /**
     *
     * leave Game geht noch nicht
     */

    // change color when mouse enters button
    @FXML
    void changeColor(MouseEvent event) {
        if (event.getSource().equals(buttonLeave)) {
            if (!left) {
                buttonLeave.setStyle("-fx-background-color: #ff9191");
            } else {
                buttonLeave.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        } else if (event.getSource().equals(buttonFiftyFifty)) {
            if (!fiftyFifty.getUsed()) {
                buttonFiftyFifty.setStyle("-fx-background-color: #ffe9a1");
            } else {
                buttonFiftyFifty.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        } else if (event.getSource().equals(buttonTelephone)) {
            if (!telephone.getUsed()) {
                buttonTelephone.setStyle("-fx-background-color: #ffe9a1");
            } else {
                buttonTelephone.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        } else if (event.getSource().equals(buttonSecondChance)) {
            if (!secondChance.getUsed()) {
                buttonSecondChance.setStyle("-fx-background-color: #ffe9a1");
            } else {
                buttonSecondChance.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        } else {
            ((Button) event.getSource()).setStyle("-fx-background-color: #d1fffa");
        }
    }

    // change color when mouse exits button
    @FXML
    void changeColorBack(MouseEvent event) {
        if (event.getSource().equals(buttonLeave)) {
            if (!left) {
                buttonLeave.setStyle("-fx-background-color: #ff8787");
            } else {
                buttonLeave.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        } else if (event.getSource().equals(buttonFiftyFifty)) {
            if (!fiftyFifty.getUsed()) {
                buttonFiftyFifty.setStyle("-fx-background-color: #ffe07a");
            } else {
                buttonFiftyFifty.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        } else if (event.getSource().equals(buttonTelephone)) {
            if (!telephone.getUsed()) {
                buttonTelephone.setStyle("-fx-background-color: #ffe07a");
            } else {
                buttonTelephone.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        } else if (event.getSource().equals(buttonSecondChance)) {
            if (!secondChance.getUsed()) {
                buttonSecondChance.setStyle("-fx-background-color: #ffe07a");
            } else {
                buttonSecondChance.setStyle("-fx-background-color: #9c9992; -fx-text-fill: #454340;");
            }
        } else {
            ((Button) event.getSource()).setStyle("-fx-background-color: #a2fff4");
        }
    }

    // print question if 50:50 Joker is used
    private void printFiftyFiftyQuestion() { // working with booleans from Question class
        labelQ.setText(currentQuestion.getQuestion());
        if (!currentQuestion.isHideA()) { // if answer not hidden yet
            buttonA.setText("A: " + currentQuestion.getA());
        } else {
            buttonA.setText("A: ");
            buttonA.setMouseTransparent(true);
        }
        if (!currentQuestion.isHideB()) {
            buttonB.setText("B: " + currentQuestion.getB());
        } else {
            buttonB.setText("B: ");
            buttonB.setMouseTransparent(true);
        }
        if (!currentQuestion.isHideC()) {
            buttonC.setText("C: " + currentQuestion.getC());
        } else {
            buttonC.setText("C: ");
            buttonC.setMouseTransparent(true);
        }
        if (!currentQuestion.isHideD()) {
            buttonD.setText("D: " + currentQuestion.getD());
        } else {
            buttonD.setText("D: ");
            buttonD.setMouseTransparent(true);
        }
    }

    //use 50 : 50 ConsoleCode Joker
    //
    @FXML
    void useFiftyFifty(ActionEvent e) { // use 50:50 Joker
        if (!fiftyFifty.getUsed()) {
            fiftyFifty.useFiftyFiftyJoker(currentQuestion);
            printFiftyFiftyQuestion();
        } else {
            labelOutput.setText("Du hast diesen Joker bereits verwendet.");
            labelOutput.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    void useTelephone(ActionEvent e) {
        if (!telephone.getUsed()) {
            labelOutput.setStyle("-fx-text-fill: black; -fx-text-weight: bold");
            labelOutput.setText(telephone.telephoneHelpMe(currentQuestion));
        } else {
            labelOutput.setText("Du hast diesen Joker bereits verwendet.");
            labelOutput.setStyle("-fx-text-fill: red");
        }
    }

    @FXML
    void useSecondChance(ActionEvent event) {
        if (secondChance.getUsed()) {
            labelOutput.setText("Du hast diesen Joker bereits verwendet.");
            labelOutput.setStyle("-fx-text-fill: red");
        } else {
            secondChance.changeUsed();
            currentQuestion.changeSecondChance();
            labelOutput.setText("Second Chance Joker ist jetzt aktiv!");
            labelOutput.setStyle("-fx-text-fill: #ff9f00");
        }
    }

    // change pointer of current category
    @FXML
    void changeCategory(int category) {
        if (category == 1) {
            labelCat1.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat1.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 2) {
            labelCat2.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat2.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 3) {
            labelCat3.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat3.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 4) {
            labelCat4.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat4.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 5) {
            labelCat5.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat5.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 6) {
            labelCat6.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat6.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 7) {
            labelCat7.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat7.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 8) {
            labelCat8.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat8.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 9) {
            labelCat9.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat9.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 10) {
            labelCat10.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat10.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 11) {
            labelCat11.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat11.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 12) {
            labelCat12.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat12.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 13) {
            labelCat13.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat13.setStyle("-fx-background-color: #b8ffec;");
        }

        if (category == 14) {
            labelCat14.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat14.setStyle("-fx-background-color: #ffffff;");
        }

        if (category == 15) {
            labelCat15.setStyle("-fx-background-color: #ffd447");
        } else {
            labelCat15.setStyle("-fx-background-color: #b8ffec;");
        }
    }
}








