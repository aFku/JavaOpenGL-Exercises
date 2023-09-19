package org.rcbg.afku;

public interface Window {
    public void showWindow();
    public void setCurrentContextForThisWindow();
    public void maximiseWindow();
    public boolean windowShouldClose();
    public void update();
    public void setClearColor(float r, float g, float b, float a);
    public boolean isKeyPressed(int keycode);
    public void destroyWindow();
    public void changeTitle(String title);
}
