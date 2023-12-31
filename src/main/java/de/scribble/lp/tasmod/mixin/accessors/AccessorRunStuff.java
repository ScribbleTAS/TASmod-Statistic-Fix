package de.scribble.lp.tasmod.mixin.accessors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Timer;

@Mixin(Minecraft.class)
public interface AccessorRunStuff {
	@Invoker("runTickKeyboard")
	public void runTickKeyboardAccessor();
	
	@Invoker("runTickMouse")
	public void runTickMouseAccessor();
	
	@Accessor("timer")
	public Timer timer();
}
