package codeu.chat.util;

import java.lang.StringBuilder;
import java.io.IOException;

public final class Tokenizer {

  /******************************
  The following instance variables help keep track of:
  token - the current token we are building
  source - the original String
  at - our current index to get each character
  ******************************/
  private StringBuilder token;
  private String source;
  private int at;

  public Tokenizer(String source) {
    this.source = source;
    token = new StringBuilder();
  }

  // Method that reads in the next token for a given source
  public String next() throws IOException {
    // Skip all leading whitespace
    while (remaining() > 0 && Character.isWhitespace(peek())) {
      read(); // ignore the result because we already konw that it is a whitespace character
    }
    if (remaining() <= 0) {
      return null;
    } else if (peek() == '"') {
      // read a token that is surrounded by quotes
      readWithQuotes();
    } else {
      //read a token that is not surrounded by quotes
      readWithNoQuotes();
    }
  }

  // Read in a token that is not surrounded by quotes
  private String readWithNoQuotes() throws IOException {
    token.setLength(0); //clear the token
    while (remaining() > 0 && !Character.isWhitespace(peek())) {
      token.append(read());
    }
    return token.toString();
  }

  // Read in a token that is surrounded by quotes
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

// Tells us how many characters are remaining in
// our current source
  private int remaining() {
    int result = source.length() - at;
    return result;
  }

// Gives us the current character without modifying
// the source
  private char peek() throws IOException {
    if (at < source.length()) {
      return source.charAt(at);
    } else {
      throw new IOException();
    }
  }

// Reads in one character from our source
  private char read() throws IOException {
    final char c = peek();
    at += 1;
    return c;
  }

}
