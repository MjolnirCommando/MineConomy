package me.mjolnir.mineconomy.internal.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.internal.util.IOH;

/**
 * Handles refresh button action.
 * 
 * @author MjolnirCommando
 */
public class Refresh2Listener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{
	    IOH.log("Reloading MineConomy...", IOH.IMPORTANT);
	    MineConomy.reload();
	    IOH.log("Successfully Reloaded MineConomy!", IOH.IMPORTANT);
	}

}
