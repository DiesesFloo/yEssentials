package systems.floo.yessentials.commands.repair;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class RepairCommandProvider {

    /**
     * Repairs the durability of the item
     * given in the params.
     *
     * @param i The {@link ItemStack} to repair
     */
    public static void repairItem(ItemStack i) {
        ItemMeta meta = i.getItemMeta();

        if (meta != null) {
            ((Damageable) meta).setDamage(0);
        }

        i.setItemMeta(meta);
    }

    /**
     * Repairs the durability of the item
     * in the main hand of the player given
     * in the params.
     *
     * @param p The player which item in hand to repair
     */
    public static void repairItemInHand(Player p) {
        repairItem(p.getInventory().getItemInMainHand());
    }

    /**
     * Repairs the durability of all items
     * in the inventory of the player given
     * in the params.
     *
     * @param p The player which inventory to repair
     */
    public static void repairInventory(Player p) {
        final ItemStack[] content = p.getInventory().getContents();

        for (int i = 0; i < content.length; i++) {
            final ItemStack item = content[i];
            repairItem(item);
            content[i] = item;
        }

        p.getInventory().setContents(content);
        p.updateInventory();
    }

}
