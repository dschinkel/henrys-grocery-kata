package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandLineUITest {
  private ByteArrayOutputStream output;
  private StubInputStream input;

  @BeforeEach
  void setUp() {
    output = new ByteArrayOutputStream();
    input = new StubInputStream();
  }

  @Test
  void displayStartMessage() {
    CommandLineUI ui = new CommandLineUI(input, output);
    ui.displayStartMessage();
    assertTrue(output.toString().contains("READY FOR CHECKOUT!\n"));
  }

  @Test
  void display_items_toSelectFrom() {
    CommandLineUI ui = new CommandLineUI(input, output);
    Map<Integer, String> stockItems = new HashMap<Integer, String>();
    stockItems.put(0, "soup");0
    stockItems.put(1, "bread");
    stockItems.put(2, "milk");
    stockItems.put(3, "apples");
    String finalMessage = "Items in Stock: 0:soup 1:bread 2:milk 3:apples \n";

    ui.displayItemsForSelection(stockItems);

    assertEquals(finalMessage, output.toString());
  }

  @Test
  void promptFor_InventoryItem_Selection() {
    CommandLineUI ui = new CommandLineUI(input, output);
    String promptForInventoryItemMessage = "Please Specify an Item by Number:\n";
    input.setInputStream("0");

    String userInput = ui.promptForInventoryItemSelection();

    assertEquals("0", userInput);
    assertEquals(promptForInventoryItemMessage, output.toString());
  }
}
