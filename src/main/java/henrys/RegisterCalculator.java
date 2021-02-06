package henrys;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class RegisterCalculator {
    public Double tallyTotalForPurchasedStockItems(ArrayList<StockItem> purchasedItems) {
    Double total = 0.00;

    for (StockItem stockItem : purchasedItems) {
      Integer stockItemId =ga stockItem.getItemId();
      total += stockItem.calculateStockItemTotalCost(stockItemId);
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
