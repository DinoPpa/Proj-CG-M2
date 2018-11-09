/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classe;

import java.util.ArrayList;
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
        double[][] listaInfo = null;
        
        switch(id)
        {
            case 1:
                listaInfo = m1();
            break;
              
            case 2:
               // listaInfo = m2();
            break;
            
            default: 
                throw new UnsupportedOperationException("ERRO - ID mapa incorreto");
        }
        if(listaInfo == null)
        {
            throw new UnsupportedOperationException("ERRO - listaInfo retornou NULL");
        }
        
        renderizar(listaInfo, gl);
    }
    
    //Cria um piso neste ponto (0-> inicio, 1-> fim, 2ou+->piso)
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
    
    //Retorna uma lista com os dados da coordenada X,Y,Z 
    //(Foram separado numa funcao para diminui a complexidade)
    //OBS.: primeiro => inicio e Segundo => fim
    private double[][] m1() 
    {
        double[][] temp = 
        {{1,2,3},
        {1,2,3}};
                
        return temp;
    }
    
    //OBS.: listaInfo: 
    //o primeiro => inicio e 
    //o segundo => fim
    private void renderizar(double[][] listaInfo, GL2 gl) 
    {
        for (int i = 0; i < listaInfo.length; i++) 
        {
            gl.glPushMatrix();
            gl.glTranslated(listaInfo[i][0],listaInfo[i][1],listaInfo[i][2]]);
            gl.glPopMatrix();
        }
    }
}
