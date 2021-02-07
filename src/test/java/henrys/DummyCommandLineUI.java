package henrys;

import java.util.ArrayList;

public class DummyCommandLineUI implements RegisterUI {

  public void displayStartMessage() { }

  @Override
  public void displayItemsForSelection(ArrayList<StockItem> stockItems) {}

  @Override
  public String promptForInventoryItemSelection() {
    return "";
  }

  @Override
  public void promptForInventoryItemQuantityMessage() {}

  @Override
  public String promptForInventoryItemQuantity() {
    return null;
  }

  @Override
  public Object[] inputAllItems() {
    return null;
  }

  @Override
  public void promptForDoneMessage() {
  }

  @Override
  public String promptforDone() {
    return null;
  }

  @Override
  public void displayTotalPrice(Double price) {
  }
}
