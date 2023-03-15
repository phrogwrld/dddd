package net.silthus.template;

import lombok.Getter;
import lombok.experimental.Accessors;
import net.silthus.template.listener.DeathEvent;

import org.bukkit.plugin.java.JavaPlugin;

public class TemplatePlugin extends JavaPlugin {

    @Getter
    @Accessors(fluent = true)
    private static TemplatePlugin instance;

    public TemplatePlugin() {
        instance = this;
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();
        registerListeners();
    }

    @Override
    public void onDisable() {
    }

    public void registerListeners() {
        new DeathEvent(this);
    }

}
