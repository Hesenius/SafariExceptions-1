package optional;

import java.util.Map;

public class SimpleEx {
  public static String getFirstName() {
    return "Freddy";
  }

//  public static String [] getFirstName() {
//    if (Math.random() > 0.5) {
//      return new String[]{"Freddy"};
//    } else {
//      return new String[]{};
//    }
//  }

  public static void main(String[] args) {
    Map<String, String> names = Map.of("Fred", "Jones");
    String first = getFirstName();
//    String [] first = getFirstName();
//    for (String f : first) { // won't do anything if it's not there!!!
    if (first != null) {
      String last = names.get(first);
      if (last != null) {
        String upper = last.toUpperCase();
        if (upper != null) {
          String message = "Dear " + upper;
          if (message != null) {
            System.out.println(message);
          }
        }
      }
    }
  }
}
