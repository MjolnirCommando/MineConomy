package me.mjolnir.mineconomy.internal.gui.graphics;

import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.PixelGrabber;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import me.mjolnir.mineconomy.internal.util.IOH;

/**
 * @author MjolnirCommando
 */
public class ImagePanel extends JPanel
{
	private BufferedImage	image;
	private int				width;
	private int				height;

	/**
	 * Creates new ImagePanel
	 * 
	 * @param path 
	 * @param wd 
	 * @param ht 
	 */
	public ImagePanel(String path, int wd, int ht)
	{
		image = toBufferedImage(Toolkit.getDefaultToolkit().getImage(
				ImagePanel.class.getClassLoader().getResource("me/mjolnir/mineconomy/" + path)));
		width = wd;
		height = ht;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(image, 0, 0, width, height, null);
	}

	private BufferedImage toBufferedImage(Image image)
	{
		if (image instanceof BufferedImage)
		{
			return (BufferedImage) image;
		}

		image = new ImageIcon(image).getImage();

		boolean hasAlpha = hasAlpha(image);

		BufferedImage bimage = null;
		GraphicsEnvironment ge = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		try
		{
			int transparency = Transparency.OPAQUE;
			if (hasAlpha)
			{
				transparency = Transparency.BITMASK;
			}

			GraphicsDevice gs = ge.getDefaultScreenDevice();
			GraphicsConfiguration gc = gs.getDefaultConfiguration();
			bimage = gc.createCompatibleImage(image.getWidth(null),
					image.getHeight(null), transparency);
		}
		catch (HeadlessException e)
		{
			IOH.error("HeadlessException", e);
		}

		if (bimage == null)
		{
			int type = BufferedImage.TYPE_INT_RGB;
			if (hasAlpha)
			{
				type = BufferedImage.TYPE_INT_ARGB;
			}
			bimage = new BufferedImage(image.getWidth(null),
					image.getHeight(null), type);
		}

		Graphics g = bimage.createGraphics();

		g.drawImage(image, 0, 0, null);
		g.dispose();

		return bimage;
	}

	private boolean hasAlpha(Image image)
	{
		if (image instanceof BufferedImage)
		{
			BufferedImage bimage = (BufferedImage) image;
			return bimage.getColorModel().hasAlpha();
		}

		PixelGrabber pg = new PixelGrabber(image, 0, 0, 1, 1, false);
		try
		{
			pg.grabPixels();
		}
		catch (InterruptedException e)
		{
		    IOH.error("InterruptedException", e);
		}

		ColorModel cm = pg.getColorModel();
		return cm.hasAlpha();
	}
}