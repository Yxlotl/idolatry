package yxlotl.idolatry.item;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import yxlotl.idolatry.IdolatryItems;

public class EchoDustItem extends Item {
    public EchoDustItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        if (context.getItemInHand().getItem().equals(IdolatryItems.ECHO_DUST)) {
            BlockPos pos = context.getClickedPos();
            if (level.getBlockState(pos).is(Blocks.BUDDING_AMETHYST)) {
                if (!level.isClientSide()) {
                    level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
                    ServerLevel sl = (ServerLevel) level;
                    ItemEntity entity = new ItemEntity(sl, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, new ItemStack(IdolatryItems.SCULK_HEART, 1));
                    entity.setPickUpDelay(5);
                    sl.addFreshEntity(entity);
                    level.playSound(null, pos, SoundEvents.SCULK_CATALYST_BLOOM, SoundSource.BLOCKS, 2f, 1f);
                    context.getItemInHand().shrink(1);
                    return InteractionResult.SUCCESS_SERVER;
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
