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
            
            defineCor(baixo, gl);
            baixo(gl);
            
        gl.glPopMatrix();
    }
   
    //Criação das faces do cubo
    //----------------------------------------------------------------
    
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
            gl.glVertex3d(0.5,0.5, 0.5); 
        gl.glEnd();
    }
    private void baixo(GL2 gl){
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
    private void tras(GL2 gl){
        gl.glBegin(GL2.GL_QUADS);
            gl.glVertex3d(-0.5, -0.5, 0.5);
            gl.glVertex3d(0.5,-0.5,0.5);
            gl.glVertex3d(0.5,-0.5,-0.5);
            gl.glVertex3d(-0.5,-0.5,-0.5);
        gl.glEnd();
    }
        
    //Mover cubos
    //----------------------------------------------------------------
    
    public void moverDireita(GL2 gl){
        x += 1;
        
        //animaDireita(gl);
        
        mudancaDeFace(1);
    }
    public void moverEsquerda(GL2 gl){
        x -= 1;
        
        //animaEsquerda(gl);
        
        mudancaDeFace(3);
    }
    public void moverFrente(GL2 gl){
        y += 1;
        
        //animaFrente(gl);
        
        mudancaDeFace(4);
    }
    public void moverTras(GL2 gl){
        y -= 1;
        
        //animaTras(gl);
        
        mudancaDeFace(2);
    }
    
    //----------------------------------------------------------------
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
    
    //1-direita, 2-tras,3-esquerda,4- frente
    private void mudancaDeFace(int lado)
    {
        System.out.println("Sem alteração: "+this);
        
        if (cima) 
        {
            cima = false;
            switch(lado)
            {
                case 1:
                    direita = true;
                    break;
                case 2:
                    atras = true;
                    break;
                case 3:
                    esquerda = true;
                    break;
                case 4:
                    frente = true;
                    break;
            }
        }
        else if(baixo)
        {
            baixo = false;
            
            switch(lado)
            {
                case 1:
                    esquerda = true;
                    break;
                case 2:
                    frente = true;
                    break;
                case 3:
                    direita = true;
                    break;
                case 4:
                    atras = true;
                    break;
            }
        }
        else if (direita)
        {
            direita = false;
            switch(lado)
            {
                case 1:
                     baixo = true;
                    break;
                case 3:
                     cima = true;
                    break;
                default:
                    direita = true;
                    break;
            }
        }
        else if(esquerda)
        {
            esquerda = false;
            switch(lado)
            {
                case 1:
                    cima = true;
                    break;
                case 3:
                    baixo = true;
                    break;
                default:
                    esquerda = true;
                    break;
            }
        }
        else if(atras)
        {
            atras = false;
            switch(lado)
            {
                case 2:
                    baixo = true;
                    break;
                case 4:
                    cima = true;
                    break;
                default:
                    atras = true;
                    break;
            }
        }
        else if(frente)
        {
            frente = false;
            switch(lado)
            {
                case 2:
                    frente = true;
                    break;
                case 4:
                    baixo = true;
                    break;
                default:
                    frente = true;
                    break;
            }
        }
        else
        {
            throw new UnsupportedOperationException("ERRO - Todas variaveis boolean esta falsa, qual face é a correta?");
        }
        
        System.out.println("Com alteração: "+this);
    }
    
    //Animação 
    //----------------------------------------------------------------
    private void animaDireita(GL2 gl) 
    {
        gl.glPushMatrix();
            gl.glTranslated(1, 0, 0);
            gl.glRotated(90, 0, 0, 0);
        gl.glPopMatrix();
    }

    private void animaEsquerda(GL2 gl) 
    {
        gl.glPushMatrix();
            gl.glTranslated(-1, 0, 0);
            gl.glRotated(90, 0, 0, 0);
        gl.glPopMatrix();
    }

    private void animaFrente(GL2 gl) 
    {
        gl.glPushMatrix();
            gl.glTranslated(0, 0, 1);
            gl.glRotated(90, 0, 0, 0);
        gl.glPopMatrix();
    }

    private void animaTras(GL2 gl) 
    {
        gl.glPushMatrix();
            gl.glTranslated(0, 0, -1);
            gl.glRotated(90, 0, 0, 0);
        gl.glPopMatrix();
    }
    
    //Get e Set
    //----------------------------------------------------------------
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
    
    //String
    //---------------------------------------------------------------
    @Override
    public String toString() {
        String s = "Cubo{";
        if (cima) 
        {
            s += "cima=" + cima;
        }
        else if(baixo)
        {
            s += "baixo=" + baixo;
        }
        else if (direita)
        {
            s += "direita="+direita;
        }
        else if(esquerda)
        {
            s += "esquerda="+esquerda;
        }
        else if(atras)
        {
            s += "atras="+atras;
        }
        else if(frente)
        {
            s += "frente="+frente;
        }
        
        s += ", x=" + x + ", y=" + y + ", z=" + z + '}';
        
        return s;
    }    
    
}
