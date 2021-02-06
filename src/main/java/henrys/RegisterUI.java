package henrys;

import java.util.ArrayList;
import java.util.Map;

public interface  RegisterUI {
  void displayStartMessage();
  void displayItemsForSelection(ArrayList<StockItem> stockItems);
  String promptForInventoryItemSelection();
  void promptForInventoryItemQuantityMessage();
  String promptForInventoryItemQuantity();
  Map<Integer, Integer> inputAllItems();
  void promptForDoneMessage();

  String promptforDone();

  void displayTotalPrice(String price);
}