package me.mjolnir.mineconomy.internal.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.mjolnir.mineconomy.internal.MCCom;
import me.mjolnir.mineconomy.internal.Settings;
import me.mjolnir.mineconomy.internal.gui.GUI;

/**
 * Handles set button action.
 * 
 * @author MjolnirCommando
 */
public class SetListener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			double amount = Double.parseDouble(GUI.amount.getText());
			if (amount >= Double.parseDouble("-" + Settings.maxDebt))
			{
			    MCCom.setBalance(GUI.selectedAccount, amount);
				if (MCCom.getBalance(GUI.selectedAccount) >= 0)
				{
					GUI.balance
							.setText("<html><center>Balance: <span style=\"color:green;\">"
									+ MCCom.getBalance(
											GUI.selectedAccount)
									+ "</span> "
									+ MCCom.getAccountCurrency(GUI.selectedAccount) + "</center></html>");
				}
				else
				{
					GUI.balance
							.setText("<html><center>Balance: <span style=\"color:red;\">"
									+ MCCom.getBalance(
											GUI.selectedAccount)
									+ "</span> "
									+ MCCom.getAccountCurrency(GUI.selectedAccount) + "</center></html>");
				}
				GUI.balance.revalidate();
				GUI.reloadAccounts(false);
				GUI.title
						.setText("<html><center>Control Panel<br><br><br></center></html>");
			}
			else
			{
				GUI.title
						.setText("<html><center>Control Panel<br><span style=\"color:red;\">That number is below the maximum debt!.</span><br><br></center></html>");
			}
		}
		catch (NumberFormatException e1)
		{
			GUI.title
					.setText("<html><center>Control Panel<br><span style=\"color:red;\">Amount must be a number.</span><br><br></center></html>");
		}
	}

}
