package henrys;

public class StockItem {
  private int quantityPurchased = 0;
  private int itemId;

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
}
