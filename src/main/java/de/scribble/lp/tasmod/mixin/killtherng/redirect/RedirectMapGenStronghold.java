package de.scribble.lp.tasmod.mixin.killtherng.redirect;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import de.pfannekuchen.killtherng.KillTheRNG;
import de.pfannekuchen.killtherng.utils.WorldRandom;
import net.minecraft.world.gen.structure.MapGenStronghold;

@Mixin(MapGenStronghold.class)
public class RedirectMapGenStronghold {

	@Redirect(method = "generatePositions", at = @At(value = "NEW", target = "Ljava/util/Random;<init>()Ljava/util/Random;", remap = false))
	public Random redirectRandom2() {
		return KillTheRNG.ISDISABLED ? new Random() : new WorldRandom();
	}
	
}
