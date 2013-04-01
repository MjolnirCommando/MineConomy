package me.mjolnir.mineconomy.internal;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeSet;

@SuppressWarnings("javadoc")
public abstract class AccountingBase
{
    public Hashtable<String, String> hashaccount;
    
    public TreeSet<String> treeaccount;
    
    public abstract void load();
    
    public abstract void reload();
    
    public abstract void save();
    
    protected abstract double getBalance(String account);
    
    protected abstract void setBalance(String account, double amount);
    
    protected abstract boolean exists(String account);
    
    protected abstract void delete(String account);
    
    protected abstract void create(String account);
    
    protected abstract String getCurrency(String account);
    
    protected abstract void setCurrency(String account, String currency);
    
    protected abstract String getStatus(String account);
    
    protected abstract void setStatus(String account, String status);
    
    protected abstract ArrayList<String> getAccounts();
}
