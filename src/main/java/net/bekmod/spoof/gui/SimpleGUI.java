package net.bekmod.spoof.gui;

import io.github.cottonmc.cotton.gui.client.LightweightGuiDescription;
import io.github.cottonmc.cotton.gui.widget.WButton;
import io.github.cottonmc.cotton.gui.widget.WGridPanel;
import io.github.cottonmc.cotton.gui.widget.WLabel;
import io.github.cottonmc.cotton.gui.widget.WTextField;
import net.bekmod.spoof.MainMod;
import net.bekmod.spoof.entity.solutions.Result;
import net.bekmod.spoof.service.MathHelper;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class SimpleGUI extends LightweightGuiDescription {

    private WGridPanel root;
    private WLabel info;
    private WButton switchOverlayButton;
    private WButton switchMPButton; //message processor

    private WTextField inputFieldCost;
    private WLabel costL;
    private WTextField inputFieldAmount;
    private WLabel amountL;
    private WTextField inputFieldFee;
    private WLabel feeL;
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
            costL = new WLabel(Text.literal(" cost:").setStyle(Style.EMPTY.withColor(0xFF_474747)));
        inputFieldAmount = new WTextField();
            amountL = new WLabel(Text.literal(" amount:").setStyle(Style.EMPTY.withColor(0xFF_474747)));
        inputFieldFee = new WTextField();
            feeL = new WLabel(Text.literal(" fee:").setStyle(Style.EMPTY.withColor(0xFF_474747)));
        calculate = new WButton(Text.literal("=").setStyle(Style.EMPTY.withBold(true))); //new LiteralTextContent("=").setStyle(Style.EMPTY.withBold(true))
        solution = new WLabel(Text.empty());

        switchOverlayButton = new WButton();
        switchMPButton = new WButton();

    }

    private void registerItems() {

        root.add(info, 0, 0, 3, 1);
        root.add(switchOverlayButton, 10, 1, 5, 1);
        root.add(switchMPButton, 5, 1, 5, 1);

        root.add(inputFieldCost  , 0,3, 3, 1);
            root.add(costL, 0,2, 3, 1); // label
        root.add(inputFieldFee   , 4,3, 3, 1);
            root.add(feeL   , 4,2, 3, 1); // label
        root.add(inputFieldAmount, 8,3, 3, 1);
            root.add(amountL, 8,2, 3, 1); //label
        root.add(calculate       , 11,3, 1, 1);
        root.add(solution        , 12,3, 2, 1);
    }

    private void refresh() {
        info.setText(Text.of(MainMod.getInstance().getMessage()));
        switchOverlayButton.setLabel(Text.of("HUD: " + (MainMod.getInstance().isSwitchOverlay() ? "ON" : "OFF")));
        switchMPButton.setLabel(Text.of("Chat Proc: " + (MainMod.getInstance().isMessageProcessorOn() ? "ON" : "OFF")));

        if (isAnswer)
            solution.setText(Text.literal(" " + answer + "$").setStyle(Style.EMPTY.withColor(Formatting.GREEN).withBold(true)));
    }

    private void setEvents() {
        switchOverlayButton.setOnClick(() -> {
            MainMod.getInstance().setSwitchOverlay();
            refresh();
        });
        switchMPButton.setOnClick(() -> {
            MainMod.getInstance().switchMessageProc();
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
