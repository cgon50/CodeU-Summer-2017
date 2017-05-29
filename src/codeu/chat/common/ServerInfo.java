package codeu.chat.common;
import codeu.chat.util.Uuid;
import java.io.IOException;

public final class ServerInfo {

  private final static String SERVER_VERSION = "1.0.0";

  public final Uuid version;

  //sets the server version to either null or
  public ServerInfo() {
    Uuid temp = null;
    try{
      temp =  Uuid.parse(SERVER_VERSION);
    } catch(IOException e){
      System.out.println("Server could not parse the user id");
    }
    version = temp;
  }
  //a second constuctor to assign your own version
  public ServerInfo(Uuid version) {
      this.version = version;
  }
  //displays the server info in a 'pretty' way
  public String toString(){
    return "The Current Server Version is: "+ SERVER_VERSION;
  }
}
