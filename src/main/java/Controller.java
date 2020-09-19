import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    connectToDb();//calls connectToDb method when add product button is pushed.

  }

  @FXML
  void recordProduct(ActionEvent event) {
    System.out.println("Product Recorded");//prints out "product recorded" into console

  }

  public void initialize() {
    for(int i = 1; i<=10; i++)
    cmbType.getItems().add(String.valueOf(i));//for loop for combobox. Lists numbers 1-10

    cmbType.getSelectionModel().select(0);//Defaults number to 1
  }

  public void connectToDb()
  {

    final String JDBC_DRIVER = "org.h2.Driver";
    final String DB_URL = "jdbc:h2:./res/HR";

    //  Database credentials
    final String USER = "";
    final String PASS = "";
    Connection conn = null;
    Statement stmt = null;
    System.out.println("Product added");
    String product = productName.getText();//used to get product name from text box
    System.out.println(product);// used to print out product
    String manufacturer = manufactureName.getText();//used to get manufacturer name from textbox
    System.out.println(manufacturer);//used to print out manufacturer
    try {
      // STEP 1: Register JDBC driver
      Class.forName(JDBC_DRIVER);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(DB_URL, USER, PASS);

      //STEP 3: Execute a query
      stmt = conn.createStatement();

      String insertSql =  "INSERT INTO Product(type, manufacturer, name) "
          + "VALUES ( 'AUDIO', 'Apple', 'iPod' )"; //sql statement used to add into product table


      stmt.executeUpdate(insertSql);//Used to execute sql statement.





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




