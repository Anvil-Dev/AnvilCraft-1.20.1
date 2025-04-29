package dev.dubhe.anvilcraft.api.entity.attribute;

import com.google.common.collect.Multimap;
import dev.dubhe.anvilcraft.api.entity.attribute.forge.EntityReachAttributeImpl;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class EntityReachAttribute {

    public static @NotNull Supplier<Multimap<Attribute, AttributeModifier>> getRangeModifierSupplier(
        @NotNull AttributeModifier modifier
    ) {
        return EntityReachAttributeImpl.getRangeModifierSupplier(modifier);
    }

    public static @NotNull Attribute getReachAttribute() {
        return EntityReachAttributeImpl.getReachAttribute();
    }
}
