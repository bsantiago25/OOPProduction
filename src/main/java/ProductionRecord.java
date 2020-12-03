import java.util.Date;

public class ProductionRecord {
  int productionNumber;
  int productId;
  String serialNumber;
  Date dateProduced;
  String count;





  //database constructor
  public ProductionRecord(int productionNumber, int productId, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productId = productId;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;

  }




  public ProductionRecord(Product productProduced, int quantity) {
    this.productId = productProduced.getId();
    productionNumber = 0;
    dateProduced = new Date();
    count = String.format("%05d", quantity);
    serialNumber = generateSerialNumber(productProduced);
  }


  public int getProductionNum() {
    return productionNumber;
  }

  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  public int getProductId() {
    return productId;
  }

  public void setProductId(int productId) {
    this.productId = productId;
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

  public String generateSerialNumber(Product productProduced) {

    return productProduced.getManufacturer().substring(0, 3).toUpperCase()
        + productProduced.getType().label + count;
  }


  @Override
  public String toString() {
    return "Prod. Num: " + getProductionNum() + " Product ID: " + getProductId() + " Serial Num: "
        + getSerialNum() + " Date: " + getProdDate() + '\n';
  }

}


