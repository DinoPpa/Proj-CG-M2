/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import javax.media.opengl.GL;
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
    
    private double i; // usado como axiliar para animação
    
    private boolean animando = false; //é usado como controle de transição entre animar e realmente mover
    private boolean permitirMovimentacao = false; //auxiliar para avisa a animação foi terminada
    
    public void gerarCubo(GL2 gl, int lado)
    {        
        gl.glPushMatrix();
            gl.glTranslated(x, y, z);
            
            if (lado != 0) 
            {  
                anima(gl, lado);
            }
            
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
    
    public boolean moverDireita(GL2 gl){
        if (animando) // animação acabo?
        {
            if (permitirMovimentacao) 
            {
                animando = false;
                x += 1;
                mudancaDeFace(1);
                return true;
            }
        }
        else
        {
            animando = true;
        }
        
        return false;
    }
    public boolean moverEsquerda(GL2 gl){
        
        if (animando) // animação acabo?
        {
            if (permitirMovimentacao) 
            {
                animando = false;
                x -= 1;
                mudancaDeFace(3);
                return true;
            }
        }
        else
        {
            animando = true;
        }
        
        return false;
    }
    public boolean moverFrente(GL2 gl){
        
        if (animando) // animação acabo?
        {
            if (permitirMovimentacao) 
            {
                animando = false;
                y += 1;
                mudancaDeFace(4);
                return true;
            }
        }
        else
        {
            animando = true;
        }
        
        return false;
    }
    public boolean moverTras(GL2 gl){
        //Primeira vez: permitir animicao e retorna false
        //Durante animação return false
        //Fim: realizar a movimentacao
        
        if (animando) // animação acabo?
        {
            if (permitirMovimentacao) 
            {
                animando = false;
                y -= 1;
                mudancaDeFace(2);
                return true;
            }
        }
        else
        {
            animando = true;
        }
        
        return false;
    }
    
    //----------------------------------------------------------------
    private void defineCor(boolean b,GL2 gl){
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
        System.out.println("Antes da alteração: "+this);
        
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
                    cima = true;
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
        
        System.out.println("Depois da alteração: "+this);
    }
    
    public void reniciaValores()
    {
        cima = true;
        baixo = false;
        direita = false;
        esquerda = false;
        atras = false;
        frente = false;

        x = 0;
        y = 0;

        i = 0;

        animando = false;
        permitirMovimentacao = false; 
    }
    
    public void gerarEfeitoLuz(GL2 gl) 
    {
        float matDifusa[]  = {1f,0f,0f};
        float materialAmbiente[] ={0.25f,0,0,1};
        float materialEspecular[] ={1,1,1,1};
        float brilho = 40;
        
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_DIFFUSE,matDifusa,0);
       
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_AMBIENT,materialAmbiente,0);
        
        gl.glMaterialfv(GL2.GL_FRONT_AND_BACK,GL2.GL_SPECULAR,materialEspecular,0);
        
        gl.glMaterialf(GL2.GL_FRONT_AND_BACK, GL2.GL_SHININESS,brilho);
    }

    //Animação 
    //----------------------------------------------------------------
    
    private void anima(GL2 gl, int lado) 
    {
        i++; //Este valor define a velocidade da animação
        
        switch(lado)
        {
            case 1:
                animaDireita(gl);
                break;
            case 2:
                animaTras(gl);
                break;
            case 3:
                animaEsquerda(gl);
                break;
            case 4:
                animaFrente(gl);
                break;
            
        }
                
        if (i < 90) 
        {
            permitirMovimentacao = false;
        }
        else
        {
            i = 0;
            permitirMovimentacao = true;
        }
    }
    
    private void animaDireita(GL2 gl) 
    {
        gl.glTranslated(0.5, 0, -0.5);
        gl.glRotated(i, 0, 1, 0);
        gl.glTranslated(-0.5, 0, 0.5);
    }

    private void animaEsquerda(GL2 gl) 
    {
        gl.glTranslated(-0.5, 0, -0.5);
        gl.glRotated(-i, 0, 1, 0);
         gl.glTranslated(0.5, 0, 0.5);
    }

    private void animaFrente(GL2 gl) 
    {
        gl.glTranslated(0, 0.5, -0.5); 
        gl.glRotated(-i, 1, 0, 0);
        gl.glTranslated(0, -0.5, 0.5); 
    }

    private void animaTras(GL2 gl) 
    {
        gl.glTranslated(0, -0.5, -0.5); 
        gl.glRotated(i, 1, 0, 0);
        gl.glTranslated(0, 0.5, 0.5); 
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
