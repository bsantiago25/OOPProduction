//Programmer name: Brandon Santiago
//This file is the controller.java file and contains and contains all SQL codes and
//most of the coding for this program.
//Date: 9/19/2020
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
  private Button btnRecordProduct;

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

  ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  ArrayList<ProductionRecord> productionLog1 = new ArrayList<>();

  ArrayList<ProductionRecord> productionLog2 = new ArrayList<>();



  final String Jdbc_Driver = "org.h2.Driver";
  final String Db_Url = "jdbc:h2:./res/PRODUCTDB";

  //  Database credentials
  final String User = "";
  final String Pass = "";


  @FXML
  void addProduct(ActionEvent event) throws SQLException {

    //calls connectToDb method when add product button is pushed.

    System.out.println("Product added");
    String name = productName.getText();
    //used to get product name from text box

    String manufacturer = manufactureName.getText();
    //used to get manufacturer name from textbox

    String type = chbxType.getValue();
    //used to get type from choice box

    //adds info to observablelist called productline and adds that into tableview
    Product product = new Product(name, manufacturer, ItemType.valueOf(type));

    productLine.add(product);
    addToProductDB();
    produceView.setItems(productLine);
    //This prints out info from product table view into produce tab

    loadProductList();

  }

  @FXML
  void recordProduct(ActionEvent event) throws SQLException {
    System.out.println("Product Recorded");
    //prints out "product recorded" into console

    //This takes the selected item on the listview and grabs the info needed to make a new product
    //record
    Product productProduced = produceView.getSelectionModel().getSelectedItem();

    int tick = 1;

    for (ProductionRecord productionRecord : productionLog2) {

      for (int k = 0; k < productLine.toArray().length; k++) {

        if (productLine.get(k).getId() == productionRecord.getProductID()
            && productProduced.getName() == productLine.get(k).getName()) {
          tick++;
        }

      }
    }








    int quantity = Integer.parseInt(cmbProduce.getValue());

    //For loop takes input from combobox and uses that to determine how many products were made.
    try {
      for (int j = tick; j < tick + quantity; j++) {
        //Prints out products onto text area using ProductionRecords tostring method.
        ProductionRecord productRec = new ProductionRecord(productProduced,j);
        productionRun.add(productRec);

      }
      addToProductionDB();
      productionRun.clear();
    } catch (NullPointerException e) {
      System.out.println("Please pick product");
    }
    loadProductionDB();


  }

  /**
   * This method initializes the combobox to make a total types of 1-10.
   */
  public void initialize() throws SQLException {

    for (int i = 1; i <= 10; i++) {
      cmbProduce.getItems().add(String.valueOf(i));
      //for loop for combobox. Lists numbers 1-10
    }
    cmbProduce.setEditable(true);
    cmbProduce.getSelectionModel().selectFirst();
    //Defaults number to 1, resource for this code was by prof. Vanselow's website

    //Cycles through choicebox for Itemtype
    for (ItemType p : ItemType.values()) {
      chbxType.getItems().add(String.valueOf(p));
    }
    //Selects first option for itemtype
    chbxType.getSelectionModel().selectFirst();

    loadProductList();
    setupProductLineTable();
    produceView.setItems(productLine);
    loadProductionDB();


  }

  private Connection conn = null;
  private Statement stmt = null;


  /**
   * This method initializes the program to connect to database and populate it with data.
   */


  public void addToProductDB() {

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

      String insertSql = "INSERT INTO PRODUCT(type, manufacturer, name) "
          + "VALUES ( ?, ?, ? )";
      //sql statement used to add into product table

      PreparedStatement ps = conn.prepareStatement(insertSql);

      ps.setString(1, type);
      ps.setString(2, manufacturer);
      ps.setString(3, product);

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

  private void loadProductList() {
    try {
      //STEP 1: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);
      //Create statement
      stmt = conn.createStatement();
      String reader = "SELECT * FROM Product";
      //STEP 3: Execute a query
      ResultSet looker = stmt.executeQuery(reader);
      //Clears Tableview to input new Table
      produceView.getItems().clear();
      while (looker.next()) {
        // these lines correspond to the database table columns
        //Reads from database
        int id = looker.getInt("id");
        String name = looker.getString("name");
        String type = looker.getString("type");
        String manufacturer = looker.getString("manufacturer");
        // create Product Object
        Product productFromDb = new Product(id, name, manufacturer, ItemType.valueOf(type));

        productLine.add(productFromDb);
      }
      looker.close();
    } catch (SQLException se) {
      se.printStackTrace();
      Alert a = new Alert(AlertType.ERROR);

      a.show();
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("This is where the error is");
    }

  }

  public void loadProductionDB() {
    try {
      //STEP 1: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);
      //Create statement
      stmt = conn.createStatement();
      String reader2 = "SELECT * FROM Productionrecord";
      //STEP 3: Execute a query
      ResultSet looker2 = stmt.executeQuery(reader2);
      productionLog2.clear();
      //Clears Tableview to input new Table

      while (looker2.next()) {
        // these lines correspond to the database table columns
        //Reads from database
        int pNum = looker2.getInt("production_num");
        int pId = looker2.getInt("product_id");
        String sNum = looker2.getString("serial_num");
        Date dProduced = looker2.getDate("date_produced");
        // create Product Object
        ProductionRecord productionRecordFromDb = new ProductionRecord(pNum, pId, sNum, dProduced);
        productionLog1.add(productionRecordFromDb);
        productionLog2.add(productionRecordFromDb);
      }
      productionLog.clear();
      showProduction();
      looker2.close();
    } catch (SQLException se) {
      se.printStackTrace();
      Alert a = new Alert(AlertType.ERROR);

      a.show();
    }
  }


  public void addToProductionDB() {
    try {
      // STEP 1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);
      //Acknowledged as bug, but nothing can be done here for now.
      stmt = conn.createStatement();
      //STEP 3: Execute a query
      String insertSql;
      for (int i = 0; i < productionRun.size(); i++) {

        insertSql = "INSERT INTO ProductionRecord(product_id,serial_num,date_produced) "
            + "VALUES (?, ?, ? )";
        //sql statement used to add into product table
        java.sql.Date sDate = new java.sql.Date(productionRun.get(i).getProdDate().getTime());

        PreparedStatement ps = conn.prepareStatement(insertSql);

        //To Do: Work on serial Number.

        ps.setInt(1, productionRun.get(i).getProductID());
        ps.setString(2, productionRun.get(i).getSerialNum());
        ps.setDate(3, sDate);

        ps.executeUpdate();
        //Used to execute sql statement.

        // STEP 4: Clean-up environment
        ps.close();

      }
    } catch (ClassNotFoundException e) {
      e.printStackTrace();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  //This table is designed to get text from user input and record them into product table
  public void setupProductLineTable() {

    prodID.setCellValueFactory(new PropertyValueFactory("id"));
    //column for product name.
    prodName.setCellValueFactory(new PropertyValueFactory("name"));

    //column for manufacturer
    mfr.setCellValueFactory(new PropertyValueFactory("manufacturer"));

    //Column for item type
    iType.setCellValueFactory(new PropertyValueFactory("type"));

    //adding products from observableList to tableView
    productTable.setItems(productLine);
  }

  public void showProduction() {


    for (int j = 0; j < productionLog1.size(); j++) {

      for (int k = 0; k < productLine.toArray().length; k++) {

        if (productLine.get(k).getId() == productionLog1.get(j).getProductID())

        productionLog.appendText(
            "Prod. Num: " + productionLog1.get(j).productionNumber + " Product Name: "
                + productLine.get(k).getName() +
                " Serial Num: " + productionLog1.get(j).getSerialNum() + " Date: "
                + productionLog1
                .get(j).getProdDate() + '\n');
        {

        }
      }
    }
    productionLog1.clear();
    //Deletes the productionLog1 list so it doesnt duplicate list again.
  }
}




