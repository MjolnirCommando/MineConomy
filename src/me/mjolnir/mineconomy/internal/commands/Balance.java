package me.mjolnir.mineconomy.internal.commands;

import java.text.DecimalFormat;
import java.util.ArrayList;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.exceptions.AccountNameConflictException;
import me.mjolnir.mineconomy.exceptions.BankNameConflictException;
import me.mjolnir.mineconomy.exceptions.InsufficientFundsException;
import me.mjolnir.mineconomy.exceptions.MaxDebtException;
import me.mjolnir.mineconomy.exceptions.NoAccountException;
import me.mjolnir.mineconomy.exceptions.NoBankException;
import me.mjolnir.mineconomy.internal.MCCom;
import me.mjolnir.mineconomy.internal.MCLang;
import me.mjolnir.mineconomy.internal.util.MCFormat;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Keeps MCCommandExecutor code neater.
 * 
 * @author MjolnirCommando
 */
public class Balance
{
    private static MineConomy plugin;

    /**
     * Sends the help message to player.
     * 
     * @param player
     * @param page
     */
    public static void help(Player player, int page)
    {
        String pgcontent = "";
        
        if (page == 1)
        {
            pgcontent = MCLang.messageHelp1;
//            player.sendMessage(" ");
//            player.sendMessage(ChatColor.GREEN + "===== MineConomy Help Page 1/3 =====");
//            player.sendMessage(ChatColor.WHITE
//                    + "<REQUIRED> [OPTION] (OPTIONAL)");
//            player.sendMessage(ChatColor.GRAY + "/mc help <PAGE #>"
//                    + ChatColor.WHITE + "- displays this menu.");
//            player.sendMessage(ChatColor.GRAY + "/mc balance "
//                    + ChatColor.WHITE + "or " + ChatColor.GRAY + "/money "
//                    + ChatColor.WHITE + "- displays your account balance.");
//            player.sendMessage(ChatColor.GRAY + "/mc pay <ACCOUNT> "
//                    + ChatColor.WHITE + "- pays specified account.");
//            player.sendMessage(ChatColor.GRAY + "/mc get <ACCOUNT> "
//                    + ChatColor.WHITE
//                    + "- displays specified account's balance.");
//            player.sendMessage(ChatColor.GRAY + "/mc set <ACCOUNT> <AMOUNT> "
//                    + ChatColor.WHITE + "- sets specified account's balance.");
//            player.sendMessage(ChatColor.GRAY + "/mc empty <ACCOUNT> "
//                    + ChatColor.WHITE
//                    + "- sets specifeid account's balance to 0.");
//            player.sendMessage(ChatColor.GRAY + "/mc create <ACCOUNT> "
//                    + ChatColor.WHITE + "- creates new account.");
//            player.sendMessage(ChatColor.GRAY + "/mc delete <ACCOUNT> "
//                    + ChatColor.WHITE + "- deletes existing account.");
//            player.sendMessage(ChatColor.GRAY + "/mc give <ACCOUNT> <AMOUNT> "
//                    + ChatColor.WHITE
//                    + "- gives specified account the specified " + "amount.");
//            player.sendMessage(ChatColor.GRAY + "/mc take <ACCOUNT> <AMOUNT> "
//                    + ChatColor.WHITE + "- takes specified amount from the "
//                    + "specified account.");
//            player.sendMessage(ChatColor.GRAY + "/mc exp " + ChatColor.WHITE
//                    + "- displays your amount of experience.");
        }
        else if (page == 2)
        {
            pgcontent = MCLang.messageHelp2;
//            player.sendMessage(" ");
//            player.sendMessage(ChatColor.GREEN + "===== MineConomy Help Page 2/3 =====");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mc deposit <TYPE> <AMOUNT> "
//                    + ChatColor.WHITE
//                    + "- deposits specified amount of physical currency into your account.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mc withdraw <TYPE> <AMOUNT> "
//                    + ChatColor.WHITE
//                    + "- withdraws specified amount of physical currency from your account.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mc top <SIZE>"
//                    + ChatColor.WHITE
//                    + "- displays the richest accounts on the server.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mc setcurrency (ACCOUNT) <CURRENCY> "
//                    + ChatColor.WHITE
//                    + "- sets the specified account's currency.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb <BANK> "
//                    + ChatColor.WHITE
//                    + "- displays your balance in the specified bank.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb balance <BANK> "
//                    + ChatColor.WHITE
//                    + "- displays your balance in the specified bank.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb create <BANK> (ACCOUNT) "
//                    + ChatColor.WHITE
//                    + "- creates new bank/bank account.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb delete <BANK> (ACCOUNT) "
//                    + ChatColor.WHITE
//                    + "- deletes specified bank/bank account.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb get <BANK> <ACCOUNT> "
//                    + ChatColor.WHITE
//                    + "- displays the balance of specified bank account.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb set <BANK> <ACCOUNT> "
//                    + ChatColor.WHITE
//                    + "- sets the balance of specified bank account.");
        }
        else if (page == 3)
        {
            pgcontent = MCLang.messageHelp3;
//            player.sendMessage(" ");
//            player.sendMessage(ChatColor.GREEN + "===== MineConomy Help Page 3/3 =====");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb empty <BANK> <ACCOUNT> "
//                    + ChatColor.WHITE
//                    + "- empties the specified bank account.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb rename <BANK> (ACCOUNT) <NEW_BANK> (NEW_ACCOUNT) "
//                    + ChatColor.WHITE
//                    + "- renames the specified bank/bank account.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb transfer <BANK> (ACCOUNT) <TO_BANK> <TO_ACCOUNT> <AMOUNT> "
//                    + ChatColor.WHITE
//                    + "- transfers the specified amount from bank account to bank account.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb join <BANK> "
//                    + ChatColor.WHITE
//                    + "- joins specified bank.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb leave <BANK> "
//                    + ChatColor.WHITE
//                    + "- leaves specified bank.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb deposit <BANK> <AMOUNT> "
//                    + ChatColor.WHITE
//                    + "- deposits amount in your account.");
//            player.sendMessage(ChatColor.GRAY
//                    + "/mcb withdraw <BANK> <AMOUNT> "
//                    + ChatColor.WHITE
//                    + "- withdraws amount from your account.");
        }
        
        String[] breaks = pgcontent.split("<br>");
        
        for (int i = 0; breaks.length > i; i++)
        {
            player.sendMessage(breaks[i]);
        }
    }

