package gregsconstruct.common;

import gregicadditions.GAMaterials;
import gregtech.api.unification.material.IMaterialHandler;
import gregtech.api.unification.material.Materials;
import gregtech.api.unification.material.type.FluidMaterial;
import gregtech.api.unification.material.type.IngotMaterial;
import gregtech.api.unification.material.type.Material;
import net.minecraftforge.fml.common.Loader;

@IMaterialHandler.RegisterMaterialHandler
public class FluidTemp implements IMaterialHandler {
    @Override
    public void onMaterialsInit() {
        for (Material mat : Material.MATERIAL_REGISTRY)
            if (mat instanceof FluidMaterial) {
                if (mat instanceof IngotMaterial && ((IngotMaterial) mat).blastFurnaceTemperature > 0)
                    ((IngotMaterial) mat).setFluidTemperature(((IngotMaterial) mat).blastFurnaceTemperature);
                else if (mat != Materials.Ice && mat != Materials.Water && ((FluidMaterial) mat).getFluidTemperature() <= 300)
                    ((FluidMaterial) mat).setFluidTemperature(500);
            }
        Materials.SeedOil.setFluidTemperature(1774);
        Materials.Creosote.setFluidTemperature(1900);
        Materials.Biomass.setFluidTemperature(1900);
        Materials.Oil.setFluidTemperature(2800);
        Materials.OilLight.setFluidTemperature(3200);
        Materials.OilHeavy.setFluidTemperature(3500);
        Materials.SulfuricHeavyFuel.setFluidTemperature(3500);
        Materials.SulfuricLightFuel.setFluidTemperature(3200);
        Materials.Methanol.setFluidTemperature(2300);
        Materials.Ethanol.setFluidTemperature(2500);
        Materials.BioDiesel.setFluidTemperature(2800);
        Materials.Fuel.setFluidTemperature(3400);
        Materials.NitroFuel.setFluidTemperature(3700);
        Materials.HeavyFuel.setFluidTemperature(4200);
        Materials.RocketFuel.setFluidTemperature(5475);
        Materials.NaquadahEnriched.setFluidTemperature(10000);
    }
}
