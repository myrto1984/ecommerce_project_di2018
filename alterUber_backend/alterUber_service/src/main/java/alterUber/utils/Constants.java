package alterUber.utils;

public class Constants {

  public enum LUGGAGE_VOL {

    HANDBAGS {
      @Override
      public String toLuggageVolString() {
        return "χειραποσκευές";
      }
    },
    FEWSUITCASES {
      @Override
      public  String toLuggageVolString() {
        return "1-3 βαλίτσες";
      }
    },
    HEAVYLUGGAGE {
      @Override
      public String toLuggageVolString() {
        return "πάνω από 3 βαλίτσες";
      }
    };

    public abstract String toLuggageVolString();
  }

  public static final double CHARGE_PER_KM = 0.60;
  public static final double VAT = 0.24;


}
