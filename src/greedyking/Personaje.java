/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Jaime
 */
public class Personaje {
        private int scale = 4;
        private int unidadAnchoRun = 17*scale;
        private int unidadAltoRun = 22*scale;
        private int unidadAnchoImagenOriginal = 17;
        private int unidadAltoImagenOriginal = 22;
        private int posicionX = 8*scale;
        private int posicionY = 75*scale;
        private int numImagen = 0;//numero de animaciones
        private int[][] animacion = {//Matriz con las posiciones para la animacion del personaje
                {5,2}//No se mueve
                ,{106,27},{131,27},{156,27},{181,27}//movimiento a la derecha
        };
        private Image pjImage;
        
        public Personaje(){
            this.pjImage = loadImage("Animacion Pj.png");
        };
        
        public void moveRight(){
            this.posicionX += 1*scale;
            this.numImagen +=1;
            if(this.numImagen==5){
                this.numImagen = 1;
            };
        }
        
        public int numeroDeImagenAncho(){
            return animacion[this.numImagen][0];
        };
        
        public int numeroDeImagenAlto(){
            return animacion[this.numImagen][1];
        };
        
        public void moveLeft(){
            this.posicionX -= 1*scale;
        }
        
        public void moveUP(){
            this.posicionY -= 1*scale;
        }
        
        public void moveDown(){
            this.posicionY += 1*scale;
        }
        
        public void setPosicionX(int posicionX) {
            this.posicionX = posicionX;
        }

        public void setPosicionY(int posicionY) {
            this.posicionY = posicionY;
        }
        
        public int getUnidadAnchoRun() {
            return unidadAnchoRun;
        }

        public int getUnidadAltoRun() {
            return unidadAltoRun;
        }

        public int getUnidadAnchoImagenOriginal() {
            return unidadAnchoImagenOriginal;
        }

        public int getUnidadAltoImagenOriginal() {
            return unidadAltoImagenOriginal;
        }

        public int getPosicionX() {
            return posicionX;
        }

        public int getPosicionY() {
            return posicionY;
        }

        public Image getPjImage() {
            return pjImage;
        }
        
        public Image loadImage(String imageName){
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
        };
        
        public void setScale(int scale){//Le asigna un valor a la escala
            this.scale = scale;
        };
    };
