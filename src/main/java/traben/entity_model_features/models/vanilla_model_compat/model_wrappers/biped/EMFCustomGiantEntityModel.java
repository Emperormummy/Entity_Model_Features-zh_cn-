package traben.entity_model_features.models.vanilla_model_compat.model_wrappers.biped;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.GiantEntityModel;
import net.minecraft.client.render.entity.model.ModelWithHat;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.GiantEntity;
import traben.entity_model_features.mixin.accessor.ModelAccessor;
import traben.entity_model_features.models.EMFArmorableModel;
import traben.entity_model_features.models.EMFCustomEntityModel;
import traben.entity_model_features.models.EMFGenericCustomEntityModel;

import java.util.HashMap;

public class EMFCustomGiantEntityModel<T extends LivingEntity> extends GiantEntityModel implements EMFCustomEntityModel<T>, EMFArmorableModel, ModelWithHat {

    public EMFGenericCustomEntityModel<T> getThisEMFModel() {
        return thisEMFModel;
    }



    public boolean doesThisModelNeedToBeReset() {
        return false;
    }

    private final EMFGenericCustomEntityModel<T> thisEMFModel;

    private static final HashMap<String,String> optifineMap = new HashMap<>(){{
        put("hat","headwear");
    }};
    public EMFCustomGiantEntityModel(EMFGenericCustomEntityModel<T> model) {
        super( EMFCustomEntityModel.getFinalModelRootData(
                GiantEntityModel.getModelData(Dilation.NONE,0).getRoot().createPart(0,0)
                ,model,optifineMap));

        thisEMFModel=model;
        thisEMFModel.clearAllFakePartChildrenData();
        ((ModelAccessor)this).setLayerFactory(getThisEMFModel()::getLayer2);

    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {

            thisEMFModel.render(matrices, vertices, light, overlay, red, green, blue, alpha);

    }
//    @Override
//    public void setAngles(GiantEntity livingEntity, float f, float g, float h, float i, float j) {
//        setAngles((T)livingEntity,f,g,h,i,j);
//    }
    @Override
    public void setAngles(GiantEntity livingEntity, float f, float g, float h, float i, float j) {

            thisEMFModel.child = child;
            thisEMFModel.sneaking = sneaking;
            thisEMFModel.riding = riding;
            thisEMFModel.handSwingProgress = handSwingProgress;
            try {
                thisEMFModel.setAngles((T) livingEntity, f, g, h, i, j);
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

    }

//    @Override
//    public void animateModel(GiantEntity livingEntity, float f, float g, float h) {
//        animateModel((T)livingEntity, f, g, h);
//    }
    @Override
    public void animateModel(GiantEntity livingEntity, float f, float g, float h) {
        //super.animateModel(livingEntity, f, g, h);

            try {
                thisEMFModel.animateModel((T) livingEntity, f, g, h);
            } catch (ClassCastException e) {
                e.printStackTrace();
            }

    }


    @Override
    public EMFGenericCustomEntityModel<?> getArmourModel(boolean getInner) {
        return thisEMFModel.getArmourModel(getInner);
    }


    @Override
    public void setHatVisible(boolean visible) {
        thisEMFModel.setHatVisible(visible);
    }

//    @Override
//    public void setArmAngle(Arm arm, MatrixStack matrices) {
//        //super.setArmAngle(arm, matrices);
//        if(thisEMFModel.vanillaModel instanceof BipedEntityModel<?> model){
//            model.setArmAngle(arm, matrices);
//        }
//    }
}
