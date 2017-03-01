package io.vevox.fayzel.core.register;

import io.vevox.fayzel.core.registrar.IRegistrar;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @since 1.0.0
 * @author Matthew Struble
 */
public abstract class AbstractRegister<K, V> {

  private Map<K, V> register = new HashMap<>();

  /**
   * Registers the given {@link V} value to the given {@link K} key.
   *
   * @param value The value.
   */
  public void register(V value) {
    register.put(keyMap().apply(value), value);
  }

  void register(IRegistrar<? extends V> registrar) {
    registrar.registry().forEach(this::register);
  }

  /**
   * Gets the {@link V} value of the given {@link K} key, or null if not found.
   *
   * @param key The key.
   *
   * @return The value if found, or else null.
   */
  public V get(K key) {
    return register.get(key);
  }

  /**
   * Iterates over this register, passing each value to the given consumer.
   *
   * @param consumer The consumer.
   *
   * @see Map#forEach(BiConsumer)
   */
  public void forEach(BiConsumer<K, V> consumer) {
    register.forEach(consumer);
  }

  public Set<Map.Entry<K, V>> entrySet() {
    return register.entrySet();
  }

  public abstract Function<V, K> keyMap();



}
