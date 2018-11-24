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
public class Cubo 
{
    private boolean cima = true;
    private boolean baixo = false;
    private boolean direita = false;
    private boolean esquerda = false;
    private boolean atras = false;
    private boolean frente = false;
    
    private double x = 0;
    private double y = 0;
    private final double z = 0.55; // este determina a ditancia do cubo em relacao ao mapa
    
    public void gerarCubo(GL2 gl)
    {
        gl.glPushMatrix();
            gl.glTranslated(x, y, z);
            
            defineCor(cima, gl);
            cima(gl);
            
            defineCor(frente, gl);
            frente(gl);
            
            defineCor(direita, gl);
            direita(gl);
            
            defineCor(atras, gl);
            tras(gl);
            
            defineCor(esquerda, gl);
            esquerda(gl);
            
            defineCor(direita, gl);
            baixo(gl);
            
        gl.glPopMatrix();
    }
   
    //Formação do cubo
    private void cima(GL2 gl){
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
    private void frente(GL2 gl){
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
    //Fim formação do cubo
    //-----------------------------------------------------------------------------------------------------//
    //mover cubos
    
    public void moverDireita(GL2 gl){
        x += 1;
        
        gl.glPushMatrix();
            gl.glTranslated(1, 0, 0);
            gl.glRotated(90, 0, 0, 0);
        gl.glPopMatrix();
    }
    
    public void moverEsquerda(GL2 gl){
        x -= 1;
        gl.glPushMatrix();
            gl.glTranslated(-1, 0, 0);
            gl.glRotated(90, 0, 0, 0);
        gl.glPopMatrix();
    }
    
    public void moverFrente(GL2 gl){
        y += 1;
        gl.glPushMatrix();
            gl.glTranslated(0, 0, 1);
            gl.glRotated(90, 0, 0, 0);
        gl.glPopMatrix();
    }
    
    public void moverTras(GL2 gl){
        y -= 1;
        gl.glPushMatrix();
            gl.glTranslated(0, 0, -1);
            gl.glRotated(90, 0, 0, 0);
        gl.glPopMatrix();
    }

    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY(double y) {
        this.y = y;
    }
    public double getZ() {
        return z;
    }
    public boolean isBaixo() {
        return baixo;
    }
    
    private void defineCor(boolean b,GL2 gl)
    {
        if (b) 
        {
           gl.glColor3f(1, 0, 0);
        } 
        else 
        {
           gl.glColor3f(0, 1, 0);
        }
    }
}
