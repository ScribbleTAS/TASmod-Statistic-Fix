package de.scribble.lp.tasmod.mixin.killtherng.redirect;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import de.pfannekuchen.killtherng.KillTheRNG;
import de.pfannekuchen.killtherng.utils.WorldRandom;
import net.minecraft.world.Explosion;

@Mixin(Explosion.class)
public class RedirectExplosion {

	@Redirect(method = "<init>", at = @At(value = "NEW", target = "Ljava/util/Random;<init>()Ljava/util/Random;"))
	public Random redirectRandom() {
		return KillTheRNG.ISDISABLED ? new Random() : new WorldRandom();
	}
	
}
