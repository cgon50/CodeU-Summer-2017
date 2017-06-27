package codeu.chat.server;

import codeu.chat.util.Time;
import codeu.chat.util.Uuid;
import codeu.chat.common.Message;
import codeu.chat.common.User;
import codeu.chat.common.ConversationHeader;


public class Transaction {

  private User user;
  private Message message;
  private ConversationHeader conversation;

  public Transaction(User u) {
    user = u;
  }

  public Transaction(Message m) {
    message = m;
  }

  public Transaction(ConversationHeader ch) {
    conversation = ch;
  }

  private String userTransaction() {
    StringBuilder sb = new StringBuilder();
    sb.append("ADD-USER ");
    sb.append(user.id);
    sb.append(" \"");
    sb.append(user.name);
    sb.append("\" ");
    sb.append(user.creation);
    return sb.toString();
  }

  private String messageTransaction() {
    StringBuilder sb = new StringBuilder();
    sb.append("ADD-MESSAGE ");
    sb.append(message.id);
    sb.append(" ");
    sb.append(message.creation);
    sb.append(" ");
    sb.append(message.author);
    sb.append(" \"");
    sb.append(message.content);
    sb.append("\"");
    return sb.toString();
  }

  private String conversationTransaction() {
    StringBuilder sb = new StringBuilder();
    sb.append("ADD-CONVERSATION ");
    sb.append(conversation.id);
    sb.append(" \"");
    sb.append(conversation.title);
    sb.append("\" ");
    sb.append(conversation.creation);
    sb.append(" ");
    sb.append(conversation.owner);
    return sb.toString();
  }

  public String toString() {
    if (user != null) {
      return userTransaction();
    }
    else if (message != null) {
      return messageTransaction();
    }
    else if (conversation != null) {
      return conversationTransaction();
    }
    return null;
  }

}
