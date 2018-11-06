/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

/**
 *
 * @author Yudi-PC
 */
public class Mapa 
{
     GLU glu = new GLU();
    
    //Pede para gerar o mapa X
    public void gerarMapa(int id, GL2 gl)
    {
        switch(id)
        {
            case 1:
                m1(gl);
            break;
              
            case 2:
                m2(gl);
            break;
            
            default: 
                throw new UnsupportedOperationException("ID mapa incorreto");
        }
    }
    
    //Cria um piso neste ponto (0-> padrao, 1-> inicio, 2->fim)
    private void gerarPiso(GL2 gl,int tipo)
    {
        gl.glPushMatrix();
            gl.glColor3f(0.0f, 0.0f, 1.0f);

            // Desenha um quadrado preenchido com a cor corrente
            gl.glBegin(GL2.GL_QUADS);
                      gl.glVertex3d(1,1,0);
                      gl.glVertex3d(-1,1,0);
                      gl.glVertex3d(-1,-1,0);
                      gl.glVertex3d(1,-1,0);               
            gl.glEnd();
        
        gl.glPopMatrix();
    }
    
    //Cada funcao é um mapa
    private void m1(GL2 gl) 
    {
        gl.glPushMatrix();
            
        
        gl.glPopMatrix();
    }

    private void m2(GL2 gl) 
    {
        gl.glPushMatrix();
            
        
        gl.glPopMatrix();
    }
}
