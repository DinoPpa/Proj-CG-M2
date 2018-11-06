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
                m1();
            break;
              
            case 2:
                m2();
            break;
            
            default: 
                throw new UnsupportedOperationException("ID mapa incorreto");
        }
    }
    
    //Cria um piso neste ponto
    private void gerarPiso(float x, float y, float z)
    {
     //   glu.
    }
    
    //Cada funcao é um mapa
    private void m1() 
    {
        
    }

    private void m2() 
    {
        
    }
}
