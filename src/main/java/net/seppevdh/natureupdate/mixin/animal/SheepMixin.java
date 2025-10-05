package net.seppevdh.natureupdate.mixin.animal;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityReference;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Shearable;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Optional;

@Mixin({Sheep.class})
public abstract class SheepMixin extends Animal implements Shearable {
    protected SheepMixin(EntityType<? extends Animal> p_27557_, Level p_27558_) {
        super(p_27557_, p_27558_);
    }

    private static final EntityDataAccessor<Optional<EntityReference<LivingEntity>>> LAST_FEED = SynchedEntityData.defineId(Sheep.class, EntityDataSerializers.OPTIONAL_LIVING_ENTITY_REFERENCE);

    @Inject(
            at = @At("HEAD"),
            method = "defineSynchedData"
    )
    private void defineSynchedData (SynchedEntityData.Builder builder, CallbackInfo callBack) {
        builder.define(LAST_FEED, Optional.empty());
    }

    @Inject(
            at = @At("HEAD"),
            cancellable = true,
            method = {"isFood"}
    )
    private void isFood (ItemStack stack, CallbackInfoReturnable<Boolean> callBack) {
        callBack.setReturnValue(false);
    }

    private Optional<Player> getPlayer () {
        LivingEntity entity = EntityReference.get(this.entityData.get(LAST_FEED).orElse(null), this.level(), LivingEntity.class);
        if (entity instanceof Player player) return Optional.of(player);
        return Optional.empty();
    }

    @Inject(
            at = @At("HEAD"),
            method = {"aiStep"}
    )
    private void aiStep (CallbackInfo callback) {
        if (this.getHealth() >= this.getMaxHealth() && !this.isBaby() && this.isAlive() && this.level().random.nextFloat() < 0.0006f && this.canFallInLove()) {
            this.setInLove(getPlayer().orElse(null));
            this.setInLoveTime(500);
        }
    }

    @Inject(
            at = @At("HEAD"),
            cancellable = true,
            method = {"mobInteract"}
    )
    private void mobInteract (Player player, InteractionHand hand, CallbackInfoReturnable<InteractionResult> callBack) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.is(ItemTags.SHEEP_FOOD) ) {
            if (this.getHealth() < this.getMaxHealth()) {
                this.heal(2f);
                this.usePlayerItem(player, hand, stack);
                this.gameEvent(GameEvent.EAT);
                this.entityData.set(LAST_FEED, Optional.of(player).map(EntityReference::new));
                callBack.setReturnValue(InteractionResult.SUCCESS);
            } else if (this.isBaby()) {
                this.usePlayerItem(player, hand, stack);
                this.ageUp(getSpeedUpSecondsWhenFeeding(-this.age), true);
                this.playEatingSound();
                this.entityData.set(LAST_FEED, Optional.of(player).map(EntityReference::new));
                callBack.setReturnValue(InteractionResult.SUCCESS);
            }
        }
    }

    @Inject(
            at = @At("HEAD"),
            method = "addAdditionalSaveData"
    )
    private void addAdditionalSaveData(CompoundTag tag, CallbackInfo callback) {
        Optional<EntityReference<LivingEntity>> entityReference = this.entityData.get(LAST_FEED);
        entityReference.ifPresent(reference -> reference.store(tag, "Owner"));
    }

    @Inject(
            at = @At("HEAD"),
            method = "readAdditionalSaveData"
    )
    private void readAdditionalSaveData(CompoundTag tag, CallbackInfo callback) {
        this.entityData.set(LAST_FEED, Optional.ofNullable(EntityReference.readWithOldOwnerConversion(tag, "Owner", this.level())));
    }
}