    /**
     * Checks the player's balance.
     * 
     * @param player
     */
    public static void check(Player player)
    {
        double balance = 0;

        try
        {
            balance = MCCom.getBalance(player.getName());
        }
        catch (NoAccountException e)
        {
            noAccount(player);
            return;
        }
        
        String[] args = {MCCom.getCurrency(player.getName()), balance + ""};

        player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageAccountBalance, args));
    }

    /**
     * Checks the player's experience points.
     * 
     * @param player
     */
    public static void checkexp(Player player)
    {
        DecimalFormat dec = new DecimalFormat("####");
        int currentLevel = player.getLevel() * 100;
        String currentExp = dec.format(player.getExp() * 100.0F);
        int totalXP = Integer.valueOf(currentLevel).intValue()
                + Integer.valueOf(currentExp).intValue();

        String[] args = {MCFormat.format(totalXP, false)};
        
        player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageExpCheck, args));
    }

    /**
     * Gets a player's balance.
     * 
     * @param player
     * @param toPlayer
     */
    public static void get(Player player, String toPlayer)
    {
        boolean HasAccount = MCCom.exists(toPlayer);

        if (HasAccount)
        {
            String[] args = {toPlayer, MCFormat.format(MCCom.getBalance(toPlayer)), MCCom.getCurrency(toPlayer)};
            
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageGetBalance, args));
        }
        else
        {
            noAccount(player);
        }
    }

    /**
     * Sets a player's balance.
     * 
     * @param player
     * @param toPlayer
     * @param amount
     */
    public static void set(Player player, String toPlayer, double amount)
    {
        boolean HasAccount = MCCom.exists(toPlayer);

        if (HasAccount)
        {
            try
            {
                MCCom.setBalance(toPlayer, amount);
                
                String[] args = {toPlayer, MCFormat.format(MCCom.getBalance(toPlayer)), MCCom.getCurrency(player.getName())};
                
                player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageSetBalance, args));
            }
            catch (MaxDebtException e)
            {
                player.sendMessage(MCLang.tag + MCLang.errorMaxDebt);
            }
            
        }
        else
        {
            noAccount(player);
        }
    }

    /**
     * Pays a player.
     * 
     * @param player
     * @param toPlayer
     * @param payAmount
     */
    public static void pay(Player player, String toPlayer, double payAmount)
    {
        boolean hasAccount = MCCom.exists(player.getName());
        if (hasAccount)
        {
            double balance = MCCom.getBalance(player.getName());
            double amount = Math.abs(payAmount);
            boolean toHasAccount = MCCom.exists(toPlayer);
            if (toHasAccount == true)
            {

                if (MCCom.canAfford(player.getName(), amount))
                {
                    double newBalance = balance - amount;
                    MCCom.setBalance(player.getName(), newBalance);
                    double toBalance = MCCom.getBalance(toPlayer);

                    double value = MCCom.getCurrencyValue(MCCom
                            .getCurrency(player.getName()));
                    double toValue = MCCom.getCurrencyValue(MCCom
                            .getCurrency(toPlayer));

                    double base = amount / value;
                    base = base * toValue;

                    double newToBalance = toBalance + base;
                    MCCom.setBalance(toPlayer, newToBalance);
                    
                    String[] args = {MCFormat.format(amount), MCCom.getCurrency(player.getName()), toPlayer};
                    
                    player.sendMessage(MCLang.tag
                            + MCLang.parse(MCLang.messagePayedTo, args));
                    try
                    {
                        Player reciever = plugin.getServer()
                                .getPlayer(toPlayer);
                        
                        String[] args2 = {player.getName(), MCFormat.format(amount), MCCom.getCurrency(player.getName())};
                        
                        reciever.sendMessage(MCLang.tag
                                + MCLang.parse(MCLang.messagePayedFrom, args2));
                    }
                    catch (NullPointerException e)
                    {
                        //IOH.error("NullPointerException", e); In case player is offline
                    }
                }
                else
                {
                    player.sendMessage(MCLang.tag
                            + MCLang.errorYouEnough);
                }
            }
            else
            {
                noAccount(player);
            }
        }
    }

    /**
     * Gives a player money.
     * 
     * @param player
     * @param toPlayer
     * @param payAmount
     */
    public static void give(Player player, String toPlayer, String payAmount)
    {
        boolean HasAccount = MCCom.exists(toPlayer);

        if (HasAccount)
        {
            double amount = Double.parseDouble(payAmount);
            amount += MCCom.getBalance(toPlayer);
            MCCom.setBalance(toPlayer, amount);
            
            String[] args = {toPlayer, payAmount, MCCom.getCurrency(player.getName())};
            
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageGive, args));
        }
        else
        {
            noAccount(player);
        }
    }

    /**
     * Takes money from a player.
     * 
     * @param player
     * @param toPlayer
     * @param takeAmount
     */
    public static void take(Player player, String toPlayer, String takeAmount)
    {
        boolean HasAccount = MCCom.exists(toPlayer);

        if (HasAccount)
        {
            double amount = Double.parseDouble(takeAmount);
            double balance = MCCom.getBalance(toPlayer);
            if (MCCom.canAfford(toPlayer, amount))
            {
                MCCom.setBalance(toPlayer, balance - amount);
                
                String[] args = {MCFormat.format(amount), MCCom.getCurrency(player.getName()), toPlayer};
                
                player.sendMessage(MCLang.tag
                        + MCLang.parse(MCLang.messageTook, args));
            }
            else
            {
                String[] args = {toPlayer};
                player.sendMessage(MCLang.tag
                        + MCLang.parse(MCLang.errorTheyEnough, args));
            }
        }
        else
        {
            noAccount(player);
        }
    }

    /**
     * Sets a player's balance to 0.
     * 
     * @param player
     * @param toPlayer
     */
    public static void empty(Player player, String toPlayer)
    {
        boolean HasAccount = MCCom.exists(toPlayer);

        if (HasAccount)
        {
            MCCom.setBalance(toPlayer, 0);
            try
            {
                String[] args = {toPlayer};
                
                player.sendMessage(MCLang.tag
                        + MCLang.parse(MCLang.messageEmpty, args));
            }
            catch (NullPointerException e)
            {
                // IGNORE
            }
        }
        else
        {
            noAccount(player);
        }
    }

    /**
     * Creates a new account.
     * 
     * @param player
     * @param toPlayer
     */
    public static void create(Player player, String toPlayer)
    {
        String[] args = {toPlayer};
        
        try
        {
            MCCom.create(toPlayer);
            
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageCreated, args));
        }
        catch (AccountNameConflictException e)
        {
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.errorAccountExists, args));
        }
    }

    /**
     * Deletes an account.
     * 
     * @param player
     * @param toPlayer
     */
    public static void delete(Player player, String toPlayer)
    {
        boolean HasAccount = MCCom.exists(toPlayer);

        if (HasAccount)
        {
            MCCom.delete(toPlayer);
            
            String[] args = {toPlayer};
            
            player.sendMessage(MCLang.tag
                    + MCLang.parse(MCLang.messageDeleted, args));
        }
        else
        {
            noAccount(player);
        }
    }

    /**
     * Deposits the specified amount of physical currency into specified
     * account.
     * 
     * @param player
     * @param currency
     * @param amount
     */
    public static void deposit(Player player, String currency, int amount)
    {
        boolean HasAccount = MCCom.exists(player.getName());

        if (HasAccount)
        {
            if (MCCom.physicalCurrencyExists(currency))
            {
                if (MCCom.idExists("_exp"))
                {
                    String expcurrency = MCCom.getCurrencyById("_exp");

                    if (currency.equals(expcurrency))
                    {
                        double balance = MCCom.getBalance(player.getName());

                        DecimalFormat dec = new DecimalFormat("####");
                        int currentLevel = player.getLevel() * 100;
                        String currentExp = dec
                                .format(player.getExp() * 100.0F);
                        int totalXP = Integer.valueOf(currentLevel).intValue()
                                + Integer.valueOf(currentExp).intValue();

                        if (totalXP >= amount)
                        {
                            double value = MCCom.getCurrencyValue(MCCom
                                    .getAccountCurrency(player.getName()));
                            double physvalue = MCCom
                                    .getPhysicalCurrencyValue(expcurrency);

                            double base = amount * physvalue;
                            base = base / value;

                            MCCom.setBalance(player.getName(), balance + base);

                            int left = Integer.valueOf(totalXP).intValue()
                                    - amount;
                            int LVLleft = left / 100;
                            int EXP = Integer.valueOf(left).intValue() % 100;
                            float EXPleft = EXP / 100.0F;
                            player.setLevel(LVLleft);
                            player.setExp(EXPleft);

                            player.sendMessage(MCLang.tag + MCLang.messageTransactionComplete);
                            return;
                        }
                        else
                        {
                            player.sendMessage(MCLang.tag + MCLang.errorExpEnough);
                            return;
                        }
                    }
                }

                ItemStack is = new ItemStack(1);
                ItemStack[] ik = player.getInventory().getContents();

                for (int i = 0; ik.length > i; i++)
                {
                    try
                    {
                        if (ik[i].getTypeId() == Integer.parseInt(MCCom
                                .getCurrencyId(currency)))
                        {
                            is = ik[i];
                        }
                    }
                    catch (NullPointerException e)
                    {
                        //
                    }
                }

                int isamount = is.getAmount();
                if (isamount >= amount)
                {
                    double value = MCCom.getCurrencyValue(MCCom
                            .getAccountCurrency(player.getName()));
                    double physvalue = MCCom.getPhysicalCurrencyValue(currency);

                    double base = amount * physvalue;
                    base = base / value;

                    MCCom.setBalance(player.getName(),
                            MCCom.getBalance(player.getName()) + base);

                    player.getInventory().removeItem(is);
                    is = new ItemStack(Integer.parseInt(MCCom
                            .getCurrencyId(currency)));

                    if (!(isamount - amount == 0))
                    {
                        is.setAmount(isamount - amount);
                        player.getInventory().addItem(is);
                    }

                    return;
                }
                else
                {
                    player.sendMessage(MCLang.tag + MCLang.errorYouEnough);
                    return;
                }
            }
            else
            {
                player.sendMessage(MCLang.tag + MCLang.errorNoPhysicalCurrency);
            }
        }
        else
        {
            noAccount(player);
        }
    }

    /**
     * Withdraws the specified amount of exp from specified account.
     * 
     * @param player
     * @param currency
     * @param amount
     */
    public static void withdraw(Player player, String currency, int amount)
    {
        boolean HasAccount = MCCom.exists(player.getName());

        if (HasAccount)
        {
            if (MCCom.physicalCurrencyExists(currency))
            {
                if (MCCom.idExists("_exp"))
                {
                    String expcurrency = MCCom.getCurrencyById("_exp");

                    if (currency.equals(expcurrency))
                    {
                        double balance = MCCom.getBalance(player.getName());

                        DecimalFormat dec = new DecimalFormat("####");
                        int currentLevel = player.getLevel() * 100;
                        String currentExp = dec
                                .format(player.getExp() * 100.0F);
                        int totalXP = Integer.valueOf(currentLevel).intValue()
                                + Integer.valueOf(currentExp).intValue();

                        try
                        {
                            double value = MCCom.getCurrencyValue(MCCom
                                    .getAccountCurrency(player.getName()));
                            double physvalue = MCCom
                                    .getPhysicalCurrencyValue(expcurrency);

                            double base = amount * physvalue;
                            base = base / value;

                            MCCom.setBalance(player.getName(), balance - base);

                            int left = Integer.valueOf(totalXP).intValue()
                                    + amount;
                            int LVLleft = left / 100;
                            int EXP = Integer.valueOf(left).intValue() % 100;
                            float EXPleft = EXP / 100.0F;
                            player.setLevel(LVLleft);
                            player.setExp(EXPleft);

                            player.sendMessage(MCLang.tag + MCLang.messageTransactionComplete);
                            return;
                        }
                        catch (MaxDebtException e)
                        {
                            player.sendMessage(MCLang.tag + MCLang.errorYouEnough);
                            return;
                        }
                    }
                }
                
                double balance = MCCom.getBalance(player.getName());

                try
                {
                    double value = MCCom.getCurrencyValue(MCCom
                            .getAccountCurrency(player.getName()));
                    double physvalue = MCCom.getPhysicalCurrencyValue(currency);

                    double base = amount * physvalue;
                    base = base / value;

                    MCCom.setBalance(player.getName(), balance - base);

                    String id = MCCom.getCurrencyId(currency);

                    ItemStack is = new ItemStack(Integer.parseInt(id));
                    is.setAmount(amount);

                    player.getInventory().addItem(is);
                    return;
                }
                catch (MaxDebtException e)
                {
                    player.sendMessage(MCLang.tag + MCLang.errorYouEnough);
                    return;
                }
            }
            else
            {
                player.sendMessage(MCLang.tag + MCLang.errorNoPhysicalCurrency);
            }
        }
        else
        {
            noAccount(player);
        }
    }

    /**
     * Sends the player the top ten richest accounts on the server.
     * 
     * @param player
     * @param size 
     */
    public static void getTop(Player player, int size)
    {
        ArrayList<String> accounts = MCCom.getAccounts();
        ArrayList<String> top = new ArrayList<String>();
        
        size += 1;

        for (int i = 0; accounts.size() > i; i++)
        {
            if (top.size() == 0)
            {
                top.add(accounts.get(i));
            }
            else
            {
                for (int j = 0; top.size() > j; j++)
                {
                    if (MCCom.getBalance(accounts.get(i)) > MCCom
                            .getBalance(top.get(j)))
                    {
                        top.add(j, accounts.get(i));
                        j = top.size() + 100;
                    }
                }

                if ((top.size() < size) && !top.contains(accounts.get(i)))
                {
                    top.add(accounts.get(i));
                }
            }
        }

        while (!(top.size() < size))
        {
            top.remove(top.size() - 1);
        }

        for (int i = 0; top.size() > i; i++)
        {
            player.sendMessage((i + 1) + ") " + top.get(i) + " - "
                    + MCCom.getBalance(top.get(i)) + " "
                    + MCCom.getCurrency(top.get(i)));
        }
    }
    
    /**
     * Sets the specified player's currency to the specified currency.
     * 
     * @param player
     * @param toPlayer
     * @param currency
     */
    public static void setCurrency(Player player, String toPlayer, String currency)
    {
        if (MCCom.exists(toPlayer))
        {
            if (MCCom.currencyExists(currency))
            {
                double toBalance = MCCom.getBalance(toPlayer);

                double value = MCCom.getCurrencyValue(MCCom
                        .getCurrency(toPlayer));
                double toValue = MCCom.getCurrencyValue(currency);

                double base = toBalance / value;
                base = base * toValue;
                
                MCCom.setAccountCurrency(toPlayer, currency);
                MCCom.setBalance(toPlayer, base);
                
                String[] args = {toPlayer, currency};
                
                player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageCurrencySet, args));
            }
            else
            {
                player.sendMessage(MCLang.tag + MCLang.errorCurrencyNotFound);
            }
        }
        else
        {
            noAccount(player);
        }
    }
    
    /**
     * Check's the player's bank balance.
     * 
     * @param bank
     * @param player
     */
    public static void check(String bank, Player player)
    {
        double balance = 0;

        try
        {
            balance = MCCom.getBalance(bank, player.getName());
        }
        catch (NoAccountException e)
        {
            noAccount(player, bank);
            return;
        }
        catch (NoBankException e)
        {
            noBank(player, bank);
            return;
        }

        String[] args = {bank, balance + ""};

        player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageBankBalance, args));
    }
    
    /**
     * Creates new bank.
     * 
     * @param player
     * @param bank
     */
    public static void createBank(Player player, String bank)
    {
        String[] args = {bank};
        try
        {
            MCCom.createBank(bank);
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageBankCreated, args));
        }
        catch (BankNameConflictException e)
        {
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.errorBankExists, args));
        }
    }
    
    /**
     * Creates new bank account.
     * 
     * @param player
     * @param bank
     * @param account 
     */
    public static void create(Player player, String bank, String account)
    {
        String[] args = {account, bank};
        
        try
        {
            MCCom.create(bank, account);
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageBankAccountCreated, args));
        }
        catch (AccountNameConflictException e)
        {
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.errorBankAccountExists, args));
        }
    }
    
    /**
     * Deletes specified bank.
     * 
     * @param player
     * @param bank
     */
    public static void deleteBank(Player player, String bank)
    {
        String[] args = {bank};
        try
        {
            MCCom.deleteBank(bank);
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageBankDeleted, args));
        }
        catch (NoBankException e)
        {
            noBank(player, bank);
        }
    }
    
    /**
     * Deletes specified bank account.
     * 
     * @param player
     * @param bank
     * @param account 
     */
    public static void delete(Player player, String bank, String account)
    {
        String[] args = {account, bank};
        
        try
        {
            MCCom.delete(bank, account);
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageBankAccountDeleted, args));
        }
        catch (NoAccountException e)
        {
            noAccount(player, bank);
        }
    }
    
    /**
     * Displays balance of specified bank account.
     * 
     * @param player
     * @param bank
     * @param account
     */
    public static void get(Player player, String bank, String account)
    {
        try
        {
            double balance = MCCom.getBalance(bank, account);
            String[] args = {account, bank, MCFormat.format(balance)};
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageGetBankAccount, args));
        }
        catch (NoAccountException e)
        {
            noAccount(player, bank);
        }
        catch (NoBankException e)
        {
            noBank(player, bank);
        }
    }
    
    /**
     * Sets account balance.
     * 
     * @param player
     * @param bank
     * @param account
     * @param balance
     */
    public static void set(Player player, String bank, String account, double balance)
    {
        try
        {
            MCCom.setBalance(bank, account, balance);
            String[] args = {account, bank, MCFormat.format(balance)};
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageSetBankAccount, args));
        }
        catch (NoAccountException e)
        {
            noAccount(player, bank);
        }
        catch (NoBankException e)
        {
            noBank(player, bank);
        }
        catch (MaxDebtException e)
        {
            player.sendMessage(MCLang.tag + MCLang.errorMaxDebt);
        }
    }
    
    /**
     * Deposits amount in bank account.
     * 
     * @param player
     * @param bank
     * @param account
     * @param amount
     */
    public static void deposit(Player player, String bank, double amount)
    {
        amount = Math.abs(amount);
        
        try
        {
            MCCom.subtract(player.getName(), amount);
            
            double toBalance = MCCom.getBalance(bank, player.getName());

            double value = MCCom.getCurrencyValue(MCCom
                    .getCurrency(player.getName()));
            double toValue = 1.0;

            double base = amount / value;
            base = base * toValue;

            double newToBalance = toBalance + base;
            
            
            MCCom.setBalance(bank, player.getName(), newToBalance);
            
            String[] args = {bank, player.getName(), MCFormat.format(amount)};
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageBankAccountDeposit, args));
        }
        catch (NoAccountException e)
        {
            if (e.getMethodCause().contains("subtract"))
            {
                noAccount(player);
            }
            else
            {
                noAccount(player, bank);
            }
        }
        catch (NoBankException e)
        {
            noBank(player, bank);
        }
        catch (InsufficientFundsException e)
        {
            player.sendMessage(MCLang.tag + MCFormat.color(MCLang.errorYouEnough));
        }
    }
    
    /**
     * Withdraws amount from bank account.
     * 
     * @param player
     * @param bank
     * @param account
     * @param amount
     */
    public static void withdraw(Player player, String bank, double amount)
    {
        amount = Math.abs(amount);
        
        try
        {
            MCCom.subtract(bank, player.getName(), amount);
            
            double toBalance = MCCom.getBalance(player.getName());

            double toValue = MCCom.getCurrencyValue(MCCom
                    .getCurrency(player.getName()));
            double value = 1.0;

            double base = amount / value;
            base = base * toValue;

            double newToBalance = toBalance + base;
            
            
            MCCom.setBalance(player.getName(), newToBalance);
            
            
            String[] args = {bank, player.getName(), MCFormat.format(amount)};
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageBankAccountWithdraw, args));
        }
        catch (NoAccountException e)
        {
            if (e.getMethodCause().contains("add"))
            {
                noAccount(player);
            }
            else
            {
                noAccount(player, bank);
            }
        }
        catch (NoBankException e)
        {
            noBank(player, bank);
        }
        catch (InsufficientFundsException e)
        {
            String[] args = {player.getName()};
            player.sendMessage(MCLang.tag
                    + MCLang.parse(MCLang.errorTheyEnough, args));
        }
    }
    
    /**
     * Empties specified bank account.
     * 
     * @param player
     * @param bank
     * @param account
     */
    public static void empty(Player player, String bank, String account)
    {
        try
        {
            MCCom.empty(bank, account);
            String[] args = {account, bank};
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageEmptyBankAccount, args));
        }
        catch (NoAccountException e)
        {
            noAccount(player, bank);
        }
        catch (NoBankException e)
        {
            noBank(player, bank);
        }
    }
    
    /**
     * Renames specified bank to specified name.
     * 
     * @param player
     * @param bank
     * @param newBank
     */
    public static void renameBank(Player player, String bank, String newBank)
    {
        String[] args = {newBank, bank};
        try
        {
            MCCom.renameBank(bank, newBank);
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageBankRenamed, args));
        }
        catch (NoBankException e)
        {
            noBank(player, bank);
        }
        catch (BankNameConflictException e)
        {
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.errorBankExists, args));
        }
    }
    
    /**
     * Renames specified bank account to specified bank account.
     * 
     * @param player
     * @param bank
     * @param account
     * @param newBank
     * @param newAccount
     */
    public static void rename(Player player, String bank, String account, String newBank, String newAccount)
    {
        String[] args = {newAccount, newBank, account, bank};
        
        try
        {
            MCCom.rename(bank, account, newBank, newAccount);
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageBankAccountRenamed, args));
        }
        catch (NoBankException e)
        {
            if (e.getVariableCause().equals("oldBank"))
            {
                noBank(player, bank);
            }
            else
            {
                noBank(player, newBank);
            }
        }
        catch (NoAccountException e)
        {
            noAccount(player, bank);
        }
        catch (AccountNameConflictException e)
        {
            player.sendMessage(MCLang.tag + MCLang.parse(MCLang.errorBankAccountExists, args));
        }
    }
    
    /**
     * Transfers specified amount between the specified bank accounts.
     * 
     * @param player
     * @param bank
     * @param account
     * @param toBank
     * @param toAccount
     * @param amount
     */
    public static void transfer(Player player, String bank, String account, String toBank, String toAccount, double amount)
    {
        amount = Math.abs(amount);
        
        try
        {
            MCCom.transfer(bank, account, toBank, toAccount, amount);
        }
        catch (NoBankException e)
        {
            if (e.getVariableCause().equals("bankFrom"))
            {
                noBank(player, bank);
            }
            else
            {
                noBank(player, toBank);
            }
        }
        catch (NoAccountException e)
        {
            if (e.getVariableCause().equals("accountFrom"))
            {
                noAccount(player, account);
            }
            else
            {
                noAccount(player, toAccount);
            }
        }
        catch (InsufficientFundsException e)
        {
            player.sendMessage(MCLang.tag + MCFormat.color(MCLang.errorYouEnough));
        }
    }
    
    private static void noAccount(Player p)
    {
        p.sendMessage(MCLang.tag + MCLang.errorNoAccount);
    }
    
    private static void noAccount(Player p, String bank)
    {
        String[] args = {bank};
        
        p.sendMessage(MCLang.tag + MCLang.parse(MCLang.errorNoBankAccount, args));
    }
    
    private static void noBank(Player p, String bank)
    {
        String[] args = {bank};
        
        p.sendMessage(MCLang.tag + MCLang.parse(MCLang.errorNoBank, args));
    }
}
