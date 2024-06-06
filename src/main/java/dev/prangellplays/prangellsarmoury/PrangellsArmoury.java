package dev.prangellplays.prangellsarmoury;

import dev.prangellplays.prangellsarmoury.registry.PrangellsArmouryEnchantments;
import dev.prangellplays.prangellsarmoury.registry.PrangellsArmouryItemGroups;
import dev.prangellplays.prangellsarmoury.registry.PrangellsArmouryItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class PrangellsArmoury implements ModInitializer {
	public static final String MOD_ID = "prangellsarmoury";
	public static final Logger LOGGER = LoggerFactory.getLogger("prangellsarmoury");

	@Override
	public void onInitialize() {
		PrangellsArmouryItems.init();
		PrangellsArmouryItemGroups.init();
		PrangellsArmouryEnchantments.init();
	}

	public static Identifier id(String path) {
		return new Identifier(MOD_ID, path);
	}
}