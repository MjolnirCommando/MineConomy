package me.mjolnir.mineconomy.internal.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import me.mjolnir.mineconomy.MineConomy;
import me.mjolnir.mineconomy.internal.Currency;
import me.mjolnir.mineconomy.internal.MCCom;
import me.mjolnir.mineconomy.internal.Settings;
import me.mjolnir.mineconomy.internal.gui.graphics.ImagePanel;
import me.mjolnir.mineconomy.internal.gui.listeners.AccountListListener;
import me.mjolnir.mineconomy.internal.gui.listeners.CFUListener;
import me.mjolnir.mineconomy.internal.gui.listeners.CreateListener;
import me.mjolnir.mineconomy.internal.gui.listeners.DeleteListener;
import me.mjolnir.mineconomy.internal.gui.listeners.EmptyListener;
import me.mjolnir.mineconomy.internal.gui.listeners.GiveListener;
import me.mjolnir.mineconomy.internal.gui.listeners.HelpListener;
import me.mjolnir.mineconomy.internal.gui.listeners.QuitListener;
import me.mjolnir.mineconomy.internal.gui.listeners.Refresh2Listener;
import me.mjolnir.mineconomy.internal.gui.listeners.RefreshListener;
import me.mjolnir.mineconomy.internal.gui.listeners.SetListener;
import me.mjolnir.mineconomy.internal.gui.listeners.TakeListener;
import me.mjolnir.mineconomy.internal.util.IOH;
import me.mjolnir.mineconomy.internal.util.Update;

/**
 * Handles MineConomy GUI.
 * 
 * @author MjolnirCommando
 */
@SuppressWarnings("javadoc")
public class GUI
{
    public static JFrame                    window;
    public static JPanel                    accounts;
    public static JPanel                    settings;
    public static JPanel                    banks;
    public static JTabbedPane               tabs;
    public static JTabbedPane               currencies;
    public static JPanel                    content;
    private static JPanel                   pane1;
    public static JLabel                    title;
    public static JComboBox                 accountList;
    public static ArrayList<String>         accountNames;
    public static Hashtable<String, Double> accountBalances;
    public static JLabel                    balance;
    public static boolean                   accountSelected;
    public static String                    selectedAccount;
    public static JButton                   cfubutton;
    public static JButton                   quitbutton;
    public static JButton                   refreshbutton;
    public static JTextField                amount;
    public static JTextField                newaccount;
    public static JButton                   givebutton;
    public static JButton                   takebutton;
    public static JButton                   helpbutton;
    public static JButton                   createbutton;
    public static JButton                   setbutton;
    public static JButton                   emptybutton;
    public static JButton                   deletebutton;
    public static JButton                   refreshbutton2;
    public static JTextArea                 logtext;

    /**
     * Creates new GUI object.
     */
    public GUI()
    {
        create();
    }

