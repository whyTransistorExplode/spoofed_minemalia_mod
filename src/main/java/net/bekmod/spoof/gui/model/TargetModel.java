package net.bekmod.spoof.gui.model;

import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.bekmod.spoof.entity.Envoy;

public class TargetModel extends WPlainPanel {

    private Envoy envoy;



    public TargetModel(){
        setSize(100, 50);
        layout();
    }

    public void takeEnvoy(Envoy envoy) {
        this.envoy = envoy;

    }
}
