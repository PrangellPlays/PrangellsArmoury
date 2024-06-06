package dev.prangellplays.prangellsarmoury.enchantments;

import dev.prangellplays.prangellsarmoury.registry.PrangellsArmouryItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;

public class DashEnchantment extends Enchantment {
    public DashEnchantment(Rarity weight, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(weight, target, slotTypes);
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.isOf(PrangellsArmouryItems.PLASMYTHIC_LONGSWORD);
    }

    @Override
    public int getMaxLevel() {
        return 1;
    }
}
