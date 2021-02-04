package henrys;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResourceTest {
  @Test
  void startRegister_displaysStartMessage() {
    StubCommandLineUI ui = new StubCommandLineUI();
    Register register = new Register(ui);

    register.start();

    assertTrue(ui.showedStartMessage());
  }
}