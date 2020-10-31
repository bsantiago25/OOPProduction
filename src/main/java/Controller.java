//Programmer name: Brandon Santiago
//This file is the controller.java file and contains and contains all SQL codes and
//most of the coding for this program.
//Date: 9/19/2020
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.h2.command.dml.Select;

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
  private TextArea productionLog;

  @FXML
  private TableView<Product> productTable;

  @FXML
  private TableColumn<?, ?> prodID;

  @FXML
  private TableColumn<?, ?> prodName;

  @FXML
  private TableColumn<?, ?> mfr;

  @FXML
  private TableColumn<?, ?> iType;

  @FXML
  private TextField productName;

  @FXML
  private TextField manufactureName;

  @FXML
  private ListView<Product> produceView;

  @FXML
  private ComboBox<String> cmbProduce;

  @FXML
  private ChoiceBox<String> chbxType;

  //Observable List
  ObservableList<Product> productLine = FXCollections.observableArrayList();



  @FXML
  void addProduct(ActionEvent event) throws SQLException {
    connectToDb();
    //calls connectToDb method when add product button is pushed.

    System.out.println("Product added");

    setupProductLineTable();
    setupProduceListview();

  }

  @FXML
  void recordProduct(ActionEvent event) {
    System.out.println("Product Recorded");
    //prints out "product recorded" into console

    ObservableList selectedIndices;
    selectedIndices = produceView.getSelectionModel().getSelectedIndices();


  }

  /**
   * This method initializes the combobox to make a total types of 1-10.
   */
  public void initialize() {
    for (int i = 1; i <= 10; i++) {
      cmbProduce.getItems().add(String.valueOf(i));
      //for loop for combobox. Lists numbers 1-10
    }
    cmbProduce.getSelectionModel().selectFirst();
    //Defaults number to 1, resource for this code was by prof. Vanselow's website

    for(ItemType p : ItemType.values())
    {
      chbxType.getItems().add(String.valueOf(p));
    }

    chbxType.getSelectionModel().selectFirst();

  }

  private Connection conn = null;
  private Statement stmt = null;




  /**
   * This method initializes the program to connect to database and populate it with data.
   */
  public void connectToDb() {

    final String Jdbc_Driver = "org.h2.Driver";
    final String Db_Url = "jdbc:h2:./res/PRODUCTDB";



    //  Database credentials
    final String User = "";
    final String Pass = "";

    String product = productName.getText();
    //used to get product name from text box

    System.out.println(product);
    // used to print out product

    String manufacturer = manufactureName.getText();
    //used to get manufacturer name from textbox

    System.out.println(manufacturer);
    //used to print out manufacturer

    String type = chbxType.getValue();
    //used to get type from choice box

    System.out.println(type);
    //prints out type from choicebox


    try {
      // STEP 1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);
      //Acknowledged as bug, but nothing can be done here for now.
      stmt = conn.createStatement();
      //STEP 3: Execute a query
      String insertSql;



      insertSql = "INSERT INTO Product(type, manufacturer, name) "
          + "VALUES ( ?, ?, ? )";
      //sql statement used to add into product table

      PreparedStatement ps = conn.prepareStatement(insertSql);

      ps.setString(1,type);
      ps.setString(2,manufacturer);
      ps.setString(3,product);

      ps.executeUpdate();
      //Used to execute sql statement.

      // STEP 4: Clean-up environment
      ps.close();

    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }


    private void loadProductList () throws SQLException {
    String sql = "SELECT * FROM Product";

    ResultSet rs = stmt.executeQuery(sql);

    while (rs.next()) {
      String name = rs.getString("Name");
      String type = rs.getString("Type");
      String manufacturer = rs.getString("Manufacturer");

      Product productFromDB;

      switch (ItemType.valueOf(type))
      {
        case AUDIO:
          productFromDB = new Product(name, manufacturer, ItemType.AUDIO);
          productLine.add(productFromDB);
          break;
        case VISUAL:
          productFromDB = new Product(name, manufacturer, ItemType.VISUAL);
          productLine.add(productFromDB);
          break;
        case AUDIO_MOBILE:
          productFromDB = new Product(name, manufacturer, ItemType.AUDIO_MOBILE);
          productLine.add(productFromDB);
          break;
        case VISUAL_MOBILE:
          productFromDB = new Product(name, manufacturer, ItemType.VISUAL_MOBILE);
          productLine.add(productFromDB);
          break;
        default:
          break;

      }



    }
      prodName.setCellValueFactory(new PropertyValueFactory("name"));
      mfr.setCellValueFactory(new PropertyValueFactory("manufacturer"));
      iType.setCellValueFactory(new PropertyValueFactory("type"));
      productTable.setItems(productLine);
    rs.close();
    conn.close();
  }





//  public void testUserInput()
//  {
//    String name = productName.getText();
//    //used to get product name from text box
//
//    String manufacturer = manufactureName.getText();
//    //used to get manufacturer name from textbox
//
//    String type = chbxType.getValue();
//    //used to get type from choice box
//
//    Product product = new Product(name,manufacturer,ItemType.valueOf(type));
//    productLine.add(product);
//    prodName.setCellValueFactory(new PropertyValueFactory("name"));
//    mfr.setCellValueFactory(new PropertyValueFactory("manufacturer"));
//    iType.setCellValueFactory(new PropertyValueFactory("type"));
//    productTable.setItems(productLine);
//
//
//
//  }

  public void setupProductLineTable()
  {
    String name = productName.getText();
    //used to get product name from text box

    String manufacturer = manufactureName.getText();
    //used to get manufacturer name from textbox

    String type = chbxType.getValue();
    //used to get type from choice box

    Product product = new Product(name,manufacturer,ItemType.valueOf(type));
    productLine.add(product);
    prodName.setCellValueFactory(new PropertyValueFactory("name"));
    mfr.setCellValueFactory(new PropertyValueFactory("manufacturer"));
    iType.setCellValueFactory(new PropertyValueFactory("type"));

    //adding products from observableList to tableView
    productTable.setItems(productLine);
  }

  public void setupProduceListview()
  {
    //Shows Product in Produce table
    produceView.setItems(productLine);
  }

}




