package dev.prangellplays.prangellsarmoury.item.weapon.plasmythic;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class PlasmythicScytheItem extends SwordItem {
    public PlasmythicScytheItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        Potion potion = PotionUtil.getPotion(stack);
        for(StatusEffectInstance effect : potion.getEffects())
            target.addStatusEffect(new StatusEffectInstance(effect.getEffectType(), 200, 0, true, true, true));
        return super.postHit(stack, target, attacker);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        NbtCompound tag = stack.getNbt();

        if(tag == null)
            return;

        Potion potion = Potion.byId(tag.getString("Potion"));

        for(StatusEffectInstance effect : potion.getEffects())
            tooltip.add(Text.translatable(effect.getTranslationKey()).formatted(effect.getEffectType().isBeneficial() ? Formatting.GREEN : Formatting.RED));
    }
}
