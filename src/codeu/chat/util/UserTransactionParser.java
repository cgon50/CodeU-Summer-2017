package codeu.chat.util;

import java.io.IOException;
import java.util.Scanner;
import codeu.chat.util.TransactionObject;
import codeu.chat.common.User;
import codeu.chat.util.Uuid;
import codeu.chat.util.Time;

public class UserTransactionParser {

  String uuid;
  StringBuilder nameBuilder;
  long time;

  public User parse(String input) throws IOException {
    Scanner in = new Scanner(input);
    if (in.hasNext()) {
      uuid = in.next();
    }
    else {
      throw new IOException("Error: cannot read in a Uuid for this User.");
    }

    while (!in.hasNextLong() && in.hasNext()) {
      nameBuilder.append(in.next());
    }
    // This check is to make sure the transaction string has a timestamp or is properly formatted.
    if (!in.hasNext()) {
      throw new IOException("Error: The transaction does not have a timestamp or is improperly formatted.");
    }
    // we should probably test this at one point
    String name = nameBuilder.toString().substring(1, nameBuilder.toString().length() - 1);

    if (in.hasNextLong()) {
      time = in.nextLong();
    }
    else {
      throw new IOException("Error: cannot read in an appropriate timestamp for this User.");
    }

    Uuid uid = Uuid.parse(uuid);
    Time creation = Time.fromMs(time);
    return new User(uid, name, creation);
  }

}
