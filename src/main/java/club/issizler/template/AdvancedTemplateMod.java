package club.issizler.template;

import club.issizler.okyanus.api.Mod;
import club.issizler.okyanus.api.cmd.CommandBuilder;

public class AdvancedTemplateMod extends Mod {

    @Override
    public void init() {
        registerCommand(
                new CommandBuilder("hello")
                        .run(source -> {
                            source.send("Hello, world!");
                            return 1;
                        })
        );
    }

}
