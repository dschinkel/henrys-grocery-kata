package henrys;

import java.util.ArrayList;

public interface  RegisterUI {
  void displayStartMessage();
  void displayItemsForSelection(ArrayList<StockItem> stockItems);
  String promptForInventoryItemSelection();
  void promptForInventoryItemQuantityMessage();
  String promptForInventoryItemQuantity();
  Object[] inputAllItems();
  void promptForDoneMessage();
  String promptforDone();
  void displayTotalPrice(Double price);
}