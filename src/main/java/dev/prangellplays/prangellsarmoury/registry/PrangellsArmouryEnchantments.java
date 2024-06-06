package dev.prangellplays.prangellsarmoury.registry;

import dev.prangellplays.prangellsarmoury.PrangellsArmoury;
import dev.prangellplays.prangellsarmoury.enchantments.DashEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class PrangellsArmouryEnchantments {
    public static final Enchantment DASH = register("dash", new DashEnchantment(Enchantment.Rarity.COMMON, EnchantmentTarget.WEAPON, new EquipmentSlot[]{EquipmentSlot.MAINHAND}));

    private static Enchantment register(String name, Enchantment enchantment) {
        return Registry.register(Registries.ENCHANTMENT, new Identifier(PrangellsArmoury.MOD_ID, name), enchantment);
    }

    public static void init() {
        PrangellsArmoury.LOGGER.info("Registering ModEnchantments for " + PrangellsArmoury.MOD_ID);
    }
}
