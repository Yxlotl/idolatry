package yxlotl.idolatry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import yxlotl.idolatry.item.EchoDustItem;
import yxlotl.idolatry.item.IdolItem;

import java.util.function.Function;

public class IdolatryItems {
    public static <T extends Item> T register(String name, Function<Item.Properties, T> itemFactory, Item.Properties settings) {
        // Create the item key.
        ResourceKey<Item> itemKey = ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(Idolatry.MOD_ID, name));

        // Create the item instance.
        T item = itemFactory.apply(settings.setId(itemKey));

        // Register the item.
        Registry.register(BuiltInRegistries.ITEM, itemKey, item);

        return item;
    }

    public static final Item ECHO_DUST = register("echo_dust", EchoDustItem::new, new Item.Properties().rarity(Rarity.UNCOMMON));
    public static final Item IDOL = register("idol", IdolItem::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    public static final Item IDOL_CURSED = register("idol_cursed", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    public static final Item IDOL_WINGS = register("idol_wings", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    public static final Item IDOL_WINGS_CURSED = register("idol_wings_cursed", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    public static final Item SCULK_HEART = register("sculk_heart", Item::new, new Item.Properties().rarity(Rarity.UNCOMMON));

    public static void initialize() {
        return;
    }
}
