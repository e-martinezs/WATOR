package com.sndk2.panels;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MainWindow extends JFrame implements Runnable, KeyListener {

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private boolean isRunning = true;
    private Thread thread;
    private CustomPanel panel;

    private long lastLoopTime = System.nanoTime();
    private long currentTime;
    private double deltaTime;

    public MainWindow(){
        displayWindow();
        displayWator();
        start();
    }

    private void displayWindow(){
        this.setTitle("WATOR");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.addKeyListener(this);
        this.setVisible(true);
    }

    private void displayWator(){
        this.setVisible(false);
        WaTorPanel wator  = new WaTorPanel();
        wator.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel = wator;
        this.add(wator);
        this.repaint();
        this.setVisible(true);
    }

    private void start(){
        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    private void stop(){
        thread = null;
        isRunning = false;
    }

    @Override
    public void run(){
        while (isRunning){
            currentTime = System.nanoTime();
            deltaTime = (currentTime - lastLoopTime)/100000.0f;
            try {
                Thread.sleep(10);
                update(deltaTime);
                render();
            } catch (Exception e){
                e.printStackTrace();
            }
            lastLoopTime = currentTime;
        }
    }

    private void update(double deltaTime){
        panel.update(deltaTime);
    }

    private void render(){
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        panel.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        panel.keyReleased(e);
    }
}
