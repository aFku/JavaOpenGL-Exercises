package org.rcbg.afku.engine.listeners;

import org.lwjgl.glfw.GLFW;

public class KeyListener {
    private static KeyListener instance;
    private boolean keyPressed[] = new boolean[350];

    private KeyListener(){
    }

    public static void KeyCallback(long window, int key, int scancode, int action, int mods){
        if(action == GLFW.GLFW_PRESS) {
            getInstance().keyPressed[key] = true;
        } else if (action == GLFW.GLFW_RELEASE) {
            getInstance().keyPressed[key] = false;
        }
    }

    public static KeyListener getInstance(){
        KeyListener result = instance;
        if(result != null) {
            return result;
        }
        synchronized (KeyListener.class){
            if (instance == null) {
                KeyListener.instance = new KeyListener();
            }
            return instance;
        }
    }

    public static boolean isKeyPressed(int keyCode){
        if (keyCode < getInstance().keyPressed.length) {
            return getInstance().keyPressed[keyCode];
        } else {
            throw new IllegalArgumentException("You pressed not existing button");
        }
    }
}
