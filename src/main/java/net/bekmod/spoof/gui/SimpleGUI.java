package net.bekmod.spoof.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.bekmod.spoof.MainMod;
import net.bekmod.spoof.entity.Envoy;
import net.bekmod.spoof.gui.model.TargetModel;
import net.bekmod.spoof.service.MessageProcess;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.BiConsumer;

public class SimpleGUI extends LightweightGuiDescription {

    private WPlainPanel root;
    private WLabel info;
    private WListPanel<Envoy, TargetModel> targetList;

    private static SimpleGUI instance = null;

    public static SimpleGUI getInstance(){
        if (instance == null) instance = new SimpleGUI();
        instance.refresh();
        return instance;
    }

    private SimpleGUI(){
        root = new WPlainPanel();
        root.setSize(350, 250);
        info = new WLabel(Text.empty());
        info.setSize(100, 70);
        root.add(info, 5, 5);
        setRootPanel(root);
    }
    public void refresh(){
        info.setText(Text.of(MainMod.getInstance().getMessage()));
        root.remove(targetList);


        BiConsumer<Envoy, TargetModel> consumer = (envoy, targetModel) -> {
            targetModel.takeEnvoy(envoy);
        };
        ArrayList<Envoy> envoys = MainMod.getInstance().getEnvoys();
        MessageProcess.setVicinity(envoys);
        targetList = new WListPanel<>(envoys, TargetModel::new, consumer);
        targetList.setSize(70, 50);
        root.add(targetList, 5,75);
        targetList.layout();
    }
}
