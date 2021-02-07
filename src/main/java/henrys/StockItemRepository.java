package henrys;

import java.util.ArrayList;

import static henrys.Utilities.createStockItem;

public class StockItemRepository {

  public ArrayList<StockItem> findAll() {
    ArrayList<StockItem> stockItems = new ArrayList<>();
    stockItems.add(createStockItem(0, "soup", .65));
    stockItems.add(createStockItem(1, "bread", .80));
    stockItems.add(createStockItem(2, "milk", 1.30));
    stockItems.add(createStockItem(3, "apples", .10));

    return stockItems;
  }
}
