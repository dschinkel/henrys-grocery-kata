package henrys;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommandLineUITest {
  private ByteArrayOutputStream output;

  @BeforeEach
  void setUp() {
    output = new ByteArrayOutputStream();
  }

  @Test
  void displayStartMessage() {
    CommandLineUI ui = new CommandLineUI(output);
    ui.displayStartMessage();
    assertTrue(output.toString().contains("READY FOR CHECKOUT!\n"));
  }

  @Test
  void display_items_toSelectFrom() {
    CommandLineUI ui = new CommandLineUI(output);
    Map<Integer, String> stockItems = new HashMap<Integer, String>();
    stockItems.put(0, "soup");
    stockItems.put(1, "bread");
    stockItems.put(2, "milk");
    stockItems.put(3, "apples");
    String finalMessage = "Please specify an item by typing the item number: 0:soup 1:bread 2:milk 3:apples ";

    ui.displayItemsForSelection(stockItems);

    assertEquals(finalMessage, output.toString());
  }

}
