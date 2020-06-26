package com.sndk2;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Dimension;

public class Main extends JFrame implements Runnable{

    public static final int WIDTH = 1280;
    public static final int HEIGHT = 720;

    private boolean isRunning = true;
    private Thread thread;
    private Wator panel;

    private long lastLoopTime = System.nanoTime();
    private long currentTime;
    private double deltaTime;

    public static void main(String[] args){
        new Main();
    }

    public Main(){
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
        this.setVisible(true);
    }

    private void displayWator(){
        Wator wator  = new Wator();
        wator.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel = wator;
        this.add(wator);
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
            deltaTime += (currentTime - lastLoopTime) / 30.0f;
            lastLoopTime = currentTime;
            while (deltaTime >= 1){
                update(deltaTime);
                deltaTime--;
            }
            render();
        }
    }

    private void update(double deltaTime){
        panel.update(deltaTime);
    }

    private void render(){
        this.repaint();
    }
}
