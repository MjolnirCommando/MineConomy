package me.mjolnir.mineconomy.internal.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Logger;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.internal.Settings;
import me.mjolnir.mineconomy.internal.gui.GUI;

/**
 * Streamlines log code
 * @author MjolnirCommando
 */
@SuppressWarnings("javadoc")
public class IOH
{
    private static File dir = new File(MineConomy.maindir + "lang/");
    private static File log = new File(dir.getPath() + "/MineConomy.log");
    
    public static final int DEV = 5;
    public static final int INFO = 4;
    public static final int WARNING = 3;
    public static final int IMPORTANT = 2;
    public static final int VERY_IMPORTANT = 1;
    
    private static boolean gui = false;
    
	/**
	 * Appends specified message to the log
	 * 
	 * @param message
	 * @param priority 
	 */
	public static void log(String message, int priority)
	{
	    if (priority == 4)
	    {
	        if (priority <= Settings.logPriority)
            {
                Logger.getLogger("Minecraft").info(
                        "[MineConomy] " + "[INFO] " + message);
            }
	        
            append("[INFO]\t\t" + message);
	    }
	    else if (priority == 2)
	    {
	        if (priority <= Settings.logPriority)
            {
                Logger.getLogger("Minecraft").info(
                        "[MineConomy] " + "[IMPORTANT] " + message);
            }
	        
            append("[IMPORTANT]\t" + message);
	    }
	    else if (priority == 1)
        {
	        if (priority <= Settings.logPriority)
            {
                Logger.getLogger("Minecraft").info(
                        "[MineConomy] " + "[VERY IMPORTANT] " + message);
            }
	        
            append("[VERY IMPORTANT]\t" + message);
        }
	    else if (priority == 3)
	    {
	        if (priority <= Settings.logPriority)
            {
                Logger.getLogger("Minecraft").warning(
                        "[MineConomy] " + "[WARNING] " + message);
            }
	        
            append("[WARNING]\t\t" + message);
	    }
	    else
        {
            if (priority <= Settings.logPriority)
            {
                Logger.getLogger("Minecraft").info(
                        "[MineConomy] " + "[DEV] " + message);
            }
            
            append("[DEV]\t\t" + message);
        }
	}

	/**
	 * Logs an exception stack trace plus an optional message.
	 * 
	 * @param message The error message to print, or null if just the
	 *                exception's message should be used alone.
	 * @param exception The exception that was thrown.
	 */
	public static void error(String message, Exception exception)
	{
		StringWriter writer = new StringWriter();
		PrintWriter out = new PrintWriter(writer);
		exception.printStackTrace(out);
		if (message != null)
		{
			message += ": " + writer;
		}
		else
		{
			message = writer.toString();
		}
		Logger.getLogger("Minecraft").severe("[MineConomy] [ERROR] " + message);
		
		append("[ERROR]\t\t" + message);
		
		if (gui)
		{
		    GUI.error(message);
		}
	}
	
	private static void append(String text)
	{
	    try
        {
	        dir.mkdirs();
	        
            if (log.createNewFile())
            {
                PrintWriter out = new PrintWriter(log);
                
                out.print("=== MineConomy Log ===\n\n");
                
                out.close();
            }
            
            DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
            Date date = new Date();
            
            String act = "[" + dateFormat.format(date) + "] " + text;
            text = "";
            
            Scanner in = new Scanner(log);
            
            while(in.hasNextLine())
            {
                text += in.nextLine() + "\n";
            }
            
            in.close();
            
            text += act;
            
            PrintWriter out = new PrintWriter(log);
            
            out.print(text);
            
            out.close();
        }
        catch (IOException e)
        {
            IOH.error("IOException", e);
        }
	    
	    if (gui)
	    {
	        updateGUILog();
	    }
	}
	
	public static void clearLog()
	{
	    PrintWriter out;
        try
        {
            out = new PrintWriter(log);
            out.print("=== MineConomy Log ===\n\n");
            out.close();
        }
        catch (FileNotFoundException e)
        {
            IOH.error("FileNotFoundException", e);
        }
        
        updateGUILog();
	}
	
	public static void updateGUILog()
	{
	    try
        {
            Scanner in = new Scanner(log);
            
            StringBuffer sb = new StringBuffer();
            
            while(in.hasNextLine())
            {
                sb.append(in.nextLine() + "\n");
            }
            GUI.logtext.setEditable(true);
            GUI.logtext.setText(sb.toString());
            GUI.logtext.setEditable(false);
            
            in.close();
        }
        catch (FileNotFoundException e)
        {
            IOH.error("FileNotFoundException", e);
        }
	    
	    gui = true;
	}
}
