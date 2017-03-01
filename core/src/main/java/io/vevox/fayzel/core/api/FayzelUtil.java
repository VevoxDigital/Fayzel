package io.vevox.fayzel.core.api;

/**
 * @author Matthew Struble
 */
public class FayzelUtil {

  public static final String TOOLTIP = "\u00a7f\u00a7o<Press Shift>";

  public static String colorize(String input) {
    return input.replaceAll("&", "\u00a7");
  }

}
