package me.mjolnir.mineconomy.internal.commands;

import java.util.ArrayList;

import me.mjolnir.mineconomy.exceptions.MaxDebtException;
import me.mjolnir.mineconomy.internal.MCCom;
import me.mjolnir.mineconomy.internal.Settings;

/**
 * Adds interest to accounts.
 * @author MjolnirCommando
 */
public class Tax
{
	/**
	 * Adds a fixed amount of interest to all accounts.
	 */
	public static void fixed()
	{
	    ArrayList<String> accounts = MCCom.getAccounts();
        for(int i = 0; accounts.size() > i; i++)
        {
            try
            {
                MCCom.setBalance(accounts.get(i), MCCom.getBalance(accounts.get(i))
                        - Settings.taxAmount);
            }
            catch (MaxDebtException e)
            {
                MCCom.setBalance(accounts.get(i), Settings.maxDebt);
            }
        }
	}
	
	/**
	 * Adds interest to all accounts based on balance.
	 */
	public static void percent()
	{
	    ArrayList<String> accounts = MCCom.getAccounts();
        for(int i = 0; accounts.size() > i; i++)
        {
            double balance = MCCom.getBalance(accounts.get(i));
            
            if (balance <= 0)
            {
                //
            }
            else
            {
                double b =  Settings.taxAmount * balance;
                b /= 100;
                
                double c = balance - b;
                c = (double)Math.round(c * 100) / 100;
                
                try
                {
                    MCCom.setBalance(accounts.get(i), c);
                }
                catch (MaxDebtException e)
                {
                    MCCom.setBalance(accounts.get(i), Settings.maxDebt);
                }
            }
        }
	}
}
