package codeu.chat.util;

import java.io.IOException;
import java.util.Scanner;
import codeu.chat.util.TransactionObject;
import codeu.chat.util.Uuid;
import codeu.chat.util.Time;
import codeu.chat.common.Message;

public class MessageTransactionParser {

  String uuid;
  long time;
  String authorUuid;
  StringBuilder messageBuilder;

  public Message parser(String input) throws IOException {
    Scanner in = new Scanner(input);

    if (in.hasNext()) {
      uuid = in.next();
    }
    else {
      throw new IOException("Error: cannot read in a Uuid for this Message.");
    }

    if (in.hasNextLong()) {
      time = in.nextLong();
    }
    else {
      throw new IOException("Error: cannot read in an appropriate timestamp for this Message.");
    }

    if (in.hasNext()) {
      authorUuid = in.next();
    }
    else {
      throw new IOException("Error: cannot read in a Uuid for the author of this message.");
    }

    messageBuilder = new StringBuilder();
    while (in.hasNext()) {
      messageBuilder.append(in.next());
    }
    String content = messageBuilder.toString().substring(1, messageBuilder.toString().length() - 1);
    in.close();

    Uuid message = Uuid.parse(uuid);
    Time creation = Time.fromMs(time);
    Uuid author = Uuid.parse(authorUuid);
    return new Message(message, null, null, creation, author, content);
  }

}
