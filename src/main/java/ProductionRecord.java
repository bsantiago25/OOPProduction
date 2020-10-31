import java.util.Date;

public class ProductionRecord {
  int productionNumber;
  int productID;
  String serialNumber;
  Date dateProduced;
  String c;



  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }


  public ProductionRecord(Product productProduced, int quantity) {
  this.productID = productID;
  productionNumber = 0;
  dateProduced = new Date();
  c = String.format("%05d",quantity);
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
    return productProduced.getManufacturer().substring(0,3) + productProduced.getType().label + c;
  }


  @Override
  public String toString() {
    return "Prod. Num: " + getProductionNum() + " Product ID: " + getProductID() + " Serial Num: "
        + getSerialNum() + " Date: " + getProdDate();
  }

}


