package henrys;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {
  @Test
  void starting_register_displays_start_message() {
    StubCommandLineUI ui = new StubCommandLineUI();
    Register register = new Register(ui);

    register.start();

    assertTrue(ui.showedStartMessage());
  }
}