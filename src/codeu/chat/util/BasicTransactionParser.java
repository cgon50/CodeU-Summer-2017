package codeu.chat.util;

import java.io.IOException;
import codeu.chat.util.TransactionObject;
import java.util.Scanner;

public class BasicTransactionParser {

  private String type = null;

  public TransactionObject initParse(String input) throws IOException {
    Scanner in = new Scanner(input);
    if (in.hasNext()) {
      type = in.next();
    }
    else {
      // ??????? maybe a better error message???
      throw new IOException("Error: Cannot read input String for parsing.");
    }
    in.close();
    return determineTransactionObject();
  }

  private TransactionObject determineTransactionObject() throws IOException {
    if (type.equals("ADD-USER")) {
      return TransactionObject.USER;
    }
    else if (type.equals("ADD-MESSAGE")) {
      return TransactionObject.MESSAGE;
    }
    else if (type.equals("ADD-CONVERSATION")) {
      return TransactionObject.CONVERSATION;
    }
    else {
      throw new IOException("This String does not represent a valid object or is not in proper format for parsing.");
    }
  }

}
