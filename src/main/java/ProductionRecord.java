/**
 * This class makes the production record of products.
 * @author Brandon Santiago
 */
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

  /**
   * This gets the production number of product.
   * @return returns the production number.
   */
  public int getProductionNum() {
    return productionNumber;
  }

  /**
   * This setd the name of the production number.
   * @param productionNumber production number of product.
   */
  public void setProductionNum(int productionNumber) {
    this.productionNumber = productionNumber;
  }

  /**
   * This gets the product ID.
   * @return returns the ID of product.
   */
  public int getProductId() {
    return productId;
  }

  /**
   * This set the id of a product.
   * @param productId product ID number.
   */
  public void setProductId(int productId) {
    this.productId = productId;
  }

  /**
   * This gets the serial number of product produced.
   * @return serial number of the product.
   */
  public String getSerialNum() {
    return serialNumber;
  }

  /**
   * Sets the serial number of a product.
   * @param serialNumber serial number of product.
   */
  public void setSerialNum(String serialNumber) {
    this.serialNumber = serialNumber;
  }

  /**
   * gets the date of the product produced.
   * @return date product was produced.
   */
  public Date getProdDate() {
    return dateProduced;
  }

  /**
   * sets the date the product was produced.
   * @param dateProduced date of the produced product.
   */
  public void setProdDate(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  /**
   * This method generates the serial number for individual products.
   * They are also unique and sequential, no wo products have the same
   * serial number.
   * @param productProduced The product thats being produced goes in here.
   * @return returns the serial number of the product.
   */
  public String generateSerialNumber(Product productProduced) {

    return productProduced.getManufacturer().substring(0, 3).toUpperCase()
        + productProduced.getType().label + count;
  }


  /**
   * This toString prints out all the info for production created into database.
   * @return prints out production info.
   */
  @Override
  public String toString() {
    return "Prod. Num: " + getProductionNum() + " Product ID: " + getProductId() + " Serial Num: "
        + getSerialNum() + " Date: " + getProdDate() + '\n';
  }

}


