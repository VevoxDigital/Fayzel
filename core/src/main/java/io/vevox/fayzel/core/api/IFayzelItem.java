package io.vevox.fayzel.core.api;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.IForgeRegistryEntry;

/**
 * A forge-register-able object acting as the parent interface for all
 * {@link Item Items} in Fayzel.
 *
 * @author Matthew Struble
 */
public interface IFayzelItem extends IFayzelObject, IForgeRegistryEntry<Item> { }
