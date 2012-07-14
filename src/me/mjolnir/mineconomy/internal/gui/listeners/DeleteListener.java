package me.mjolnir.mineconomy.internal.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import me.mjolnir.mineconomy.internal.MCCom;
import me.mjolnir.mineconomy.internal.gui.GUI;

/**
 * Handles delete button action.
 * 
 * @author MjolnirCommando
 */
public class DeleteListener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
	    String account = GUI.selectedAccount;
	
		int result = JOptionPane.showConfirmDialog(GUI.window,
				"Are you sure you want to delete account \"" + account + "\"?",
				"Confirm Deletion", JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION)
		{
		    MCCom.delete(account);
			GUI.reloadAccounts(true);
		}
	}

}
