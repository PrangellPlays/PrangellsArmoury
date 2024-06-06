package dev.prangellplays.prangellsarmoury.registry;

import dev.prangellplays.prangellsarmoury.PrangellsArmoury;
import dev.prangellplays.prangellsarmoury.item.util.PrangellsArmouryToolMaterials;
import dev.prangellplays.prangellsarmoury.item.weapon.plasmythic.PlasmythicLongswordItem;
import dev.prangellplays.prangellsarmoury.item.weapon.plasmythic.PlasmythicScytheItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;

@SuppressWarnings("unused")
public class PrangellsArmouryItems {

    public static final Item PLASMYTHIC_SCYTHE = registerItem("plasmythic_scythe", new PlasmythicScytheItem(PrangellsArmouryToolMaterials.PLASMYTHIC_SCYTHE, 12, -2.8f, new FabricItemSettings().fireproof().rarity(Rarity.EPIC)));
    public static final Item PLASMYTHIC_LONGSWORD = registerItem("plasmythic_longsword", new PlasmythicLongswordItem(PrangellsArmouryToolMaterials.PLASMYTHIC_LONGSWORD, 12, -2.4f, new FabricItemSettings().fireproof().rarity(Rarity.EPIC)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(PrangellsArmoury.MOD_ID, name), item);
    }

    public static void init() {
        PrangellsArmoury.LOGGER.info("Registering Mod Items for " + PrangellsArmoury.MOD_ID);
    }
}
