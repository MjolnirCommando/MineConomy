package me.mjolnir.mineconomy.internal.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.internal.gui.GUI;

/**
 * Handles refresh button action.
 * 
 * @author MjolnirCommando
 */
public class Refresh2Listener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
	    JFrame oldWindow = GUI.window;
        MineConomy.reload();
        new GUI();
        oldWindow.setVisible(false);
		
	}

}
