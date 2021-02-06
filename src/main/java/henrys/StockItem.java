package henrys;

import java.util.HashMap;
import java.util.Map;

import static henrys.StockItem.ItemName.*;

public class StockItem {
  private int quantityPurchased = 0;
  private int itemId;
  private String itemName;
  private Double pricePerUnit = 0.0;

  public void setName(String itemName) {
    this.itemName = itemName;
  }

  public Double getItemPricePerUnit() {
    return pricePerUnit;
  }

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

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public void setPricePerUnit(Double pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

  public int getItemId() {
    return this.itemId;
  }

  public Object getItemName() {
    return this.itemName;
  }

  public Integer getQuantityPurchased() {
    return quantityPurchased;
  }

  Double calculateStockItemTotalCost(Integer stockItemId) {
    return getQuantityPurchased() * pricePerUnit;
  }
}
