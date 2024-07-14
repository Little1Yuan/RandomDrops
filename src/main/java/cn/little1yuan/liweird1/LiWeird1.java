package cn.little1yuan.liweird1;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;

import java.util.Random;

public class LiWeird1 implements ModInitializer {
    @Override
    public void onInitialize() {
        System.out.println("LiWeird 1 init.");

    }

    public static Item getDropItem(Item origin, long seed) {
        Random random = new Random((long) Long.hashCode(seed) * origin.hashCode());
        return Registries.ITEM.get(random.nextInt(Registries.ITEM.size()));
    }
}
