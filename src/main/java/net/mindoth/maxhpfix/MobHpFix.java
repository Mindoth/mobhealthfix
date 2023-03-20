package net.mindoth.maxhpfix;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MobHpFix.MOD_ID)
public class MobHpFix {
    public static final String MOD_ID = "mobhpfix";

    public MobHpFix() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    }
}
