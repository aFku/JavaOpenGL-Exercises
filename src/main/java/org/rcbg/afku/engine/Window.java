package org.rcbg.afku.engine;

import org.lwjgl.glfw.Callbacks;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import org.rcbg.afku.engine.listeners.KeyListener;
import org.rcbg.afku.engine.listeners.MouseListener;

public class Window {

    private int width;
    private int height;
    private String title;

    private static Window window = null;

    private long glfwWindow; // Address where our window is stored

    private Window() {
        this.width = 1920;
        this.height = 1080;
        this.title = "Test title";
    }

    public static Window getInstance(){
        Window result = window;
        if(result != null) {
            return result;
        }
        synchronized (Window.class){
            if (window == null) {
                Window.window = new Window();
            }
            return window;
        }
    }

    public void run() {
        this.init();
        this.loop();

        // Free resources
        Callbacks.glfwFreeCallbacks(this.glfwWindow);
        GLFW.glfwDestroyWindow(this.glfwWindow);
        GLFW.glfwTerminate();
        GLFW.glfwSetErrorCallback(null).free();
    }

    private void init() {
        GLFWErrorCallback.createPrint(System.err).set();
        if( !GLFW.glfwInit() ){
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        GLFW.glfwDefaultWindowHints(); // Setup default parameters for window, hint = parameter
        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GL11.GL_FALSE); // Hide window before created
        GLFW.glfwWindowHint(GLFW.GLFW_RESIZABLE, GL11.GL_TRUE); // Allow resizing
        GLFW.glfwWindowHint(GLFW.GLFW_MAXIMIZED, GL11.GL_TRUE); // Start maximized

        this.glfwWindow = GLFW.glfwCreateWindow(this.width, this.height, this.title, MemoryUtil.NULL, MemoryUtil.NULL);
        if( glfwWindow == MemoryUtil.NULL) {
            throw new IllegalStateException("Failed to create the GLFW window");
        }

        // Mouse callbacks
        GLFW.glfwSetCursorPosCallback(this.glfwWindow, MouseListener::mousePosCallback);
        GLFW.glfwSetMouseButtonCallback(this.glfwWindow, MouseListener::mouseButtonCallback);
        GLFW.glfwSetScrollCallback(this.glfwWindow, MouseListener::mouseScrollCallback);

        // Key callbacks
        GLFW.glfwSetKeyCallback(this.glfwWindow, KeyListener::KeyCallback);

        GLFW.glfwMakeContextCurrent(this.glfwWindow);
        GLFW.glfwSwapInterval(1); // Enable V-sync

        GLFW.glfwShowWindow(this.glfwWindow);

        GL.createCapabilities(); // Enable bindings
    }

    private void loop() {
        float step = 0.01f;
        float r = 0.0f;
        float g = 0.0f;
        float b = 0.0f;
        while (!GLFW.glfwWindowShouldClose(this.glfwWindow)){
            GLFW.glfwPollEvents(); // Poll input events
            if(KeyListener.isKeyPressed(GLFW.GLFW_KEY_Q) && r < 1.0f){
                r += step;
            }
            if(KeyListener.isKeyPressed(GLFW.GLFW_KEY_A) && r > 0.0f){
                r -= step;
            }
            if(KeyListener.isKeyPressed(GLFW.GLFW_KEY_W) && g < 1.0f){
                g += step;
            }
            if(KeyListener.isKeyPressed(GLFW.GLFW_KEY_S) && g > 0.0f){
                g -= step;
            }
            if(KeyListener.isKeyPressed(GLFW.GLFW_KEY_E) && b < 1.0f){
                b += step;
            }
            if(KeyListener.isKeyPressed(GLFW.GLFW_KEY_D) && b > 0.0f){
                b -= step;
            }
            if(MouseListener.mouseButtonDown(GLFW.GLFW_MOUSE_BUTTON_1)){
                r = 0.0f;
                g = 0.0f;
                b = 0.0f;
            }
            GL11.glClearColor(r, g, b, 1.0f); // Set color buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT); // Use color buffer and flush it
            GLFW.glfwSwapBuffers(this.glfwWindow);
        }
    }
}
