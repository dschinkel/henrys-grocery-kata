package henrys;

import java.util.HashMap;
import java.util.Map;

public class StockItemRepository {

  public Map<Integer, String> findAll(){
    Map<Integer, String> stockItems = new HashMap<Integer, String>();
    stockItems.put(0, "soup");
    stockItems.put(1, "bread");
    stockItems.put(2, "milk");
    stockItems.put(3, "apples");

    return stockItems;
  }
}
