public abstract class Product implements Item {

  private int Id;
  private ItemType Type;
  private String Manufacturer;
  private String Name;



  Product(String name, String manufacturer, ItemType type) {
    this.Name = name;
    this.Manufacturer = manufacturer;
    this.Type = type;
  }


  public String toString() {
    return "Name: " + Name + "\n" + "Manufacturer: " + Manufacturer + "\n" + "Type: "
        + Type.label;
  }

  public int getId() {
    return Id;
  }

  public String getManufacturer() {
    return Manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    Manufacturer = manufacturer;
  }

  public String getName() {
    return Name;
  }

  public void setName(String name) {
    Name = name;
  }
}

class Widget extends Product implements Item{

  Widget(String name, String manufacturer, ItemType type) {
    super(name, manufacturer, type);
  }


}