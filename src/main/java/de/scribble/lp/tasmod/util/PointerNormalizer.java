package de.scribble.lp.tasmod.util;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiWorldSelection;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiContainerCreative;

/**
 * Adjusts the pointer/cursor of the playback to different gui scalings.
 * 
 * This was the work of many hours of trial and error.
 * 
 * Out of despair I reached out to Darkmoon to help me with this problem...
 * 
 * @author ScribbleLP, Darkmoon
 *
 */
public class PointerNormalizer {

	public static int getNormalizedX(int pointerX) {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(mc);
		int out = (int) (pointerX - (scaled.getScaledWidth() / 2D));
		return out;
	}

	public static int getNormalizedY(int pointerY) {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(mc);

		int out = pointerY;

		if (mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiContainerCreative) {
			out = (int) (pointerY - (scaled.getScaledHeight() / 2D));
		} else if (mc.currentScreen instanceof GuiWorldSelection|| mc.currentScreen instanceof GuiMultiplayer) {
			
		} else {
			out = (int) (pointerY - (scaled.getScaledHeight() / 4 + 72 + -16));
		}

		return out;
	}

	public static int getCoordsX(int normalizedX) {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(mc);
		int out = (int) Math.round(normalizedX + (scaled.getScaledWidth() / 2D));
		return limiterX(out, scaled);
	}

	public static int getCoordsY(int normalizedY) {
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution scaled = new ScaledResolution(mc);

		int out = normalizedY;
		if (mc.currentScreen instanceof GuiContainer || mc.currentScreen instanceof GuiContainerCreative) {
			out = (int) Math.round(normalizedY + (scaled.getScaledHeight() / 2D));
		} else if (mc.currentScreen instanceof GuiWorldSelection || mc.currentScreen instanceof GuiMultiplayer) {
			
		} else {
			out = (int) (normalizedY + (scaled.getScaledHeight() / 4 + 72 + -16));
		}

		return limiterY(out, scaled);
	}

	private static int limiterX(int out, ScaledResolution scaled) {
		int width = scaled.getScaledWidth();
		if (out > width) {
			out = width;
		} else if (out < 0)
			out = 0;
		return out;
	}

	private static int limiterY(int out, ScaledResolution scaled) {
		int height = scaled.getScaledHeight();
		if (out > height) {
			out = height;
		} else if (out < 0)
			out = 0;
		return out;
	}

	private static int gcd(int a, int b) {
		return (b == 0) ? a : gcd(b, a % b);
	}

	public static void printAspectRatio() {
		int height = Minecraft.getMinecraft().displayHeight;
		int width = Minecraft.getMinecraft().displayWidth;
		int gcd = gcd(width, height);
		if (gcd == 0) {
			System.out.println(gcd);
		} else {
			System.out.println(width / gcd + ":" + height / gcd);
		}
	}
	
	public static void resize(int width, int height) {
		double temp=(double)width/height;
		int newHeight=Minecraft.getMinecraft().displayHeight;
		int newWidth=(int)(temp*newHeight);
		
		try {
			Display.setDisplayMode(new DisplayMode(newWidth, newHeight));
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		Minecraft.getMinecraft().displayHeight=newHeight;
		Minecraft.getMinecraft().displayWidth=newWidth;
		
		
		Minecraft.getMinecraft().resize(newWidth, newHeight);
		Display.setResizable(false);
		Display.setResizable(true);
		Minecraft.getMinecraft().updateDisplay();
	}
	
	

	/*
	 * Here lies 10 hours of work for something I didn't even use. This code
	 * normalizes the pointers coordinates and scales it depending on the screen
	 * width and height. After 10 hours of trial and error, I finally managed to
	 * make it work, only to realize that this has no use whatsoever. The guis don't
	 * work this way and you can't even use the pointer properly... But now I have
	 * made it so I will let it stay here until I find a use, to spare me another 10
	 * hours.
	 */

//	private double getNormalizedXOld(double pointerX) {
//		Minecraft mc = Minecraft.getMinecraft();
//		ScaledResolution scaled = new ScaledResolution(mc);
//		return (double) pointerX / (double) mc.displayWidth / (4D / (double) scaled.getScaleFactor());
//	}
//
//	public static double getNormalizedYOld(int pointerY) {
//		Minecraft mc = Minecraft.getMinecraft();
//		ScaledResolution scaled = new ScaledResolution(mc);
//		double out = (double) pointerY / (double) mc.displayHeight / (4D / (double) scaled.getScaleFactor());
//		return out;
//	}
//
//	public static int getCoordsXOld(double normalizedX) {
//		Minecraft mc = Minecraft.getMinecraft();
//		ScaledResolution scaled = new ScaledResolution(mc);
//		double guiScaled = normalizedX * (double) mc.displayWidth * (4D / (double) scaled.getScaleFactor());
//		int out = (int) Math.round(guiScaled);
//		return out;
//	}
//
//	public static int getCoordsYOld(double normalizedY) {
//		Minecraft mc = Minecraft.getMinecraft();
//		ScaledResolution scaled = new ScaledResolution(mc);
//		double guiScaled = normalizedY * (double) mc.displayHeight * (4D / (double) scaled.getScaleFactor());
//		int out = (int) Math.round(guiScaled);
//		return out;
//	}

}
