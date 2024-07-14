package cn.little1yuan.liweird1.mixin;

import cn.little1yuan.liweird1.LiWeird1;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Entity.class)
public abstract class MixinMobEntity {

    @Shadow public abstract World getWorld();

    //    @Inject(method = "dropLoot", at = @At(value = "TAIL"))
//    private void modifyDropLoot(DamageSource damageSource, boolean causedByPlayer, CallbackInfo ci) {
//        if (!this.getWorld().isClient() && this.getWorld().getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
//            List<ItemStack> origin = getD
//        }
//    }
    @ModifyArg(method = "dropStack(Lnet/minecraft/item/ItemStack;F)Lnet/minecraft/entity/ItemEntity;", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ItemEntity;<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)V"), index = 4)
    private ItemStack modifyDropStack(ItemStack origin) {
        if (!((Entity)(Object)this instanceof MobEntity)) {
            return origin;
        }
        if (!getWorld().isClient && origin == null) {
            return null;
        }
        return new ItemStack(LiWeird1.getDropItem(origin.getItem(), ((ServerWorld)getWorld()).getSeed()), origin.getCount());
    }
}
