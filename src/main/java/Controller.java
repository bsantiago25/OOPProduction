//Programmer name: Brandon Santiago
//This file is the controller.java file and contains and contains all SQL codes and
//most of the coding for this program.
//Date: 9/19/2020
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

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
  private TextField productName;

  @FXML
  private TextField manufactureName;

  @FXML
  private ChoiceBox<String> cmbType;

  @FXML
  void addProduct(ActionEvent event) {
    connectToDb();
    //calls connectToDb method when add product button is pushed.

  }

  @FXML
  void recordProduct(ActionEvent event) {
    System.out.println("Product Recorded");
    //prints out "product recorded" into console

  }

  /**
   * This method initializes the combobox to make a total types of 1-10.
   */
  public void initialize() {
    for (int i = 1; i <= 10; i++) {
      cmbType.getItems().add(String.valueOf(i));
      //for loop for combobox. Lists numbers 1-10
    }

    cmbType.getSelectionModel().select(0);
    //Defaults number to 1
  }

  /**
   * This method initializes the program to connect to database and populate it with data.
   */
  public void connectToDb() {

    final String Jdbc_Driver = "org.h2.Driver";
    final String Db_Url = "jdbc:h2:./res/HR";

    //  Database credentials
    final String User = "";
    final String Pass = "";
    Connection conn = null;
    Statement stmt = null;
    System.out.println("Product added");
    String product = productName.getText();
    //used to get product name from text box
    System.out.println(product);
    // used to print out product
    String manufacturer = manufactureName.getText();
    //used to get manufacturer name from textbox
    System.out.println(manufacturer);
    //used to print out manufacturer
    try {
      // STEP 1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String insertSql = "INSERT INTO Product(type, manufacturer, name) "
          + "VALUES ( 'AUDIO', 'Apple', 'iPod' )";
      //sql statement used to add into product table

      stmt.executeUpdate(insertSql);
      //Used to execute sql statement.

      // STEP 4: Clean-up environment
      stmt.close();
      conn.close();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

}




