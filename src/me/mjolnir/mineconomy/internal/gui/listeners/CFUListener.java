package me.mjolnir.mineconomy.internal.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import me.mjolnir.mineconomy.internal.gui.GUI;
import me.mjolnir.mineconomy.internal.util.Update;

/**
 * Handles Check For Updates button action.
 * 
 * @author MjolnirCommando
 */
public class CFUListener implements ActionListener
{
    public void actionPerformed(ActionEvent e)
	{
	    if (Update.check())
		{
			JOptionPane.showMessageDialog(GUI.window,
					"<html><center>An update to MineConomy Version "
							+ Update.updateversion.replace("-", ".")
							+ " is available. Go to<br>"
							+ "<a href=\"http://dev.bukkit.org/server-mods/MineConomy\">http://dev.bukkit.org/server-mods/MineConomy</a>"
							+ "<br>to download the update!</center></html>",
					"Update Check", JOptionPane.PLAIN_MESSAGE);
		}
		else
		{
			JOptionPane
					.showMessageDialog(
							GUI.window,
							"<html>No updates can be found. <br>You have the latest version of MineConomy!</html>",
							"Update Check", JOptionPane.PLAIN_MESSAGE);
		}
	}

}
