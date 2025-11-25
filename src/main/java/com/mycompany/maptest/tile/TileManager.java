/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.maptest.tile;

import com.mycompany.maptest.GameConstants;
import com.mycompany.maptest.GamePane;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author User
 */
public class TileManager {
    private GamePane gp;
    private Tile tiles[];
    private int mapTileNum[][];
    
    public TileManager(GamePane gp) {
        this.gp = gp;
        tiles = new Tile[3];
        mapTileNum = new int[GameConstants.MAX_SCREEN_COL][GameConstants.MAX_SCREEN_ROW];
        
        getTileDetail();
        loadMap();
        
    }
    
    private void getTileDetail() {
        try {
            tiles[0] = new Tile();
            tiles[0].tileColor = new Color(115, 175, 111);
            
            tiles[1] = new Tile();
            tiles[1].tileColor = new Color(125, 141, 134);
            
            tiles[2] = new Tile();
            tiles[2].tileColor = new Color(158, 207, 212);
        } catch (Exception e) {
            System.out.println("Error!!!!");
            e.printStackTrace();
        }
    }
    
    public void loadMap() {
        try {
            InputStream input = getClass().getResourceAsStream("/maps/map1.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            
            int col =0, row = 0;
            while (col < GameConstants.MAX_SCREEN_COL && row < GameConstants.MAX_SCREEN_ROW) {
                String line = reader.readLine();
                
                while (col < GameConstants.MAX_SCREEN_COL) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == GameConstants.MAX_SCREEN_COL) {
                    col = 0;
                    row++;
                }
            }
            reader.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void draw(Graphics2D g2) {
        int col = 0, row = 0, x = 0, y = 0;
        g2.setColor(new Color(115, 175, 111));
        
        while (col < GameConstants.MAX_SCREEN_COL && row < GameConstants.MAX_SCREEN_ROW) {
            
            int tileNum = mapTileNum[col][row];
            g2.setColor(tiles[tileNum].tileColor);
            
            g2.fillRect(x, y, GameConstants.TILE_SIZE, GameConstants.TILE_SIZE);
            col++;
            x += GameConstants.TILE_SIZE;
            
            if (col == GameConstants.MAX_SCREEN_COL) {
                col = 0;
                x = 0;
                row++;
                y += GameConstants.TILE_SIZE;
            }
            
        }
    }
}
