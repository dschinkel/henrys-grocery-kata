package henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Formatter {
  public static Double formatDoubleToPrecisionOfTwo(Double value) {
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }
}
