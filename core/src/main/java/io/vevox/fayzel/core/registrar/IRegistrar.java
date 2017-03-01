package io.vevox.fayzel.core.registrar;

import java.util.Set;

/**
 * @author Matthew Struble
 */
public interface IRegistrar<V> {

  Set<V> registry();

}
