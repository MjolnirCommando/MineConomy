package me.mjolnir.mineconomy.internal;

import java.io.File;
import java.io.IOException;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.internal.util.IOH;
import me.mjolnir.mineconomy.internal.util.MCFormat;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Handles language file loading.
 * 
 * @author MjolnirCommando
 */
@SuppressWarnings("javadoc")
public class MCLang
{
    private static String langtag = "MineConomy";
    public static String tag = ChatColor.GREEN + "[" + langtag + "] ";
    
    public static String welcomeMessage = "&fWelcome to MineConomy, %player%! Your balance is &a%pos-balance%&c%neg-balance% &f%currency%. Type '/mc help' for help.";
    
    public static boolean displayWelcome = false;
    
    public static String messageWelcomeAccountCreated = "&fYour MineConomy Account has been created!";
    public static String messageAccountBalance = "&fBalance: &a%pos-balance%&c%neg-balance% &f%currency%";
    public static String messageBankBalance = "&f[%bank%] Balance: &a%pos-balance%&c%neg-balance%";
    public static String warnOp = "&eA new version of MineConomy is available! Download it at http://dev.bukkit.org/server-mods/mineconomy/";
    public static String errorPermissionHaveAccount = "&cYou do not have permission to have an account.";
    public static String errorPermissionGeneric = "&cYou do not have permission to do that.";
    public static String errorInt = "&cYou must enter an integer!";
    public static String errorInvalidArgs = "&cInvalid number of arguements!";
    public static String errorCommandRecognition = "&fMineConomy did not recognize your command. Type &7/mc help &ffor help with MineConomy commands.";
    public static String errorCurrencyNotFound = "&cThe currency you requested does not exist.";
    public static String messageCurrencySet = "&f%player%'s currency has been set to %currency%.";
    public static String messageExpCheck = "&fExperience: &a%exp% &fpoints";
    public static String messageGetBalance = "&f%player%'s account balance is %balance% %currency%.";
    public static String messageSetBalance = "&f%player%'s account balance is set to %balance% %currency%.";
    public static String errorMaxDebt = "&cThe value requested is below the maxium allowed debt.";
    public static String messageGive = "&fGave %player% %amount% %currency%.";
    public static String errorTheyEnough = "&c%player% did not have enough money!";
    public static String errorYouEnough = "&cYou do not have enough money!";
    public static String messagePayedTo = "&f%amount% %currency% has been payed to %player%.";
    public static String messagePayedFrom = "&f%player% has payed you %amount% %currency%.";
    public static String messageTook = "&fTook %amount% %currency% from %account%.";
    public static String messageEmpty = "&fSet %account%'s balance to 0.";
    public static String messageCreated = "&fCreated account '%account%'.";
    public static String errorAccountExists = "&cThe account '%account%' already exists!";
    public static String messageDeleted = "&fAccount '%account%' has been deleted.";
    public static String errorNoAccount = "&cThe account you requested does not exist.";
    public static String errorNoBank = "&cThe bank you requested (%bank%) does not exist.";
    public static String errorNoBankAccount = "&cThe account you requested in the specified bank (%bank%) does not exist.";
    public static String messageBankCreated = "&fCreated bank '%bank%'.";
    public static String errorBankExists = "&cThe bank '%bank%' already exists!";
    public static String messageBankDeleted = "&fBank '%bank%' has been deleted.";
    public static String errorMoneyFormat = "&cYou must enter the correct monetarily formatted number.";
    public static String errorBankAccountExists = "&cThe account '%account%' in bank '%bank%' does not exist";
    public static String messageBankAccountCreated = "&fCreated account '%account%' in bank '%bank%'.";
    public static String messageBankAccountDeleted = "&fDeleted account '%account%' in bank '%bank%'.";
    public static String messageEmptyBankAccount = "&fSet %account%'s balance in bank %bank% to 0.";
    public static String messageBankRenamed = "&fRenamed bank '%oldbank%' to '%bank%'.";
    public static String messageGetBankAccount = "&f%account%'s balance in bank %bank% is %balance%.";
    public static String messageSetBankAccount = "&fSet %account%'s balance in bank %bank% to %balance%.";
    public static String messageBankAccountRenamed = "&fRenamed account '%oldaccount%' in bank '%oldbank% to '%account%' in bank '%bank%'";
    public static String messageBankAccountDeposit = "&fDeposited %amount% in %account% in bank %bank%.";
    public static String messageBankAccountWithdraw = "&fWithdrew %amount% in %account% in bank %bank%.";
    public static String messageTransactionComplete = "&fTransaction complete.";
    public static String errorNoPhysicalCurrency = "&cThat Physical Currency could not be found.";
    public static String errorExpEnough = "&cYou do not have enough experience.";
    public static String messageHelp1 = "<br>&a===== MineConomy Help Page 1/3 =====<br>&f<REQUIRED> [OPTION] (OPTIONAL)<br><br>&7/mc help <PAGE>&f - displays this menu.<br>&7/mc balance &for &7/money &f- displays your account balance.<br>&7/mc pay <ACCOUNT> &f- pays specified account.<br>&7/mc get <ACCOUNT> &f- displays specified account's balance.<br>&7/mc set <ACCOUNT> <AMOUNT> &f- sets specified account's balance.<br>&7/mc empty <ACCOUNT> &f- sets specified account's balance to 0.<br>&7/mc create <ACCOUNT> &f- creates new account.<br>&7/mc delete <ACCOUNT> &f- deletes existing account.<br>&7/mc give <ACCOUNT> <AMOUNT> &f- gives specified account the specified amount.<br>&7/mc take <ACCOUNT> <AMOUNT> &f- takes specified amount from the specified account.<br>&7/mc exp &f- displays your amount of experience.";
    public static String messageHelp2 = "<br>&a===== MineConomy Help Page 2/3 =====<br>&7/mc deposit <TYPE> <AMOUNT> &f- deposits specified amount of physical currency into your account.<br>&7/mc withdraw <TYPE> <AMOUNT> &f- withdraws specified amount of physical currency from your account.<br>&7/mc top <SIZE>&f- displays the richest accounts on the server.<br>&7/mc setcurrency (ACCOUNT) <CURRENCY> &f- sets the specified account's currency.<br>&7/mcb <BANK> &f- displays your balance in the specified bank.<br>&7/mcb balance <BANK> &f- displays your balance in the specified bank.<br>&7/mcb create <BANK> (ACCOUNT) &f- creates new bank/bank account.<br>&7/mcb delete <BANK> (ACCOUNT) &f- deletes specified bank/bank account.<br>&7/mcb get <BANK> <ACCOUNT> &f- displays the balance of specified bank account.<br>&7/mcb set <BANK> <ACCOUNT> &f- sets the balance of specified bank account.";
    public static String messageHelp3 = "<br>&a===== MineConomy Help Page 3/3 =====<br>&7/mcb empty <BANK> <ACCOUNT> &f- empties the specified bank account.<br>&7/mcb rename <BANK> (ACCOUNT) <NEW_BANK> (NEW_ACCOUNT) &f- renames the specified bank/bank account.<br>&7/mcb transfer <BANK> (ACCOUNT) <TO_BANK> <TO_ACCOUNT> <AMOUNT> &f- transfers the specified amount from bank account to bank account.<br>&7/mcb join <BANK> &f- joins specified bank.<br>&7/mcb leave <BANK> &f- leaves specified bank.<br>&7/mcb deposit <BANK> <AMOUNT> &f- deposits amount in your account.<br>&7/mcb withdraw <BANK> <AMOUNT> &f- withdraws amount from your account.";
    
