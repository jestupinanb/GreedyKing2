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
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Jaime
 */
public class Board extends JPanel implements ActionListener {
    private int scale = 4;//El tamaño al que se aumenta el juego 1 = originial, 2 = al doble de grande, 3 = triple de grande etc...
    private int unidadMapaOriginal = 16;//El tile del mapa esta dividido en cuadros de 16x16
    private int unidadMapaGrande = 16*scale;//Tamaño que va a terner el juego al ejecutarse
    private int delay = 50;
    Personaje personaje;//Crear un personaje
    private int moverMapa = 0;//Dependiendo del numero que tenga mueve esa cantidad de columnas la matriz 
    private Timer timer;
    Rectangle personajeColision;
    Rectangle personajeColisionFuturo;
    ArrayList<MapaColision> colisionMovY = new ArrayList();
    ArrayList<MapaColision> colisionMovX = new ArrayList();
    ArrayList<MapaColision> colisionMovXY = new ArrayList();
    boolean elPersonajeSeMovioParaAbajo;
    boolean inicioJuego = true;
    
    public Board() {
        this.personaje = new Personaje(scale);
        timer = new Timer(this.delay,this);
        setFocusable(true);
        addKeyListener(new EventosTeclado());
        timer.start();
    }
    
    private class EventosTeclado extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e){
            elPersonajeSeMovioParaAbajo = false;
            int key = e.getKeyCode();
            boolean colisionoBoolean = false;
            switch (key){
                case KeyEvent.VK_D:
                    for(MapaColision colisiono:colisionMovX)
                        if(personajeColision.intersects(colisiono.getColisionBloque())){;
                            colisionoBoolean=true;
                        };
                        for(MapaColision colisiono:colisionMovXY){
                            if(personajeColision.intersects(colisiono.getColisionBloquexyRight())){
                                colisionoBoolean=true;
                            };
                        };
                        
                        if(colisionoBoolean!=true){
                            personaje.moveRight();
                        }else{
                            personaje.moveLeft();
                        };
                    break;
                case KeyEvent.VK_A:
                    for(MapaColision colisiono:colisionMovX)
                        if(personajeColision.intersects(colisiono.getColisionBloque())){;
                            colisionoBoolean=true;
                        };
                        for(MapaColision colisiono:colisionMovXY){
                            if(personajeColision.intersects(colisiono.getColisionBloquexyLeft())){
                                colisionoBoolean=true;
                            };
                        };
                        if(colisionoBoolean!=true){
                            personaje.moveLeft();
                        }else{
                            personaje.moveRight();
                        };
                    break;
                case KeyEvent.VK_W:
                    for(MapaColision colisiono:colisionMovY){
                        if(personajeColision.contains(colisiono.getColisionBloque())){
                                colisionoBoolean=true;
                            };
                        };
                        for(MapaColision colisiono:colisionMovXY){
                            if(personajeColision.intersects(colisiono.getColisionBloquexyDown())){
                                colisionoBoolean=true;
                            };
                        };
                        if(colisionoBoolean!=true){
                            personaje.moveUP();
                        }else{
                            personaje.moveDown();
                        };
                    break;
                case KeyEvent.VK_S:
                    personaje.moveDown();
                    elPersonajeSeMovioParaAbajo = true;
                    repaint();
                    break;
            };
        };
        public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_D:
                    personaje.sinMovimiento();
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
        boolean colisionooBoolean = false;
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        mapa1(g);//Crear el mapa, se encarga de pintarlo
        moverPj(g,inicioJuego);
        if(inicioJuego==true)inicioJuego=false;
        if(elPersonajeSeMovioParaAbajo==true){
            for(MapaColision colisiono:colisionMovY){
                        if(personajeColision.intersects(colisiono.getColisionBloque())){
                            colisionooBoolean=true;
                        };
                    }
            for(MapaColision colisiono:colisionMovXY){
                            if(personajeColision.intersects(colisiono.getColisionBloque())){
                                colisionooBoolean=true;
                            };
            };
            if(colisionooBoolean==true){
                System.out.println("Posicion pj"+personaje.getPosicionY());
                System.out.println("Colisiono "+colisionooBoolean);
                personaje.moveUP();
                System.out.println("Posicion pj"+personaje.getPosicionY());
                moverPj(g,true);
            }else{
                moverPj(g,true);
            };
        };
    };
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        repaint();
    }
    
    public Image loadImage(String imageName){
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    };
    
    public void mapa1(Graphics g){
        int[] fnul = {-1,-1,0};//SinFondo
        int[] f107 = {5,6,0};//fondoVerde
        
        int[] f027 = {10,1,3};//BloqueEsquinaArribaIzquierda
        int[] f028 = {11,1,1};//BloqueBaseArriba
        int[] f029 = {12,1,3};//BloqueEsquinaArribaDerecha
        int[] f041 = {7,2,3};//BloqueSolido
        
        int[] f061 = {10,3,3};//BloqueEsquinaAbajoIzquierda
        int[] f062 = {11,3,1};//BloqueBaseAbajo
        int[] f063 = {12,3,3};//BloqueEsquinaAbajoDerecha
        
        int[] f127 = {8,7,3};//MarBurbujas
        int[] f144 = {8,8,0};//MarOscuro  
        
        int[] f113 = {11,6,0};//FondoPastoArribaIzquierda
        int[] f114 = {12,6,0};//FondoPastoArribaBase
        int[] f115 = {13,6,0};//FondoPastoArribaIzquierda
        int[] f164 = {11,9,0};//FondoUnionPasto
        
        //Arbol
        int[] f020 = {3,1,0};
        int[] f021 = {4,1,0};
        int[] f022 = {5,1,0};
        int[] f023 = {6,1,0};
        int[] f036 = {2,2,0};
        int[] f037 = {3,2,0};
        int[] f038 = {4,2,0};
        int[] f039 = {5,2,0};
        int[] f053 = {2,3,0};
        int[] f054 = {3,3,0};
        int[] f055 = {4,3,0};
        int[] f056 = {5,3,0};
        int[] f071 = {3,4,0};
        int[] f072 = {4,4,0};
        int[] f073 = {5,4,0};
        int[] f088 = {3,5,0};
        int[] f089 = {4,5,0};
        int[] f105 = {3,6,0};
        int[] f106 = {4,6,0};
        int[] f122 = {3,7,0};
        int[] f123 = {4,7,0};
        int[] f138 = {2,8,0};
        int[] f139 = {3,8,0};
        int[] f140 = {4,8,0};
        
        int[] f017 = {0,1,0};
        int[] f018 = {1,1,0};
        int[] f35 = {1,2,0};
        
        int[] f068 = {0,4,0};int[] f069 = {1,4,0};int[] f070 = {2,4,0};
        int[] f086 = {1,5,0};int[] f087 = {2,5,0};
        
        int[] f008 = {8,0,1};int[] f009 = {9,0,1};
        int[] f130 = {11,7,0};int[] f131 = {12,7,0};//int[] f = {,};
        int[] f044 = {10,2,2};int[] f045 = {11,2,0};int[] f046 = {12,2,2};
        
//        System.out.println(f041);
        /*
        int mapaMejorado[][][][] = {
            {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}},
            {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}},
            {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}},
            {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}},
            {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}},
            {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}},
            {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}},
            {{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{},{}},
            {{fnull,0},{fnull,1},{fnull,2},{fnull,3},{fnull,4},{fnull,5},{fnull,6},{fnull,7},{fnull,8},{fnull,9},{fnull,10},{fnull,11},{fnull,12},{fnull,13},{fnull,14},{fnull,15},{fnull,16}},
        };
        */
        
        int mapa[][][][] = {
            //    0          1              2          3        4            5             6           7          8           9           10         11          12          13         14           15           16
            {{f107,f061},{fnul,f062},{fnul,f062},{f107,f063},{fnul,f107},{fnul,f107},{fnul,f107},{fnul,f107},{fnul,f107},{fnul,f107},{f107,f020},{f107,f021},{f107,f022},{f107,f023},{fnul,f107},{fnul,f107},{fnul,f107},{fnul,f107}},//0
            {{fnul,f107},{f107,f068},{f107,f069},{f107,f070},{fnul,f107},{fnul,f107},{fnul,f107},{fnul,f107},{fnul,f107},{f107,f036},{f107,f037},{f107,f038},{f107,f039},{fnul,f107},{fnul,f107},{fnul,f107},{f107,f113},{fnul,f107}},//1
            {{fnul,f107},{fnul,f107},{f107,f086},{f107,f087},{fnul,f107},{f107,f027},{fnul,f028},{f107,f029},{fnul,f107},{f107,f053},{f107,f054},{f107,f055},{fnul,f107},{fnul,f107},{f107,f113},{f107,f114},{fnul,f164},{fnul,f107}},//2
            {{fnul,f107},{fnul,f107},{f107,f113},{f107,f114},{f107,f114},{f114,f044},{fnul,f045},{f107,f046},{fnul,f107},{f107,f041},{f107,f071},{f107,f041},{f107,f041},{f107,f113},{fnul,f164},{fnul,f131},{fnul,f131},{fnul,f107}},//3
            {{fnul,f107},{fnul,f107},{f107,f130},{fnul,f131},{fnul,f131},{f131,f044},{fnul,f045},{f107,f046},{f107,f041},{fnul,f107},{f107,f088},{f107,f106},{fnul,f107},{f107,f130},{fnul,f131},{fnul,f131},{f131,f027},{fnul,f107}},//4
            {{fnul,f107},{fnul,f107},{f107,f130},{fnul,f131},{f131,f027},{fnul,f028},{fnul,f028},{fnul,f028},{f107,f029},{fnul,f107},{f107,f105},{f107,f106},{fnul,f107},{f107,f027},{fnul,f028},{fnul,f028},{fnul,f045},{fnul,f107}},//5
            {{fnul,f008},{fnul,f008},{fnul,f009},{fnul,f028},{fnul,f045},{fnul,f045},{fnul,f045},{fnul,f045},{f127,f046},{fnul,f127},{fnul,f127},{fnul,f127},{fnul,f127},{f127,f044},{fnul,f045},{fnul,f045},{fnul,f045},{fnul,f107}},//6
            {{fnul,f045},{fnul,f045},{fnul,f045},{fnul,f045},{fnul,f045},{fnul,f045},{fnul,f045},{fnul,f045},{f144,f046},{fnul,f144},{fnul,f144},{fnul,f144},{fnul,f144},{f144,f044},{fnul,f045},{fnul,f045},{fnul,f045},{fnul,f107}},//7
        };
        
        Image fondo = loadImage("Tiles.png");
//        System.out.println("f 0 0 0 0 "+mapa[6][0][0][0]);
        for(int i=0;i<8;i++){
            for (int j=0; j<17; j++) {//Largo
                if(mapa[i][j+moverMapa][0][0]!=-1){//En caso de que haya fondo se ejecuta
                  //Dibuja el fondo de la imagen g.drawImage(Imagen que va, posicion en pixeles de la columna donde empieza, posicion en pixeles de la fila donde empieza, posicion en pixeles de la columna donde termina, posicion en pixeles de la fila donde termina,
                  //Esta parte se encarga de recotar la imagen original... posicion de la columna, posicion de la fila, posicion final de la columna,posicion final de la fila, this)
                g.drawImage(fondo,j*unidadMapaGrande,i*unidadMapaGrande,j*unidadMapaGrande+unidadMapaGrande,i*unidadMapaGrande+unidadMapaGrande,
                mapa[i][j+moverMapa][0][0]*unidadMapaOriginal,mapa[i][j+moverMapa][0][1]*unidadMapaOriginal,mapa[i][j+moverMapa][0][0]*unidadMapaOriginal+16,mapa[i][j+moverMapa][0][1]*unidadMapaOriginal+16, this);
                };
                
                g.drawImage(fondo,j*unidadMapaGrande,i*unidadMapaGrande,j*unidadMapaGrande+unidadMapaGrande,i*unidadMapaGrande+unidadMapaGrande,
                mapa[i][j+moverMapa][1][0]*unidadMapaOriginal,mapa[i][j+moverMapa][1][1]*unidadMapaOriginal,mapa[i][j+moverMapa][1][0]*unidadMapaOriginal+16,mapa[i][j+moverMapa][1][1]*unidadMapaOriginal+16, this);   
                
                agregarColision(mapa[i][j+moverMapa][1][2],j+moverMapa,i,this.scale,g);
            };
        };
        
        agregarColision(3,2,2,this.scale,g);
//        this.colisionMovY.add(new MapaColision(3,5,this.scale,g));
//        this.colisionMovY.add(new MapaColision(4,5,this.scale,g));
        
//        probarMapa = new MapaColision(3,5,5,6,this.scale,g);
    };
    
    public void agregarColision(int agregarColision,int x, int y,int scale,Graphics g){
        switch(agregarColision){
            case 1:
                this.colisionMovY.add(new MapaColision(x,y,this.scale,g,"y"));
                break;
            case 2:
                this.colisionMovX.add(new MapaColision(x,y,this.scale,g,"x"));
                break;
            case 3:
                this.colisionMovXY.add(new MapaColision(x,y,this.scale,g,"xy"));
                break;
        };
    };
    
    public void moverPj(Graphics g,boolean pintar){
        //Dibuja un rectangulo que ,muestra el rectangulo de la colision, para que se pueda ver en pantalla
        g.drawRect(this.personaje.getPosicionX(),this.personaje.getPosicionY(),this.personaje.getUnidadAnchoRun(),this.personaje.getUnidadAltoRun());
        //Dibuja el rectangulo de la colision
        personajeColision = new 
         Rectangle(this.personaje.getPosicionX(),this.personaje.getPosicionY(),this.personaje.getUnidadAnchoRun(),this.personaje.getUnidadAltoRun()); 
        if(pintar)//Dibuja la imagen
        g.drawImage(this.personaje.getPjImage(),this.personaje.getPosicionX(),this.personaje.getPosicionY(),this.personaje.getPosicionX()+this.personaje.getUnidadAnchoRun(),this.personaje.getPosicionY()+this.personaje.getUnidadAltoRun(),
                this.personaje.numeroDeImagenAncho(),this.personaje.numeroDeImagenAlto(),this.personaje.numeroDeImagenAncho()+this.personaje.getUnidadAnchoImagenOriginal(),this.personaje.numeroDeImagenAlto()+this.personaje.getUnidadAltoImagenOriginal(), this);//6,2
    };

    public int getScale() {//Retoruna el tamaño al que se aumenta el juego 1 = originial, 2 = al doble de grande, 3 = triple de grande etc...
        return scale;
    }
    
}
