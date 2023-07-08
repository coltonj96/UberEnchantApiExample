package com.aim.coltonjgriswold.uber.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import com.aim.coltonjgriswold.uber.UberExample;

import me.sciguymjm.uberenchant.api.UberEnchantment;
import me.sciguymjm.uberenchant.api.utils.WeightedChance;

// Extend the UberEnchantment class
public class LightningStrike extends UberEnchantment {

    // Set plugin and unique key
    public LightningStrike() {
	super(UberExample.instance(), "lightning_strike");
    }

    // Set display name
    @Override
    public String getDisplayName() {
	return "&6Lighning Strike";
    }

    // Set a permission
    @Override
    public String getPermission() {
	return "uber_example.lightning_strike";
    }

    // Set default max level
    @Override
    public int getMaxLevel() {
	return 10;
    }

    // Set default min level
    @Override
    public int getStartLevel() {
	return 1;
    }

    // Set default EnchantmentTarget
    @Override
    public EnchantmentTarget getItemTarget() {
	return EnchantmentTarget.TOOL;
    }

    // Set if treasue
    @Override
    public boolean isTreasure() {
	return false;
    }

    // Set if cursed
    @Override
    public boolean isCursed() {
	return false;
    }

    // Do a check if conflicts with another enchantment
    @Override
    public boolean conflictsWith(Enchantment other) {
	return false;
    }

    // Do a check if can enchant the item
    @Override
    public boolean canEnchantItem(ItemStack item) {
	return true;
    }
    
    /*
     * The custom enchantment can have any number of different events.
     * This is how to tell the enchantment what to do. Make sure to check if the enchantment is being used.
     * What can be done is nearly endless, use imagination.
     */
    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
	
	// Check if the damager is a player and if the attacked entity is a LivingEntity (Can be a mob or a player)
	if (event.getDamager() instanceof Player player && event.getEntity() instanceof LivingEntity entity) {
	    
	    // Get the item in the players main hand
	    ItemStack item = player.getInventory().getItemInMainHand();
	    
	    // Make sure the item is not null and check if it contains our Lighning enchantment
	    if (item != null && containsEnchantment(item)) {
		
		// Create a new WeightedChance of type Boolean just for some random chances
		WeightedChance<Boolean> random = new WeightedChance<Boolean>();
		
		// Do a little math to determine the chance it will activate based on level
		double chance = ((double) getLevel(item)) / ((double) getMaxLevel());
		
		// Add that chance as boolean with value of true
		random.add(chance, true);
		
		// Add the remainder of that chance as a false value
		random.add(1.0 - chance, false);
		
		// Get the result and strike lightning at the location of the entity
		if (random.next())
		    player.getWorld().strikeLightning(entity.getLocation());
	    }
	}
    }

    
}