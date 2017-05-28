package codeu.chat.util;

import java.lang.StringBuilder;
import java.io.IOException;

public final class Tokenizer {

  private StringBuilder token;
  private String source;
  private int at;

  public Tokenizer(String source) {

  }

  public String next() throws IOException {
    // Skip all leading whitespace
    while (remaining() > 0 && Character.isWhitespace(peek())) {
      read(); // ignore the result because we already konw that it is a whitespace character
    }
    if (remaining() <= 0) {
      return null;
    } else if (peek() == '"') {
      // read a token that is surrounded by quotes
      return readWithQuotes();
    } else {
      //read a token that is not surrounded by quotes
      return readWithNoQuotes();
    }
  }

  private String readWithNoQuotes() throws IOException {
    token.setLength(0); //clear the token
    while (remaining() > 0 && !Character.isWhitespace(peek())) {
      token.append(read());
    }
    return token.toString();
  }

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

  private int remaining() {
    int result = source.length() - at;
    return result;
  }

  private char peek() throws IOException {
    if (at < source.length()) {
      return source.charAt(at);
    } else {
      throw new IOException();
    }
  }

  private char read() throws IOException {
    final char c = peek();
    at += 1;
    return c;
  }

}
