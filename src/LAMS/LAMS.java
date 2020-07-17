/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LAMS;

import converter.Face;
import converter.LamToObjConverter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.ArrayList;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author tyjensen
 */
public class LAMS implements GLEventListener{
    ArrayList<CartesianCoordinate> vertices = new ArrayList<CartesianCoordinate>();
    static LamToObjConverter lamConverter;
    static JFileChooser fileChooser = new JFileChooser();
    static JFrame frame;
    FileNameExtensionFilter lamFileFilter = new FileNameExtensionFilter("LAMS Output File (.lam)","lam");
    FileNameExtensionFilter lampFileFilter = new FileNameExtensionFilter("LAMS Project File (.lamp)","lamp");
    FileNameExtensionFilter objFileFilter = new FileNameExtensionFilter("3D Object File (.obj)","obj");
    
    public static void main(String[] args){
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LAMS().initComponents();
            }
        });
    }
        
    public void initComponents(){
        JFrame frame = new JFrame("LAMS 3D Viewer");
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem importLam = new JMenuItem("Import .lam file");
        JMenuItem importObj = new JMenuItem("Import .obj file");
        JMenuItem exportObj = new JMenuItem("Export .obj file");
        importLam.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File lamFile = null;
                
                fileChooser.addChoosableFileFilter(lamFileFilter);
                int res = fileChooser.showDialog(frame, "Import");
                if(res==JFileChooser.APPROVE_OPTION){
                    importLamFile(lamFile);
                }
                fileChooser.removeChoosableFileFilter(lamFileFilter);
            }
        });
        open.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File openFile = null;
                fileChooser.addChoosableFileFilter(lampFileFilter);
                int res = fileChooser.showOpenDialog(frame);
                if(res==JFileChooser.APPROVE_OPTION){
                    
                }
                fileChooser.removeChoosableFileFilter(lampFileFilter);
            }
            
        });
        
        save.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File saveFile = null;
                fileChooser.addChoosableFileFilter(lampFileFilter);
                int res = fileChooser.showSaveDialog(frame);
                if(res==JFileChooser.APPROVE_OPTION){
                    
                }
                fileChooser.removeChoosableFileFilter(lampFileFilter);
            }
            
        });
        saveAs.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File saveAsFile = null;
                fileChooser.addChoosableFileFilter(lampFileFilter);
                int res = fileChooser.showDialog(frame, "Save As");
                if(res==JFileChooser.APPROVE_OPTION){
                    
                }
                fileChooser.removeChoosableFileFilter(lampFileFilter);
            }
            
        });
        importObj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File lamFile = null;
                fileChooser.addChoosableFileFilter(objFileFilter);
                int res = fileChooser.showDialog(frame, "Import");
                if(res==JFileChooser.APPROVE_OPTION){
                    importLamFile(lamFile);
                }
                fileChooser.removeChoosableFileFilter(objFileFilter);
            }
        });
        exportObj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File objFile = null;
                fileChooser.addChoosableFileFilter(objFileFilter);
                int res = fileChooser.showDialog(frame, "Export");
                if(res==JFileChooser.APPROVE_OPTION){
                    importLamFile(objFile);
                }
                fileChooser.removeChoosableFileFilter(objFileFilter);
            }
        });
        GLCanvas canvas = new GLCanvas();

        menu.add(open);
        menu.add(save);
        menu.add(saveAs);
        menu.addSeparator();
        menu.add(importLam);
        menu.addSeparator();
        menu.add(importObj);
        menu.add(exportObj);
        menuBar.add(menu);
        canvas.addGLEventListener(new LAMS());
        frame.setJMenuBar(menuBar);
        frame.add(canvas);
        frame.setSize(800, 800);
        frame.addWindowListener(new WindowAdapter() {
        public void windowClosing(WindowEvent e) {
          // Run this on another thread than the AWT event queue to
          // make sure the call to Animator.stop() completes before
          // exiting
          new Thread(new Runnable() {
              public void run() {
                System.exit(0);
              }
            }).start();
        }
      });
        frame.show();
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();

        float h = (float)height / (float)width;

        gl.glMatrixMode(GL.GL_PROJECTION);

        System.err.println("GL_VENDOR: " + gl.glGetString(GL.GL_VENDOR));
        System.err.println("GL_RENDERER: " + gl.glGetString(GL.GL_RENDERER));
        System.err.println("GL_VERSION: " + gl.glGetString(GL.GL_VERSION));
        gl.glLoadIdentity();
        gl.glFrustum(-1.0f, 1.0f, -h, h, 5.0f, 60.0f);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glTranslatef(0.0f, 0.0f, -40.0f);
    }

    @Override
    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void displayObject(ArrayList<CartesianCoordinate> cartesian, ArrayList<Face> faces){
        
    }
    
    public void importLamFile(File lamFile){
        lamConverter.convert(lamFile);
    }
    
    
}
