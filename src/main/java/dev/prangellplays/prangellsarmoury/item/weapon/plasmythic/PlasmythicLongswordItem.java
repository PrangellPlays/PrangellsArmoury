package dev.prangellplays.prangellsarmoury.item.weapon.plasmythic;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes;
import dev.prangellplays.prangellsarmoury.registry.PrangellsArmouryEnchantments;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.*;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import java.util.UUID;

import static net.minecraft.enchantment.EnchantmentHelper.getEquipmentLevel;

public class PlasmythicLongswordItem extends SwordItem {
    public static final UUID ATTACK_RANGE_MODIFIER_ID = UUID.fromString("05869d86-c861-4954-9079-68c380ad063c");
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public PlasmythicLongswordItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", 12, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", -2.8f, EntityAttributeModifier.Operation.ADDITION));
        builder.put(ReachEntityAttributes.ATTACK_RANGE, new EntityAttributeModifier(ATTACK_RANGE_MODIFIER_ID, "Weapon modifier", 1f, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BLOCK;
    }

    public static boolean hasDash(LivingEntity entity) {
        return getEquipmentLevel(PrangellsArmouryEnchantments.DASH, entity) > 0;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        if (PlasmythicLongswordItem.hasDash(user)) {
            Vec3d vec3d = user.getRotationVector();
            double d = 3;
            double e = 0.2;
            Vec3d vec3d2 = user.getVelocity();
            user.setVelocity(vec3d2.add(vec3d.x * e + (vec3d.x * d - vec3d2.x) * 0.5, vec3d.y * e + (vec3d.y * d - vec3d2.y) * 0.5, vec3d.z * e + (vec3d.z * d - vec3d2.z) * 0.5));
            user.getItemCooldownManager().set(this, 60);
            user.fallDistance = user.fallDistance * 0.25f;
        }
        else if (user.getMainHandStack().getItem() instanceof PlasmythicLongswordItem) {
            user.setCurrentHand(hand);
            return TypedActionResult.consume(itemStack);
        }
        return TypedActionResult.fail(itemStack);
    }


}
