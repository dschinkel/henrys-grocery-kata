package henrys;

import java.util.Map;

public class StubCommandLineUI implements RegisterUI {

  private boolean showedStartMessage;
  private boolean showedItemsList;
  private boolean promptedForInventoryItemSelection;
  private boolean showedTotalPrice;

  public void displayStartMessage() {
    showedStartMessage = true;
  }

  @Override
  public void displayItemsForSelection(Map<Integer, String> stockItems) {
    showedItemsList = true;
  }

  @Override
  public String promptForInventoryItemSelection() {
    promptedForInventoryItemSelection = true;
    return "";
  }

  @Override
  public void promptForInventoryItemQuantityMessage() {
  }

  @Override
  public String promptForInventoryItemQuantity() {
    return null;
  }

  @Override
  public Map<Integer, Integer> inputAllItems() {
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
  public void displayTotalPrice(String price) {
    showedTotalPrice = true;
  }

  public boolean showedStartMessage(){
    return showedStartMessage;
  }

  public boolean showedItemsList() {
    return showedItemsList;
  }

  public boolean showedTotalPrice(){
    return showedTotalPrice;
  }

  public boolean promptedForInventoryItemSelection() {
    return promptedForInventoryItemSelection;
  }
}
