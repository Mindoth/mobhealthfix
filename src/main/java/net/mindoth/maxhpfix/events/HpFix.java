package net.mindoth.maxhpfix.events;

import net.mindoth.maxhpfix.MobHpFix;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = MobHpFix.MOD_ID)
public class HpFix {

    @SubscribeEvent
    public static void onMobJoin(final EntityJoinWorldEvent event) {
        if ( (event.getEntity() instanceof LivingEntity) && !(event.getEntity() instanceof PlayerEntity) ) {
            LivingEntity entity = (LivingEntity)event.getEntity();
            CompoundNBT data = entity.getPersistentData();
            if ( !data.contains("maxHp") ) {
                if ( !event.getWorld().isClientSide ) {
                    data.putFloat("maxHp", entity.getMaxHealth());
                }
            }
        }
    }

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void onMobUpdate(final LivingEvent.LivingUpdateEvent event) {
        if ( (event.getEntity() instanceof LivingEntity) && !(event.getEntity() instanceof PlayerEntity) ) {
            LivingEntity entity = (LivingEntity)event.getEntity();
            CompoundNBT data = entity.getPersistentData();
            if ( data.get("maxHp") != null && data.get("HasLevel") != null ) {
                if ( !entity.level.isClientSide ) {
                    if ( entity.getMaxHealth() != data.getFloat("maxHp") ) {
                        entity.setHealth(entity.getMaxHealth());
                        data.remove("maxHp");
                        data.putFloat("maxHp", entity.getMaxHealth());
                    }
                }
            }
        }
    }
}
