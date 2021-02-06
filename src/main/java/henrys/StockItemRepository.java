package henrys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StockItemRepository {

  public ArrayList<StockItem> findAll() {
    ArrayList<StockItem> stockItems = new ArrayList<>();
    stockItems.add(createStockItem(0, "soup", .65));
    stockItems.add(createStockItem(1, "bread", .80));
    stockItems.add(createStockItem(2, "milk", 1.30));
    stockItems.add(createStockItem(3, "apples", 1.30));

    return stockItems;
  }

  private StockItem createStockItem(Integer itemId, String itemName, Double pricePerUnit) {
    StockItem stockItem = new StockItem();
    stockItem.setItemId(itemId);
    stockItem.setName(itemName);
    stockItem.setPrice(pricePerUnit);

    return stockItem;
  }
}
