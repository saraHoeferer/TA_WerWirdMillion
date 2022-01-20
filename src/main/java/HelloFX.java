import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public class HelloFX extends Application {

    public static Stage stage;
    // used for switching scenes in MainController -> no new stage


    @Override
    public void start(Stage stage) throws IOException {
        // Opens "start" window
        FXMLLoader fxmlLoader = new FXMLLoader(HelloFX.class.getResource("start.fxml")); // load fxml file belonging to start window
        Scene scene = new Scene(fxmlLoader.load(), 800, 500); // create new scene and edit width and height of window
        HelloFX.stage = stage;
        stage.setTitle("Team Alphas");
        stage.setScene(scene);
        stage.setResizable(false); // static window which is not resizeable

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}
