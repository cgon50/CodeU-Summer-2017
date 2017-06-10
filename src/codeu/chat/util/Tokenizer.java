package codeu.chat.util;

import java.lang.StringBuilder;
import java.io.IOException;

// This utility class allows us to read input that may contain
// multiple tokens to allow for multi-token commands in our
// application later on.
public final class Tokenizer {

  // The following instance variables help keep track of:
  // the current token we are building,
  private StringBuilder token;
  // the original input,
  private String source;
  // and the current index we are at in our source.
  private int currentSourceIndex;

  // The constructor for a new Tokenizer object assigns the given input to our
  // source and creates a new StringBuilder for our token.
  public Tokenizer(String source) {
    this.source = source;
    token = new StringBuilder();
  }

  // The next method reads in the next available token in our input.
  public String next() throws IOException {
    // Skip all leading whitespace
    while (remaining() > 0 && Character.isWhitespace(peek())) {
      read(); // Ignore the result because we already konw that it is a whitespace character
    }
    if (remaining() <= 0) {
      return null;
    } else if (peek() == '"') {
      // Read a token that is surrounded by quotes
      return readWithQuotes();
    } else {
      // Read a token that is not surrounded by quotes
      return readWithNoQuotes();
    }
  }

  // This helper method reads in a token that is not surrounded by quotes.
  private String readWithNoQuotes() throws IOException {
    token.setLength(0); //clear the token
    while (remaining() > 0 && !Character.isWhitespace(peek())) {
      token.append(read());
    }
    return token.toString();
  }

  // This helper method reads in a token that is surrounded by quotes, which
  // will allow for multi-token commands, i.e. a phrase.
  private String readWithQuotes() throws IOException {
    token.setLength(0); //clear the token
    if (read() != '"') {
      throw new IOException("Strings must start with opening quote");
    }
    while (peek() != '"') {
      token.append(read());
    }
    read(); //read the closing quote that allowed us to exit the loop
    return token.toString();
  }

// This method tells us how many characters we have remaining
// in our source.
  private int remaining() {
    return source.length() - currentSourceIndex;
  }

// This method gives us the next character without modifying
// the source or our token.
  private char peek() throws IOException {
    if (currentSourceIndex < source.length()) {
      return source.charAt(currentSourceIndex);
    } else {
      throw new IOException("No more characters in source.");
    }
  }

// This helper method reads in one character and updates
// our current index.
  private char read() throws IOException {
    final char c = peek();
    currentSourceIndex += 1;
    return c;
  }

}
