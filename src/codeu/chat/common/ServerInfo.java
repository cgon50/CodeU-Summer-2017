package codeu.chat.common;
import codeu.chat.util.Uuid;
import java.io.IOException;
import codeu.chat.util.Time;

public final class ServerInfo {

  public final Time startTime;

  private final static String SERVER_VERSION = "1.0.0";

  public final Uuid version;

  // Sets the server version to either null or the current server version.
  public ServerInfo() {
    Uuid temp = null;
    //Sets our variable 'startTime' to the current time
    this.startTime = Time.now();
    try{
      temp =  Uuid.parse(SERVER_VERSION);
    } catch(IOException e){
      System.out.println("Server could not parse the server version.");
    }
    version = temp;
  }
  // A second constuctor to assign your own version number.
  public ServerInfo(Uuid version) {
      this.version = version;
      //Sets the current time, 'Time.now()' to 'startTime'
      this.startTime = startTime;
  }
  // Displays the server info in a 'pretty' way.
  public String toString(){
    return "The Current Server Version is: "+ SERVER_VERSION;
  }
}
