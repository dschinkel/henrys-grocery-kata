package henrys;

import static henrys.StockItem.ItemName.*;

public class StockItem {
  private int quantityPurchased = 0;
  private int itemId;

  enum ItemName {
    SOUP(0), BREAD(1), MILK(2), APPLES(3);
    private int value = 0;

    ItemName(int value) {
      this.value = value;
    }
    public int getValue() {
      return value;
    }
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

  Double calculateStockItemTotalCost(Double total, Integer stockItemId) {
    if(stockItemId.equals(SOUP.getValue())) {
      total += getQuantityPurchased() * .65;
    }
    if(stockItemId.equals(BREAD.getValue())){
      total += getQuantityPurchased() * .80;
    }
    if(stockItemId.equals(MILK.getValue())){
      total += getQuantityPurchased() * 1.30;
    }
    if(stockItemId.equals(APPLES.getValue())){
      total += getQuantityPurchased() * .10;
    }
    return total;
  }
}
