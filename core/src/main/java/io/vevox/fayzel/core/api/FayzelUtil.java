package io.vevox.fayzel.core.api;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 * @author Matthew Struble
 */
@SuppressWarnings("unused WeakerAccess Guava")
public class FayzelUtil {

  public static final String TOOLTIP = "\u00a7f\u00a7o<Press Shift>";

  public static String colorize(String input) {
    return input.replaceAll("&", "\u00a7");
  }

  public static <T> Predicate<T> toJavaPredicate(com.google.common.base.Predicate<T> predicate) {
    return predicate::apply;
  }

  public static <T> com.google.common.base.Predicate<T> toGuavaPredicate(Predicate<T> predicate) {
    return predicate::test;
  }

  public static <T,R> Function<T,R> toJavaFunction(com.google.common.base.Function<T,R> function) {
    return function::apply;
  }

  public static <T,R> com.google.common.base.Function<T,R> toGuavaFunction(Function<T,R> function) {
    return function::apply;
  }

}
