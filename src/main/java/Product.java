public class Product implements Item {

  private int id;
  private String name;
  private String manufacturer;
  private ItemType type;





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

  public int getId() {
    return id;
  }

  public void setID(int id){this.id=id;}

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ItemType getType()
  {
    return type;
  }

}

class Widget extends Product implements Item{

  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }


}