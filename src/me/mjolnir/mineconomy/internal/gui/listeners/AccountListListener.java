package me.mjolnir.mineconomy.internal.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.mjolnir.mineconomy.internal.MCCom;
import me.mjolnir.mineconomy.internal.gui.GUI;

/**
 * Handles accounts being chosen from JComboBox
 * 
 * @author MjolnirCommando
 */
public class AccountListListener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		if (GUI.accountList.getSelectedItem().equals("Accounts ---"))
		{
			//
		}
		else
		{
			GUI.selectedAccount = GUI.accountList.getSelectedItem().toString();

			if (GUI.accountList.getItemAt(0).equals("Accounts ---"))
			{
				GUI.accountList.removeItemAt(0);
			}

			GUI.accountSelected = true;
			if (MCCom.getBalance(GUI.selectedAccount) >= 0)
			{
				GUI.balance
						.setText("<html><center>Balance: <span style=\"color:green;\">"
								+ MCCom.getBalance(GUI.selectedAccount)
								+ "</span> "
								+ MCCom.getAccountCurrency(GUI.selectedAccount) + "</center></html>");
			}
			else
			{
				GUI.balance
						.setText("<html><center>Balance: <span style=\"color:red;\">"
								+ MCCom.getBalance(GUI.selectedAccount)
								+ "</span> "
								+ MCCom.getAccountCurrency(GUI.selectedAccount) + "</center></html>");
			}
			GUI.balance.revalidate();
			GUI.amount.setEnabled(true);
			GUI.givebutton.setEnabled(true);
			GUI.takebutton.setEnabled(true);
			GUI.setbutton.setEnabled(true);
			GUI.emptybutton.setEnabled(true);
			GUI.deletebutton.setEnabled(true);
		}
	}

}
