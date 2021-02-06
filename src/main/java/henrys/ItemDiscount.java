package henrys;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static henrys.StockItem.ItemName.BREAD;

public class ItemDiscount {
  ArrayList<StockItem> stockItemsDB = new StockItemRepository().findAll();;

  public Double applyDiscounts(ArrayList<StockItem> purchasedItems, Double baseTotal) {
    Double totalWithDiscounts = 0.0;
    totalWithDiscounts += twoSoupGetOneLoafBreadHalfOff(purchasedItems, baseTotal);
    return totalWithDiscounts;
  }

  public Double twoSoupGetOneLoafBreadHalfOff(ArrayList<StockItem> purchasedItems, Double totalWithoutDiscounts) {
    Stream<StockItem> soupQuantity = purchasedItems.stream().filter(item -> item.getItemId() == BREAD.getValue());

    if(soupQuantity.count() >= 2) {
      StockItem bread = stockItemsDB.get(BREAD.getValue());
      Double discountedPrice = (totalWithoutDiscounts += -(bread.getItemPricePerUnit() / 2));
      return discountedPrice;
    } else {
      return totalWithoutDiscounts;
    }
  }


}
