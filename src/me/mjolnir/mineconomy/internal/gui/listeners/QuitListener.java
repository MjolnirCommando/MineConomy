package me.mjolnir.mineconomy.internal.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.mjolnir.mineconomy.internal.gui.GUI;

import org.bukkit.Bukkit;

/**
 * Handles quit button action.
 * 
 * @author MjolnirCommando
 */
public class QuitListener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
		GUI.window.setVisible(false);
		Bukkit.getServer().shutdown();
	}

}
