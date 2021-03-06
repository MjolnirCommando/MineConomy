package me.mjolnir.mineconomy.internal.commands;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.exceptions.AccountNameConflictException;
import me.mjolnir.mineconomy.exceptions.InsufficientFundsException;
import me.mjolnir.mineconomy.exceptions.MaxDebtException;
import me.mjolnir.mineconomy.exceptions.NoAccountException;
import me.mjolnir.mineconomy.internal.MCCom;
import me.mjolnir.mineconomy.internal.MCLang;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Handles commands given to MineConomy.
 * 
 * @author MjolnirCommando
 */
public class ChatExecutor implements CommandExecutor
{

    /**
     * Creates new MCCommandExecutor object.
     * 
     * @param plugin
     */
    public ChatExecutor(MineConomy plugin)
    {
        // Do Nothing
    }

    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args)
    {
        if (command.getName().equalsIgnoreCase("mc")
                || command.getName().equalsIgnoreCase("money"))
        {
            if (!(sender instanceof Player))
            {
                try
                {
                    if (args.length == 1)
                    {
                        if (args[0].equalsIgnoreCase("save"))
                        {
                            sender.sendMessage(MCLang.tag + ChatColor.BLACK
                                    + "Saving MineConomy...");
                            MineConomy.save();
                            sender.sendMessage(MCLang.tag + ChatColor.BLACK
                                    + "Save complete!");
                        }
                        else if (args[0].equalsIgnoreCase("reload"))
                        {
                            sender.sendMessage(MCLang.tag + ChatColor.BLACK
                                    + "Reloading MineConomy...");
                            MineConomy.reload();
                            sender.sendMessage(MCLang.tag + ChatColor.BLACK
                                    + "Reload complete!");
                        }
                        else
                        {
                            sender.sendMessage(MCLang.tag
                                    + MCLang.errorCommandRecognition);
                        }
                    }
                    else if (args.length == 2)
                    {
                        if (args[0].equalsIgnoreCase("get"))
                        {
                            try
                            {
                                sender.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageAccountBalance, new String[] {MCCom.getAccountCurrency(args[1]), MCCom.getBalance(args[1]) + ""}));
                            }
                            catch (NoAccountException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorNoAccount);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("create"))
                        {
                            try
                            {
                                MCCom.create(args[1]);
                                sender.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageCreated, new String[] {MCCom.getAccount(args[1])}));
                            }
                            catch (AccountNameConflictException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.parse(MCLang.errorAccountExists, new String[] {args[1]}));
                            }
                        }
                        else if (args[0].equalsIgnoreCase("delete"))
                        {
                            try
                            {
                                MCCom.delete(args[1]);
                                sender.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageDeleted, new String[] {MCCom.getAccount(args[1])}));
                            }
                            catch (NoAccountException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorNoAccount);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("empty"))
                        {
                            try
                            {
                                MCCom.empty(args[1]);
                                sender.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageEmpty, new String[] {MCCom.getAccount(args[1])}));
                            }
                            catch (NoAccountException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorNoAccount);
                            }
                        }
                        else
                        {
                            sender.sendMessage(MCLang.tag
                                    + MCLang.errorCommandRecognition);
                        }
                    }
                    else if (args.length == 3)
                    {
                        if (args[0].equalsIgnoreCase("set"))
                        {
                            try
                            {
                                MCCom.setBalance(args[1],
                                        Double.parseDouble(args[2]));
                                sender.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageSetBalance, new String[] {MCCom.getAccount(args[1]), MCCom.getBalance(args[1]) + "", MCCom.getCurrency(args[1])}));
                            }
                            catch (NumberFormatException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorMoneyFormat);
                            }
                            catch (NoAccountException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorNoAccount);
                            }
                            catch (MaxDebtException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorMaxDebt);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("give"))
                        {
                            try
                            {
                                MCCom.add(args[1], Double.parseDouble(args[2]));
                                sender.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageGive, new String[] {MCCom.getAccount(args[1]), Double.parseDouble(args[2]) + "", MCCom.getCurrency(args[1])}));
                            }
                            catch (NumberFormatException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorMoneyFormat);
                            }
                            catch (NoAccountException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorNoAccount);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("take"))
                        {
                            try
                            {
                                MCCom.subtract(args[1],
                                        Double.parseDouble(args[2]));
                                sender.sendMessage(MCLang.tag + MCLang.parse(MCLang.messageTook, new String[] {Double.parseDouble(args[2]) + "", MCCom.getCurrency(args[1]), MCCom.getAccount(args[1])}));
                            }
                            catch (NumberFormatException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorMoneyFormat);
                            }
                            catch (NoAccountException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.errorNoAccount);
                            }
                            catch (InsufficientFundsException e)
                            {
                                sender.sendMessage(MCLang.tag
                                        + MCLang.parse(MCLang.errorTheyEnough, new String[] {MCCom.getAccount(args[1])}));
                            }
                        }
                        else
                        {
                            sender.sendMessage(MCLang.tag
                                    + MCLang.errorCommandRecognition);
                        }
                    }
                    else
                    {
                        sender.sendMessage(MCLang.tag
                                + MCLang.errorCommandRecognition);
                    }
                }
                catch (IndexOutOfBoundsException e)
                {
                    sender.sendMessage(MCLang.tag + MCLang.errorInvalidArgs);
                    e.printStackTrace();
                }
                return true;
            }
            else
            {

                Player player = (Player) sender;

                try
                {
                    if (player.hasPermission("mineconomy.account.have"))
                    {
                        boolean HasAccount = MCCom.exists(player.getName());
                        if (HasAccount == false)
                        {
                            MCCom.create(player.getName());
                        }
                    }
                    else
                    {
                        warn(player);
                    }

                    if (args.length == 0)
                    {
                        if (player.hasPermission("mineconomy.balance.check"))
                        {
                            Balance.check(player);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args.length == 1)
                    {
                        if (args[0].equalsIgnoreCase("balance"))
                        {
                            if (player
                                    .hasPermission("mineconomy.balance.check"))
                            {
                                Balance.check(player);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("exp"))
                        {
                            Balance.checkexp(player);
                        }
                        else if (args[0].equalsIgnoreCase("help"))
                        {
                            if (player.hasPermission("mineconomy.help"))
                            {
                                Balance.help(player, 1);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("save"))
                        {
                            if (player.hasPermission("mineconomy.save"))
                            {
                                player.sendMessage(MCLang.tag + ChatColor.WHITE
                                        + "Saving MineConomy...");
                                MineConomy.save();
                                player.sendMessage(MCLang.tag + ChatColor.WHITE
                                        + "Save complete!");
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("reload"))
                        {
                            if (player.hasPermission("mineconomy.save"))
                            {
                                player.sendMessage(MCLang.tag + ChatColor.WHITE
                                        + "Reloading MineConomy...");
                                MineConomy.reload();
                                player.sendMessage(MCLang.tag + ChatColor.WHITE
                                        + "Reload complete!");
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else
                        {
                            player.sendMessage(MCLang.tag
                                    + MCLang.errorCommandRecognition);
                        }
                    }
                    else if (args.length == 2)
                    {
                        if (args[0].equalsIgnoreCase("get"))
                        {
                            if (player.hasPermission("mineconomy.balance.get"))
                            {
                                Balance.get(player, args[1]);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("create"))
                        {
                            if (player
                                    .hasPermission("mineconomy.account.create"))
                            {
                                Balance.create(player, args[1]);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("delete"))
                        {
                            if (player
                                    .hasPermission("mineconomy.account.delete"))
                            {
                                Balance.delete(player, args[1]);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("empty"))
                        {
                            if (player
                                    .hasPermission("mineconomy.balance.empty"))
                            {
                                Balance.empty(player, args[1]);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("help"))
                        {
                            if (player.hasPermission("mineconomy.help"))
                            {
                                try
                                {
                                    Balance.help(player,
                                            Integer.parseInt(args[1]));
                                }
                                catch (NumberFormatException e)
                                {
                                    player.sendMessage(MCLang.tag
                                            + MCLang.errorInt);
                                }
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("setcurrency"))
                        {
                            if (player.hasPermission("mineconomy.currency.set"))
                            {
                                Balance.setCurrency(player, player.getName(),
                                        args[1]);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
//                        else if (args[0].equalsIgnoreCase("top"))
//                        {
//                        if (player.hasPermission("mineconomy.balance.top"))
//                        {
//                        try
//                        {
//                        Balance.getTop(player, Integer.parseInt(args[1]));
//                        }
//                        catch (NumberFormatException e)
//                        {
//                        player.sendMessage(MCLang.tag + MCLang.errorInt);
//                        }
//                        }
//                        else
//                        {
//                        warn(player);
//                        }
//                        }
                        else
                        {
                            player.sendMessage(MCLang.tag
                                    + MCLang.errorCommandRecognition);
                        }
                    }
                    else if (args.length == 3)
                    {
                        if (args[0].equalsIgnoreCase("pay"))
                        {
                            if (player.hasPermission("mineconomy.balance.pay"))
                            {
                                try
                                {
                                    Balance.pay(player, args[1],
                                            Double.parseDouble(args[2]));
                                }
                                catch (NumberFormatException e)
                                {
                                    player.sendMessage(MCLang.tag
                                            + MCLang.errorMoneyFormat);
                                }
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("set"))
                        {
                            if (player.hasPermission("mineconomy.balance.set"))
                            {
                                try
                                {
                                    Balance.set(player, args[1],
                                            Double.parseDouble(args[2]));
                                }
                                catch (NumberFormatException e)
                                {
                                    player.sendMessage(MCLang.tag
                                            + MCLang.errorMoneyFormat);
                                }
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("give"))
                        {
                            if (player.hasPermission("mineconomy.balance.give"))
                            {
                                Balance.give(player, args[1], args[2]);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("take"))
                        {
                            if (player.hasPermission("mineconomy.balance.take"))
                            {
                                Balance.take(player, args[1], args[2]);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("deposit"))
                        {
                            if (player
                                    .hasPermission("mineconomy.balance.deposit"))
                            {
                                try
                                {
                                    Balance.deposit(player, args[1],
                                            Integer.parseInt(args[2]));
                                }
                                catch (NumberFormatException e)
                                {
                                    player.sendMessage(MCLang.tag
                                            + MCLang.errorInt);
                                }
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("withdraw"))
                        {
                            if (player
                                    .hasPermission("mineconomy.balance.withdraw"))
                            {
                                try
                                {
                                    Balance.withdraw(player, args[1],
                                            Integer.parseInt(args[2]));
                                }
                                catch (NumberFormatException e)
                                {
                                    player.sendMessage(MCLang.tag
                                            + MCLang.errorInt);
                                }
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else if (args[0].equalsIgnoreCase("setcurrency"))
                        {
                            if (player
                                    .hasPermission("mineconomy.currency.set.others"))
                            {
                                Balance.setCurrency(player, args[1], args[2]);
                            }
                            else
                            {
                                warn(player);
                            }
                        }
                        else
                        {
                            player.sendMessage(MCLang.tag
                                    + MCLang.errorCommandRecognition);
                        }
                    }
                    else
                    {
                        player.sendMessage(MCLang.tag
                                + MCLang.errorCommandRecognition);
                    }
                }
                catch (IndexOutOfBoundsException e)
                {
                    player.sendMessage(MCLang.tag + MCLang.errorInvalidArgs);
                    e.printStackTrace();
                }
            }
        }
        else if (command.getName().equalsIgnoreCase("mcb"))
        {
            if (!(sender instanceof Player))
            {
                sender.sendMessage("Sorry, only players can execute MineConomy commands.");
                return true;
            }

            Player player = (Player) sender;

            try
            {
                if (args.length == 1)
                {
                    if (player.hasPermission("mineconomy.bank.account.balance"))
                    {
                        Balance.check(args[0], player);
                    }
                    else
                    {
                        warn(player);
                    }
                }
                else if (args.length == 2)
                {
                    if (args[0].equalsIgnoreCase("balance"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.balance"))
                        {
                            Balance.check(args[1], player);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("create"))
                    {
                        if (player.hasPermission("mineconomy.bank.create"))
                        {
                            Balance.createBank(player, args[1]);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("delete"))
                    {
                        if (player.hasPermission("mineconomy.bank.delete"))
                        {
                            Balance.deleteBank(player, args[1]);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("join"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.create"))
                        {
                            Balance.create(player, args[1], player.getName());
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("leave"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.delete"))
                        {
                            Balance.delete(player, args[1], player.getName());
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                }
                else if (args.length == 3)
                {
                    if (args[0].equalsIgnoreCase("get"))
                    {
                        if (player.hasPermission("mineconomy.bank.account.get"))
                        {
                            Balance.get(player, args[1], args[2]);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("empty"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.empty"))
                        {
                            Balance.empty(player, args[1], args[2]);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("create"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.create.others"))
                        {
                            Balance.create(player, args[1], args[2]);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("delete"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.delete.others"))
                        {
                            Balance.delete(player, args[1], args[2]);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("rename"))
                    {
                        if (player.hasPermission("mineconomy.bank.rename"))
                        {
                            Balance.renameBank(player, args[1], args[2]);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("deposit"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.deposit"))
                        {
                            try
                            {
                                Balance.deposit(player, args[1],
                                        Double.parseDouble(args[2]));
                            }
                            catch (NumberFormatException e)
                            {
                                player.sendMessage(MCLang.tag
                                        + MCLang.errorMoneyFormat);
                            }
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("withdraw"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.withdraw"))
                        {
                            try
                            {
                                Balance.withdraw(player, args[1],
                                        Double.parseDouble(args[2]));
                            }
                            catch (NumberFormatException e)
                            {
                                player.sendMessage(MCLang.tag
                                        + MCLang.errorMoneyFormat);
                            }
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                }
                else if (args.length == 4)
                {
                    if (args[0].equalsIgnoreCase("set"))
                    {
                        if (player.hasPermission("mineconomy.bank.account.set"))
                        {
                            try
                            {
                                Balance.set(player, args[1], args[2],
                                        Double.parseDouble(args[3]));
                            }
                            catch (NumberFormatException e)
                            {
                                player.sendMessage(MCLang.tag
                                        + MCLang.errorMoneyFormat);
                            }
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                }
                else if (args.length == 5)
                {
                    if (args[0].equalsIgnoreCase("rename"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.rename"))
                        {
                            Balance.rename(player, args[1], args[2], args[3],
                                    args[4]);
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                    else if (args[0].equalsIgnoreCase("transfer"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.transfer"))
                        {
                            try
                            {
                                Balance.transfer(player, args[1],
                                        player.getName(), args[2], args[3],
                                        Double.parseDouble(args[4]));
                            }
                            catch (NumberFormatException e)
                            {
                                player.sendMessage(MCLang.tag
                                        + MCLang.errorMoneyFormat);
                            }
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                }
                else if (args.length == 6)
                {
                    if (args[0].equalsIgnoreCase("transfer"))
                    {
                        if (player
                                .hasPermission("mineconomy.bank.account.transfer.others"))
                        {
                            try
                            {
                                Balance.transfer(player, args[1], args[2],
                                        args[3], args[4],
                                        Double.parseDouble(args[5]));
                            }
                            catch (NumberFormatException e)
                            {
                                player.sendMessage(MCLang.tag
                                        + MCLang.errorMoneyFormat);
                            }
                        }
                        else
                        {
                            warn(player);
                        }
                    }
                }
                else
                {
                    player.sendMessage(MCLang.tag
                            + MCLang.errorCommandRecognition);
                }
            }
            catch (IndexOutOfBoundsException e)
            {
                player.sendMessage(MCLang.tag + MCLang.errorInvalidArgs);
            }
        }

        return true;

    }

    private void warn(Player player)
    {
        player.sendMessage(MCLang.tag + MCLang.errorPermissionGeneric);
    }
}
