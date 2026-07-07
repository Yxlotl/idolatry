package yxlotl.idolatry.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.LodestoneTracker;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import yxlotl.idolatry.IdolatryItems;

import java.util.Optional;

public class IdolItem extends Item {
    public IdolItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (context.getItemInHand().getItem().equals(IdolatryItems.IDOL)) {
            BlockPos pos = context.getClickedPos();
            if (level.getBlockState(pos).is(Blocks.LODESTONE)) {
                if (!level.isClientSide()) {
                    level.playSound(null, pos, SoundEvents.LODESTONE_COMPASS_LOCK, SoundSource.BLOCKS, 2f, 1f);
                    GlobalPos gp = GlobalPos.of(level.dimension(), pos);
                    LodestoneTracker tracker = new LodestoneTracker(Optional.of(gp), true);
                    context.getItemInHand().set(DataComponents.LODESTONE_TRACKER, tracker);
                    context.getItemInHand().set(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true);
                    return InteractionResult.SUCCESS_SERVER;
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }


}
