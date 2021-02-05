package henrys;

public class StockItem {
  private int itemId = 0;
  private int quantityPurchased = 0;

  public void setQuantityPurchased(int quantityPurchased) {
    this.quantityPurchased = quantityPurchased;
  }

  public int getItemId() {
    return itemId;
  }

  public Integer getQuantityPurchased() {
    return quantityPurchased;
  }
}
