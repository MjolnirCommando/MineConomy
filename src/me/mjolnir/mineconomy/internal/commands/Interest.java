package me.mjolnir.mineconomy.internal.commands;

import java.util.ArrayList;

import me.mjolnir.mineconomy.internal.MCCom;
import me.mjolnir.mineconomy.internal.Settings;

/**
 * Adds interest to accounts.
 * @author MjolnirCommando
 */
public class Interest
{
	/**
	 * Adds a fixed amount of interest to all accounts.
	 */
	public static void fixed()
	{
		ArrayList<String> accounts = MCCom.getAccounts();
		for(int i = 0; accounts.size() > i; i++)
		{
		    MCCom.setBalance(accounts.get(i), MCCom.getBalance(accounts.get(i))
					+ Settings.interestAmount);
		}
	}
	
	/**
	 * Adds interest to all accounts based on balance.
	 */
	public static void percent()
	{
	    ArrayList<String> accounts = MCCom.getAccounts();
	    
	    for(int i = 0; accounts.size() > i; i ++)
		{
	        double balance = MCCom.getBalance(accounts.get(i));
	        
			double b =  Settings.interestAmount / balance;
			b *= 100;
			
			double c = balance + b;
			c = (double)Math.round(c * 100) / 100;
			
			MCCom.setBalance(accounts.get(i), c);
		}
	}
}
