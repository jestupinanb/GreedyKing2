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
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jaime
 */
public class Board extends JPanel implements ActionListener {
    private int unidadMapa = 16;
    private int delay = 250;
    Personaje personaje = new Personaje();
    private Timer timer;
    
    
    public Board() {
        timer = new Timer(this.delay,this);
        timer.start();
    }
    
    @Override
    public void paintComponent(Graphics g){
//       System.out.println("esakdmpaaa");
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        mapa1(g);
        personaje.moverPersonaje(g);
    };
    
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("hola");
        personaje.actionPerformed(e);
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
            {{-1,-1,5,6},{-1,-  1,5,6},{5,6,1,5},{5,6,2,5},{-1,-1,5,6},{5,6,10,1},{-1,-1,11,1},{5,6,12,1},{-1,-1,5,6},{5,6,2,3},{5,6,3,3},{5,6,4,3},{-1,-1,5,6},{-1,-1,5,6},{5,6,11,6},{5,6,12,6},{-1,-1,11,9}},
            {{-1,-1,5,6},{-1,-1,5,6},{5,6,11,6},{5,6,12,6},{5,6,12,6},{12,7,10,2},{-1,-1,11,2},{5,6,12,2},{-1,-1,5,6},{5,6,7,2},{5,6,3,4},{5,6,7,2},{5,6,7,2},{5,6,11,6},{-1,-1,11,9},{-1,-1,12,7},{-1,-1,12,7}},
            {{-1,-1,5,6},{-1,-1,5,6},{5,6,11,7},{-1,-1,12,7},{-1,-1,12,7},{12,7,10,2},{-1,-1,11,2},{5,6,12,2},{5,6,7,2},{-1,-1,5,6},{5,6,3,5},{5,6,4,5},{-1,-1,5,6},{5,6,11,7},{-1,-1,12,7},{-1,-1,12,7},{12,7,10,1}},
            {{-1,-1,5,6},{-1,-1,5,6},{5,6,11,7},{-1,-1,12,7},{12,7,10,1},{-1,-1,11,1},{-1,-1,11,1},{-1,-1,11,1},{5,6,12,1},{-1,-1,5,6},{5,6,3,6},{5,6,4,6},{-1,-1,5,6},{12,7,10,1},{-1,-1,11,1},{-1,-1,11,1},{-1,-1,11,2}},
            {{-1,-1,8,0},{-1,-1,8,0},{-1,-1,8,0},{-1,-1,11,1},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{8,7,12,2},{-1,-1,8,7},{-1,-1,8,7},{-1,-1,8,7},{-1,-1,8,7},{8,7,10,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2}},
            {{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2},{8,8,12,2},{-1,-1,8,8},{-1,-1,8,8},{-1,-1,8,8},{-1,-1,8,8},{8,8,10,2},{-1,-1,11,2},{-1,-1,11,2},{-1,-1,11,2}}
        };
        Image fondo = loadImage("Tiles.png");
        for(int i=0;i<8;i++){
            for (int j=0; j<17; j++) {
                if(mapa[i][j][0]!=-1)
                    g.drawImage(fondo,j*unidadMapa,i*unidadMapa,j*unidadMapa+16,i*unidadMapa+16,
                mapa[i][j][0]*unidadMapa,mapa[i][j][1]*unidadMapa,mapa[i][j][0]*unidadMapa+16,mapa[i][j][1]*unidadMapa+16, this);
                
                g.drawImage(fondo,j*unidadMapa,i*unidadMapa,j*unidadMapa+16,i*unidadMapa+16,
                mapa[i][j][2]*unidadMapa,mapa[i][j][3]*unidadMapa,mapa[i][j][2]*unidadMapa+16,mapa[i][j][3]*unidadMapa+16, this);   
            };
        };
    };
    
    public class Personaje extends JPanel implements ActionListener{
        private int unidadAnchoRun = 17;
        private int unidadAltoRun = 22;
        private Graphics g;
        private int posicionX = 8;
        private int posicionY = 75;
        private Image pj;
        
        public Personaje(){
            this.pj = loadImage("Animacion Pj.png");
        };
        public void moverPersonaje(Graphics g){
//            g.drawRect(0+posicionX, 0+posicionY, unidadAnchoRun, unidadAnchoRun);
//            Rectangle carro = new Rectangle(0+posicionX,0+posicionY,unidadAnchoRun,unidadAnchoRun);
            this.g = g;
            g.drawImage(pj,posicionX,posicionY,posicionX+unidadAnchoRun,posicionY+unidadAltoRun,
                6,2,6+unidadAnchoRun,2+unidadAltoRun, this);
        };
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            System.out.println("Pos x= "+posicionX);
            this.posicionX +=1;
        }
    };
}
