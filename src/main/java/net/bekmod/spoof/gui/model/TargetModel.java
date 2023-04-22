package net.bekmod.spoof.gui.model;

import io.github.cottonmc.cotton.gui.client.BackgroundPainter;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WPlainPanel;
import net.bekmod.spoof.entity.Envoy;
import net.minecraft.item.CompassItem;
import net.minecraft.text.Text;

public class TargetModel extends WPlainPanel {

    private Envoy envoy;
    private final int SIZE_X = 100;
    private final int SIZE_Y = 60;

    private WButton removeButton;
    private WLabel label;
    private WLabel label2;



    public TargetModel(){
        setSize(SIZE_X, SIZE_Y);
        layout();
        BackgroundPainter backgroundPainter = BackgroundPainter.createColorful(10);
        this.setBackgroundPainter(backgroundPainter);
        removeButton = new WButton(Text.of("-"));
        label = new WLabel(Text.of(""));
        label2 = new WLabel(Text.of(""));

        this.add(label, 0, 0, 85, 30);
        this.add(label2, 0, 0, 85, 30);
        this.add(removeButton, 85, 0, 15, 60);

    }

    public void takeEnvoy(Envoy envoy) {
        this.envoy = envoy;
        setFields();
    }

    private void setFields(){
        label.setText(Text.of(" envoy x=" + envoy.getCoordX() + " y=" + envoy.getCoordY() +
                " z=" + envoy.getCoordZ()));
        label2.setText(Text.of(" distance = " +  envoy.getProximity() + "blocks"));
    }


}
