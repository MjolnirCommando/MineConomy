package me.mjolnir.mineconomy.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.internal.util.IOH;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Handles currency actions.
 * 
 * @author MjolnirCommando
 */
public class Currency
{
    private static File                      currencyFile = new File(
                                                                  MineConomy.maindir
                                                                          + "currencies.yml");
    private static YamlConfiguration         currencies;
    private static ArrayList<String>         currencylist;
    private static Hashtable<String, Double> currencyvalue;
    private static ArrayList<String>         physicallist;
    private static Hashtable<String, Double> physicalvalue;
    private static Hashtable<String, String> physicalid;
    private static Hashtable<String, String> physicalnames;
    private static ArrayList<String>         physicalids;

    private static String                    defaultCurrency;

    /**
     * Loads currency file
     */
    public static void load()
    {
        currencies = YamlConfiguration.loadConfiguration(currencyFile);
        currencies
                .options()
                .header("=== MineConomy Currencies ===\n\n    Experience ID: _exp\n");

        currencylist = new ArrayList<String>();
        currencyvalue = new Hashtable<String, Double>();
        physicallist = new ArrayList<String>();
        physicalvalue = new Hashtable<String, Double>();
        physicalid = new Hashtable<String, String>();
        physicalnames = new Hashtable<String, String>();
        physicalids = new ArrayList<String>();

        if (!currencyFile.exists())
        {
            IOH.log("Currency file not found...", IOH.INFO);
            currencies.set("Currencies", "");
            currencies.set("Physical", "");
            IOH.log("Currency file created!", IOH.INFO);
            save();
        }

        IOH.log("Loading Currency file...", IOH.INFO);

        reload();

        IOH.log("Currency file loaded!", IOH.INFO);
    }

    /**
     * Reloads currency file
     */
    public static void reload()
    {
        currencies = YamlConfiguration.loadConfiguration(currencyFile);

        currencylist = new ArrayList<String>();
        currencyvalue = new Hashtable<String, Double>();
        physicallist = new ArrayList<String>();
        physicalvalue = new Hashtable<String, Double>();
        physicalid = new Hashtable<String, String>();
        physicalnames = new Hashtable<String, String>();
        physicalids = new ArrayList<String>();
        
        defaultCurrency = "Dollars";

        ConfigurationSection cs = currencies
                .getConfigurationSection("Currencies");
        Object[] t;
        try
        {
            t = cs.getKeys(true).toArray();
        }
        catch (NullPointerException e)
        {
            return;
        }

        for (int i = 0; t.length > i; i++)
        {
            String[] parent = t[i].toString().replace(".", "-").split("-");

            if (parent.length == 1)
            {
                currencylist.add(parent[0]);
            }
        }

        for (int i = 0; currencylist.size() > i; i++)
        {
            String currency = currencylist.get(i);

            currencyvalue.put(
                    currency,
                    Double.parseDouble(currencies.get(
                            "Currencies." + currency + ".Value").toString()));

            if (currencies.getBoolean("Currencies." + currency + ".Default"))
            {
                defaultCurrency = currency;
            }
        }

        cs = currencies.getConfigurationSection("Physical");
        try
        {
            t = cs.getKeys(true).toArray();
        }
        catch (NullPointerException e)
        {
            return;
        }

        for (int i = 0; t.length > i; i++)
        {
            String[] parent = t[i].toString().replace(".", "-").split("-");

            if (parent.length == 1)
            {
                physicallist.add(parent[0]);
            }
        }

        for (int i = 0; physicallist.size() > i; i++)
        {
            String physical = physicallist.get(i);
            String id = currencies.get("Physical." + physical + ".ID")
                    .toString();

            physicalvalue.put(
                    physical,
                    Double.parseDouble(currencies.get(
                            "Physical." + physical + ".Value").toString()));
            physicalid.put(physical, id);
            physicalids.add(id);
            physicalnames.put(id, physical);
        }
    }

    /**
     * Saves currency file
     */
    public static void save()
    {
        currencies.set("Currencies", "");
        currencies.set("Physical", "");

        for (int i = 0; currencylist.size() > i; i++)
        {
            currencies.set("Currencies." + currencylist.get(i) + ".Value",
                    currencyvalue.get(currencylist.get(i)));

            if (currencylist.get(i).toString().equals(defaultCurrency))
            {
                currencies.set(
                        "Currencies." + currencylist.get(i) + ".Default", true);
            }
            else
            {
                currencies
                        .set("Currencies." + currencylist.get(i) + ".Default",
                                false);
            }
        }

        for (int i = 0; physicallist.size() > i; i++)
        {
            currencies.set("Physical." + physicallist.get(i) + ".Value",
                    physicalvalue.get(physicallist.get(i)));
            currencies.set("Physical." + physicallist.get(i) + ".ID",
                    physicalid.get(physicallist.get(i)));
        }

        try
        {
            currencies.save(currencyFile);
        }
        catch (IOException e)
        {
            IOH.error("IOException", e);
        }
        
        reload();
    }

    protected static String getDefault()
    {
        return defaultCurrency;
    }

    protected static boolean isDefault(String currency)
    {
        if (currency.equals(defaultCurrency))
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    protected static void setDefault(String currency)
    {
        defaultCurrency = currency;
    }

    protected static boolean exists(String currency)
    {
        return currencylist.contains(currency);
    }

    protected static void create(String currency, double value, boolean def)
    {
        currencylist.add(currency);
        currencyvalue.put(currency, value);

        if (def)
        {
            defaultCurrency = currency;
        }
    }

    protected static void delete(String currency)
    {
        currencylist.remove(currency);
        currencyvalue.remove(currency);
    }

    protected static void setValue(String currency, double value)
    {
        currencyvalue.put(currency, value);
    }

    protected static double getValue(String currency)
    {
        return currencyvalue.get(currency);
    }

    protected static String getId(String currency)
    {
        return physicalid.get(currency);
    }

    protected static boolean idExists(String id)
    {
        if (physicalids.contains(id))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    protected static boolean physicalExists(String currency)
    {
        return physicallist.contains(currency);
    }

    protected static String getCurrency(String id)
    {
        return physicalnames.get(id);
    }
    
    protected static double getPhysicalValue(String currency)
    {
        return physicalvalue.get(currency);
    }
}