    public static File                      langFile = new File(
            MineConomy.maindir + "lang/"
                    + "lang-" + Settings.lang + ".yml");
    private static YamlConfiguration         lang;
    
    public static void load()
    {
        lang = YamlConfiguration.loadConfiguration(langFile);
        lang
                .options()
                .header("=== MineConomy Language ===\n\n    \n");

        if (!langFile.exists())
        {
            IOH.log("Language file not found...", IOH.INFO);
            lang.set("Lang", "");
            lang.set("Lang.Tag", langtag);
            lang.set("Lang.Message.Welcome Message", welcomeMessage);
            lang.set("Lang.Message.Welcome Account Created", messageWelcomeAccountCreated);
            lang.set("Lang.Message.Account Balance", messageAccountBalance);
            lang.set("Lang.Message.Bank Balance", messageBankBalance);
            lang.set("Lang.Op.Update", warnOp);
            lang.set("Lang.Error.Permission.Have Account", errorPermissionHaveAccount);
            lang.set("Lang.Error.Permission.Generic", errorPermissionGeneric);
            lang.set("Lang.Error.Int", errorInt);
            lang.set("Lang.Error.Invalid Args", errorInvalidArgs);
            lang.set("Lang.Error.Command.Recognition", errorCommandRecognition);
            lang.set("Lang.Error.Currency Not Found", errorCurrencyNotFound);
            lang.set("Lang.Message.Currency Set", messageCurrencySet);
            lang.set("Lang.Message.Exp Check", messageExpCheck);
            lang.set("Lang.Message.Get Balance", messageGetBalance);
            lang.set("Lang.Message.Set Balance", messageSetBalance);
            lang.set("Lang.Error.Max Debt", errorMaxDebt);
            lang.set("Lang.Message.Give", messageGive);
            lang.set("Lang.Error.They Enough", errorTheyEnough);
            lang.set("Lang.Error.You Enough", errorYouEnough);
            lang.set("Lang.Message.Payed To", messagePayedTo);
            lang.set("Lang.Message.Payed From", messagePayedFrom);
            lang.set("Lang.Message.Took", messageTook);
            lang.set("Lang.Message.Empty", messageEmpty);
            lang.set("Lang.Message.Account Created", messageCreated);
            lang.set("Lang.Error.Account Exists", errorAccountExists);
            lang.set("Lang.Message.Account Deleted", messageDeleted);
            lang.set("Lang.Error.No Account", errorNoAccount);
            lang.set("Lang.Error.No Bank", errorNoBank);
            lang.set("Lang.Error.No Bank Account", errorNoBankAccount);
            lang.set("Lang.Message.Bank Created", messageBankCreated);
            lang.set("Lang.Error.Bank Exists", errorBankExists);
            lang.set("Lang.Message.Bank Deleted", messageBankDeleted);
            lang.set("Lang.Error.Money Format", errorMoneyFormat);
            lang.set("Lang.Error.Bank Account Exists", errorBankAccountExists);
            lang.set("Lang.Message.Bank Account Created", messageBankAccountCreated);
            lang.set("Lang.Message.Bank Account Deleted", messageBankAccountDeleted);
            lang.set("Lang.Message.Empty Bank Account", messageEmptyBankAccount);
            lang.set("Lang.Message.Bank Renamed", messageBankRenamed);
            lang.set("Lang.Message.Get Bank Account", messageGetBankAccount);
            lang.set("Lang.Message.Set Bank Account", messageSetBankAccount);
            lang.set("Lang.Message.Bank Account Renamed", messageBankAccountRenamed);
            lang.set("Lang.Message.Bank Account Deposit", messageBankAccountDeposit);
            lang.set("Lang.Message.Bank Account Withdraw", messageBankAccountWithdraw);
            lang.set("Lang.Message.Transaction Complete", messageTransactionComplete);
            lang.set("Lang.Error.No Physical Currency", errorNoPhysicalCurrency);
            lang.set("Lang.Error.Exp Enough", errorExpEnough);
            lang.set("Lang.Message.Help.Page 1", messageHelp1);
            lang.set("Lang.Message.Help.Page 2", messageHelp2);
            lang.set("Lang.Message.Help.Page 3", messageHelp3);
            
            IOH.log("Language file created!", IOH.INFO);
            save();
        }

        IOH.log("Loading Language file...", IOH.INFO);

        reload();
        
        IOH.log("Language file loaded!", IOH.INFO);
    }
    
