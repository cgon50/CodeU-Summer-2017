package codeu.chat.util;

import java.io.IOException;
import java.util.Scanner;
import codeu.chat.util.TransactionObject;
import codeu.chat.util.Uuid;
import codeu.chat.util.Time;
import codeu.chat.common.ConversationHeader;


public ConversationHeader {

  String uuid;
  String nameBuilder;
  long time;
  String authorUuid;

  public ConversationHeader parse(String input) throws IOException {
    Scanner in = new Scanner(input);

    if (in.hasNext()) {
      uuid = in.next();
    }
    else {
      throw new IOException("Error: cannot read in a Uuid for this Conversation.");
    }

    nameBuilder = new StringBuilder();
    while (!in.hasNextLong() && in.hasNext()) {
      nameBuilder.append(in.next());
    }
    if (!in.hasNext()) {
      throw new IOException("Error: the transaction for this Conversation is not properly formatted.");
    }
    String title = nameBuilder.toString().substring(1, nameBuilder.toString().length() - 1);

    if (in.hasNextLong()) {
      time = in.nextLong();
    }
    else {
      throw new IOException("Error: cannot read in an appropriate timestamp for this Conversation.");
    }

    if (in.hasNext()) {
      authorUuid = in.next();
    }
    else {
      throw new IOException("Error: cannot read in a Uuid for the author of this conversation.");
    }
    in.close();

    Uuid conversation = Uuid.parse(uuid);
    Uuid owner = Uuid.parse(authorUuid);
    Time creation = Time.fromMs(time);
    return new ConversationHeader(conversation, owner, creation, title);
  }

}
