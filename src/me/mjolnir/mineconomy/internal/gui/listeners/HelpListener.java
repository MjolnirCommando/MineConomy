package me.mjolnir.mineconomy.internal.gui.listeners;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import me.mjolnir.mineconomy.internal.Settings;
import me.mjolnir.mineconomy.internal.gui.GUI;

/**
 * Handles help button action.
 * 
 * @author MjolnirCommando
 */
public class HelpListener implements ActionListener
{

	public void actionPerformed(ActionEvent e)
	{

		Scanner in = new Scanner(new BufferedReader(new InputStreamReader(
				Settings.class.getClassLoader().getResourceAsStream(
						"me/mjolnir/mineconomy/dev/readme_template.txt"))));
		StringBuffer sb = new StringBuffer();
		while (in.hasNextLine())
		{
		    String line = in.nextLine();
			if (line.equals("-= GUI =-"))
			{
				sb.append(line + "\n");

				while (!line.equals("-= Change Log =-"))
				{
					line = in.nextLine();
					if (!line.equals("-= Change Log =-"))
					{
						sb.append(line + "\n");
					}
				}
				
			}
		}

		final JFrame window = new JFrame("MineConomy - GUI - Help");
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e1)
			{
				window.setVisible(false);
			}
			
		});
		JPanel content = new JPanel();
		content.setLayout(new BorderLayout());
		window.add(content);
		JTextArea text = new JTextArea();
		text.setText(sb.toString());
		text.setEditable(false);
		text.setLineWrap(false);
		JScrollPane textscroll = new JScrollPane(text);
		textscroll.setPreferredSize(new Dimension(400, 300));
		content.add(textscroll, BorderLayout.CENTER);
		JPanel buttonFlow = new JPanel();
		buttonFlow.setLayout(new FlowLayout());
		content.add(buttonFlow, BorderLayout.SOUTH);
		JButton close = new JButton("Close");
		close.addActionListener(new ActionListener()
		{

			public void actionPerformed(ActionEvent e)
			{
				window.setVisible(false);
			}

		});
		buttonFlow.add(close);
		text.select(0, 0);
		window.pack();
		window.setLocationRelativeTo(GUI.window);
		window.setVisible(true);
	}

}
