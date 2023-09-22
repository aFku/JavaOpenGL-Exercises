package org.rcbg.afku;

import org.lwjgl.*;
import org.lwjgl.opengl.GL;
import org.rcbg.afku.engine.Window;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Window window = Window.getInstance();
        window.run();
    }
}