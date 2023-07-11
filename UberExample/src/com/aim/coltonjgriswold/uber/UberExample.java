package com.aim.coltonjgriswold.uber;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.bukkit.plugin.java.JavaPlugin;

import com.aim.coltonjgriswold.uber.enchantments.LightningStrike;

import me.sciguymjm.uberenchant.api.utils.UberConfiguration;

public class UberExample extends JavaPlugin {
    
    // Create static variable for UberExample
    private static UberExample plugin;

    public void onEnable() {
	// Set plugin to this for easy static reference
	plugin = this;

	// Create new file instance of our custom config
	File file = new File(getDataFolder(), "enchantments.yml");
	
	// Create instance of Lightning Strike enchantment
	LightningStrike enchantment = new LightningStrike();
	
	// Check if our file exists
	if (!file.exists()) {
	    
	    // Register Lightning Strike with UberEnchant and add to UberRecords
	    UberConfiguration.registerUberRecord(
		enchantment,			// Create new instance of Lightning Strike
		1000.0,				// Set cost to use via UberEnchant
		0.4,				// Set cost multiplier
		100.0,				// Set removal cost
		2000.0,				// Set extraction cost
		true,				// Set if can use on anything
		List.of("lightning"),		// Set alaises (Can be empty)
		Map.of(10, 10000.0));		// Set Cost at level (Can be empty)
	    
	    // Save to our config
	    UberConfiguration.saveToFile(this, file);
	} else {
	    
	    // Register our enchantment so it can be found
	    enchantment.register();
	    
	    // Load our config
	    UberConfiguration.loadFromFile(file);
	}
    }
    
    // Static method to return instance of this class
    public static UberExample instance() {
	return plugin;
    }
}
