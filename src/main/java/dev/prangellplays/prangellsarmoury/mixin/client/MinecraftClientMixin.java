package dev.prangellplays.prangellsarmoury.mixin.client;

import dev.prangellplays.prangellsarmoury.item.weapon.plasmythic.PlasmythicLongswordItem;
import dev.prangellplays.prangellsarmoury.registry.PrangellsArmouryItems;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Environment(EnvType.CLIENT)
@Mixin(value = MinecraftClient.class, priority = 999)
public class MinecraftClientMixin {
    @Shadow
    @Nullable
    public ClientPlayerEntity player;
    @Shadow
    protected int attackCooldown;
    @Shadow
    @Nullable
    public HitResult crosshairTarget;
    @Shadow
    @Nullable
    public ClientPlayerInteractionManager interactionManager;
    @Unique
    private int offhandAttackCooldown;
    @Unique
    private boolean attackedOffhand;

    @Inject(method = "Lnet/minecraft/client/MinecraftClient;tick()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;handleInputEvents()V"))
    public void tickMixin(CallbackInfo info) {
        if (this.offhandAttackCooldown > 0)
            --this.offhandAttackCooldown;
    }

    @Inject(method = "doAttack", at = @At(value = "HEAD"), cancellable = true)
    private void doAttackMixin(CallbackInfoReturnable<Boolean> info) {
        if (player != null) {
                ItemStack itemStack = player.getMainHandStack();
                if (itemStack.getItem() instanceof PlasmythicLongswordItem && (!player.getOffHandStack().isEmpty() || player.isSwimming() || player.hasVehicle()))
                    info.setReturnValue(false);
            }
        }

    @Inject(method = "doItemUse", at = @At(value = "HEAD"), cancellable = true)
    private void doItemUseMixin(CallbackInfo info) {
        if (player != null) {
            ItemStack itemStack = player.getMainHandStack();
            if (itemStack.getItem() instanceof PlasmythicLongswordItem && (!player.getOffHandStack().isEmpty() || player.isSwimming() || player.hasVehicle()))
                info.cancel();
        }
    }

    @Inject(method = "handleBlockBreaking", at = @At(value = "HEAD"), cancellable = true)
    private void handleBlockBreakingMixin(boolean bl, CallbackInfo info) {
        if (player != null) {
            ItemStack itemStack = player.getMainHandStack();
            if (itemStack.getItem() instanceof PlasmythicLongswordItem && (!player.getOffHandStack().isEmpty() || player.isSwimming() || player.hasVehicle()))
                info.cancel();
        }
    }
}
