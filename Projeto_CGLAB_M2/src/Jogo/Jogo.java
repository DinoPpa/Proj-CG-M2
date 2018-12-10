/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Jogo;

import Classe.*;
import com.jogamp.opengl.util.Animator;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Glauco
 */
public class Jogo 
            implements GLEventListener,
            KeyListener {

    GLU glu = new GLU();
    
 
    
    private boolean up = false;
    private boolean down = false;
    private boolean right = false;
    private boolean left = false;
    private boolean controlEnable = true; //Controla que o usuario nao devera fazer nenhum movimento, até acaba animação
    JLabel gameOver = new JLabel();
     JPanel container = new JPanel();
    
    private boolean E = false;
    private boolean R = false;
    private boolean D = false;
    private boolean F = false;
    
    private boolean verificar = false; //Esta variavel foi uma forma de otimizar
    
    private Cubo c = new Cubo();
    private Mapa m = new Mapa();
    
    private int selectFase = 1;
    
    private boolean mostraMenu = false;
    
    private float[] posLuz = {0, 0, 4, 1}; //posição da luz
    private boolean LuzR = true;
    private boolean LuzG = true;
    private boolean LuzB = true;    
        public static int larguraJa = 600;
    public static int alturaJa = 550;
    
    private int auxAnimacao = 0;
   
    //private float luzEspecular[] = {0.25f,0.25f,0.25f,1}; //RGB A - Luz Especular
    //private float luzDifusa[]  = {0.5f,0.5f,0.5f,1f}; //RGB A - Luz Difusa
    private float luzAmbiente[]  = {1,1,1,1.0f}; //RGB A - Luz Ambiente

    public Jogo(int selectFase) {
        GLJPanel canvas = new GLJPanel();
        canvas.addGLEventListener(this);
         this.selectFase = selectFase;
         
          gameOver.setBounds((larguraJa/2)-40,0,100,20);
        
        JFrame frame = new JFrame("Jogo");
        frame.setSize(700, 700); //define o tamanho da tela
        frame.getContentPane().add(canvas);
        frame.setVisible(true);
        frame.addKeyListener(this);
        
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                new Thread(new Runnable() {
                    public void run() {
                        System.exit(0);
                    }
                }).start();
            }
        });
        
    }


    
    @Override
    public void init(GLAutoDrawable glAuto) {
        Animator a = new Animator(glAuto);
        a.start();
        
        GL2 gl = glAuto.getGL().getGL2(); 
        gl.glClearColor(0.4f, 0.4f, 0.4f, 0.4f); //define a cor de fundo
        gl.glEnable(GL.GL_DEPTH_TEST); //teste de profundidade
        
        gl.glEnable(GL2.GL_COLOR_MATERIAL);
        
        gl.glEnable(GL2.GL_LIGHTING);
        gl.glEnable(GL2.GL_LIGHT1);
        
        gl.glLightfv(GL2.GL_LIGHT1,GL2.GL_POSITION,posLuz,0); //Onde estará localizado a luz
        //gl.glLightfv(GL2.GL_LIGHT1,GL2.GL_DIFFUSE,luzDifusa,0); 
        //gl.glLightfv(GL2.GL_LIGHT1,GL2.GL_AMBIENT,luzAmbiente,0); 
        //gl.glLightfv(GL2.GL_LIGHT1,GL2.GL_SPECULAR,luzEspecular,0); 
        
    }

    public void display(GLAutoDrawable glAuto) {
        
        GL2 gl = glAuto.getGL().getGL2();
        
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        
        gl.glLoadIdentity(); //Renicia todos acumulativos
        gl.glLightfv(GL2.GL_LIGHT1,GL2.GL_AMBIENT,luzAmbiente,0); 
               
        gl.glTranslated(0,0,-15); //Onde estara a camera em posição
        
        botoesCamera(gl); //rotaciona o mapa de acordo com o usuario (A,S,D,F,G)
        botoesLuz();   
        
        
        if (mostraMenu) // obs.: MostraMenu esta false, pois precisa dá parte da Eloa
        {
            //MENU
            //OBS.: Fase é qual mapa deve ser gerado
        }
        
        //OBS.: o 0,0,0 sempre estara no centro do quadrado azul (inicio) e sua distancia em outros quadrados é 1
        
        //m.gerarEfeitoLuz(gl);
        m.gerarMapa(selectFase, gl);
        
        movimentacao(gl); //Controle de animação e movimentação
        
        //c.gerarEfeitoLuz(gl);
        
        c.gerarCubo(gl,auxAnimacao);
        
        if (verificar) //isso foi feito para otimizar
        {
            //obs.: as duas funçoes sao boolean e será para o avisa o gameover ou tela de vitoria
            VerificarVitoria();
            VerificarDerrota(); 
        }
        
    }
    
    public void reshape(GLAutoDrawable gLAutoDrawable, int x, int y, int w, int h) 
    {  
        GL2 gl = gLAutoDrawable.getGL().getGL2(); 
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 1, 30);
        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslated(0, 0, -5);
    }

    /*
     public void reshape(GLAutoDrawable drawable, int x, int y, int w, int h) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glViewport(0, 0, w, h);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        if (w <= h) {
            gl.glOrtho(-1.5, 1.5,
                    -1.5 * (float) h / (float) w,//
                    1.5 * (float) h / (float) w,//
                    -10.0, 10.0
            );
        } else {
            gl.glOrtho(-1.5 * (float) w / (float) h, //
                    1.5 * (float) w / (float) h, //
                    -1.5, 1.5, -10.0, 10.0
            );
        }

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }*/
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        
    }
    
    public void dispose(GLAutoDrawable glad) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
    
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
        if (controlEnable) //Controle do jogador no cubo
        {
            controlEnable = false;
            verificar = true;
            switch (e.getKeyCode()) 
            {
                case KeyEvent.VK_UP:
                    up = true;
                    break;

                case KeyEvent.VK_DOWN:
                    down = true;
                    break;

                case KeyEvent.VK_RIGHT:
                    right = true;
                    break;

                case KeyEvent.VK_LEFT:
                    left = true;
                    break;

                default:
                    controlEnable = true;
                    verificar = false;
                    break;
            }
        }
        
        switch (e.getKeyCode()) //Controle de camera do jogador
        {
            case KeyEvent.VK_R:
                restartCamera();
                R = true;
                break;

            case KeyEvent.VK_E:
                restartCamera();
                E = true;
                break;

            case KeyEvent.VK_D:
                restartCamera();
                D = true;
                break;

            case KeyEvent.VK_F:
                restartCamera();
                F = true;
                break;
                
            case KeyEvent.VK_G:
                restartCamera();
                break;
                
            case KeyEvent.VK_1:
                LuzR = inverterBoolean(LuzR);
                mensagemLuz();
                break;
                
            case KeyEvent.VK_2:
                LuzG = inverterBoolean(LuzG);
                mensagemLuz();
            break;
            
            case KeyEvent.VK_3:
                LuzB = inverterBoolean(LuzB);
                mensagemLuz();
            break;
                
            default:
                break;
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
    
    }
    
    //Renicia o boolean relacionado a camera
    private void restartCamera()
    {
        E = false;
        R = false;
        D = false;
        F = false;
    }
    
    //Renicia o boolean relacionado a controle
    private void restartControle()
    {
        up = false;
        down = false;
        left = false;
        right = false;
    }

    //Realiza as ações da camera 
    private void botoesCamera(GL2 gl) 
    {
        if (F) {
            gl.glRotated(-45, 1, 0, 0);
            gl.glRotated(-45, 0, 0, 1);
        } 
        else if (D){
            gl.glRotated(-45, 1, 0, 0);
            gl.glRotated(45, 0, 0, 1);
        }
        else if (E){
            gl.glRotated(-45, 1, 0, 0);
            gl.glRotated(135, 0, 0, 1);
        }
        else if (R){
            gl.glRotated(-45, 1, 0, 0);
            gl.glRotated(-135, 0, 0, 1);
        } 
    }

    private void movimentacao(GL2 gl) 
    {
        if (!controlEnable) 
        {
            if (up) 
            {
                controlEnable = c.moverFrente(gl);
                auxAnimacao = 4;
            } 
            else if(right) 
            {
                controlEnable = c.moverDireita(gl);
                auxAnimacao = 1;
            } 
            else if (left)
            {
                controlEnable = c.moverEsquerda(gl);
                auxAnimacao = 3;
            }
            else if (down)
            {
                controlEnable = c.moverTras(gl);
                auxAnimacao = 2;
            }
            else
            {
                throw new UnsupportedOperationException("ERRO - Controle inativo e nenhuma animação permetida");
            }
            System.out.println(auxAnimacao +" -"+ controlEnable);          
        }
        else
        {
            auxAnimacao = 0;
            restartControle();
        }
    }

    //Esta parte ira confirma se o jogador ganhou ou nao
    private boolean VerificarVitoria() 
    {
        double[][] lista = m.infoMapa(selectFase);
        boolean vitoria = false;
        verificar = true;
        
        if (lista[1][0] == c.getX() && 
            lista[1][1] == c.getY() &&
            c.isBaixo()) 
        {
            vitoria = true;
            System.out.println("Foi encontra uma vitoria");
             
         
            verificar = false;
            JOptionPane.showMessageDialog(null, "Vitória");
        }
        
        return vitoria;
    }
    
    
    
  
    //Esta parte ira confirma se o jogador derrubou o cubo
    private boolean VerificarDerrota() 
    {
        double[][] lista = m.infoMapa(selectFase);
        boolean derrota = true;
        
        for (double[] ds : lista) 
        {
            if (ds[0] == c.getX() && 
                ds[1] == c.getY()) 
            {
                derrota = false;
                break;
            }
            
        }
        
        if (derrota) 
        {
            verificar = false;
 
            System.out.println("Foi encontra uma derrota");
              JOptionPane.showMessageDialog(null, "Game Over");
        }
        
        return derrota;
    }
    


    private boolean inverterBoolean(boolean b) 
    {
        if (b) 
        {
            return false;
        } 
        else 
        {
            return true;
        }
    }

    private void botoesLuz() 
    {
        if (LuzR) 
        {
            luzAmbiente[0] = 1;
        } 
        else 
        {
            luzAmbiente[0] = 0;
        }
        
        if (LuzG) 
        {
            luzAmbiente[1] = 1;
        } 
        else 
        {
            luzAmbiente[1] = 0;
        }
        
        if (LuzB) 
        {
            luzAmbiente[2] = 1;
        } 
        else 
        {
            luzAmbiente[2] = 0;
        }
    }
    
    private void mensagemLuz()
    {
        System.out.println("Luz ambiente alterada: R="+LuzR +" | G="+ LuzG +" | B="+ LuzB);
    }
}
