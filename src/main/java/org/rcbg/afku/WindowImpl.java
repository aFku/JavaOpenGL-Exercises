package org.rcbg.afku;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL11;

public class WindowImpl implements Window{

    private final long windowHandle;
    private int width, height;
    private boolean resizable;
    private boolean vSyncEnabled;

    public WindowImpl(long windowHandle, int width, int height, boolean vSync) {
        this.windowHandle = windowHandle;
        this.width = width;
        this.height = height;
        this.resizable = false;
        this.vSyncEnabled = vSync;
        initCallbacks();
        if(height == 0 || width == 0){
            maximiseWindow();
        } else {
            setWindowOnCenter();
        }
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
                GLFW.glfwSetWindowShouldClose(this.windowHandle, true);
            }
        });
    }

    public void maximiseWindow(){
        GLFW.glfwMaximizeWindow(this.windowHandle);
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

    public boolean windowShouldClose(){
        return GLFW.glfwWindowShouldClose(this.windowHandle);
    }

    public void destroyWindow() {
        GLFW.glfwDestroyWindow(this.windowHandle);
    }

    public void update() {
        GLFW.glfwSwapBuffers(this.windowHandle);
        GLFW.glfwPollEvents();
    }

    public void setClearColor(float r, float g, float b, float a){
        GL11.glClearColor(r, g, b, a);
    }

    public boolean isKeyPressed(int keycode){
        return GLFW.glfwGetKey(this.windowHandle, keycode) == GLFW.GLFW_PRESS;
    }

    public void changeTitle(String title){
        GLFW.glfwSetWindowTitle(this.windowHandle, title);
    }
}
