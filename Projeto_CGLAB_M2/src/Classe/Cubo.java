/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import javax.media.opengl.GL2;

/**
 *
 * @author Yudi-PC
 */
public class Cubo {
    public void cubo(GL2 gl){
        frente(gl);
        direita(gl);
        tras(gl);
        esquerda(gl);
        cima(gl);
        baixo(gl);
    }
    
    private void frente(GL2 gl){
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(-0.5,-0.5,0.5);
            gl.glVertex3d(0.5,-0.5,0.5);
            gl.glVertex3d(0.5,0.5,0.5);
            gl.glVertex3d(-0.5,0.5,0.5); 
        gl.glEnd();
    }
    private void direita(GL2 gl){
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(0.5,-0.5,0.5);
            gl.glVertex3d(0.5,-0.5,-0.5);
            gl.glVertex3d(0.5,0.5,-0.5);
            gl.glVertex3d(0.5,0.5, -0.5); 
        gl.glEnd();
    }
    private void tras(GL2 gl){
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(-0.5,-0.5,-0.5);
            gl.glVertex3d(0.5,-0.5,-0.5);
            gl.glVertex3d(0.5,0.5,-0.5);
            gl.glVertex3d(-0.5,0.5,-0.5);
        gl.glEnd();
    }
    private void esquerda(GL2 gl){
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(-0.5,-0.5,0.5);
            gl.glVertex3d(-0.5,0.5,0.5);
            gl.glVertex3d(-0.5,0.5, -0.5);
            gl.glVertex3d(-0.5,-0.5,-0.5);
        gl.glEnd();
    }
    private void cima(GL2 gl){
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(-0.5,0.5,0.5);
            gl.glVertex3d(0.5,0.5,0.5);
            gl.glVertex3d(0.5,0.5,-0.5);
            gl.glVertex3d(-0.5,0.5,-0.5);
        gl.glEnd();
    }
    private void baixo(GL2 gl){
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(-0.5, -0.5, 0.5);
            gl.glVertex3d(0.5,-0.5,0.5);
            gl.glVertex3d(0.5,-0.5,-0.5);
            gl.glVertex3d(-0.5,-0.5,-0.5);
        gl.glEnd();
    }
    
}
