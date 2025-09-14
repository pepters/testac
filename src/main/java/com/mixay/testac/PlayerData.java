package com.mixay.testac;

import java.util.HashMap;
import java.util.UUID;

public class PlayerData {
    public boolean banqueued = false;
    public boolean ismitigated = false;
    private boolean swung = false;
    public String nick;
    public HashMap<CheckType, Integer> failed = new HashMap<>();
    public PlayerData (String name) {
        this.nick = name;
    }
    public void startswing () {
        swung = true;
    }
    public void finishswing () {
        swung = false;
    }
    public boolean hasSwung () {
        return swung;
    }
}