    private static void create()
    {
        accountSelected = false;

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addComponentListener(new ComponentListener()
        {
            public void componentResized(ComponentEvent e)
            {
                int width = window.getWidth();
                int height = window.getHeight();
                boolean resize = false;
                if (width < 850)
                {
                    resize = true;
                    width = 850;
                }
                if (height < 500)
                {
                    resize = true;
                    height = 500;
                }
                if (resize)
                {
                    window.setSize(width, height);
                }
            }

            public void componentMoved(ComponentEvent e)
            {
            }

            public void componentShown(ComponentEvent e)
            {
            }

            public void componentHidden(ComponentEvent e)
            {
            }
        });
        window.setTitle("MineConomy - GUI");

        tabs = new JTabbedPane();
        
        content = new JPanel();
        content.setLayout(new BorderLayout());
        
        window.setContentPane(content);
        
        createAccounts();
        createCurrencies();
        createBanks();
        createSettings();
        createLog();

        JPanel pane3 = new JPanel();
        pane3.setLayout(new BorderLayout());

        JPanel pane3EastFlow = new JPanel();
        pane3EastFlow.setLayout(new FlowLayout());

        quitbutton = new JButton("Stop Server");
        quitbutton.addActionListener(new QuitListener());
        pane3EastFlow.add(quitbutton);

        refreshbutton = new JButton("Refresh Server");
        refreshbutton.addActionListener(new RefreshListener());
        pane3EastFlow.add(refreshbutton);
        
        refreshbutton2 = new JButton("Refresh MineConomy");
        refreshbutton2.addActionListener(new Refresh2Listener());
        pane3EastFlow.add(refreshbutton2);

        helpbutton = new JButton("Help");
        helpbutton.addActionListener(new HelpListener());
        pane3EastFlow.add(helpbutton);

        cfubutton = new JButton("Check For Updates...");
        cfubutton.addActionListener(new CFUListener());
        pane3EastFlow.add(cfubutton);

        pane3.add(pane3EastFlow, BorderLayout.EAST);

        JLabel label2 = new JLabel(
                "<html><span style=\"font-size:8px;\">&nbsp;[Console Version 3.0]</span></html>");
        pane3.add(label2, BorderLayout.WEST);

        content.add(tabs, BorderLayout.CENTER);
        content.add(pane3, BorderLayout.SOUTH);

        // window.setSize(700, 400);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
    private static void createAccounts()
    {
        accounts = new JPanel();
        accounts.setLayout(new BorderLayout());
        JScrollPane accountsScroll = new JScrollPane(accounts);
        tabs.addTab("Accounts", accountsScroll);

        JPanel titleFlow = new JPanel();
        titleFlow.setLayout(new FlowLayout());

        title = new JLabel();

        if (Update.check())
        {
            title.setText("<html><center>Control Panel<br><span style=\"color:blue;\">Updates Available!</span><br><br></center></html>");
        }
        else
        {
            title.setText("<html><center>Control Panel<br><br><br></center></html>");
        }

        titleFlow.add(title);
        accounts.add(titleFlow, BorderLayout.NORTH);

        JPanel pane1Flow = new JPanel();
        pane1Flow.setLayout(new FlowLayout());

        pane1 = new JPanel();
        pane1.setLayout(new GridLayout(2, 1));

        pane1Flow.add(pane1);
        accounts.add(pane1Flow, BorderLayout.WEST);

        JLabel label1 = new JLabel(
                "<html><center>Please choose an account.</center></html>");
        pane1.add(label1);

        reloadAccounts(false);
        accountList.insertItemAt("Accounts ---", 0);
        accountList.setSelectedIndex(0);

        JPanel pane2Flow = new JPanel();
        pane2Flow.setLayout(new FlowLayout());

        JPanel pane2 = new JPanel();
        pane2.setLayout(new GridLayout(7, 1));

        balance = new JLabel("<html><center>Balance: -.-- ---"
                + "</center></html>");

        pane2.add(balance);
        pane2Flow.add(pane2);
        accounts.add(pane2Flow, BorderLayout.CENTER);

        amount = new JTextField(10);
        amount.setEnabled(accountSelected);
        pane2.add(amount);

        JPanel buttonFlow = new JPanel();
        buttonFlow.setLayout(new FlowLayout());
        pane2.add(buttonFlow);

        givebutton = new JButton("Give");
        givebutton.addActionListener(new GiveListener());
        givebutton.setEnabled(accountSelected);
        buttonFlow.add(givebutton);

        takebutton = new JButton("Take");
        takebutton.addActionListener(new TakeListener());
        takebutton.setEnabled(accountSelected);
        buttonFlow.add(takebutton);

        setbutton = new JButton("Set");
        setbutton.addActionListener(new SetListener());
        setbutton.setEnabled(accountSelected);
        buttonFlow.add(setbutton);

        emptybutton = new JButton("Empty");
        emptybutton.addActionListener(new EmptyListener());
        emptybutton.setEnabled(accountSelected);
        buttonFlow.add(emptybutton);

        deletebutton = new JButton("Delete");
        deletebutton.addActionListener(new DeleteListener());
        deletebutton.setEnabled(accountSelected);
        buttonFlow.add(deletebutton);

        JLabel label3 = new JLabel("");
        pane2.add(label3);

        newaccount = new JTextField(10);
        newaccount.setEnabled(true);
        pane2.add(newaccount);

        createbutton = new JButton("Create Account");
        createbutton.setEnabled(true);
        createbutton.addActionListener(new CreateListener());
        pane2.add(createbutton);

        ImagePanel logo = new ImagePanel("internal/gui/graphics/mineconomy.jpg", 250, 50);
        logo.setPreferredSize(new Dimension(250, 50));
        pane2.add(logo);
    }
    
    private static void createCurrencies()
    {
        currencies = new JTabbedPane();

        JPanel mine = new JPanel();
        mine.setLayout(new BoxLayout(mine, BoxLayout.Y_AXIS));

        JScrollPane mineScroll = new JScrollPane(mine);

        JPanel label = new JPanel();
        label.setLayout(new FlowLayout());
        label.add(new JLabel("<html><span style=\"color:red;\">CURRENCY GUI NOT YET FULLY IMPLEMENTED.</span></html>"));
        mine.add(label);

        StringBuffer sb = new StringBuffer();

        try
        {
            Scanner in = new Scanner(new File(MineConomy.maindir
                    + "currencies.yml"));

            while (in.hasNextLine())
            {
                sb.append(in.nextLine() + "\n");
            }
            sb.replace(sb.length() - 1, sb.length(), "");

            in.close();
        }
        catch (FileNotFoundException e)
        {
            IOH.error("FileNotFoundException", e);
        }

        final JTextArea bankEdit = new JTextArea(sb.toString());

        JScrollPane bankEditScroll = new JScrollPane(bankEdit);
        bankEditScroll.setPreferredSize(new Dimension(750, 500));

        mine.add(bankEditScroll);

        JButton button = new JButton("Save");

        button.addActionListener(new ActionListener()
        {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                PrintWriter out;
                try
                {
                    out = new PrintWriter(new File(MineConomy.maindir
                            + "currencies.yml"));

                    out.print(bankEdit.getText());

                    out.close();
                }
                catch (FileNotFoundException e)
                {
                    IOH.error("FileNotFoundException", e);
                }

                Currency.reload();
                
                JOptionPane.showMessageDialog(window, "Currency File has been saved.", "MineConomy - Save Complete", JOptionPane.PLAIN_MESSAGE);
            }

        });

        JPanel buttonflow = new JPanel();
        buttonflow.setLayout(new FlowLayout());
        buttonflow.add(button);

        mine.add(buttonflow);

        currencies.addTab("Currencies", mineScroll);
        
//        JPanel physical = new JPanel();
//        
//        JScrollPane physicalScroll = new JScrollPane(physical);
//        
//        physical.add(new JLabel("<html><span style=\"color:red;\">CURRENCY GUI NOT YET IMPLEMENTED.</span></html>"));
//        
//        currencies.addTab("Physical Currencies", physicalScroll);
        
        tabs.addTab("Currencies", currencies);
    }
    
