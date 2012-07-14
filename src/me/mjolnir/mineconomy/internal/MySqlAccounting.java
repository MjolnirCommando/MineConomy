package me.mjolnir.mineconomy.internal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import me.mjolnir.mineconomy.internal.util.IOH;

@SuppressWarnings("javadoc")
public final class MySqlAccounting extends AccountingBase
{
    private static Connection con = null;
    private static String driver = "com.mysql.jdbc.Driver";
    
    protected MySqlAccounting()
    {
        //
    }
    
    public void load()
    {
        IOH.log("Loading Accounts from database...", IOH.INFO);
        
        try
        {
            Class.forName(driver).newInstance();
            con = DriverManager
                    .getConnection("jdbc:mysql://" + Settings.dburl + ":3306/" + Settings.dbname, Settings.dbuser, Settings.dbpass);
        }
        catch (Exception e)
        {
            IOH.error("MySQL Error", e);
        }
        
        try
        {
            Statement st = con.createStatement();
            String com = "SELECT * FROM mineconomy_accounts WHERE id = '1'";
            st.execute(com);
        }
        catch (Exception e)
        {
            try
            {
                IOH.log("Accounts Table not found in database!", IOH.INFO);
                Statement st = con.createStatement();
                String com = "CREATE TABLE mineconomy_accounts(id int NOT NULL AUTO_INCREMENT, account text NOT NULL, balance double NOT NULL, currency text NOT NULL, status text NOT NULL, PRIMARY KEY (id) )";
                st.execute(com);
                IOH.log("Created Accounts Table in database...", IOH.INFO);
            }
            catch (SQLException e1)
            {
                IOH.error("MySQL Error", e1);
            }
        }
        
        IOH.log("Accounts loaded from database!", IOH.INFO);
    }
    
    public void reload()
    {
        // Nothing to reload.
    }
    
    public void save()
    {
        reload();
    }
    
    protected double getBalance(String account)
    {
        try
        {
            ResultSet st = con.createStatement().executeQuery("SELECT balance FROM mineconomy_accounts WHERE account = '" + account + "'");
            st.next();
            return st.getDouble(1);
        }
        catch (SQLException e)
        {
            IOH.error("MySQL Error", e);
            return 0;
        }
    }
    
    protected void setBalance(String account, double amount)
    {
        amount = (double) Math.round(amount * 100) / 100;
        
        if (amount > 9999999.99)
        {
            amount = 9999999.99;
        }
        
        try
        {
            Statement st = con.createStatement();
            String com = "UPDATE mineconomy_accounts SET balance = '" + amount + "' WHERE account = '" + account + "';";
            st.execute(com);
        }
        catch (Exception e)
        {
            IOH.error("MySQL Error", e);
        }
    }
    
    protected boolean exists(String account)
    {
        try
        {
            ResultSet st = con.createStatement().executeQuery("SELECT * FROM mineconomy_accounts WHERE account = '" + account + "'");
            return st.next();
        }
        catch (SQLException e)
        {
            IOH.error("MySQL Error", e);
            return false;
        }
    }
    
    protected void delete(String account)
    {
        try
        {
            Statement st = con.createStatement();
            String com = "DELETE FROM mineconomy_accounts WHERE account = '" + account + "';";
            st.execute(com);
        }
        catch (Exception e)
        {
            IOH.error("MySQL Error", e);
        }
    }
    
    protected void create(String account)
    {
        try
        {
            Statement st = con.createStatement();
            String com = "INSERT INTO mineconomy_accounts(account, balance, currency, status) VALUES ('" + account + "', " + Settings.startingBalance + ", '" + Currency.getDefault() + "', 'NORMAL')";
            st.execute(com);
        }
        catch (Exception e)
        {
            IOH.error("MySQL Error", e);
        }
    }
    
    protected String getCurrency(String account)
    {
        try
        {
            ResultSet st = con.createStatement().executeQuery("SELECT currency FROM mineconomy_accounts WHERE account = '" + account + "'");
            st.next();
            return st.getString(1);
        }
        catch (SQLException e)
        {
            IOH.error("MySQL Error", e);
            return "";
        }
    }
    
    protected void setCurrency(String account, String currency)
    {
        try
        {
            Statement st = con.createStatement();
            String com = "UPDATE mineconomy_accounts SET currency = '" + currency + "' WHERE account = '" + account + "';";
            st.execute(com);
        }
        catch (Exception e)
        {
            IOH.error("MySQL Error", e);
        }
    }
    
    protected String getStatus(String account)
    {
        try
        {
            ResultSet st = con.createStatement().executeQuery("SELECT status FROM mineconomy_accounts WHERE account = '" + account + "'");
            st.next();
            return st.getString(1);
        }
        catch (SQLException e)
        {
            IOH.error("MySQL Error", e);
            return "";
        }
    }
    
    protected void setStatus(String account, String status)
    {
        try
        {
            Statement st = con.createStatement();
            String com = "UPDATE mineconomy_accounts SET status = '" + status + "' WHERE account = '" + account + "';";
            st.execute(com);
        }
        catch (Exception e)
        {
            IOH.error("MySQL Error", e);
        }
    }
    
    protected ArrayList<String> getAccounts()
    {
        ArrayList<String> result = new ArrayList<String>();
        
        try
        {
            ResultSet rs = con.createStatement().executeQuery("SELECT account FROM mineconomy_accounts");
            
            while (rs.next())
            {
                result.add(rs.getString("account"));
            }
        }
        catch (SQLException e)
        {
            IOH.error("MySQL Error", e);
        }
        
        return result;
    }
}
