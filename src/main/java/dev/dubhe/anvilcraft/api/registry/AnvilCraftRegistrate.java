package dev.dubhe.anvilcraft.api.registry;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.Builder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.builders.NoConfigBuilder;
import com.tterrag.registrate.util.entry.RegistryEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import dev.dubhe.anvilcraft.api.registry.forge.AnvilCraftRegistrateImpl;
import dev.dubhe.anvilcraft.util.IFormattingUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.IdentityHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public abstract class AnvilCraftRegistrate extends Registrate {

    protected AnvilCraftRegistrate(String modId) {
        super(modId);
    }

    @NotNull
    public static AnvilCraftRegistrate create(@NotNull String modId) {
        return AnvilCraftRegistrateImpl.create(modId);
    }

    public abstract void registerRegistrate();

    @Override
    public <T extends Item> @NotNull ItemBuilder<T, Registrate> item(
        @NotNull String name,
        @NotNull NonNullFunction<Item.Properties, T> factory
    ) {
        return super.item(name, factory).lang(IFormattingUtil.toEnglishName(name.replaceAll("/.", "_")));
    }

    private RegistryEntry<CreativeModeTab> currentTab;
    private static final Map<RegistryEntry<?>, RegistryEntry<CreativeModeTab>> TAB_LOOKUP = new IdentityHashMap<>();

    public void creativeModeTab(@NotNull Supplier<RegistryEntry<CreativeModeTab>> currentTab) {
        this.currentTab = currentTab.get();
    }

    public void creativeModeTab(RegistryEntry<CreativeModeTab> currentTab) {
        this.currentTab = currentTab;
    }

    public boolean isInCreativeTab(RegistryEntry<?> entry, RegistryEntry<CreativeModeTab> tab) {
        return TAB_LOOKUP.get(entry) == tab;
    }

    public void setCreativeTab(RegistryEntry<?> entry, @Nullable RegistryEntry<CreativeModeTab> tab) {
        TAB_LOOKUP.put(entry, tab);
    }

    @Override
    protected <R, T extends R> @NotNull RegistryEntry<T> accept(
        @NotNull String name,
        @NotNull ResourceKey<? extends Registry<R>> type,
        @NotNull Builder<R, T, ?, ?> builder,
        @NotNull NonNullSupplier<? extends T> creator,
        @NotNull NonNullFunction<RegistryObject<T>, ? extends RegistryEntry<T>> entryFactory
    ) {
        RegistryEntry<T> entry = super.accept(name, type, builder, creator, entryFactory);

        if (this.currentTab != null) {
            TAB_LOOKUP.put(entry, this.currentTab);
        }

        return entry;
    }

    @Override
    public <P> @NotNull NoConfigBuilder<CreativeModeTab, CreativeModeTab, P> defaultCreativeTab(
        @NotNull P parent, @NotNull String name, @NotNull Consumer<CreativeModeTab.Builder> config
    ) {
        return createCreativeModeTab(parent, name, config);
    }

    protected <P> NoConfigBuilder<CreativeModeTab, CreativeModeTab, P> createCreativeModeTab(
        P parent, String name, Consumer<CreativeModeTab.Builder> config
    ) {
        return super.defaultCreativeTab(parent, name, config);
    }
}
