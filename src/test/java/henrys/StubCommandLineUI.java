package henrys;

import java.util.Map;

public class StubCommandLineUI implements RegisterUI {

  private boolean showedStartMessage;
  boolean showedItemsList;
  boolean promptedForInventoryItemSelection;

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

  public boolean showedStartMessage(){
    return showedStartMessage;
  }

  public boolean showedItemsList() {
    return showedItemsList;
  }

  public boolean promptedForInventoryItemSelection() {
    return promptedForInventoryItemSelection;
  }
}