    public static void reload()
    {
        lang = YamlConfiguration.loadConfiguration(langFile);
        
        welcomeMessage = MCFormat.color(lang.getString("Lang.Message.Welcome Message", welcomeMessage));
        if (welcomeMessage.equals(""))
        {
            displayWelcome = false;
        }
        else
        {
            displayWelcome = true;
        }
        
        langtag = MCFormat.color(lang.getString("Lang.Tag", langtag));
        messageWelcomeAccountCreated = MCFormat.color(lang.getString("Lang.Message.Welcome Account Created", messageWelcomeAccountCreated));
        messageAccountBalance = MCFormat.color(lang.getString("Lang.Message.Account Balance", messageAccountBalance));
        messageBankBalance = MCFormat.color(lang.getString("Lang.Message.Bank Balance", messageBankBalance));
        warnOp = MCFormat.color(lang.getString("Lang.Op.Update", warnOp));
        errorPermissionHaveAccount = MCFormat.color(lang.getString("Lang.Error.Permission.Have Account", errorPermissionHaveAccount));
        errorPermissionGeneric = MCFormat.color(lang.getString("Lang.Error.Permission.Generic", errorPermissionGeneric));
        errorInt = MCFormat.color(lang.getString("Lang.Error.Int", errorInt));
        errorInvalidArgs = MCFormat.color(lang.getString("Lang.Error.Invalid Args", errorInvalidArgs));
        errorCommandRecognition = MCFormat.color(lang.getString("Lang.Error.Command.Recognition", errorCommandRecognition));
        errorCurrencyNotFound = MCFormat.color(lang.getString("Lang.Error.Currency Not Found", errorCurrencyNotFound));
        messageCurrencySet = MCFormat.color(lang.getString("Lang.Message.Currency Set", messageCurrencySet));
        messageExpCheck = MCFormat.color(lang.getString("Lang.Message.Exp Check", messageExpCheck));
        messageGetBalance = MCFormat.color(lang.getString("Lang.Message.Get Balance", messageGetBalance));
        messageSetBalance = MCFormat.color(lang.getString("Lang.Message.Set Balance", messageSetBalance));
        errorMaxDebt = MCFormat.color(lang.getString("Lang.Error.Max Debt", errorMaxDebt));
        messageGive = MCFormat.color(lang.getString("Lang.Message.Give", messageGive));
        errorTheyEnough = MCFormat.color(lang.getString("Lang.Error.They Enough", errorTheyEnough));
        errorYouEnough = MCFormat.color(lang.getString("Lang.Error.You Enough", errorYouEnough));
        messagePayedTo = MCFormat.color(lang.getString("Lang.Message.Payed To", messagePayedTo));
        messagePayedFrom = MCFormat.color(lang.getString("Lang.Message.Payed From", messagePayedFrom));
        messageTook = MCFormat.color(lang.getString("Lang.Message.Took", messageTook));
        messageEmpty = MCFormat.color(lang.getString("Lang.Message.Empty", messageEmpty));
        messageCreated = MCFormat.color(lang.getString("Lang.Message.Account Created", messageCreated));
        errorAccountExists = MCFormat.color(lang.getString("Lang.Error.Account Exists", errorAccountExists));
        messageDeleted = MCFormat.color(lang.getString("Lang.Message.Account Deleted", messageDeleted));
        errorNoAccount = MCFormat.color(lang.getString("Lang.Error.No Account", errorNoAccount));
        errorNoBank = MCFormat.color(lang.getString("Lang.Error.No Bank", errorNoBank));
        errorNoBankAccount = MCFormat.color(lang.getString("Lang.Error.No Bank Account", errorNoBankAccount));
        messageBankCreated = MCFormat.color(lang.getString("Lang.Message.Bank Created", messageBankCreated));
        errorBankExists = MCFormat.color(lang.getString("Lang.Error.Bank Exists", errorBankExists));
        messageBankDeleted = MCFormat.color(lang.getString("Lang.Message.Bank Deleted", messageBankDeleted));
        errorMoneyFormat = MCFormat.color(lang.getString("Lang.Error.Money Format", errorMoneyFormat));
        errorBankAccountExists = MCFormat.color(lang.getString("Lang.Error.Bank Account Exists", errorBankAccountExists));
        messageBankAccountCreated = MCFormat.color(lang.getString("Lang.Message.Bank Account Created", messageBankAccountCreated));
        messageBankAccountDeleted = MCFormat.color(lang.getString("Lang.Message.Bank Account Deleted", messageBankAccountDeleted));
        messageEmptyBankAccount = MCFormat.color(lang.getString("Lang.Message.Empty Bank Account", messageEmptyBankAccount));
        messageBankRenamed = MCFormat.color(lang.getString("Lang.Message.Bank Renamed", messageBankRenamed));
        messageGetBankAccount = MCFormat.color(lang.getString("Lang.Message.Get Bank Account", messageGetBankAccount));
        messageSetBankAccount = MCFormat.color(lang.getString("Lang.Message.Set Bank Account", messageSetBankAccount));
        messageBankAccountRenamed = MCFormat.color(lang.getString("Lang.Message.Bank Account Renamed", messageBankAccountRenamed));
        messageBankAccountDeposit = MCFormat.color(lang.getString("Lang.Message.Bank Account Deposit", messageBankAccountDeposit));
        messageBankAccountWithdraw = MCFormat.color(lang.getString("Lang.Message.Bank Account Withdraw", messageBankAccountWithdraw));
        messageTransactionComplete = MCFormat.color(lang.getString("Lang.Message.Transaction Complete", messageTransactionComplete));
        errorNoPhysicalCurrency = MCFormat.color(lang.getString("Lang.Error.No Physical Currency", errorNoPhysicalCurrency));
        errorExpEnough = MCFormat.color(lang.getString("Lang.Error.Exp Enough", errorExpEnough));
        messageHelp1 = MCFormat.color(lang.getString("Lang.Message.Help.Page 1", messageHelp1));
        messageHelp2 = MCFormat.color(lang.getString("Lang.Message.Help.Page 2", messageHelp2));
        messageHelp3 = MCFormat.color(lang.getString("Lang.Message.Help.Page 3", messageHelp3));
        
        tag = ChatColor.GREEN + "[" + langtag + "] ";
    }
    
