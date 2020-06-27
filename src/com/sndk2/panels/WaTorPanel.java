package com.sndk2.panels;

import com.sndk2.entities.World;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class WaTorPanel extends CustomPanel {

    private BufferedImage buffer;
    private Graphics2D g2d;
    private World world;
    private int chrononStep = 1;

    private float cameraX;
    private float cameraY;
    private float cameraSpeed = 0.1f;

    private boolean rightKey;
    private boolean leftKey;
    private boolean upKey;
    private boolean downKey;

    public WaTorPanel(){
        createWorld();
        createBuffer();
    }

    private void createWorld(){
        world = new World(5, 5, 10.0f, 100.0f);
    }

    private void createBuffer(){
        buffer = new BufferedImage(world.getCols()*World.CELL_SIZE, world.getRows()*World.CELL_SIZE, BufferedImage.TYPE_INT_ARGB);
        g2d = buffer.createGraphics();
    }

    private void nextStep(){
        for (int i=0; i<chrononStep; i++){
            world.update();
        }
    }

    @Override
    public void update(double delta){
        updateCamera(delta);
    }

    private void updateCamera(double delta){
        if (rightKey){
            cameraX += cameraSpeed*delta;
        } else if (leftKey){
            cameraX -= cameraSpeed*delta;
        }
        if (upKey){
            cameraY -= cameraSpeed*delta;
        } else if (downKey){
            cameraY += cameraSpeed*delta;
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, MainWindow.WIDTH, MainWindow.HEIGHT);
        world.render(g2d);
        g.drawImage(buffer, (int)-cameraX , (int)-cameraY, this);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightKey = true;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            leftKey = true;
        } else if (e.getKeyCode() == KeyEvent.VK_UP){
            upKey = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            downKey = true;
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE){
            nextStep();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT){
            rightKey = false;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT){
            leftKey = false;
        } else if (e.getKeyCode() == KeyEvent.VK_UP){
            upKey = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN){
            downKey = false;
        }
    }
}
