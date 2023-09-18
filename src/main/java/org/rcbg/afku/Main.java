package org.rcbg.afku;

import org.lwjgl.*;
import org.lwjgl.opengl.GL;

public class Main {
    public static void main(String[] args) {
        System.out.println(Version.getVersion());
        new GLFWInitializer().init();

        WindowBuilder builder = new WindowBuilder();
        Window myWindow = builder.withHeight(400).withWidth(400).withTitle("Test Title").resizable(false).build();
        myWindow.showWindow();
        Window myWindow2 = builder.withHeight(400).withWidth(400).withTitle("Test Title2").resizable(false).build();
        myWindow2.showWindow();
        GL.createCapabilities();
    }
}