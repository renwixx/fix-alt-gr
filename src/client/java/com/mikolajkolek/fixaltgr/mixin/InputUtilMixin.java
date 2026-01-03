package com.mikolajkolek.fixaltgr.mixin;

import com.mikolajkolek.fixaltgr.FixAltGrClient;
import net.minecraft.client.util.InputUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import java.util.concurrent.TimeUnit;

@Mixin(InputUtil.class)
public class InputUtilMixin { //69696969696969 jubert to nooooobek
    @Inject(at = @At(value = "HEAD"), method = "isKeyPressed", cancellable = true)
	private static void isKeyPressed(long window, int code, CallbackInfoReturnable<Boolean> cir) {
		if (code != 341) return;

		if (!FixAltGrClient.listener.controlKeyPressed || FixAltGrClient.listener.altKeyPressed) {
			cir.setReturnValue(false);
			return;
		}

		if (FixAltGrClient.axiomLoaded) return;

		long timeSinceChange = System.currentTimeMillis() - FixAltGrClient.listener.lastStateChangeTime;

		if (timeSinceChange < 10) {
			cir.setReturnValue(false);
		}
	}
}
