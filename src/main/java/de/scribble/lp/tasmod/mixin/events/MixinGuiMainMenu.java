package de.scribble.lp.tasmod.mixin.events;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import de.scribble.lp.tasmod.events.OpenGuiEvents;
import de.scribble.lp.tasmod.gui.GuiMultiplayerWarn;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu extends GuiScreen {

	@Inject(method = "initGui", at = @At("HEAD"))
	public void inject_initGui(CallbackInfo ci) {
		OpenGuiEvents.openGuiMainMenu((GuiMainMenu) (Object) this);
	}
	
	@Redirect(method = "actionPerformed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Minecraft;displayGuiScreen(Lnet/minecraft/client/gui/GuiScreen;)V", ordinal = 3))
	public void redirectOpenGuiMultiplayer(Minecraft mc, GuiScreen screen) {
		mc.displayGuiScreen(new GuiMultiplayerWarn((GuiMainMenu)(Object)this));
	}
}
