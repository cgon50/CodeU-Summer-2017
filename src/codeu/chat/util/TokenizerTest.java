package codeu.chat.util;

import java.io.IOException;
import static org.junit.Assert.*;
import org.junit.Test;

// This is a public class created in util that helps with testing our Tokenizer class.
public final class TokenizerTest {

  // These tests help us determine if our Tokenizer class can read in tokens
  // that are surrounded by quotes.
  @Test
  public void testWithQuotes() throws IOException {
    final Tokenizer tokenizer = new Tokenizer("\"hello world\" \"how are you\"");
    assertEquals(tokenizer.next(), "hello world");
    assertEquals(tokenizer.next(), "how are you");
    assertEquals(tokenizer.next(), null);
  }

  // These tests help us determine if our Tokenizer class can read in tokens
  // that are not surrounded by quotes.
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
