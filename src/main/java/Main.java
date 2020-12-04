//Programmer Name:Brandon Santiago
//This java file is the main file for the program. Doesn't contain much code
//since the coding goes into Controller mostly.
//Date:9/19/2020
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * Main method of this program.
 * @author Brandon Santiago
 */
public class Main extends Application {
  public static void main(String[]args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

    Scene scene = new Scene(root, 500, 470);

    primaryStage.setTitle("OOP Production Project");
    primaryStage.setScene(scene);
    primaryStage.show();


  }



}

