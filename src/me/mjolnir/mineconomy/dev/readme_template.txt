.: MineConomy Readme File :.

-= Table of Contents =-
Introduction
Config File
Currencies File
Commands
Permission Nodes
GUI
Change Log



-= Introduction =-

MineConomy is a mid-weight, simple Bukkit Economy Plugin.

If you need more help than is in this readme file, please go to the MineConomy Plugin page (http://dev.bukkit.org/server-mods/mineconomy) for more information and tutorials.

YML IS CASE SENSITIVE! Be sure to follow correct case patterns when editting MineConomy configuration files.

NOTICE: Do NOT edit the accounts.yml or banks.yml save files. This may result in MineConomy corruption.



-= Config File =-

The Config file is in the MineConomy folder as config.yml. Times default to seconds, but may have the suffixes (s, m, h, d) at the end of a integer. E.G. 100s = 100 seconds, 60m = 60 minutes, 120h = 120 hours, 2d = 2 days.

Balance:
  Starting Balance: 100.0           This will be the amount of money that all accounts will have when they are created.
  Max Debt: 100.0                   This is the maximum amount of debt an account can have on your server. Leave at 0 to disallow debt.
Display GUI: true                   This may be set to "true" (enabling) or "false" (disabling). Determines whether to show the MineConomy GUI on the server host computer.
Interest:
  Amount: 5                         This is the amount of interest given to all accounts on the server.
  Interval: 1200s                   This is the amount of time between when interest will be applied to accounts. This must be a natural number. The smaller this number is, the more strain there will be on the server. Set to 0 to disable interest.
  Mode: percent                     This is the mode that interest will be taken in. This field can be set as "fixed", which will give accounts the Interest-Amount. This field can also be set as "percent", which will give accounts the percentage from Interest-Amount based on their balance. To have no interest, set this field to "none".
Tax:
  Amount: 10                        This is the amount of tax taken from all the accounts on the server.
  Interval: 15m                     This is the amount of time between when tax will be applied to accounts. This must be a natural number. The smaller this number is, the more strain there will be on the server. Set to 0 to disable tax.
  Mode: fixed                       This is the mode that tax will be taken in. This field can be set as "fixed", which takes the Interest-Amount. This field can also be set as "percent", which will take the percentage from Interest-Amount from accounts based on their balance. To have no tax, set this field to "none".
Database:
  URL: localhost:3306/              This is the URL and Port of the server to connect to.
  Name: minecraft                   This is the name of the Database to access.
  Username: myuser                  This is the username to access the Database with.
  Password: ******                  This is the password to access the Database with.
  Type: mysql                       This is the type of Database to use. Set to "mysql" for MySQL Databases or set to "none" to use MineConomy's flatfile YML.
Lang: en                            This is the last two characters of the language file to use ("en" for English by default).
Auto-Save Interval: 1h              This is the amount of time between MineConomy auto-saves. A lower time will save more often. Set to 0 to disable.
iConomy Compatibility Mode: false   This is used to make a converted iConomy save file case insensitive, therefore making it compatible with MineConomy.
Migration Mode: none                This is the Migration mode that MineConomy will use. Can be set to "iconomy" to migrate iConomy accounts to MineConomy's flatfile YML. Can also be set to "mysql" to migrate MineConomy's flatfile YML to a MySQL Database.


-= Currencies File =-

The Currencies file is in the MineConomy folder as currencies.yml.

Currencies:
  Dollars:           The name of the MineConomy currency
    Value: 1.0       The value of the currency
    Default: true    Whether this currency is the default of MineConomy
Physical:
  exp:               The name of the physical currency
    Value: 2.0       The currency's value
    ID: _exp         The currency's MineConomy ID*
  wood:              The name of the physical currency
    Value: 1.0       The currency's value
    ID: '5'          The Minecraft Item ID number of this physical currency surrounded by single-quotes.
    
* ID's with underscores ('_') before them are special items, such as Experience.



-= Commands =-

< > - Required Field
[ : ] - Option Field
( ) - Unrequired Field

/mc - displays balance
/mc balance - displays balance
/mc help - displays in-game help menu
/mc set <ACCOUNT> <AMOUNT> - sets specified account's balance
/mc get <ACCOUNT> - displays specified account's balance
/mc pay <ACCOUNT> <AMOUNT> - pays specified account the specified amount
/mc create <ACCOUNT> - creates new account with specified name
/mc delete <ACCOUNT> - deletes specified account
/mc give <ACCOUNT> <AMOUNT> - gives specified account the specified amount
/mc take <ACCOUNT> <AMOUNT> - takes specified amount from the specified account
/mc empty <ACCOUNT> - sets the specified account's balance to 0
/mc deposit <AMOUNT> - deposits specified amount of physical currency into your account
/mc withdraw <AMOUNT> - withdraws specified amount of physical currency from your account
/mc exp - displays your amount of experience
/mc top <SIZE> - displays the richest accounts on the server
/mc setcurrency (ACCOUNT) <CURRENCY> - sets specified account's currency
/mc save - saves all MineConomy data
/mc reload - reloads all MineConomy data

/mcb <BANK> - displays your balance in specified bank
/mcb balance <BANK> - displays your balance in specified bank
/mcb join <BANK> - joins specified bank
/mcb leave <BANK> - leaves specified bank
/mcb deposit <BANK> <ACCOUNT> <AMOUNT> - deposits amount in your account
/mcb withdraw <BANK> <ACCOUNT> <AMOUNT> - withdraws amount from your account
/mcb create <BANK> (ACCOUNT) - creates new bank/bank account
/mcb delete <BANK> (ACCOUNT) - deletes specified bank/bank account
/mcb get <BANK> <ACCOUNT> - displays the balance of specified bank account
/mcb set <BANK> <ACCOUNT> <BALANCE> - sets the balance of specified bank account
/mcb empty <BANK> <ACCOUNT> - empties the specified bank account
/mcb rename <BANK> (ACCOUNT) <NEW_BANK> (NEW_ACCOUNT) - renames the specified bank/bank account
/mcb transfer <BANK> (ACCOUNT) <TO_BANK> <TO_ACCOUNT> <AMOUNT> - transfers the specified amount from bank account to bank account


-= Permission Nodes =-

mineconomy.* - allows users full access to MineConomy (Not recommended)
    mineconomy.account.* - allows users full access to this permission branch (Recommended for admins)
        mineconomy.account.have - allows users to have accounts
        mineconomy.account.create - allows users to create other accounts
        mineconomy.account.delete - allows users to delete other accounts
    mineconomy.balance.* - allows users full access to this permission branch (Recommended for admins)
        mineconomy.balance.check - allows users to check their own balances
        mineconomy.balance.get - allows users to get another account's balance
        mineconomy.balance.set - allows users to set another account's balance
        mineconomy.balance.pay - allows users to pay other users
        mineconomy.balance.give - allows users to give money to other accounts
        mineconomy.balance.take - allows users to take money away from another account
        mineconomy.balance.empty - allows users to set another account's balance to 0
        mineconomy.balance.deposit - allows users to deposit physical currency into their account
        mineconomy.balance.withdraw - allows users to withdraw physical currency from their account
    mineconomy.currency.* - allows users full access to this permission branch (Recommended for admins)
        mineconomy.currency.set - allows users to set their own account currency
        mineconomy.currency.set.others - allows users to set other accounts' currencies
    mineconomy.help - allows users to have the in-game help menu displayed to them. 
    mineconomy.save - allows users to save MineConomy data
    mineconomy.reload - allows users to reload MineConomy data
    mineconomy.bank.* - allows users full acces to this permission branch (Recommended for admins)
        mineconomy.bank.create - allows user to create banks
        mineconomy.bank.delete - allows user to delete banks
        mineconomy.bank.rename - allows user to rename banks
        mineconomy.bank.account.* - allows users full access to this permission branch (Recommended for admins)
            mineconomy.bank.account.balance - allows user to check their own bank account balance
            mineconomy.bank.account.deposit - allows user to deposit money in their bank accounts
            mineconomy.bank.account.withdraw - allows user to withdraw money from their bank accounts
            mineconomy.bank.account.create - allows user to create their own bank accounts
            mineconomy.bank.account.delete - allows user to delete their own bank accounts
            mineconomy.bank.account.create.others - allows user to create bank accounts
            mineconomy.bank.account.delete.others - allows user to delete bank accounts
            mineconomy.bank.account.get - allows user to get bank account balances
            mineconomy.bank.account.set - allows user to set bank account balances
            mineconomy.bank.account.empty - allows user to empty bank accounts
            mineconomy.bank.account.rename - allows user to rename bank accounts
            mineconomy.bank.account.transfer - allows user to transfer bank account funds
            mineconomy.bank.account.transfer.others - allows user to transfer other bank account funds



-= GUI =-


At any time you may use the buttons on the bottom of the server-side GUI screen.


You may 'Stop' or 'Refresh' (a.k.a. Reload) the server, just like in the server prompt.

You may 'Refresh MineConomy', which reloads only MineConomy, not the entire server.

Finally, you may 'Check for Updates' to MineConomy.


Go to http://dev.bukkit.org/server-mods/mineconomy for additional assistance.



- Accounts -

Choose an account from the left menu and the balance will be displayed.

Enter an amount in the text field to either 'Give', 'Take' or 'Set' from that account, just like in-game.
Also you can 'Empty' (set balance to 0) or 'delete' the selected account.

The lower text field can create an account. Enter the account name and click 'Create Account'.



- Currencies -

You may edit the Currencies YML file in the text area and click the 'Save' button at the bottom to make changes.



- Banks -

NOTICE: This GUI is not yet implemented.



- Settings -

You may edit all settings in this window exactly like you would in the YML file.

Click 'Save' to finalize your changes.



- Language -

You may edit your selected Language YML file in the text area and click the 'Save' button at the bottom to make changes.



- Log -

In this window you may view the MineConomy Log file. Click 'Clear Log' to clear the log file.



- Info -

This window displays the current version information and change log.



-= Change Log =-

- Version 1.5 -
* Updated to Bukkit 1.3.1-R2.0
* "Decolored" MCLang Save files (Fix)
* MySQL Updates
* Language Updates (Help Menu added, MineConomy tag fix)
* Various Bug Fixes (Tax, Hardcoded Tag, etc.)
* Interest/Tax Percent Fix [ (PxR)/100 ]

- Version 1.4 -
* Updated to Bukkit 1.2.5-R4.0
* MCFormat class added
* Balances are now formatted
* MySqlAccounting class added (Accounting class for MySQL)
* Underlying code structure reorganized (MCCom still fully accessible)
* Added support for Vault and Multiple Currencies
* Settings optimized for database implementations
* Time Formats for Settings
* Migration Support for Language File, Settings File and Databases added
* Language File Updates (Added MineConomy tag)
* Auto-Saves (With Time Format)

- Version 1.3 -
* Updated to Bukkit 1.2.5-R3.0
* iConomy to MineConomy conversion built-in
* Log updates
* "_Default" currency changed to "Dollars"
* Bug fixes, Minor additions and Code cleanup

- Version 1.2 -
* Updated to Minecraft 1.2.5
* Tax and Interest fixes
* MCLang class added
* Language support implemented
* Customizable Language Files
* Currency fixes
* Balance handling fixes
* ".accounts" to ".yml" conversion removed
* In-Game reload command (save command fix)
* Banks implemented
* In-Game Commands for Banks
* GUI updated
* Logging Priority Settings
* MineConomy Private Log
* Numerous other bug fixes and updates

- Version 1.1 -
* Properties changed to Config (YML)
* ExteriorPlugin class changed to MCCom
* MCPlayerListener class changed to MCListener
* MCCommandExecutor class changed to ChatExecutor
* Internal classes accessed through MCCom (An Added Safety Precaution)
* Currencies
* Updates to implement currencies
* In-Game Save Command
* Auto Update deprecated, Update check is unchanged
* GUI No Longer Reloads Server
* Welcome Message Fix
* Top Command
* Updated to Minecraft 1.2.3
* Banks system added (not yet implemented)

- Version 1.0.4 -
* Colored Balances (Based on Pos/Neg Balance)
* Tax/Interest (Optional/Configurable)
* Auto Generate Readme
* Organization for Properties File (Alphabetic)
* Welcome Message on Login
* "/money" Command Label acts the same as "/mc" Command Label
* Auto Update Feature
* Server-side GUI (Optional)
* Experience as a Physical Currency (Optional)
* Support for more future physical currencies
* Added further support for Exterior Plugins

- Version 1.0.3 -
* Give Command Fix

- Version 1.0.2 -
* "Empty" Node Fix
* New Class Added (IOH) - Streamlines Log Code

- Version 1.0 -
* Basic Structure Added
* Basic Features Added


Last Edited: Wed Aug 22, 2012 @ 6:00PM EST