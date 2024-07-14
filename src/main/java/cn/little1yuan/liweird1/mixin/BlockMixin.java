package cn.little1yuan.liweird1.mixin;

import cn.little1yuan.liweird1.LiWeird1;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(Block.class)
public class BlockMixin {
    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;)Ljava/util/List;", at = @At(value = "RETURN"), cancellable = true)
    private static void dropMixin(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (!world.isClient) {
            List<ItemStack> origin = cir.getReturnValue();
            List<ItemStack> replaced = new ArrayList<>();
            for (ItemStack itemStack : origin) {
                Item item = LiWeird1.getDropItem(itemStack.getItem(), world.getSeed());
                replaced.add(new ItemStack(item, itemStack.getCount()));
            }
            cir.setReturnValue(replaced);
        }
    }

    @Inject(method = "getDroppedStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)Ljava/util/List;", at = @At(value = "RETURN"), cancellable = true)
    private static void dropMixin(BlockState state, ServerWorld world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack, CallbackInfoReturnable<List<ItemStack>> cir) {
        if (!world.isClient) {
            List<ItemStack> origin = cir.getReturnValue();
            List<ItemStack> replaced = new ArrayList<>();
            for (ItemStack itemStack : origin) {
                Item item = LiWeird1.getDropItem(itemStack.getItem(), world.getSeed());
                replaced.add(new ItemStack(item, itemStack.getCount()));
            }
            cir.setReturnValue(replaced);
        }
    }
}
