package codeu.chat.util;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

/********************
 Utility class that allows us to test our new Tokenizer class
********************/
public final class TokenizerTest {

  // Test if the Tokenizer can read tokens surrounded by quotes
  @Test
  public void testWithQuotes() throws IOException {
    final Tokenizer tokenizer = new Tokenizer("\"hello world\" \"how are you\"");
    assertEquals(tokenizer.next(), "hello world");
    assertEquals(tokenizer.next(), "how are you");
    assertEquals(tokenizer.next(), null);
  }

  // Test if the Tokenizer can read tokens not surrounded by quotes
  @Test
  public void testWithNoQuotes() throws IOException {
    final Tokenizer tokenizer = new Tokenizer("hello world how are you");
    assertEquals(tokenizer.next(), "hello");
    assertEquals(tokenizer.next(), "world");
    assertEquals(tokenizer.next(), "how");
    assertEquals(tokenizer.next(), "are");
    assertEquals(tokenizer.next(), "you");
    assertEquals(tokenizer.next(), null);
  }

}
