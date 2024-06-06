package dev.prangellplays.prangellsarmoury.mixin.server;

import dev.prangellplays.prangellsarmoury.item.weapon.plasmythic.PlasmythicLongswordItem;
import dev.prangellplays.prangellsarmoury.registry.PrangellsArmouryItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "Lnet/minecraft/entity/LivingEntity;getHandSwingDuration()I", at = @At("HEAD"), cancellable = true)
    private void getHandSwingDuration(CallbackInfoReturnable<Integer> info) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack itemStack = livingEntity.getMainHandStack();
        if (itemStack.getItem() instanceof PlasmythicLongswordItem) {
            info.setReturnValue(10);
        }
    }

    // Items can not block projectiles
    @Inject(method = "blockedByShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/PersistentProjectileEntity;getPierceLevel()B"), cancellable = true)
    private void blockedByShieldMixin(DamageSource source, CallbackInfoReturnable<Boolean> info) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack itemStack = livingEntity.getMainHandStack();
        if (itemStack.getItem() instanceof PlasmythicLongswordItem) {
            info.setReturnValue(false);
        }
    }

    @Inject(method = "blockedByShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/Vec3d;dotProduct(Lnet/minecraft/util/math/Vec3d;)D", shift = At.Shift.AFTER), cancellable = true)
    private void blockedByShieldDamageWeaponMixin(DamageSource source, CallbackInfoReturnable<Boolean> info) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;
        ItemStack itemStack = livingEntity.getActiveItem();
        if (itemStack.getItem() instanceof PlasmythicLongswordItem) {
            if (livingEntity instanceof PlayerEntity) {
                ((PlayerEntity) livingEntity).getItemCooldownManager().set(itemStack.getItem(), 60);
            }
            livingEntity.clearActiveItem();
            if (!this.getWorld().isClient()) {
                livingEntity.getMainHandStack().damage(1, livingEntity, (p) -> p.sendToolBreakStatus(p.getActiveHand()));
            }
        }
    }
}