    public static void save()
    {
        lang.set("Lang", "");
        lang.set("Lang.Tag", MCFormat.decolor(langtag));
        lang.set("Lang.Message.Welcome Message", MCFormat.decolor(welcomeMessage));
        lang.set("Lang.Message.Welcome Account Created", MCFormat.decolor(messageWelcomeAccountCreated));
        lang.set("Lang.Message.Account Balance", MCFormat.decolor(messageAccountBalance));
        lang.set("Lang.Message.Bank Balance", MCFormat.decolor(messageBankBalance));
        lang.set("Lang.Op.Update", MCFormat.decolor(warnOp));
        lang.set("Lang.Error.Permission.Have Account", MCFormat.decolor(errorPermissionHaveAccount));
        lang.set("Lang.Error.Permission.Generic", MCFormat.decolor(errorPermissionGeneric));
        lang.set("Lang.Error.Int", MCFormat.decolor(errorInt));
        lang.set("Lang.Error.Invalid Args", MCFormat.decolor(errorInvalidArgs));
        lang.set("Lang.Error.Command.Recognition", MCFormat.decolor(errorCommandRecognition));
        lang.set("Lang.Error.Currency Not Found", MCFormat.decolor(errorCurrencyNotFound));
        lang.set("Lang.Message.Currency Set", MCFormat.decolor(messageCurrencySet));
        lang.set("Lang.Message.Exp Check", MCFormat.decolor(messageExpCheck));
        lang.set("Lang.Message.Get Balance", MCFormat.decolor(messageGetBalance));
        lang.set("Lang.Message.Set Balance", MCFormat.decolor(messageSetBalance));
        lang.set("Lang.Error.Max Debt", MCFormat.decolor(errorMaxDebt));
        lang.set("Lang.Message.Give", MCFormat.decolor(messageGive));
        lang.set("Lang.Error.They Enough", MCFormat.decolor(errorTheyEnough));
        lang.set("Lang.Error.You Enough", MCFormat.decolor(errorYouEnough));
        lang.set("Lang.Message.Payed To", MCFormat.decolor(messagePayedTo));
        lang.set("Lang.Message.Payed From", MCFormat.decolor(messagePayedFrom));
        lang.set("Lang.Message.Took", MCFormat.decolor(messageTook));
        lang.set("Lang.Message.Empty", MCFormat.decolor(messageEmpty));
        lang.set("Lang.Message.Account Created", MCFormat.decolor(messageCreated));
        lang.set("Lang.Error.Account Exists", MCFormat.decolor(errorAccountExists));
        lang.set("Lang.Message.Account Deleted", MCFormat.decolor(messageDeleted));
        lang.set("Lang.Error.No Account", MCFormat.decolor(errorNoAccount));
        lang.set("Lang.Error.No Bank", MCFormat.decolor(errorNoBank));
        lang.set("Lang.Error.No Bank Account", MCFormat.decolor(errorNoBankAccount));
        lang.set("Lang.Message.Bank Created", MCFormat.decolor(messageBankCreated));
        lang.set("Lang.Error.Bank Exists", MCFormat.decolor(errorBankExists));
        lang.set("Lang.Message.Bank Deleted", MCFormat.decolor(messageBankDeleted));
        lang.set("Lang.Error.Money Format", MCFormat.decolor(errorMoneyFormat));
        lang.set("Lang.Error.Bank Account Exists", MCFormat.decolor(errorBankAccountExists));
        lang.set("Lang.Message.Bank Account Created", MCFormat.decolor(messageBankAccountCreated));
        lang.set("Lang.Message.Bank Account Deleted", MCFormat.decolor(messageBankAccountDeleted));
        lang.set("Lang.Message.Empty Bank Account", MCFormat.decolor(messageEmptyBankAccount));
        lang.set("Lang.Message.Bank Renamed", MCFormat.decolor(messageBankRenamed));
        lang.set("Lang.Message.Get Bank Account", MCFormat.decolor(messageGetBankAccount));
        lang.set("Lang.Message.Set Bank Account", MCFormat.decolor(messageSetBankAccount));
        lang.set("Lang.Message.Bank Account Renamed", MCFormat.decolor(messageBankAccountRenamed));
        lang.set("Lang.Message.Bank Account Deposit", MCFormat.decolor(messageBankAccountDeposit));
        lang.set("Lang.Message.Bank Account Withdraw", MCFormat.decolor(messageBankAccountWithdraw));
        lang.set("Lang.Message.Transaction Complete", MCFormat.decolor(messageTransactionComplete));
        lang.set("Lang.Error.No Physical Currency", MCFormat.decolor(errorNoPhysicalCurrency));
        lang.set("Lang.Error.Exp Enough", MCFormat.decolor(errorExpEnough));
        lang.set("Lang.Message.Help.Page 1", MCFormat.decolor(messageHelp1));
        lang.set("Lang.Message.Help.Page 2", MCFormat.decolor(messageHelp2));
        lang.set("Lang.Message.Help.Page 3", MCFormat.decolor(messageHelp3));
        
        try
        {
            lang.save(langFile);
        }
        catch (IOException e)
        {
            IOH.error("IOException", e);
        }
        
        reload();
    }
    
