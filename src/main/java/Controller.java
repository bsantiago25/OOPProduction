/**

 * This is the controller class for my project.
 * Contains all the methods needed for the program to function.
  * @author Brandon Santiago

 */

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;



public class Controller {

  @FXML
  private TextArea productionLog;

  @FXML
  private TableView<Product> productTable;

  @FXML
  private TextField messageProduce;

  @FXML
  private TextField messageProductLine;

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

  @FXML
  private TextField empName;

  @FXML
  private TextField empPassword;

  @FXML
  private TextField usernameText;

  @FXML
  private Button empCreate;

  @FXML
  private Button signInButton;

  @FXML
  private TextArea empTextArea;


  //Global Instances to help with functionality of project
  final ObservableList<Product> productLine = FXCollections.observableArrayList();

  final ArrayList<ProductionRecord> productionRun = new ArrayList<>();

  final ArrayList<ProductionRecord> productionLog1 = new ArrayList<>();

  final ArrayList<ProductionRecord> productionLog2 = new ArrayList<>();





  final String Jdbc_Driver = "org.h2.Driver";
  final String Db_Url = "jdbc:h2:./res/PRODUCTDB";

  //  Database credentials
  final String User = "";
  final String Pass = "";


  /**
   * A button method that activates another method that connects to
   * database.
   * @param event this is just the event for add product.
   */
  @FXML
  void addProduct(ActionEvent event) throws SQLException {
    //When add product button is pushed, connects to database to add product.
    addToProductDb();
    produceView.setItems(productLine);
    //This prints out info from product table view into produce tab

    loadProductList();
    //loads the product list

  }

  /**
   * A button method for recording product.
   * @param event this is just the event for record product
   */
  @FXML
  void recordProduct(ActionEvent event) throws SQLException {

    //prints out "product recorded" into console

    //This takes the selected item on the listview and grabs the info needed to make a new product
    //record
    try {
    Product productProduced = produceView.getSelectionModel().getSelectedItem();


    int productTally = 1;

    //This is done to keep tallies on the amount of products that are already in product list.
    for (ProductionRecord productionRecord : productionLog2) {

      for (int k = 0; k < productLine.toArray().length; k++) {

        if (productLine.get(k).getId() == productionRecord.getProductId()
            && productProduced.getName().equals(productLine.get(k).getName())) {
          productTally++;
        }

      }
    }

    //This is used to allow any number besides the one given as default.
    int quantity = Integer.parseInt(cmbProduce.getValue());

    //For loop takes input from combobox and uses that to determine how many products were made.

      for (int j = productTally; j < productTally + quantity; j++) {
        ProductionRecord productRec = new ProductionRecord(productProduced, j);
        productionRun.add(productRec);

      }
      addToProductionDb();
      messageProduce.clear();
      messageProduce.appendText("Product Recorded");
      productionRun.clear();
    } catch (NullPointerException e) {

      messageProduce.clear();
      messageProduce.appendText("Pick Produce Please");

    } catch (NumberFormatException e) {
      messageProduce.clear();
      messageProduce.appendText("Input a number please.");
    }
    loadProductionLog();

    //The catches are there in case the user uses the wrong input.



  }

  /**
   * This is the method for creating an employee.
   * @param event this is the event for creating employee.
   */
  @FXML
  void makeEmployee(ActionEvent event) {
    addEmployeeDb();
  }

  /**
   * This is the method for signing in an employee.
   * @param event this is the event for signing in employee
   */
  @FXML
  void signIn(ActionEvent event) {
    signInEmployee();
  }


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

    empTextArea.setEditable(false);
    productionLog.setEditable(false);
    //Prevents the textarea from being editable.

