package org.rcbg.afku;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

public class WindowBuilder {

    private String title;
    private int width;
    private int height;
    private boolean vSyncEnabled;

    public WindowBuilder() {
        reset();
    }

    public void reset(){
        this.title = "default";
        this.width = 0;
        this.height = 0;
        this.vSyncEnabled = false;
    }

    public WindowBuilder withTitle(String title) {
        this.title = title;
        return this;
    }

    public WindowBuilder withSize(int width, int height) {
        this.width = width;
        this.height = height;
        return this;
    }

    public WindowBuilder withEnabledVSync(boolean vSync){
        this.vSyncEnabled = vSync;
        return this;
    }

    public WindowBuilder withSizeMaximised(){
        this.width = 100;
        this.height = 100;
        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GLFW.GLFW_TRUE);
        return this;
    }

    public Window build(){
        long window = GLFW.glfwCreateWindow(this.width, this.height, this.title, MemoryUtil.NULL, MemoryUtil.NULL);
        if (window == MemoryUtil.NULL) {
            throw new RuntimeException("Cannot create window correctly");
        }

        return new WindowImpl(window, this.width, this.height, this.vSyncEnabled);
    }
}
