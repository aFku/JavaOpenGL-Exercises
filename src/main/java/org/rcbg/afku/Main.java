package org.rcbg.afku;

import org.lwjgl.*;
import org.lwjgl.opengl.GL;

import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Version.getVersion());
        new GLFWInitializer().init();

        WindowBuilder builder = new WindowBuilder();
        Window myWindow = builder.withSizeMaximised().withTitle("Test Title").build();
        GL.createCapabilities();

        myWindow.showWindow();
        while (!myWindow.windowShouldClose()){
            myWindow.update();
            myWindow.setClearColor(100.0f, 0.0f, 0.0f, 1.0f);
        }
    }
}