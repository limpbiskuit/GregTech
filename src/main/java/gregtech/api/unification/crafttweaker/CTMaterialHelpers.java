package gregtech.api.unification.crafttweaker;

import com.google.common.collect.ImmutableList;
import crafttweaker.CraftTweakerAPI;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.MaterialRegistry;
import gregtech.api.unification.stack.MaterialStack;

public class CTMaterialHelpers {

    protected static ImmutableList<MaterialStack> validateComponentList(MaterialStack[] components) {
        return components == null || components.length == 0 ? ImmutableList.of() : ImmutableList.copyOf(components);
    }

    protected static Material.FluidType validateFluidType(String fluidTypeName) {
        if (fluidTypeName == null || fluidTypeName.equals("fluid")) return Material.FluidType.FLUID;
        else if (fluidTypeName.equals("gas")) return Material.FluidType.GAS;
        else throw new IllegalArgumentException("Fluid Type must be either \"fluid\" or \"gas\"!");
    }

    protected static boolean checkFrozen(String description) {
        if (MaterialRegistry.isFrozen()) {
            CraftTweakerAPI.logError("Cannot " + description + " now, must be done in a file labeled with \"#loader gregtech\"");
            return true;
        } return false;
    }

    protected static void logError(Material m, String cause, String type) {
        CraftTweakerAPI.logError("Cannot " + cause + " of a Material with no " + type + "! Try calling \"add" + type + "\" in your \"#loader gregtech\" file first if this is intentional. Material: " + m.getUnlocalizedName());
    }
}
