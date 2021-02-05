package henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculator {
  public Double calculateTotalPriceForItems(ArrayList<StockItem> purchasedItems) {
    Double total = tallyTotal(purchasedItems);
    return total;
  }


  private Double tallyTotal(ArrayList<StockItem> purchasedItems) {
    Double total = 0.00;

    for (StockItem stockItem : purchasedItems) {
      Integer stockItemId = stockItem.getItemId();
      if(stockItemId.equals(StockItem.ItemName.SOUP.valueOf("SOUP").ordinal())) {
        total += stockItem.calculateTotalForSoup();
      }
      if(stockItemId.equals(StockItem.ItemName.BREAD.valueOf("BREAD").ordinal())){
        total += stockItem.calculateTotalForBread();
      }
      if(stockItemId.equals(StockItem.ItemName.MILK.valueOf("MILK").ordinal())){
        total += stockItem.calculateTotalForMilk();
      }
      if(stockItemId.equals(StockItem.ItemName.APPLES.valueOf("APPLES").ordinal())){
        total += stockItem.calculateTotalForApples();
      }
    }

    Double formattedTotal = formatDoubleToPrecisionOfTwo(total);
    return formattedTotal;
  }

  private Double formatDoubleToPrecisionOfTwo(Double value) {
    DecimalFormat formatDecimal = new DecimalFormat("##.00");
    Double formattedValue = Double.parseDouble(formatDecimal.format(value));
    return formattedValue;
  }

}
