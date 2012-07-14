package me.mjolnir.mineconomy.internal.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.mjolnir.mineconomy.internal.MCCom;
import me.mjolnir.mineconomy.internal.gui.GUI;

/**
 * Handles empty button action.
 * 
 * @author MjolnirCommando
 */
public class EmptyListener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
	    MCCom.setBalance(GUI.selectedAccount, 0);
		GUI.balance.setText("<html><center>Balance: <span style=\"color:green;\">"
				+ MCCom.getBalance(GUI.selectedAccount) + "</span> " + MCCom.getAccountCurrency(GUI.selectedAccount)
				+ "</center></html>");
		GUI.balance.revalidate();
		GUI.reloadAccounts(false);
	}

}