    loadProductList();
    setupProductLineTable();
    produceView.setItems(productLine);
    loadProductionLog();


  }

  private Connection conn = null;
  private Statement stmt = null;


  /**
   * This method is the method used for adding products into the
   * database. It's linked with the button for adding products.
   */
  public void addToProductDb() {

      String product = productName.getText();
      //used to get product name from text box

      String manufacturer = manufactureName.getText();
      //used to get manufacturer name from textbox

      String type = chbxType.getValue();
      //used to get type from choice box



    //Try is used to reset this button press in case nothing was inputted.
    try {
      if (productName.getText().isEmpty() || manufactureName.getText().isEmpty()) {
        throw new RuntimeException();

      }
    } catch (RuntimeException e) {
      messageProductLine.clear();
      messageProductLine.appendText("Text inputs are blank.");
      return;
    }

    messageProductLine.clear();
    messageProductLine.appendText("Product Added");



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

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();

    }
  }

  /**
   * This is the method that connects to database to load the product table.
   */
  private void loadProductList() {
    try {
      //STEP 1: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);
      //Create statement
      stmt = conn.createStatement();

      //Reader is used to go through the database
      String reader = "SELECT * FROM Product";
      //STEP 3: Execute a query
      ResultSet Writer = stmt.executeQuery(reader);
      //Writer is used to write in info from the database into an object.

      //Clears Tableview to input new Table
      produceView.getItems().clear();
      while (Writer.next()) {

        int id = Writer.getInt("id");

        String name = Writer.getString("name");

        String type = Writer.getString("type");

        String manufacturer = Writer.getString("manufacturer");
        // create Product Object
        Product productFromDb = new Product(id, name, manufacturer, ItemType.valueOf(type));

        productLine.add(productFromDb);
      }

      Writer.close();

    } catch (SQLException se) {
      se.printStackTrace();
      Alert a = new Alert(AlertType.ERROR);

      a.show();
    } catch (Exception e) {
      e.printStackTrace();
      }

  }

  /**
   * This is the method for loading up the production log.
   */
  public void loadProductionLog() {
    try {
      //STEP 1: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);
      //Create statement
      stmt = conn.createStatement();

      String reader = "SELECT * FROM Productionrecord";

      //STEP 3: Execute a query
      ResultSet writer = stmt.executeQuery(reader);
      productionLog2.clear();
      //Clears productionLog2.

      while (writer.next()) {
        int pNum = writer.getInt("production_num");
        int pId = writer.getInt("product_id");
        String sNum = writer.getString("serial_num");
        Date dProduced = writer.getDate("date_produced");
        // create ProductionRecord Object
        ProductionRecord productionRecordFromDb = new ProductionRecord(pNum, pId, sNum, dProduced);
        productionLog1.add(productionRecordFromDb);
        productionLog2.add(productionRecordFromDb);
      }
      productionLog.clear();
      showProduction();
      writer.close();
    } catch (SQLException se) {
      se.printStackTrace();
      Alert a = new Alert(AlertType.ERROR);

      a.show();
    }
  }

  /**
   * This is the method for adding production into the database.
   * This is linked with the record production button.
   */
  public void addToProductionDb() {
    try {
      // STEP 1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);
      //Acknowledged as bug, but nothing can be done here for now.
      stmt = conn.createStatement();
      //STEP 3: Execute a query
      String insertSql;
      for (ProductionRecord productionRecord : productionRun) {

        insertSql = "INSERT INTO ProductionRecord(product_id,serial_num,date_produced) "
            + "VALUES (?, ?, ? )";
        //sql statement used to add into product table
        Date sDate = new Date(productionRecord.getProdDate().getTime());

        PreparedStatement ps = conn.prepareStatement(insertSql);



        ps.setInt(1, productionRecord.getProductId());
        ps.setString(2, productionRecord.getSerialNum());
        ps.setDate(3, sDate);

        ps.executeUpdate();
        //Used to execute sql statement.

        // STEP 4: Clean-up environment
        ps.close();

      }
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();

    }
  }

  /**
   * This loads up the product line table.
   */
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

  /**
   * This shows the production log in the textfield for produce.
   */
  public void showProduction() {

    //Goes through an arraylist that has the updated list of produced products.
    for (ProductionRecord productionRecord : productionLog1) {

      //Goes through productLine to look for matching Product ID
      for (int k = 0; k < productLine.toArray().length; k++) {

        //If all the properties match, Gets the name and prints it out into
        //the production Text Field.
        if (productLine.get(k).getId() == productionRecord.getProductId()) {
          productionLog.appendText(
              "Prod. Num: " + productionRecord.productionNumber + " Product Name: "
                  + productLine.get(k).getName()
                  + " Serial Num: " + productionRecord.getSerialNum() + " Date: "
                  + productionRecord.getProdDate() + '\n');
        }

      }
    }

    productionLog1.clear();
    //Deletes the productionLog1 list so it doesnt duplicate list again.
  }

  /**
   * This is the method for adding employees into the database.
   * It is linked with create button in employees tab.
   */
 public void addEmployeeDb() {
    try {
     // STEP 1: Register JDBC driver
      Class.forName(Jdbc_Driver);

      //STEP 2: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);

      stmt = conn.createStatement();
      //STEP 3: Execute a query
      String insertSql;

      //clears textarea for employee
      empTextArea.clear();

      String name = empName.getText();

      String password = empPassword.getText();

      try {
        //Determines if the empName text field is empty
        //Throws an exception so the button doesn't continue with function
        if (empName.getText().isEmpty()) {

          empTextArea.clear();
          empTextArea.appendText("Input Name please.");
          throw new RuntimeException();

        }
      }catch (RuntimeException e) {

       return;

      }

      Employee employee = new Employee(name,password);

      empTextArea.appendText(employee.toString());

      insertSql = "INSERT INTO Employee(name, username, password, email) "
          + "VALUES (?, ?, ?, ? )";

      PreparedStatement ps = conn.prepareStatement(insertSql);

      ps.setString(1, employee.name);
      ps.setString(2, employee.userName);
      ps.setString(3, employee.password);
      ps.setString(4, employee.email);

        ps.executeUpdate();
        //Used to execute sql statement.

        // STEP 4: Clean-up environment
        ps.close();



    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();

    }
  }

  /**
   * This is the method for signing in employees.
   * It is linked with the sign-in button in the employees
   * tab.
   */
  public void signInEmployee() {
   //Creates an arraylist so if employee signs in, it will see if
    // they're in database.
    ArrayList<Employee> employees = new ArrayList<>();


    try {
      //STEP 1: Open a connection
      conn = DriverManager.getConnection(Db_Url, User, Pass);
      //Create statement
      stmt = conn.createStatement();
      String reader = "SELECT * FROM Employee";
      //STEP 3: Execute a query
      ResultSet writer = stmt.executeQuery(reader);



      while (writer.next()) {

        // Reads from database
        String name = writer.getString("name");
        String password = writer.getString("password");
        // create Employee Object
        Employee employee = new Employee(name, password);
        employees.add(employee);

      }
      writer.close();

      String username = usernameText.getText();

      String password = empPassword.getText();

      //this ensures that the employee put in the correct credentials.
      for (Employee employee : employees) {
        if (employee.userName.equals(username) && employee.password.equals(password)) {
          empTextArea.clear();
          empTextArea.appendText("Welcome back! " + employee.name + ".");

        } else {
          empTextArea.clear();
          empTextArea.appendText("Incorrect username or password.");
        }
      }

    } catch (SQLException se) {
      se.printStackTrace();
      Alert a = new Alert(AlertType.ERROR);

      a.show();
    }

  }

}






