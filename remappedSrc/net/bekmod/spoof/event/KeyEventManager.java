package net.bekmod.spoof.event;

import java.util.ArrayList;
import java.util.function.Consumer;

public class KeyEventManager {
    private ArrayList<Consumer<KeyEvent>> actions;
    private static KeyEventManager instance;

    public static KeyEventManager getInstance() {
        if(instance == null) instance = new KeyEventManager();
        return instance;
    }

    private KeyEventManager(){
        actions = new ArrayList<>();
    }

    public void registerEvent(Consumer<KeyEvent> action){
        actions.add(action);
    }
    public void fire(KeyEvent event){

        for (int i = 0; i < actions.size(); i++) {
            actions.get(i).accept(event);
        }
    }
}
