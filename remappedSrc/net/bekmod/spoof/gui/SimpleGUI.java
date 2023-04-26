package net.bekmod.spoof.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WListPanel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.bekmod.spoof.MainMod;
import net.bekmod.spoof.entity.Envoy;
import net.bekmod.spoof.gui.model.TargetModel;
import net.bekmod.spoof.service.MessageProcess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiConsumer;

public class SimpleGUI extends LightweightGuiDescription {

    private WPlainPanel root;
    private WLabel info;
    private WListPanel<Envoy, TargetModel> targetList;
    private WButton switchOverlayButton;

    private static SimpleGUI instance = null;

    public static SimpleGUI getInstance(){
        if (instance == null) instance = new SimpleGUI();
        instance.refresh();
        return instance;
    }

    private SimpleGUI(){

        root = new WPlainPanel();
        root.setSize(350, 250);
        info = new WLabel(Text.of(""));
        info.setSize(200, 70);
        root.add(info, 5, 5);
        setRootPanel(root);

        switchOverlayButton = new WButton();

        root.add(switchOverlayButton, 120, 5, 80, 50);
        setEvents();
    }
    private void refresh(){
        info.setText(Text.of(MainMod.getInstance().getMessage()));
        root.remove(targetList);

        switchOverlayButton.setLabel(Text.of("switched: " + (MainMod.getInstance().isSwitchOverlay()?"ON":"OFF")));

        ArrayList<Envoy> envoys = MainMod.getInstance().getProximitedEnvoys();
        if(envoys.size() > 0) {
            BiConsumer<Envoy, TargetModel> consumer = (envoy, targetModel) -> {
                targetModel.takeEnvoy(envoy);
            };
            envoys.sort(Comparator.comparingInt(Envoy::getProximity));
            targetList = new WListPanel<>(envoys, TargetModel::new, consumer);

            root.add(targetList, 5, 75, 150, 200);
            targetList.layout();
        }
    }

    private void setEvents(){
        switchOverlayButton.setOnClick(() ->{
            MainMod.getInstance().setSwitchOverlay();
            refresh();
        });
    }

}
