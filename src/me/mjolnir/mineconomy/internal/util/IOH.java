package me.mjolnir.mineconomy.internal.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.internal.Settings;
import me.mjolnir.mineconomy.internal.gui.GUI;

/**
 * Streamlines log code
 * 
 * @author MjolnirCommando
 */
@SuppressWarnings("javadoc")
public class IOH
{
    private static File         dir            = new File(MineConomy.maindir
                                                       + "lang/");
    private static File         logFile        = new File(MineConomy.maindir
                                                       + "lang/MineConomy.log");
    private static StringBuffer log            = new StringBuffer();

    public static boolean       gui            = false;

    public static final int     DEV            = 5;
    public static final int     INFO           = 4;
    public static final int     WARNING        = 3;
    public static final int     IMPORTANT      = 2;
    public static final int     VERY_IMPORTANT = 1;

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
     * @param message
     *            The error message to print, or null if just the exception's
     *            message should be used alone.
     * @param exception
     *            The exception that was thrown.
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

        if (Settings.gui)
        {
            GUI.error(message);
        }
    }

    private static void append(String text)
    {

        DateFormat dateFormat = new SimpleDateFormat("MM/dd HH:mm:ss");
        Date date = new Date();

        String act = "\n[" + dateFormat.format(date) + "] " + text;

        log.append(act);

        if (Settings.gui)
        {
            updateGUILog();
        }
    }

    public static void clearLog()
    {
        log = new StringBuffer();
        log.append("=== MineConomy Log File ===\n\n");

        updateGUILog();
    }

    public static void updateGUILog()
    {
        if (gui)
        {
            GUI.logtext.setEditable(true);
            GUI.logtext.setText(log.toString());
            GUI.logtext.setEditable(false);
            GUI.logtext.select(GUI.logtext.getText().length(), GUI.logtext
                    .getText().length());
        }
    }

    public static void saveLog()
    {
        try
        {
            PrintWriter out = new PrintWriter(logFile);
            out.print(log.toString());
            out.close();
        }
        catch (IOException e)
        {
            IOH.error(e.getClass().getSimpleName(), e);
        }
    }

    public static void loadLog()
    {
        try
        {
            dir.mkdirs();

            if (logFile.createNewFile())
            {
                PrintWriter out = new PrintWriter(logFile);

                out.print("=== MineConomy Log ===\n\n");

                out.close();
            }

            final char[] raw = new char[65536];
            FileReader reader = new FileReader(logFile);
            log = new StringBuffer();
            long position = logFile.length() - raw.length;
            if (position >= 1)
            {
                reader.skip(position);
            }
            int count = reader.read(raw);
            log.append(raw, 0, count);

            reader.close();
        }
        catch (IOException e)
        {
            IOH.error(e.getClass().getSimpleName(), e);
        }
    }
}
