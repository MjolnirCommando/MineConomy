package me.mjolnir.mineconomy.internal.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import me.mjolnir.mineconomy.MineConomy;

/**
 * Handles update checks and update downloading.
 * 
 * @author MjolnirCommando
 */
public class Update
{
	/**
	 * Holds the new update version.
	 */
	public static String	updateversion;

	/**
	 * Returns true if update is available.
	 * 
	 * @return boolean
	 */
	public static boolean check()
	{
		try
		{
			URL url = new URL(
					"http://dl.dropbox.com/u/17845242/MineConomy%20Updates.txt");

			BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream()));
			updateversion = in.readLine();
			in.close();

			return Version.compare(new Version(updateversion), new Version(MineConomy.getVersion())) == Version.NEWER;
		}
		catch (IOException e)
		{
		    IOH.error("IOException", e);
		    return false;
		}
	}
}
