package henrys;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Formatter {
  public static Double formatDoubleToPrecisionOfTwo(Double value) {
    BigDecimal bd = new BigDecimal(value);
    bd = bd.setScale(2, RoundingMode.HALF_UP);
    return bd.doubleValue();
  }

  public static Double subtractDoubles(Double value1, Double value2) {
    BigDecimal bd1 = new BigDecimal(value1);
    BigDecimal bd2 = new BigDecimal(value2);
    bd1 = bd1.setScale(2, RoundingMode.HALF_UP);
    bd2 = bd2.setScale(2, RoundingMode.HALF_UP);
    return bd1.subtract(bd2).doubleValue();
  }
}
