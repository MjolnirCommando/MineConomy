package me.mjolnir.mineconomy.internal.util;

import java.text.NumberFormat;
import java.util.Locale;

import me.mjolnir.mineconomy.exceptions.FormatException;

import org.bukkit.ChatColor;

@SuppressWarnings("javadoc")
public class MCFormat
{
    public static String color(String message)
    {
        message = message.replace("&0", ChatColor.BLACK + "");
        message = message.replace("&1", ChatColor.DARK_BLUE + "");
        message = message.replace("&2", ChatColor.DARK_GREEN + "");
        message = message.replace("&3", ChatColor.DARK_AQUA + "");
        message = message.replace("&4", ChatColor.DARK_RED + "");
        message = message.replace("&5", ChatColor.DARK_PURPLE + "");
        message = message.replace("&6", ChatColor.GOLD + "");
        message = message.replace("&7", ChatColor.GRAY + "");
        message = message.replace("&8", ChatColor.DARK_GRAY + "");
        message = message.replace("&9", ChatColor.BLUE + "");

        message = message.replace("&a", ChatColor.GREEN + "");
        message = message.replace("&b", ChatColor.AQUA + "");
        message = message.replace("&c", ChatColor.RED + "");
        message = message.replace("&d", ChatColor.LIGHT_PURPLE + "");
        message = message.replace("&e", ChatColor.YELLOW + "");
        message = message.replace("&f", ChatColor.WHITE + "");

        return message;
    }

    public static String format(double amount)
    {
        return format(amount, true);
    }

    public static String format(double amount, boolean flag)
    {
        NumberFormat numberFormatter = NumberFormat
                .getNumberInstance(new Locale("US"));

        String result = numberFormatter.format(amount);

        if (flag)
        {
            int length = 0;
            try
            {
                length = result.replace(".", "-").split("-")[1].length();
            }
            catch (Exception e)
            {
                length = 0;
            }
            if (length == 0)
            {
                result += ".00";
            }
            else if (length == 1)
            {
                result += "0";
            }
        }

        return result;
    }

    public static int time(String time)
    {
        int seconds = 0;

        if (time.endsWith("s"))
        {
            time = time.split("s")[0];

            try
            {
                seconds = Integer.parseInt(time);
            }
            catch (NumberFormatException e)
            {
                throw new FormatException(
                        "MCFormat: public static int time(String time)", "time");
            }
        }
        else if (time.endsWith("m"))
        {
            time = time.split("m")[0];

            if (time.contains("."))
            {
                double minutes;
                try
                {
                    minutes = Double.parseDouble(time);
                }
                catch (NumberFormatException e)
                {
                    throw new FormatException(
                            "MCFormat: public static int time(String time)",
                            "time");
                }

                minutes *= 60;

                seconds = Integer.parseInt(Long.toString(Math.round(minutes)));
            }
            else
            {
                try
                {
                    seconds = Integer.parseInt(time) * 60;
                }
                catch (NumberFormatException e)
                {
                    throw new FormatException(
                            "MCFormat: public static int time(String time)",
                            "time");
                }
            }
        }
        else if (time.endsWith("h"))
        {
            time = time.split("h")[0];

            if (time.contains("."))
            {
                double hours;
                try
                {
                    hours = Double.parseDouble(time);
                }
                catch (NumberFormatException e)
                {
                    throw new FormatException(
                            "MCFormat: public static int time(String time)",
                            "time");
                }

                hours *= 3600;

                seconds = Integer.parseInt(Long.toString(Math.round(hours)));
            }
            else
            {
                try
                {
                    seconds = Integer.parseInt(time) * 3600;
                }
                catch (NumberFormatException e)
                {
                    throw new FormatException(
                            "MCFormat: public static int time(String time)",
                            "time");
                }
            }
        }
        else if (time.endsWith("d"))
        {
            time = time.split("d")[0];

            if (time.contains("."))
            {
                double days;
                try
                {
                    days = Double.parseDouble(time);
                }
                catch (NumberFormatException e)
                {
                    throw new FormatException(
                            "MCFormat: public static int time(String time)",
                            "time");
                }

                days *= 86400;

                seconds = Integer.parseInt(Long.toString(Math.round(days)));
            }
            else
            {
                try
                {
                    seconds = Integer.parseInt(time) * 86400;
                }
                catch (NumberFormatException e)
                {
                    throw new FormatException(
                            "MCFormat: public static int time(String time)",
                            "time");
                }
            }
        }
        else
        {
            try
            {
                return Integer.parseInt(time);
            }
            catch (NumberFormatException e)
            {
                throw new FormatException(
                        "MCFormat: public static int time(String time)",
                        "time");
            }
        }

        return seconds;
    }
}
