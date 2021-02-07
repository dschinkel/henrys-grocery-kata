package henrys;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineUI implements RegisterUI {
  private final PrintStream outputStream;
  private final InputStream inputStream;
  private Scanner scanner;
  Map<Integer, Integer> enteredItems = new HashMap<>();
  private ArrayList<StockItem> stockItems;

  public CommandLineUI(InputStream input, OutputStream output) {
    this.inputStream = input;
    this.outputStream = new PrintStream(output);
    this.scanner = new Scanner(inputStream);
  }

  public CommandLineUI() {
    this.inputStream = System.in;
    this.outputStream = System.out;
    this.scanner = new Scanner(inputStream);
  }

  public void setStockItems(ArrayList<StockItem> stockItems){
    this.stockItems = stockItems;
  }

  public void displayStartMessage() {
    outputStream.println("READY FOR CHECKOUT!");
  }

  public void displayItemsForSelection(ArrayList<StockItem> stockItems) {
    this.stockItems = stockItems;
    StringBuilder message = new StringBuilder("Items in Stock: ");
    stockItems.forEach(stockItem ->
      message.append(String.format("%d:%s ", stockItem.getItemId(), stockItem.getItemName()))
    );
    outputStream.println(message.toString());
  }
  Object[] inputs;
  @Override
  public Object[] inputAllItems() {
    String itemId = promptForInventoryItemSelection();
    String itemQty = promptForInventoryItemQuantity();
    String done = promptforDone();

    if(done.equals("d")) {
      enteredItems.put(Integer.parseInt(itemId), Integer.parseInt(itemQty));
      String date = promptforDate();
      inputs = combinePurchasedItemsAndPurchasedDate(date);
      return inputs;
    }

    enteredItems.put(Integer.parseInt(itemId), Integer.parseInt(itemQty));
    inputAllItems();
    return inputs;
  }

  @Override
  public String promptforDone() {
    promptForDoneMessage();
    return getUserInput();
  }

  @Override
  public void displayTotalPrice(double price) {
    String message = String.format("Total Price is %s", price);
    outputStream.println(message);
  }

  @Override
  public void promptForDoneMessage() {
    outputStream.println("Press d for done or c to continue:");
  }

  @Override
  public String promptForInventoryItemSelection() {
    promptForInventoryItem();
    String input = getUserInput();
    displaySelectedItem(input);
    return input;
  }

  private void promptForInventoryItem() {
    outputStream.println("Please Specify an Item by Number:");
  }

  @Override
  public String promptForInventoryItemQuantity() {
    promptForInventoryItemQuantityMessage();
    return getUserInput();
  }

  @Override
  public void promptForInventoryItemQuantityMessage() {
    outputStream.println("Please Specify a Qty (e.g. 1, 2, 3, etc.) for this item:");
  }

  private String getUserInput() {
    String input = "";
    while (scanner.hasNextLine()) {
      input = scanner.nextLine();
      break;
    }
    return input;
  }

  public String promptforDate() {
    outputStream.println("Please Specify a purchased date in a format of mm/dd/yyyy):");
    return getUserInput();
  }

  private Object[] combinePurchasedItemsAndPurchasedDate(String date) {
    return new Object[]{enteredItems, date};
  }

  private void displaySelectedItem(String input) {
    outputStream.printf("You selected: %s \n", stockItems.get(Integer.parseInt(input)).getItemName());
  }

}
