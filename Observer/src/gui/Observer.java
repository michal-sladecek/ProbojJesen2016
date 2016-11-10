/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author siegrift
 */
public class Observer extends javax.swing.JPanel {

    /**
     * Creates new form Observer
     */
    int data[][];
    int N, M;
    int PIXEL_SIZE = 50;
    ArrayList<Color> playerColors;
    ArrayList<ArrayList<Integer> > playerHeads;
    final int UNDEFINED = -1;
    private int HEAD = 1000;

    public Observer() {
        initComponents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.setColor(Color.GRAY);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        PIXEL_SIZE = Math.min(getHeight() / N, getWidth()/ M);
        int xoffset = (getWidth() - N*PIXEL_SIZE) / 2;
        int yoffset = (getHeight() - M*PIXEL_SIZE) / 2;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (data[i][j] == UNDEFINED) {
                    g.setColor(Color.WHITE);
                } else if(data[i][j] >= HEAD){
                    g.setColor(playerColors.get(data[i][j] - HEAD).darker().darker().darker());
                }else {
                    g.setColor(playerColors.get(data[i][j]));
                }
                g.fillRect(xoffset + i * PIXEL_SIZE, yoffset + j * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    synchronized void setData(int[][] whoseArea, int[][] whoseSnake, ArrayList<ArrayList<Integer> > heads) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (whoseSnake[i][j] == UNDEFINED) {
                    data[i][j] = whoseArea[i][j];
                } else {
                    data[i][j] = whoseSnake[i][j];
                }
                //if(data[i][j] != -1)System.out.println(data[i][j] + " " +i +" "+j);
            }
        }
        playerHeads = heads;
        for (int i = 0; i < playerHeads.size(); i++) {
            ArrayList<Integer> l = playerHeads.get(i);
            data[l.get(0)][l.get(1)] = HEAD+i;
        }
    }

    void initData(ArrayList<Color> cols, int n, int m) {
        playerColors = cols;
        data = new int[n][m];
        N = n;
        M = m;
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