    private static void createBanks()
    {
        banks = new JPanel();
        
        banks.setLayout(new BoxLayout(banks, BoxLayout.Y_AXIS));
        
        JPanel labelflow = new JPanel();
        labelflow.setLayout(new FlowLayout());
        labelflow.add(new JLabel("<html><span style=\"color:red;\">BANK GUI NOT YET IMPLEMENTED. TRY USING THE IN-GAME COMMANDS. SORRY FOR THE INCONVIENCE</span></html>"));
        
        banks.add(labelflow);
        
//        StringBuffer sb = new StringBuffer();
//        
//        try
//        {
//            Scanner in = new Scanner(new File(MineConomy.maindir + "banks.yml"));
//            
//            while(in.hasNextLine())
//            {
//                sb.append(in.nextLine() + "\n");
//            }
//            sb.replace(sb.length() - 1, sb.length(), "");
//            
//            in.close();
//        }
//        catch (FileNotFoundException e)
//        {
//            IOH.error("", e);
//        }
//        
//        final JTextArea bankEdit = new JTextArea(sb.toString());
//        
//        JScrollPane bankEditScroll = new JScrollPane(bankEdit);
//        bankEditScroll.setPreferredSize(new Dimension(750, 500));
//        
//        banks.add(bankEditScroll);
//        
//        JButton button = new JButton("Save");
//        
//        button.addActionListener(new ActionListener() {
//
//            @Override
//            public void actionPerformed(ActionEvent arg0)
//            {
//                PrintWriter out;
//                try
//                {
//                    out = new PrintWriter(new File(MineConomy.maindir + "banks.yml"));
//                    
//                    out.print(bankEdit.getText());
//                    
//                    out.close();
//                }
//                catch (FileNotFoundException e)
//                {
//                    IOH.error("", e);
//                }
//                
//                Banking.reload();
//            }
//            
//        });
//        
//        JPanel buttonflow = new JPanel();
//        buttonflow.setLayout(new FlowLayout());
//        buttonflow.add(button);
//        
//        banks.add(buttonflow);
        
        JScrollPane banksScroll = new JScrollPane(banks);
        
        tabs.addTab("Banks", banksScroll);
    }
    
