import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;

public class Controller {

  @FXML
  private Label lblOutput;

  @FXML
  private Tab tab1;

  @FXML
  private Button btnAddProduct;

  @FXML
  private Tab tab2;

  @FXML
  private Tab tab3;

  @FXML
  void addProduct(ActionEvent event) {
    System.out.println("Product added");
  }

  @FXML
  void recordProduct(ActionEvent event) {
    System.out.println("Product Recorded");

  }





}