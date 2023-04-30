package net.bekmod.spoof.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import net.bekmod.spoof.MainMod;
import net.bekmod.spoof.entity.solutions.Result;
import net.bekmod.spoof.service.MathHelper;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SimpleGUI extends LightweightGuiDescription {

    private WGridPanel root;
    private WLabel info;
    private WButton switchOverlayButton;

    private WTextField inputFieldCost;
    private WTextField inputFieldAmount;
    private WTextField inputFieldFee;
    private WButton calculate;
    private WLabel solution;


    private boolean isAnswer;
    private String answer;

    private static SimpleGUI instance = null;

    public static SimpleGUI getInstance() {
        if (instance == null) instance = new SimpleGUI();
        instance.refresh();
        return instance;
    }

    private SimpleGUI() {
        root = new WGridPanel(20);
        root.setSize(320, 240);

        setRootPanel(root);

        initialize();
        registerItems();
        setEvents();
    }

    private void initialize() {
        info = new WLabel(Text.of(""));
        info.setSize(200, 70);

        inputFieldCost = new WTextField();
        inputFieldAmount = new WTextField();
        inputFieldFee = new WTextField();
        calculate = new WButton(new LiteralText("=").setStyle(Style.EMPTY.withBold(true)));
        solution = new WLabel("");

        switchOverlayButton = new WButton();

    }

    private void registerItems() {

        root.add(info, 0, 0, 3, 1);
        root.add(switchOverlayButton, 10, 1, 5, 1);

        root.add(inputFieldCost  , 0,3, 3, 1);
        root.add(inputFieldFee   , 4,3, 3, 1);
        root.add(inputFieldAmount, 8,3, 3, 1);
        root.add(calculate       , 11,3, 1, 1);
        root.add(solution        , 12,3, 2, 1);
    }

    private void refresh() {
        info.setText(Text.of(MainMod.getInstance().getMessage()));
        switchOverlayButton.setLabel(Text.of("switched: " + (MainMod.getInstance().isSwitchOverlay() ? "ON" : "OFF")));

        if (isAnswer)
            solution.setText(new LiteralText(" " + answer + "$").setStyle(Style.EMPTY.withColor(Formatting.GREEN).withBold(true)));
    }

    private void setEvents() {
        switchOverlayButton.setOnClick(() -> {
            MainMod.getInstance().setSwitchOverlay();
            refresh();
        });

        calculate.setOnClick(()-> {
           if (inputFieldFee.getText().length() > 0 && inputFieldAmount.getText().length() > 0){
               String amount = inputFieldAmount.getText().length()>0?inputFieldAmount.getText():"1";
               Result result = MathHelper.calculateText(
                       "("+inputFieldFee.getText() +"+"+ inputFieldCost.getText() + ")*" + amount);
               if(result.isSuccess()) {
                   isAnswer = true;
                   answer = String.valueOf(result.getNumber().getNumber());
                   refresh();
               }
           }
        });
    }

}
