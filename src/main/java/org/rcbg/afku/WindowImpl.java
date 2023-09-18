package org.rcbg.afku;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;

public class WindowImpl implements Window{

    private final long windowHandle;
    private int width, height;
    private boolean resizable;
    private boolean vSyncEnabled;

    public WindowImpl(long windowHandle, int width, int height, boolean resizable, boolean vSync) {
        this.windowHandle = windowHandle;
        this.width = width;
        this.height = height;
        this.resizable = resizable;
        this.vSyncEnabled = vSync;
        initCallbacks();
        setWindowOnCenter();
        GLFW.glfwMakeContextCurrent(this.windowHandle);
        if(this.vSyncEnabled){
            GLFW.glfwSwapInterval(1);
        }
    }

    private void initCallbacks(){

        GLFW.glfwSetFramebufferSizeCallback(this.windowHandle, (window, width, height) -> {
            this.width = width;
            this.height = height;
            this.resizable = true;
        });

        GLFW.glfwSetKeyCallback(this.windowHandle, (window, key, scancode, action, mods) -> {
            if(key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_RELEASE){
                GLFW.glfwSetWindowShouldClose(window, true);
            }
        });
    }

    private void setWindowOnCenter(){
        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(this.windowHandle, (vidMode.width() - this.width) / 2, (vidMode.height() - this.height) / 2);
    }

    public void showWindow(){
        GLFW.glfwShowWindow(this.windowHandle);
    }

    public void setCurrentContextForThisWindow() {
        GLFW.glfwMakeContextCurrent(this.windowHandle);
    }
}
