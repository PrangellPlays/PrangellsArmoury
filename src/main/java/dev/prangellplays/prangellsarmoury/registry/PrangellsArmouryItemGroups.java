package dev.prangellplays.prangellsarmoury.registry;

import dev.prangellplays.prangellsarmoury.PrangellsArmoury;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@SuppressWarnings("unused")
public class PrangellsArmouryItemGroups {
    public static final ItemGroup PRANGELLS_ARMOURY_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(PrangellsArmoury.MOD_ID, "items"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.items")).icon(() -> new ItemStack(PrangellsArmouryItems.PLASMYTHIC_LONGSWORD)).entries((displayContext, entries) -> {
                //Weapons
                entries.add(PrangellsArmouryItems.PLASMYTHIC_LONGSWORD);
                entries.add(PrangellsArmouryItems.PLASMYTHIC_SCYTHE);
            }).build());


    public static void init() {
        PrangellsArmoury.LOGGER.info("Registering Item Groups for " + PrangellsArmoury.MOD_ID);
    }
}
