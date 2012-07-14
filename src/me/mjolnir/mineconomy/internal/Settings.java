package me.mjolnir.mineconomy.internal;

import java.io.File;
import java.io.IOException;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.internal.util.IOH;
import me.mjolnir.mineconomy.internal.util.MCFormat;

import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Handles loading settings from saved YML.
 * 
 * @author MjolnirCommando
 */
@SuppressWarnings("javadoc")
public class Settings
{
	private static File propFile = new File(MineConomy.maindir + "config.yml");
	private static YamlConfiguration config;
	
	public static double	startingBalance = 0.0;
	public static double	maxDebt = 0.0;
	public static String	interestMode = "none";
	public static double	interestAmount = 0.0;
	public static int		interestInterval = 0;
	public static String	taxMode = "none";
	public static double	taxAmount = 0.0;
	public static int		taxInterval = 0;
	public static boolean	gui = false;
	public static String    lang = "en";
	public static boolean   iconomy = false;
	public static int       logPriority = 5;
	public static String    dburl = "";
	public static String    dbname = "";
	public static String    dbuser = "";
	public static String    dbpass = "";
	public static String    dbtype = "none";
	public static int       autosaveInterval = 3600;
	public static String    migrate = "none";
	
	/**
	 * Loads the Configuration
	 */
	public static void load()
	{	
		config = YamlConfiguration.loadConfiguration(propFile);
		config.options().header("=== MineConomy Configuration ===\n\n    \n");
		
		if (!propFile.exists())
		{
			IOH.log("Config file not found. Creating Config file...", IOH.INFO);
			config.set("Balance.Starting Balance", 0);
			config.set("Balance.Max Debt", 0);
			config.set("Display GUI", false);
			config.set("Log Priority", 5);
			config.set("Interest.Amount", 0);
			config.set("Interest.Interval", "0s");
			config.set("Interest.Mode", "none");
			config.set("Tax.Amount", 0);
			config.set("Tax.Interval", "0s");
			config.set("Tax.Mode", "none");
			config.set("Database.URL", "");
			config.set("Database.Name", "");
			config.set("Database.Username", "");
			config.set("Database.Password", "");
			config.set("Database.Type", "none");
			config.set("Lang", "en");
			config.set("Auto-Save Interval", "1h");
			config.set("iConomy Compatibility Mode", false);
			config.set("Migration Mode", "none");
			IOH.log("Config file created!", IOH.INFO);
			save();
		}
		
		IOH.log("Loading Config file...", IOH.INFO);
		
		reload();
		
		IOH.log("Config file loaded!", IOH.INFO);
	}
	
	/**
	 * Reloads the Configuration
	 */
	public static void reload()
	{
		config = YamlConfiguration.loadConfiguration(propFile);
		
		startingBalance = config.getDouble("Balance.Starting Balance", startingBalance);
        maxDebt = Math.abs(config.getDouble("Balance.Max Debt", maxDebt));
        gui = config.getBoolean("Display GUI", gui);
        interestAmount = config.getDouble("Interest.Amount", interestAmount);
        interestInterval = MCFormat.time(config.getString("Interest.Interval", interestInterval + ""));
        interestMode = config.getString("Interest.Mode", interestMode);
        taxAmount = config.getDouble("Tax.Amount", taxAmount);
        taxInterval = MCFormat.time(config.getString("Tax.Interval", taxInterval + ""));
        taxMode = config.getString("Tax.Mode", taxMode);
        dburl = config.getString("Database.URL", dburl);
        dbname = config.getString("Database.Name", dbname);
        dbuser = config.getString("Database.Username", dbuser);
        dbpass = config.getString("Database.Password", dbpass);
        dbtype = config.getString("Database.Type", dbtype);
        lang = config.getString("Lang", lang);
        autosaveInterval = MCFormat.time(config.getString("Auto-Save Interval", autosaveInterval + ""));
        logPriority = config.getInt("Log Priority", logPriority);
        iconomy = config.getBoolean("iConomy Compatibility Mode", iconomy);
        migrate = config.getString("Migration Mode", migrate);
	}
	
	/**
	 * Saves the Configuration
	 */
	public static void save()
	{
	    config.set("Balance.Starting Balance", startingBalance);
        config.set("Balance.Max Debt", maxDebt);
        config.set("Display GUI", gui);
        config.set("Interest.Amount", interestAmount);
        config.set("Interest.Interval", interestInterval);
        config.set("Interest.Mode", interestMode);
        config.set("Tax.Amount", taxAmount);
        config.set("Tax.Interval", taxInterval);
        config.set("Tax.Mode", taxMode);
        config.set("Database.URL", dburl);
        config.set("Database.Name", dbname);
        config.set("Database.Username", dbuser);
        config.set("Database.Password", dbpass);
        config.set("Database.Type", dbtype);
        config.set("Lang", lang);
        config.set("Log Priority", logPriority);
        config.set("Auto-Save Interval", autosaveInterval);
        config.set("iConomy Compatibility Mode", iconomy);
        config.set("Migration Mode", migrate);
	    
		try
		{
			config.save(propFile);
		}
		catch (IOException e)
		{
			IOH.error("IOException", e);
		}
		
		reload();
	}
}