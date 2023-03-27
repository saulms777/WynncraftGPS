package com.saulms.wynncraftgps;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.CommandRegistryAccess;
import net.minecraft.text.Text;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import static com.mojang.brigadier.arguments.StringArgumentType.*;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.*;

public class Main implements ModInitializer {

    public static final String MOD_ID = "wgps";

    @Override
    public void onInitialize() {
        ClientCommandRegistrationCallback.EVENT.register(Main::register);
    }

    private static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, CommandRegistryAccess commandRegistryAccess) {
        dispatcher.register(literal("goto")
                .then(argument("from", string())
                        .then(argument("to", string())
                                .executes(ctx -> findPath(getString(ctx, "from"), getString(ctx, "to"))))));
    }

    private static int findPath(String from, String to) {
        GPS gps = new GPS();
        assert MinecraftClient.getInstance().player != null;
        MinecraftClient.getInstance().player.sendMessage(Text.literal(gps.outputPath(from, to)));

        return Command.SINGLE_SUCCESS;
    }

}
