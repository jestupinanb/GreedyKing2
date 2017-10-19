/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package greedyking;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Jaime
 */
public class MapaColision extends MapaPadre {
    Graphics g;
    Rectangle ColisionBloque;
    Rectangle ColisionBloquexyDown;
    Rectangle ColisionBloquexyRight;
    Rectangle ColisionBloquexyLeft;

    public MapaColision(int posicionX, int posicionY,int scale,Graphics g,String bloquea){
        super(posicionX, posicionY,scale);
        if(bloquea.equals("x") || bloquea.equals("y")){
        ColisionBloque = new Rectangle(posicionX*unidadMapaGrande, posicionY*unidadMapaGrande, unidadMapaGrande, unidadMapaGrande);
        g.drawRect(posicionX*unidadMapaGrande, posicionY*unidadMapaGrande, unidadMapaGrande, unidadMapaGrande);
        }else{
            ColisionBloque = new 
             Rectangle(posicionX*unidadMapaGrande+1*scale, posicionY*unidadMapaGrande, unidadMapaGrande-2*scale, 1);
            g.drawRect(posicionX*unidadMapaGrande+1*scale, posicionY*unidadMapaGrande, unidadMapaGrande-2*scale, 1);
            ColisionBloquexyDown = new
             Rectangle(posicionX*unidadMapaGrande+1*scale, (posicionY+1)*unidadMapaGrande-1, unidadMapaGrande-2*scale, 1);
            g.drawRect(posicionX*unidadMapaGrande+1*scale, (posicionY+1)*unidadMapaGrande-1, unidadMapaGrande-2*scale, 1);
            ColisionBloquexyRight = new
             Rectangle(posicionX*unidadMapaGrande, posicionY*unidadMapaGrande+1*scale, 1, unidadMapaGrande-2*scale);
            g.drawRect(posicionX*unidadMapaGrande, posicionY*unidadMapaGrande+1*scale, 1, unidadMapaGrande-2*scale);
            ColisionBloquexyLeft = new
             Rectangle((posicionX+1)*unidadMapaGrande, posicionY*unidadMapaGrande+1*scale, 1, unidadMapaGrande-2*scale);
            g.drawRect((posicionX+1)*unidadMapaGrande, posicionY*unidadMapaGrande+1*scale, 1, unidadMapaGrande-2*scale);
        };
    };
            
    public Rectangle getColisionBloque(){
        return ColisionBloque;
    };

    public Rectangle getColisionBloquexyDown() {
        return ColisionBloquexyDown;
    }

    public Rectangle getColisionBloquexyRight() {
        return ColisionBloquexyRight;
    }

    public Rectangle getColisionBloquexyLeft() {
        return ColisionBloquexyLeft;
    }
}
