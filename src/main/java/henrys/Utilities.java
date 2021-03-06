package henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utilities {
  public static double formatDoubleToPrecisionOfTwo(double value) {
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  public static StockItem createStockItem(Integer itemId, String itemName, double pricePerUnit) {
    StockItem stockItem = new StockItem();
    stockItem.setItemId(itemId);
    stockItem.setName(itemName);
    stockItem.setPricePerUnit(pricePerUnit);

    return stockItem;
  }
}