    private static void createSettings()
    {
        settings = new JPanel();
        
        settings.setLayout(new FlowLayout());
        
        JPanel settingscontent = new JPanel();
        
        settingscontent.setLayout(new BoxLayout(settingscontent, BoxLayout.Y_AXIS));
        
        JPanel titleflow = new JPanel();
        titleflow.setLayout(new FlowLayout());
        
        titleflow.add(new JLabel("Settings"));
        
        settingscontent.add(titleflow);
        settingscontent.add(new JLabel(" "));
        
        final JPanel settings1 = new JPanel();
        settings1.setLayout(new BoxLayout(settings1, BoxLayout.X_AXIS));
        
        settings1.add(new JLabel("Starting Balance: "));
        
        double starting = 9999999.99D;
        
        if (Settings.startingBalance > starting)
        {
            starting = Settings.startingBalance;
        }
        
        final JSpinner startingField = new JSpinner(new SpinnerNumberModel(Settings.startingBalance,
                0.00,
                starting,
                1));
        
        settings1.add(startingField);
        
        settingscontent.add(settings1);
        
        JPanel settings2 = new JPanel();
        settings2.setLayout(new BoxLayout(settings2, BoxLayout.X_AXIS));
        
        settings2.add(new JLabel("Max Debt: "));
        
        double maxdebt = 9999999.99D;
        
        if (Settings.maxDebt > maxdebt)
        {
            maxdebt = Settings.maxDebt;
        }
        
        final JSpinner maxDebtField = new JSpinner(new SpinnerNumberModel(Settings.maxDebt,
                0.00,
                maxdebt,
                1));
        
        settings2.add(maxDebtField);
        
        settingscontent.add(settings2);
        
        settingscontent.add(new JLabel(" "));
        
        JPanel settings3 = new JPanel();
        settings3.setLayout(new BoxLayout(settings3, BoxLayout.X_AXIS));
        
        settings3.add(new JLabel("Interest Amount: "));
        
        double iamount = 9999999.99D;
        
        if (Settings.interestAmount > iamount)
        {
            iamount = Settings.interestAmount;
        }
        
        final JSpinner interestAmount = new JSpinner(new SpinnerNumberModel(Settings.interestAmount,
                0.00,
                iamount,
                1));
        
        settings3.add(interestAmount);
        
        settingscontent.add(settings3);
        
        JPanel settings4 = new JPanel();
        settings4.setLayout(new BoxLayout(settings4, BoxLayout.X_AXIS));
        
        settings4.add(new JLabel("Interest Interval (Seconds): "));
        
        double iinterval = 100000D;
        
        if (Settings.interestInterval > iinterval)
        {
            iinterval = Settings.interestInterval;
        }
        
        final JSpinner interestInterval = new JSpinner(new SpinnerNumberModel(Settings.interestInterval,
                0,
                iinterval,
                1));
        
        settings4.add(interestInterval);
        
        settingscontent.add(settings4);
        
        JPanel settings5 = new JPanel();
        
        settings5.setLayout(new BoxLayout(settings5, BoxLayout.X_AXIS));
        
        settings5.add(new JLabel("Interest Mode: "));
        
        String[] comboModel = {"None", "Fixed", "Percent"};
        
        final JComboBox interestMode = new JComboBox(comboModel);
        
        String mode = Settings.interestMode;
        
        if (mode.equalsIgnoreCase("Fixed"))
        {
            interestMode.setSelectedIndex(1);
        }
        else if (mode.equalsIgnoreCase("Percent"))
        {
            interestMode.setSelectedIndex(2);
        }
        else
        {
            interestMode.setSelectedIndex(0);
        }
        
        settings5.add(interestMode);
        
        settingscontent.add(settings5);
        
        settingscontent.add(new JLabel(" "));
        
        JPanel settings6 = new JPanel();
        settings6.setLayout(new BoxLayout(settings6, BoxLayout.X_AXIS));
        
        settings6.add(new JLabel("Tax Amount: "));
        
        double tamount = 9999999.99D;
        
        if (Settings.taxAmount > tamount)
        {
            tamount = Settings.taxAmount;
        }
        
        final JSpinner taxAmount = new JSpinner(new SpinnerNumberModel(Settings.taxAmount,
                0.00,
                tamount,
                1));
        
        settings6.add(taxAmount);
        
        settingscontent.add(settings6);
        
        JPanel settings7 = new JPanel();
        settings7.setLayout(new BoxLayout(settings7, BoxLayout.X_AXIS));
        
        settings7.add(new JLabel("Tax Interval (Seconds): "));
        
        double tinterval = 100000D;
        
        if (Settings.taxInterval > tinterval)
        {
            tinterval = Settings.taxInterval;
        }
        
        final JSpinner taxInterval = new JSpinner(new SpinnerNumberModel(Settings.taxInterval,
                0,
                tinterval,
                1));
        
        settings7.add(taxInterval);
        
        settingscontent.add(settings7);
        
        JPanel settings8 = new JPanel();
        
        settings8.setLayout(new BoxLayout(settings8, BoxLayout.X_AXIS));
        
        settings8.add(new JLabel("Tax Mode: "));
        
        final JComboBox taxMode = new JComboBox(comboModel);
        
        mode = Settings.taxMode;
        
        if (mode.equalsIgnoreCase("Fixed"))
        {
            taxMode.setSelectedIndex(1);
        }
        else if (mode.equalsIgnoreCase("Percent"))
        {
            taxMode.setSelectedIndex(2);
        }
        else
        {
            taxMode.setSelectedIndex(0);
        }
        
        settings8.add(taxMode);
        
        settingscontent.add(settings8);
        
        settingscontent.add(new JLabel(" "));
        
        JPanel settings9 = new JPanel();
        
        settings9.add(new JLabel("Language: "));
        
        final JTextField lang = new JTextField(Settings.lang, 10);
        
        settings9.add(lang);
        
        settingscontent.add(settings9);
        
        JPanel settings10 = new JPanel();
        
        settings10.add(new JLabel("Log Priority"));
        
        final JLabel prilabel = new JLabel(Settings.logPriority + "");
        
        final JSlider prislide = new JSlider(0, 5, Settings.logPriority);
        prislide.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent arg0)
            {
                prilabel.setText(prislide.getValue() + "");
            }
            
        });
        
        settings10.add(prislide);
        
        settings10.add(prilabel);
        
        settingscontent.add(settings10);
        
        JPanel buttonflow = new JPanel();
        buttonflow.setLayout(new FlowLayout());
        
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                Settings.startingBalance = Double.parseDouble(startingField.getValue() + "");
                
                Settings.maxDebt = Double.parseDouble(maxDebtField.getValue() + "");
                
                Settings.interestAmount = Double.parseDouble(interestAmount.getValue() + "");
                Settings.interestInterval = Integer.parseInt(interestInterval.getValue() + "");
                Settings.interestMode = interestMode.getSelectedItem().toString();
                
                Settings.taxAmount = Double.parseDouble(taxAmount.getValue() + "");
                Settings.taxInterval = Integer.parseInt(taxInterval.getValue() + "");
                Settings.taxMode = taxMode.getSelectedItem().toString();
                
                Settings.lang = lang.getText();
                
                Settings.logPriority = prislide.getValue();
                
                Settings.save();
                
                JOptionPane.showMessageDialog(window, "Configuration File has been saved.", "MineConomy - Save Complete", JOptionPane.PLAIN_MESSAGE);
            }
            
        });
        buttonflow.add(save);
        
        settingscontent.add(buttonflow);
        
        settingscontent.add(new JLabel(" "));
        
        JPanel labelflow = new JPanel();
        labelflow.setLayout(new FlowLayout());
        labelflow.add(new JLabel("<html><span style=\"color:red;\">Some Changes May Require Server Reload.</span></html>"));
        
        settingscontent.add(labelflow);
        
        settings.add(settingscontent);
        
        JScrollPane settingsScroll = new JScrollPane(settings);
        
        tabs.addTab("Settings", settingsScroll);
    }
    
    private static void createLog()
    {
        JPanel log = new JPanel();
        log.setLayout(new BorderLayout());
        
        logtext = new JTextArea();
        logtext.setFont(new Font("monospace", Font.PLAIN, 12));
        logtext.setLineWrap(false);
        logtext.setEditable(false);
        IOH.updateGUILog();
        
        JScrollPane logscroll = new JScrollPane(logtext);
        logscroll.setPreferredSize(new Dimension(750, 500));
        
        log.add(logscroll, BorderLayout.CENTER);
        
        JPanel bflow1 = new JPanel();
        bflow1.setLayout(new FlowLayout());
        
        JButton clearlog = new JButton("Clear Log");
        clearlog.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                IOH.clearLog();
            }
            
        });
        bflow1.add(clearlog);
        
        log.add(bflow1, BorderLayout.SOUTH);
        
        tabs.addTab("Log", log);
    }
    
    public static void error(String text)
    {
        final JFrame error = new JFrame("MineConomy - Error Report");
        
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JPanel messageFlow = new JPanel();
        messageFlow.setLayout(new FlowLayout());
        messageFlow.add(new JLabel("<html><center>MineConomy has encountered this error.<br>"
                + "A special team of code monkeys has been dispatched.<br>"
                + "If you see them, show them this error trace.<br><br>"
                + "Or you can report it at <a href=\"http://dev.bukkit.org/server-mods/mineconomy\">http://dev.bukkit.org/server-mods/mineconomy</a>.</center></html>"));
        panel.add(messageFlow);
        
        JTextArea pane = new JTextArea();
        
        pane.setText(text);
        pane.setLineWrap(false);
        pane.setEditable(false);
        
        JScrollPane paneScroll = new JScrollPane(pane);
        paneScroll.setPreferredSize(new Dimension(500, 300));
        
        panel.add(paneScroll);
        
        JPanel buttonFlow = new JPanel();
        buttonFlow.setLayout(new FlowLayout());
        
        JButton close = new JButton("Close");
        close.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                error.setVisible(false);
                error.dispose();
            }
            
        });
        buttonFlow.add(close);
        
        panel.add(buttonFlow);
        
        pane.select(0, 0);
        
        error.add(panel);
        error.pack();
        error.setLocationRelativeTo(window);
        error.setVisible(true);
    }
    
    public static void reloadAccounts(boolean update)
    {
        MCCom.getAccounting().save();
        
        int j = 0;
        
        try
        {
            j = accountList.getSelectedIndex();
            pane1.remove(accountList);
        }
        catch (NullPointerException e)
        {
            j = 0;
        }
        
        accountBalances = new Hashtable<String, Double>();
        
        accountNames = MCCom.getAccounts();
        for (int i = 0; accountNames.size() > i; i++)
        {
            accountBalances.put(accountNames.get(i),
                    MCCom.getBalance(accountNames.get(i)));
        }

        accountList = new JComboBox(accountNames.toArray());
        try
        {
            accountList.setSelectedIndex(j);
        }
        catch (IllegalArgumentException e)
        {
            try
            {
                accountList.setSelectedIndex(j - 1);
            }
            catch (IllegalArgumentException e1)
            {
                accountList.insertItemAt("Accounts ---", 0);
                accountList.setSelectedIndex(0);
            }
        }
        
        if (update)
        {
            JFrame oldWindow = window;
            new GUI();
            oldWindow.setVisible(false);
        }
        
        accountList.addActionListener(new AccountListListener());
        
        pane1.add(accountList);
        
        accountList.repaint();
    }
}
