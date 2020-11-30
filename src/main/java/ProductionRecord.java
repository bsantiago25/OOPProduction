import java.util.Date;

public class ProductionRecord {
  int productionNumber;
  int productID;
  String serialNumber;
  Date dateProduced;
  private static int count=1;
  String c;


    //database constructor
  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  public ProductionRecord(int productID)
  {
    productionNumber=0;
    serialNumber = "0";
    dateProduced = new Date();
  }


  public ProductionRecord(Product productProduced) {
  this.productionNumber = count++;
  dateProduced = new Date();
  this.productID = productProduced.getId();
  serialNumber = generateSerialNumber(productProduced);
  }



  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductID() {
    return productID;
  }

  public void setProductID(int productID) {
    this.productID = productID;
  }

  public String getSerialNum() {
    return serialNumber;
  }

  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  public Date getProdDate() {
    return dateProduced;
  }

  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public String generateSerialNumber(Product productProduced)
  {
    return productProduced.getManufacturer().substring(0,3).toUpperCase() + productProduced.getType().label + "0000" + productID;
  }


  @Override
  public String toString() {
    return "Prod. Num: " + getProductionNum() + " Product ID: " + getProductID() + " Serial Num: "
        + getSerialNum() + " Date: " + getProdDate() +'\n';
  }

}


