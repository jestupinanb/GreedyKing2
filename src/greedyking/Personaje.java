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
        private int scale;
        private int unidadAnchoRun;
        private int unidadAltoRun;
        private int unidadAnchoImagenOriginal;
        private int unidadAltoImagenOriginal;
        private int posicionX;
        private int posicionY;
        private int numImagen;//numero de animaciones
        private int[][] animacion = {//Matriz con las posiciones para la animacion del personaje
                {5,2}//No se mueve
                ,{106,27},{131,27},{156,27},{181,27}//movimiento a la derecha
        };//kk
        private Image pjImage;
        
        public Personaje(int escala){
            this.pjImage = loadImage("Animacion Pj.png");
            this.scale = 4;
            this.unidadAnchoRun = 17*this.scale;
            this.unidadAltoRun = 22*this.scale;
            this.unidadAnchoImagenOriginal = 17;
            this.unidadAltoImagenOriginal = 22;
            this.posicionX = 8*this.scale;
            this.posicionY = 75*this.scale;
            this.numImagen = 0;
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
    };
