package me.mjolnir.mineconomy.internal;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.internal.util.IOH;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Handles accounting actions.
 * 
 * @author MjolnirCommando
 */
public final class Accounting extends AccountingBase
{
    private static File              accountsFile = new File(MineConomy.maindir
                                                          + "accounts.yml");
    private static YamlConfiguration accounts;
    private static ArrayList<String> accountlist;
    private static Hashtable<String, Double> accountbalance;
    private static Hashtable<String, String> accountcurrency;
    private static Hashtable<String, String> accountstatus;

    protected Accounting()
    {
        //
    }
    
    /**
     * Loads accounts file
     */
    public void load()
    {
        accounts = YamlConfiguration.loadConfiguration(accountsFile);
        accounts.options().header("=== MineConomy Accounts ===\n\n    Do not edit!\n");

        accountlist = new ArrayList<String>();
        accountbalance = new Hashtable<String, Double>();
        accountcurrency = new Hashtable<String, String>();
        accountstatus = new Hashtable<String, String>();
        
        if (!accountsFile.exists())
        {
            IOH.log("Accounts file not found...", IOH.INFO);
            accounts.set("Accounts", "");
            IOH.log("Accounts file created!", IOH.INFO);
            save();
        }

        IOH.log("Loading Accounts file...", IOH.INFO);

        reload();

        IOH.log("Accounts file loaded!", IOH.INFO);
    }
    
    /**
     * Reloads accounts file
     */
    public void reload()
    {
        accounts = YamlConfiguration.loadConfiguration(accountsFile);
        
        accountlist = new ArrayList<String>();
        accountbalance = new Hashtable<String, Double>();
        accountcurrency = new Hashtable<String, String>();
        accountstatus = new Hashtable<String, String>();
        
        hashaccount = new Hashtable<String, String>();
        treeaccount = new TreeSet<String>();

        ConfigurationSection cs = accounts.getConfigurationSection("Accounts");
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
                accountlist.add(parent[0]);
            }
        }
        
        for (int i = 0; accountlist.size() > i; i++)
        {
            String account = accountlist.get(i);
            
            accountbalance.put(account, Double.parseDouble(accounts.get("Accounts." + account + ".Balance").toString()));
            accountcurrency.put(account, accounts.get("Accounts." + account + ".Currency").toString());
            accountstatus.put(account, accounts.get("Accounts." + account + ".Status").toString());
            
            hashaccount.put(account.toLowerCase(), account);
            treeaccount.add(account.toLowerCase());
        }
    }
    
    /**
     * Saves bank file
     */
    public void save()
    {   
        accounts.set("Accounts", "");
        
        for (int i = 0; accountlist.size() > i; i++)
        {
            String account = accountlist.get(i);
            accounts.set("Accounts." + account + ".Balance", accountbalance.get(account));
            accounts.set("Accounts." + account + ".Currency", accountcurrency.get(account));
            accounts.set("Accounts." + account + ".Status", accountstatus.get(account));
        }

        try
        {
            accounts.save(accountsFile);
        }
        catch (IOException e)
        {
            IOH.error("IOException", e);
        }
        
        reload();
    }
    
    protected double getBalance(String account)
    {
        return accountbalance.get(account);
    }
    
    protected void setBalance(String account, double amount)
    {
        amount = (double) Math.round(amount * 100) / 100;
        
        if (amount > 9999999.99)
        {
            amount = 9999999.99;
        }
        
        accountbalance.put(account, amount);
    }
    
    protected boolean exists(String account)
    {
        if (accountlist.contains(account))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    
    protected void delete(String account)
    {
        accountlist.remove(account);
        accountbalance.remove(account);
        accountcurrency.remove(account);
        accountstatus.remove(account);
        hashaccount.remove(account.toLowerCase());
        treeaccount.remove(account.toLowerCase());
    }
    
    protected void create(String account)
    {
        accountlist.add(account);
        accountbalance.put(account, Settings.startingBalance);
        accountcurrency.put(account, Currency.getDefault());
        accountstatus.put(account, "NORMAL"); //TODO: Add status support
        hashaccount.put(account.toLowerCase(), account);
        treeaccount.add(account.toLowerCase());
    }
    
    protected String getCurrency(String account)
    {
        return accountcurrency.get(account);
    }
    
    protected void setCurrency(String account, String currency)
    {
        accountcurrency.put(account, currency);
    }
    
    protected String getStatus(String account)
    {
        return accountstatus.get(account);
    }
    
    protected void setStatus(String account, String status)
    {
        accountstatus.put(account, status);
    }
    
    protected ArrayList<String> getAccounts()
    {
        return accountlist;
    }

}
