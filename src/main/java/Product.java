/**
 * This is the product class. This creates the products in the
 * GUI.
 * @author Brandon Santiago
 */
public class Product implements Item {

  private int id;
  private String name;
  private String manufacturer;
  private final ItemType type;





  Product(String name, String manufacturer, ItemType type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  Product(int id, String name, String manufacturer, ItemType type) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  public String toString() {
    return "Name: " + name + "\n" + "Manufacturer: " + manufacturer + "\n" + "Type: "
        + type.label;
  }

  /**
   * This gets the id of the product.
   * @return id of product.
   */
  public int getId() {
    return id;
  }

  /**
   * This sets the id of the product.
   * @param id id of product.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * This gets the manufacturer name for the product.
   * @return returns manufacturer.
   */
  public String getManufacturer() {
    return manufacturer;
  }

  /**
   * This sets the manufacturer of the product.
   * @param manufacturer manufacturer of product.
   */
  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  /**
   * This gets the name of the product.
   * @return returns product name.
   */
  public String getName() {
    return name;
  }

  /**
   * This sets the name of product.
   * @param name name of the product.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * gets the type of the product.
   * @return returns type of product.
   */
  public ItemType getType() {
    return type;
  }

}




