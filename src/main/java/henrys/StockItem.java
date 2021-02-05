package henrys;

public class StockItem {
  private int quantityPurchased = 0;
  private int itemId;
  enum ItemName {
    SOUP,
    BREAD,
    MILK,
    APPLES
  }


  public void setQuantityPurchased(int quantityPurchased) {
    this.quantityPurchased = quantityPurchased;
  }

  public Integer getQuantityPurchased() {
    return quantityPurchased;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getItemId() {
    return itemId;
  }

  double calculateTotalForSoup() {
    return getQuantityPurchased() * .65;
  }

  Double calculateTotalForBread() {
    return getQuantityPurchased() * .80;
  }

  Double calculateTotalForMilk() {
    return getQuantityPurchased() * 1.30;
  }

  Double calculateTotalForApples() {
    return getQuantityPurchased() * .10;
  }
}
