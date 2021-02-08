package henrys;

import java.util.ArrayList;

import static henrys.Utilities.formatDoubleToPrecisionOfTwo;

public class StockItem {
  private int itemId;
  private String itemName;
  private double pricePerUnit = 0.0;

  public void setName(String itemName) {
    this.itemName = itemName;
  }

  public Double getItemPricePerUnit() {
    return pricePerUnit;
  }

  public void setItemId(int itemId) {
    this.itemId = itemId;
  }

  public int getItemId() {
    return this.itemId;
  }

  public Object getItemName() {
    return this.itemName;
  }

  public void setPricePerUnit(double pricePerUnit) {
    this.pricePerUnit = pricePerUnit;
  }

  double formatStockItemTotalCost() {
    return formatDoubleToPrecisionOfTwo(pricePerUnit);
  }

  public static long countOfStockItemsByType(ArrayList<StockItem> purchasedItems, Constants.ItemName itemName) {
    return purchasedItems.stream().filter(item -> item.getItemId() == itemName.getValue()).count();
  }
}
