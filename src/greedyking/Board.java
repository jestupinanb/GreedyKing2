/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jaime
 */
public class Board extends JPanel implements ActionListener {
    private int scale = 4;
    private int unidadMapaOriginal = 16;
    private int unidadMapaGrande = 16*4;
    private int delay = 30;
    Personaje personaje;
    private Timer timer;
    int[] fondoInt1 = {5,6};
    
    
    public Board() {
        timer = new Timer(this.delay,this);
        this.personaje = new Personaje();
        setFocusable(true);
        addKeyListener(new EventosTeclado());
        timer.start();
    }
    
    private class EventosTeclado extends KeyAdapter {
        
        @Override
        public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_D:
                    personaje.moveRight();
                    break;
                case KeyEvent.VK_A:
                    personaje.moveLeft();
                    break;
                case KeyEvent.VK_W:
                    personaje.moveUP();
                    break;
                case KeyEvent.VK_S:
                    personaje.moveDown();
                    break;
            };
        };
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_D:
//                    personaje.moveRight();
                    break;
                case KeyEvent.VK_A:
//                    personaje.moveLeft();
                    break;
                case KeyEvent.VK_W:
//                    personaje.moveUP();
                    break;
                case KeyEvent.VK_S:
//                    personaje.moveDown();
                    break;
            };
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        mapa1(g);
        moverPj(g);
    };
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("hola");
        repaint();
    }
    
    public Image loadImage(String imageName){
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    };
    public void mapa1(Graphics g){
        int mapa[][][] ={
            {{5,6,10,3},{-1,-1,11,3},{-1,-1,11,3},{5,6,12,3},{-1,-1,5,6},{-1,-1,5,6},{-1,-1,5,6},{-1,-1,5,6},{-1,-1,5,6},{5,6,5,6},{5,6,3,1},{5,6,4,1},{5,6,5,1},{5,6,6,1},{-1,-1,5,6},{-1,-1,5,6},{-1,-1,5,6}},
            {{-1,-1,5,6},{5,6,0,4},{5,6,1,4},{5,6,2,4},{-1,-1,5,6},{-1,-1,5,6},{-1,-1,5,6},{-1,-1,5,6},{-1,-1,5,6},{5,6,2,2},{5,6,3,2},{5,6,4,2},{5,6,5,2},{-1,-1,5,6},{-1,-1,5,6},{-1,-1,5,6},{5,6,11,6}},
            {{-1,-1,5,6},{-1,-1,5,6},{5,6,1,5},{5,6,2,5},{-1,-1,5,6},{5,6,10,1},{-1,-1,11,1},{5,6,12,1},{-1,-1,5,6},{5,6,2,3},{5,6,3,3},{5,6,4,3},{-1,-1,5,6},{-1,-1,5,6},{5,6,11,6},{5,6,12,6},{-1,-1,11,9}},
            {{-1,-1,5,6},{-1,-1,5,6},{5,6,11,6},{5,6,12,6},{5,6,12,6},{12,7,10,2},{-1,-1,11,2},{5,6,12,2},{-1,-1,5,6},{5,6,7,2},{5,6,3,4},{5,6,7,2},{5,6,7,2},{5,6,11,6},{-1,-1,11,9},{-1,-1,12,7},{-1,-1,12,7}},
            {{-1,-1,5,6},{-1,-1,5,6},{5,6,11,7},{-1,-1,12,7},{-1,-1,12,7},{12,7,10,2},{-1,-1,11,2},{5,6,12,2},{5,6,7,2},{-1,-1,5,6},{5,6,3,5},{5,6,4,5},{-1,-1,5,6},{5,6,11,7},{-1,-1,12,7},{-1,-1,12,7},{12,7,10,1}},
            {{-1,-1,5,6},{-1,-1,5,6},{5,6,11,7},{-1,-1,12,7},{12,7,10,1},{-1,-1,11,1},{-1,-1,11,1},{-1,-1,11,1},{5,6,12,1},{-1,-1,5,6},{5,6,3,6},{5,6,4,6},{-1,-1,5,6},{12,7,10,1},{-1,-1,11,1},{-1,-1,11,1},{-1,-1,11,2}},
            {{-1,-1,8,0},{-1,-1,8,0},{-1,-1,8,0},{-1,-1,11,1},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{8,7,12,2},{-1,-1,8,7},{-1,-1,8,7},{-1,-1,8,7},{-1,-1,8,7},{8,7,10,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2}},
            {{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{8,8,12,2},{-1,-1,8,8},{-1,-1,8,8},{-1,-1,8,8},{-1,-1,8,8},{8,8,10,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2}}
        };
        Image fondo = loadImage("Tiles.png");
        
        for(int i=0;i<8;i++){
            for (int j=0; j<17; j++) {
                if(mapa[i][j][0]!=-1){
                g.drawImage(fondo,j*unidadMapaGrande,i*unidadMapaGrande,j*unidadMapaGrande+unidadMapaGrande,i*unidadMapaGrande+unidadMapaGrande,
                mapa[i][j][0]*unidadMapaOriginal,mapa[i][j][1]*unidadMapaOriginal,mapa[i][j][0]*unidadMapaOriginal+16,mapa[i][j][1]*unidadMapaOriginal+16, this);
                };
                g.drawImage(fondo,j*unidadMapaGrande,i*unidadMapaGrande,j*unidadMapaGrande+unidadMapaGrande,i*unidadMapaGrande+unidadMapaGrande,
                mapa[i][j][2]*unidadMapaOriginal,mapa[i][j][3]*unidadMapaOriginal,mapa[i][j][2]*unidadMapaOriginal+16,mapa[i][j][3]*unidadMapaOriginal+16, this);   
            };
        };
    };
    
    public void moverPj(Graphics g){
        g.drawRect(this.personaje.getPosicionX(), this.personaje.getPosicionY(), this.personaje.getUnidadAnchoRun(),this.personaje.getUnidadAltoRun());
        Rectangle carro = new Rectangle(this.personaje.getPosicionX(),this.personaje.getPosicionY(),this.personaje.getUnidadAnchoRun(),this.personaje.getUnidadAltoRun());
        g.drawImage(this.personaje.getPjImage(),this.personaje.getPosicionX(),this.personaje.getPosicionY(),this.personaje.getPosicionX()+this.personaje.getUnidadAnchoRun(),this.personaje.getPosicionY()+this.personaje.getUnidadAltoRun(),
                this.personaje.numeroDeImagenAncho(),this.personaje.numeroDeImagenAlto(),this.personaje.numeroDeImagenAncho()+this.personaje.getUnidadAnchoImagenOriginal(),this.personaje.numeroDeImagenAlto()+this.personaje.getUnidadAltoImagenOriginal(), this);//6,2
    };
}
