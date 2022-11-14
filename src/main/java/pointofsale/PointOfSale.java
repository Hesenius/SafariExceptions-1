package pointofsale;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;

class ModemDidNotConnectException extends Exception {}

class ModemUtils {
  public static void dialModem(String phone) throws ModemDidNotConnectException {

  }
}

public class PointOfSale {
  public static boolean useModem = false;

  public static void collectCCPayment()
      throws ModemDidNotConnectException, IOException {
    // process payment
    if (useModem) {
      // retry three times
      ModemUtils.dialModem("800-PAY-MEXX");
    } else {
      // retry??
      Socket s = new Socket("127.0.0.1", 9000);
    }
    // communicate payment request..

  }

  public static void sellSomething() {
    // determine price and discounts
    // select payment mechanism
    // if credit card...
    try (
        // "resources" must implement AutoCloseable
        // ensures proper closing unless infrastructure collapses
        // without messing about with finally
        FileInputStream fis = new FileInputStream("SupportingInfo.txt");
        FileWriter fw = new FileWriter("StoreHere.txt");
        ) {
      collectCCPayment();
    } catch (IOException
          /* | FileNotFoundException*/
             | ModemDidNotConnectException e) {
      // common code for "couldn't connect to server"
      // siblings only, not parent/child
    }
      // Why don't we like this, for unifying the handling?
//    } catch (Exception e) // Catches almost EVERYTHING, including NPE {
      // ask for human intervention
      // wiggle phone connection
      // retry something else?
//    } catch (ModemDidNotConnectException e) {
      // ask for human intervention
      // wiggle phone connection
      // retry something else?
//    } catch (IOException ioe) {
// PLEASE NEVER DUPLICATE CODE, NOT EVEN SIMPLE CODE
    // ask for human intervention
    // wiggle phone connection
    // retry something else?
    }
  }
}