    public static String parse(String message, String[] args)
    {
        if (message.equals(welcomeMessage))
        {
            message = message.replace("%player%", args[0]);
            message = message.replace("%currency%", args[1]);
            
            double balance = Double.parseDouble(args[2]);
                
            if (balance >= 0)
            {
                message = message.replace("%pos-balance%", MCFormat.format(balance));
                message = message.replace("%neg-balance%", "");
            }
            else
            {
                message = message.replace("%pos-balance%", "");
                message = message.replace("%neg-balance%", MCFormat.format(balance));
            }
        }
        else if (message.equals(messageAccountBalance))
        {
            message = message.replace("%currency%", args[0]);
            
            double balance = Double.parseDouble(args[1]);
                
            if (balance >= 0)
            {
                message = message.replace("%pos-balance%", MCFormat.format(balance));
                message = message.replace("%neg-balance%", "");
            }
            else
            {
                message = message.replace("%pos-balance%", "");
                message = message.replace("%neg-balance%", MCFormat.format(balance));
            }
        }
        else if (message.equals(messageBankBalance))
        {
            message = message.replace("%bank%", args[0]);
            
            double balance = Double.parseDouble(args[1]);
                
            if (balance >= 0)
            {
                message = message.replace("%pos-balance%", MCFormat.format(balance));
                message = message.replace("%neg-balance%", "");
            }
            else
            {
                message = message.replace("%pos-balance%", "");
                message = message.replace("%neg-balance%", MCFormat.format(balance));
            }
        }
        else if (message.equals(messageCurrencySet))
        {
            message = message.replace("%player%", args[0]);
            message = message.replace("%currency%", args[1]);
        }
        else if (message.equals(messageExpCheck))
        {
            message = message.replace("%exp%", args[0]);
        }
        else if (message.equals(messageGetBalance) || message.equals(messageSetBalance))
        {
            message = message.replace("%player%", args[0]);
            message = message.replace("%balance%", MCFormat.format(Double.parseDouble(args[1])));
            message = message.replace("%currency%", args[2]);
        }
        else if (message.equals(messageGive))
        {
            message = message.replace("%player%", args[0]);
            message = message.replace("%amount%", MCFormat.format(Double.parseDouble(args[1])));
            message = message.replace("%currency%", args[2]);
        }
        else if (message.equals(errorTheyEnough))
        {
            message = message.replace("%player%", args[0]);
        }
        else if (message.equals(messagePayedTo))
        {
            message = message.replace("%amount%", MCFormat.format(Double.parseDouble(args[0])));
            message = message.replace("%currency%", args[1]);
            message = message.replace("%player%", args[2]);
        }
        else if (message.equals(messagePayedFrom))
        {
            message = message.replace("%player%", args[0]);
            message = message.replace("%amount%", MCFormat.format(Double.parseDouble(args[1])));
            message = message.replace("%currency%", args[2]);
        }
        else if (message.equals(messageTook))
        {
            message = message.replace("%amount%", MCFormat.format(Double.parseDouble(args[0])));
            message = message.replace("%currency%", args[1]);
            message = message.replace("%account%", args[2]);
        }
        else if (message.equals(messageCreated) || message.equals(messageDeleted) || message.equals(messageEmpty) || message.equals(errorAccountExists))
        {
            message = message.replace("%account%", args[0]);
        }
        else if (message.equals(errorNoBank) || message.equals(errorNoBankAccount) || message.equals(messageBankCreated) || message.equals(errorBankExists) || message.equals(messageBankDeleted))
        {
            message = message.replace("%bank%", args[0]);
        }
        else if (message.equals(errorBankAccountExists) || message.equals(messageBankAccountDeleted) || message.equals(messageBankAccountCreated) || message.equals(messageEmptyBankAccount))
        {
            message = message.replace("%account%", args[0]);
            message = message.replace("%bank%", args[1]);
        }
        else if (message.equals(messageBankRenamed))
        {
            message = message.replace("%bank%", args[0]);
            message = message.replace("%oldbank%", args[1]);
        }
        else if (message.equals(messageGetBankAccount) || message.equals(messageSetBankAccount))
        {
            message = message.replace("%account%", args[0]);
            message = message.replace("%bank%", args[1]);
            message = message.replace("%balance%", args[2]);
        }
        else if (message.equals(messageBankAccountRenamed))
        {
            message = message.replace("%account%", args[0]);
            message = message.replace("%bank%", args[1]);
            message = message.replace("%oldaccount%", args[2]);
            message = message.replace("%oldbank%", args[3]);
        }
        else if (message.equals(messageBankAccountDeposit) || message.equals(messageBankAccountWithdraw))
        {
            message = message.replace("%bank%", args[0]);
            message = message.replace("%account%", args[1]);
            message = message.replace("%amount%", MCFormat.format(Double.parseDouble(args[2])));
        }
        
        return MCFormat.color(message);
    }
}
