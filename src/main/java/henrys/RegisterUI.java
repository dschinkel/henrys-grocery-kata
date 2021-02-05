package henrys;

import java.util.Map;

public interface  RegisterUI {
  void displayStartMessage();
  void displayItemsForSelection(Map<Integer, String> stockItems);
  String promptForInventoryItemSelection();
}