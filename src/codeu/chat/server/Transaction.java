package codeu.chat.server;


public interface Transaction {

  String transaction();

  T parse(String input);


}
