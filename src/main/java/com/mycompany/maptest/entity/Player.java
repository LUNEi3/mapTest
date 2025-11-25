/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maptest.entity;

import com.mycompany.maptest.GameConstants;
import com.mycompany.maptest.GamePane;
import com.mycompany.maptest.KeyHandler;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 *
 * @author User
 */
public class Player extends Entity {
    private GamePane gp;
    private KeyHandler keyH;
    private BufferedImage right1, right2, left1, left2;
    private String direction;
    private int spriteCounter;
    private boolean swapSprite = false;
    
    public Player(GamePane gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        setDefaultValues();
        getPlayerImage();
    }
    
    private void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "right";
    }
    
    private void getPlayerImage() {
        try {
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed) {
                direction = "up";
                y -= speed;
            } else if (keyH.downPressed) {
                direction = "down";
                y += speed;
            } else if (keyH.leftPressed) {
                direction = "left";
                x -= speed;
            } else if (keyH.rightPressed) {
                direction = "right";
                x += speed;
            }

            spriteCounter ++;
            if (spriteCounter > 10) {
                swapSprite = !swapSprite;
                spriteCounter = 0;
            }
        }
    }
    
    public void draw(Graphics2D g2) {
        // g2.setColor(Color.red);
        // g2.fillRect(x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
        
        BufferedImage image = null;
        
        switch(direction) {
            case "up" -> {
                // System.out.println("Switch is running...");
                if (swapSprite) {
                    image = right2;
                } else {
                    image = left1;
                }
            }
                
            case "down" -> {
                if (swapSprite) {
                    image = right1;
                } else {
                    image = left2;
                }
            }
                
            case "left" -> {
                if (swapSprite) {
                    image = left1;
                } else {
                    image = left2;
                }
            }
                
            case "right" -> {
                if (swapSprite) {
                    image = right1;
                } else {
                    image = right2;
                }
            }   
        }
        
        g2.drawImage(image, x, y, (int) (GameConstants.TILE_SIZE * 1.5), (int) (GameConstants.TILE_SIZE * 1.5), null);
    }
}
