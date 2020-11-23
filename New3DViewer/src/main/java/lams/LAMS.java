/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lams;

import converter.CartesianCoordinate;
import converter.CartesianVector;
import converter.Edge;
import converter.FaceVertex;
import converter.LamToObjConverter;
import converter.Obj3D;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author tyjen
 */
public class LAMS extends javax.swing.JFrame {
    ArrayList<CartesianCoordinate> vertices = new ArrayList<CartesianCoordinate>();
    LamToObjConverter lamConverter = new LamToObjConverter();
    JFileChooser fileChooser = new JFileChooser();
    private JFrame viewSettingsFrame;
    private JFrame converterSettingsFrame;
    static ToolsMenu toolMenu;
    static Draw3D canvas;
    static JFrame frame;
    private FileNameExtensionFilter lamFileFilter = new FileNameExtensionFilter("this Output File (.lam)","lam");
    private FileNameExtensionFilter lampFileFilter = new FileNameExtensionFilter("this Project File (.lamp)","lamp");
    private FileNameExtensionFilter objFileFilter = new FileNameExtensionFilter("3D Object File (.obj)","obj");
    private int x=50;
    private int y=50;
    private int z=-50;
    private int posX=50;
    private int posY=50;
    private int posZ=50;
    private float transX=0;
    private float transY=0;
    private float transZ=0;
    private float rotx=92.0f;
    private float roty=180.0f;
    private float rotz=0.0f;
    private int mouseButton = 0;
    private int height;
    private int width;
    private float distance=100;
    public ArrayList<Obj3D> objects;
    
    
    public static boolean refresh = false;
    //color settings
    //solidView color scheme
    public static Color backgroundColorSolidView = Color.BLACK;
    public static Color xAxisColorSolidView = Color.GREEN;
    public static Color yAxisColorSolidView = Color.BLUE;
    public static Color zAxisColorSolidView = Color.RED;
    public static Color xyGridColorSolidView = Color.LIGHT_GRAY;
    public static Color spineColorSolidView = Color.ORANGE;
    //wireframeView color scheme
    public static Color backgroundColorWireframeView = Color.BLACK;
    public static Color xAxisColorWireframeView = Color.GREEN;
    public static Color yAxisColorWireframeView = Color.BLUE;
    public static Color zAxisColorWireframeView = Color.RED;
    public static Color xyGridColorWireframeView = Color.LIGHT_GRAY;
    //static Color spineColorWireframeView = Color.ORANGE;
    //vertexView color scheme
    public static Color backgroundColorVertexView = Color.BLACK;
    public static Color pointColorVertexView = Color.RED;
    public static Color xAxisColorVertexView = Color.GREEN;
    public static Color yAxisColorVertexView = Color.BLUE;
    public static Color zAxisColorVertexView = Color.RED;
    public static Color xyGridColorVertexView = Color.LIGHT_GRAY;
    
    //boolean settings
    //solidView settings
    public static boolean solidViewEnable = true;
    public static boolean xAxisEnableSolidView = false;
    public static boolean yAxisEnableSolidView = false;
    public static boolean zAxisEnableSolidView = true;
    public static boolean xyGridEnableSolidView = true;
    public static boolean spineEnableSolidView = false;
    
    //wireframeView settings
    public static boolean wireframeViewEnable = false;
    public static boolean xAxisEnableWireframeView = false;
    public static boolean yAxisEnableWireframeView = false;
    public static boolean zAxisEnableWireframeView = true;
    public static boolean xyGridEnableWireframeView = true;
    //static boolean spineEnableWireframeView = false;
    
    //vertexView settings
    public static boolean averagePoints = false;
    public static double averageAngle = 1.0;
    public static int pointSize=5;
    public static boolean vertexViewEnable = false;
    public static boolean xAxisEnableVertexView = false;
    public static boolean yAxisEnableVertexView = false;
    public static boolean zAxisEnableVertexView = true;
    public static boolean xyGridEnableVertexView = false;
    public boolean objectInteriorView = false;
    //tool settings
    public static boolean toolMenuEnable = true;
    public static int toolSelection = 0;
    public Obj3D focusedObject;

    //menu items
    //set main menu bar 1st level options
    JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");
        JMenu viewMenu = new JMenu("View");
        JMenu windowMenu = new JMenu("Window");
        //second level file menu options
        JMenuItem open = new JMenuItem("Open");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save As");
        JMenuItem converterSettings = new JMenuItem(".lam to .obj converter Settings");
        JMenuItem importLam = new JMenuItem("Import .lam file");
        JMenuItem importObj = new JMenuItem("Import .obj file");
        JMenuItem exportObj = new JMenuItem("Export .obj file");
        JCheckBoxMenuItem processSettings = new JCheckBoxMenuItem("GPU Processing");
        //edit menu second level options
        JMenuItem editObjects = new JMenuItem("Objects");
        //view menu second level options
        JMenuItem viewSettings = new JMenuItem("Settings");
        JCheckBoxMenuItem viewToggleInteriorView = new JCheckBoxMenuItem("Toggle Interior View");
        JCheckBoxMenuItem wireframeView = new JCheckBoxMenuItem("Wireframe View");
        JCheckBoxMenuItem vertexView = new JCheckBoxMenuItem("Vertex View");
        JCheckBoxMenuItem solidView = new JCheckBoxMenuItem("Solid View");
        //window menu second level options
        JCheckBoxMenuItem toolMenuWindowItem = new JCheckBoxMenuItem("Tool Menu");
    
    /**
     * Creates new form LAMS
     */
    public LAMS() {
        LAMS.frame = this;
        initComponents();
        initMenuComponents();
        
        //canvas.eyePosition(new CartesianCoordinate(0.0f,0.0f,-10.0f));
        /*for(int i = 0;i<=10;i++){
            for(int j = 0;j<10;j++){
                canvas.addLine(new CartesianCoordinate(10*(i-5),0.0f,50),new CartesianCoordinate(10*(i-5),0.0f,-50),Color.white);
                canvas.addLine(new CartesianCoordinate(50,0.0f,10*(i-5)),new CartesianCoordinate(-50,0.0f,10*(i-5)),Color.white);
            }
        }
        
        canvas.addLine(new CartesianCoordinate(200,0.0,0.0),new CartesianCoordinate(-200,0,0),Color.green);
        canvas.addLine(new CartesianCoordinate(0.0,200.0,0.0),new CartesianCoordinate(0.0,-200,0),Color.blue);
        canvas.addLine(new CartesianCoordinate(0.0,0.0,200.0),new CartesianCoordinate(0.0,0.0,-200),Color.yellow);
        
        CartesianCoordinate c1 = new CartesianCoordinate(-10.0f,-10.0f,-10.0f);
        CartesianCoordinate c2 = new CartesianCoordinate(10.0f,-10.0f,-10.0f);
        CartesianCoordinate c3 = new CartesianCoordinate(10.0f,10.0f,-10.0f);
        CartesianCoordinate c4 = new CartesianCoordinate(-10.0f,10.0f,-10.0f);
        CartesianCoordinate c5 = new CartesianCoordinate(-10.0f,-10.0f,10.0f);
        CartesianCoordinate c6 = new CartesianCoordinate(10.0f,-10.0f,10.0f);
        CartesianCoordinate c7 = new CartesianCoordinate(10.0f,10.0f,10.0f);
        CartesianCoordinate c8 = new CartesianCoordinate(-10.0f,10.0f,10.0f);*/
        
        /*CartesianCoordinate c1 = new CartesianCoordinate(-10.0f,-10.0f,0.0f);
        CartesianCoordinate c2 = new CartesianCoordinate(0.0f,-10.0f,10.0f);
        CartesianCoordinate c3 = new CartesianCoordinate(0.0f,10.0f,10.0f);
        CartesianCoordinate c4 = new CartesianCoordinate(-10.0f,10.0f,0.0f);
        CartesianCoordinate c5 = new CartesianCoordinate(0.0f,-10.0f,-10.0f);
        CartesianCoordinate c6 = new CartesianCoordinate(10.0f,-10.0f,0.0f);
        CartesianCoordinate c7 = new CartesianCoordinate(10.0f,10.0f,0.0f);
        CartesianCoordinate c8 = new CartesianCoordinate(0.0f,10.0f,-10.0f);*/
        //CartesianCoordinate test = new CartesianCoordinate(10.0f,10.0f,0);
        //canvas.scale = 20.0f;
        /*canvas.addPoint(c1,Color.red,5);
        canvas.addPoint(c2,Color.red,5);
        canvas.addPoint(c3,Color.red,5);
        canvas.addPoint(c4,Color.red,5);
        canvas.addPoint(c5,Color.red,5);
        canvas.addPoint(c6,Color.red,5);
        canvas.addPoint(c7,Color.red,5);
        canvas.addPoint(c8,Color.red,5);*/
        
        //canvas.addPoint(test,Color.red,5);
        
        /*canvas.addLine(c1,c2,Color.red);
        canvas.addLine(c2,c3,Color.red);
        canvas.addLine(c3,c4,Color.red);
        canvas.addLine(c4,c1,Color.red);
        canvas.addLine(c5,c6,Color.red);
        canvas.addLine(c6,c7,Color.red);
        canvas.addLine(c7,c8,Color.red);
        canvas.addLine(c8,c5,Color.red);
        canvas.addLine(c1,c5,Color.red);
        canvas.addLine(c2,c6,Color.red);
        canvas.addLine(c3,c7,Color.red);
        canvas.addLine(c4,c8,Color.red);
        canvas.addFace(c1,c2,c3,c4, Color.white);
        canvas.addFace(c1,c2,c6,c5, Color.blue);
        canvas.addFace(c3,c4,c8,c7, Color.green);
        canvas.addFace(c2,c3,c7,c6, Color.pink);
        canvas.addFace(c4,c1,c5,c8, Color.yellow);
        canvas.addFace(c5,c6,c7,c8, Color.orange);
        canvas.addFace(c3,c4,c8,c7, Color.cyan);
        canvas.addFace(c1,c2,c6,c5, Color.magenta);*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1084, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LAMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LAMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LAMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LAMS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                frame = new LAMS();
                frame.setVisible(true);
                
            }
        });
        
        /*Thread thread = new Thread(){
            public void run(){
                while(true){
                    if(LAMS.refresh){
                        LAMS.refresh = false;
                        canvas.refresh();
                        LAMS.refresh();
                    }
                }
            }
        };
        thread.start();*/
        
    }
    
    public static void refresh(){
        if(LAMS.solidViewEnable){
            canvas.setBackgroundColor(LAMS.backgroundColorSolidView);
            canvas.xyGridColor = LAMS.xyGridColorSolidView;
            canvas.xyGridEnable = LAMS.xyGridEnableSolidView;
        }
        else if(LAMS.wireframeViewEnable){
            canvas.setBackgroundColor(LAMS.backgroundColorWireframeView);
            canvas.xyGridColor = LAMS.xyGridColorWireframeView;
            canvas.xyGridEnable = LAMS.xyGridEnableWireframeView;
        }
        else if(LAMS.vertexViewEnable){
            canvas.setBackgroundColor(LAMS.backgroundColorVertexView);
            canvas.xyGridColor = LAMS.xyGridColorVertexView;
            canvas.xyGridEnable = LAMS.xyGridEnableVertexView;
        }
        canvas.pointSize = LAMS.pointSize;
        axisAndGridHelper();
        canvas.refresh();
    }
    
    public void initMenuComponents(){
        this.height=800;
        this.width = 800;
        objects = new ArrayList<Obj3D>();
        //JFrame frame = new JFrame("this 3D Viewer");
        viewSettingsFrame = new JFrame("View Options");
        
        
        solidView.setState(true);
        JMenuItem helpTest = new JMenuItem("test");
        importLam.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File lamFile = null;
                
                fileChooser.addChoosableFileFilter(lamFileFilter);
                int res = fileChooser.showDialog(LAMS.frame, "Import");
                if(res==JFileChooser.APPROVE_OPTION){
                    importLamFile(fileChooser.getSelectedFile());
                }
                fileChooser.removeChoosableFileFilter(lamFileFilter);
            }
        });
        open.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File openFile = null;
                fileChooser.addChoosableFileFilter(lampFileFilter);
                int res = fileChooser.showOpenDialog(LAMS.frame);
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
        converterSettings.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        LamToObjConverterSettings ltoConverterSettings = new LamToObjConverterSettings();
                        ltoConverterSettings.setVisible(true);
                    }
                });
            }
        });
        importObj.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                File lamFile = null;
                fileChooser.addChoosableFileFilter(objFileFilter);
                int res = fileChooser.showDialog(frame, "Import");
                if(res==JFileChooser.APPROVE_OPTION){
                    importObjFile(fileChooser.getSelectedFile());
                    //LAMS.vertexViewEnable = true;
                    //LAMS.wireframeViewEnable = false;
                    //LAMS.solidViewEnable = false;
                    canvas.refresh();
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
                    //importLamFile(objFile);
                }
                fileChooser.removeChoosableFileFilter(objFileFilter);
            }
        });
        viewToggleInteriorView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(objectInteriorView){
                    objectInteriorView = false;
                    //this.spineEnableObjViewer = false;
                    
                }
                else{
                    objectInteriorView = true;
                    //this.spineEnableObjViewer = true;
                }
            }
            
        });
        helpTest.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println(transX + transY + transZ);
            }
            
        });
        viewSettings.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        ViewSettings vs = new ViewSettings();
                        vs.setViewSettingsColors(LAMS.backgroundColorSolidView, LAMS.xAxisColorSolidView, LAMS.yAxisColorSolidView, LAMS.zAxisColorSolidView, LAMS.xyGridColorSolidView, LAMS.spineColorSolidView,
                                            LAMS.backgroundColorWireframeView, LAMS.xAxisColorVertexView, LAMS.yAxisColorWireframeView, LAMS.zAxisColorWireframeView, LAMS.xyGridColorWireframeView,
                                            LAMS.backgroundColorVertexView, LAMS.pointColorVertexView, LAMS.xAxisColorVertexView, LAMS.yAxisColorVertexView, LAMS.zAxisColorVertexView, LAMS.xyGridColorVertexView);
                        vs.setViewSettingsBool(LAMS.xAxisEnableSolidView, LAMS.yAxisEnableSolidView, LAMS.zAxisEnableSolidView, LAMS.xyGridEnableSolidView, LAMS.spineEnableSolidView,
                                            LAMS.xAxisEnableWireframeView, LAMS.yAxisEnableWireframeView, LAMS.zAxisEnableWireframeView, LAMS.xyGridEnableWireframeView,
                                            LAMS.xAxisEnableVertexView, LAMS.yAxisEnableVertexView, LAMS.zAxisEnableVertexView, LAMS.xyGridEnableVertexView);
                        vs.setVisible(true);
                    }
                });
            }
        });
        vertexView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(vertexViewEnable){
                    vertexViewEnable = false;
                    canvas.vertexView = false;
                    vertexView.setState(false);
                    wireframeViewEnable = true;
                    canvas.wireframeView = true;
                    wireframeView.setState(true);
                    solidViewEnable = false;
                    canvas.faceView = false;
                    solidView.setState(false);
                }
                else{
                    vertexViewEnable = true;
                    canvas.vertexView = true;
                    vertexView.setState(true);
                    wireframeViewEnable = false;
                    canvas.wireframeView = false;
                    wireframeView.setState(false);
                    solidViewEnable = false;
                    canvas.faceView = false;
                    solidView.setState(false);
                }
                axisAndGridHelper();
                canvas.refresh();
            }
            
        });
        wireframeView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(wireframeViewEnable){
                    vertexViewEnable = false;
                    canvas.vertexView = false;
                    vertexView.setState(false);
                    wireframeViewEnable = false;
                    canvas.wireframeView = false;
                    wireframeView.setState(false);
                    solidViewEnable = true;
                    canvas.faceView = true;
                    solidView.setState(true);
                }
                else{
                    vertexViewEnable = false;
                    canvas.vertexView = false;
                    vertexView.setState(false);
                    wireframeViewEnable = true;
                    canvas.wireframeView = true;
                    wireframeView.setState(true);
                    solidViewEnable = false;
                    canvas.faceView = false;
                    solidView.setState(false);
                }
                axisAndGridHelper();
                canvas.refresh();
            }
        });
        solidView.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(solidViewEnable){
                    vertexViewEnable = true;
                    canvas.vertexView = true;
                    vertexView.setState(true);
                    wireframeViewEnable = false;
                    canvas.wireframeView = false;
                    wireframeView.setState(false);
                    solidViewEnable = false;
                    canvas.faceView = false;
                    solidView.setState(false);
                }
                else{
                    vertexViewEnable = false;
                    canvas.vertexView = false;
                    vertexView.setState(false);
                    wireframeViewEnable = false;
                    canvas.wireframeView = false;
                    wireframeView.setState(false);
                    solidViewEnable = true;
                    canvas.faceView = true;
                    solidView.setState(true);
                }
                axisAndGridHelper();
                canvas.refresh();
            }
        });
        processSettings.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                if(canvas.GPUProcessingEnabled){
                    canvas.GPUProcessingEnabled=false;
                    processSettings.setState(false);
                }
                else{
                    canvas.GPUProcessingEnabled=true;
                    processSettings.setState(true);
                }
                canvas.refresh();
            }
        });
        toolMenuWindowItem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(LAMS.toolMenuEnable){
                    LAMS.toolMenuEnable = true;
                    toolMenuWindowItem.setState(true);
                    LAMS.toolMenu.setVisible(true);
                }
                else{
                    LAMS.toolMenuEnable = false;
                    toolMenuWindowItem.setState(false);
                    LAMS.toolMenu.setVisible(false);
                }
            }
            
        });
       
        this.canvas = new Draw3D(jPanel1.getWidth(), jPanel1.getHeight());
        canvas.GPUProcessingEnabled = false;
        canvas.setBackgroundColor(LAMS.backgroundColorSolidView);
        canvas.setFocusable(true);
        canvas.requestFocusInWindow();
        axisAndGridHelper();
        
        this.toolMenu = new ToolsMenu();
        java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        toolMenu.setVisible(true);
                    }
        });

        this.addComponentListener(new ComponentAdapter(){
            public void componentResized(ComponentEvent e){
                Component c = (Component)e.getSource();
                canvas.setSize(c.getWidth(),c.getHeight());
            }
        });
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.addSeparator();
        fileMenu.add(importLam);
        fileMenu.addSeparator();
        fileMenu.add(converterSettings);
        fileMenu.add(importObj);
        fileMenu.add(exportObj);
        fileMenu.addSeparator();
        fileMenu.add(processSettings);
        editMenu.add(editObjects);
        viewMenu.add(viewToggleInteriorView);
        viewMenu.add(viewSettings);
        viewMenu.addSeparator();
        viewMenu.add(vertexView);
        viewMenu.add(wireframeView);
        viewMenu.add(solidView);
        windowMenu.add(toolMenuWindowItem);
        helpMenu.add(helpTest);
        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(viewMenu);  
        menuBar.add(windowMenu);
        menuBar.add(helpMenu);
        LAMS.frame.setJMenuBar(menuBar);
        jPanel1.add(canvas);
        
        //LAMS.frame.add(canvas);
        //frame.setSize(800, 800);
        frame.addWindowListener(new WindowAdapter() {
        
        });
        frame.show();
    }
    
    public static void axisAndGridHelper(){

        if((LAMS.vertexViewEnable && LAMS.xAxisEnableVertexView) || (LAMS.wireframeViewEnable && LAMS.xAxisEnableWireframeView) || (LAMS.solidViewEnable && LAMS.xAxisEnableSolidView)){
            canvas.xAxisEnable = true;
            if(LAMS.vertexViewEnable){
                canvas.xAxisColor = LAMS.xAxisColorVertexView;
            }
            if(LAMS.wireframeViewEnable){
                canvas.xAxisColor = LAMS.xAxisColorWireframeView;
            }
            if(LAMS.solidViewEnable){
                canvas.xAxisColor = LAMS.xAxisColorSolidView;
            }
        }
        else{
            canvas.xAxisEnable = false;
        }
        if((LAMS.vertexViewEnable && LAMS.yAxisEnableVertexView) || (LAMS.wireframeViewEnable && LAMS.yAxisEnableWireframeView) || (LAMS.solidViewEnable && LAMS.yAxisEnableSolidView)){
            canvas.yAxisEnable = true;
            if(LAMS.vertexViewEnable){
                canvas.yAxisColor = LAMS.yAxisColorVertexView;
            }
            if(LAMS.wireframeViewEnable){
                canvas.yAxisColor = LAMS.yAxisColorWireframeView;
            }
            if(LAMS.solidViewEnable){
                canvas.yAxisColor = LAMS.yAxisColorSolidView;
            }
        }
        else{
            canvas.yAxisEnable = false;
        }
        if((LAMS.vertexViewEnable && LAMS.zAxisEnableVertexView) || (LAMS.wireframeViewEnable && LAMS.zAxisEnableWireframeView) || (LAMS.solidViewEnable && LAMS.zAxisEnableSolidView)){
            canvas.zAxisEnable = true;
            if(LAMS.vertexViewEnable){
                canvas.zAxisColor = LAMS.zAxisColorVertexView;
            }
            if(LAMS.wireframeViewEnable){
                canvas.zAxisColor = LAMS.zAxisColorWireframeView;
            }
            if(LAMS.solidViewEnable){
                canvas.zAxisColor = LAMS.zAxisColorSolidView;
            }
        }
        else{
            canvas.zAxisEnable = false;
        }
        if((LAMS.vertexViewEnable && LAMS.xyGridEnableVertexView) || (LAMS.wireframeViewEnable && LAMS.xyGridEnableWireframeView) || (LAMS.solidViewEnable && LAMS.xyGridEnableSolidView)){
            canvas.xyGridEnable = true;
            if(LAMS.vertexViewEnable){
                canvas.xyGridColor = LAMS.xyGridColorVertexView;
            }
            if(LAMS.wireframeViewEnable){
                canvas.xyGridColor = LAMS.xyGridColorWireframeView;
            }
            if(LAMS.solidViewEnable){
                canvas.xyGridColor = LAMS.xyGridColorSolidView;
            }
        }
        else{
            canvas.xyGridEnable = false;
        }
    }

    public void display() {
        if(this.objectInteriorView){
            if(this.focusedObject!=null){
                //TODO:setup interior view
            }
            else{
                //TODO:disable interior view
            }
            createGrid(50,25);
        }
    }

    public void reshape(int width, int height) {
        this.height = height;
        this.width = width;
        this.canvas.setSize(width,height);
    }
    
    /**
     * displayObjects will cycle through all objects and display them using all relevant settings.
     * @param gl - GL2 object. 
     */
    public void displayObjects(){
        //cycle through all objects stored in the variable this.objects
        for(Obj3D ob:this.objects){
            //offset calculates the value needed to add to each vertex to raise the whole object to have a flore at z=0
            double offset = ob.minZ.dz*-1;
            //lighting needs to be disabled for vertex and edges to be displayed correctly.
            CartesianCoordinate cc;
            CartesianVector cv;
            //check which view is enabled and display the objects using that view.
            //if(this.vertexViewEnable){
                for(CartesianCoordinate tempCC:ob.vertices){
                    this.canvas.addPoint(tempCC,LAMS.pointColorVertexView,10);
                }
            //}
            //else if(this.wireframeViewEnable){
                for(Edge e:ob.edges){
                    this.canvas.addLine(e.a,e.b,Color.white);
                }
            //}
            //else if(this.solidViewEnable){
                if(this.objectInteriorView){
                    //TODO:fix normals
                }
                else{
                    //TODO:fix normals
                }
                for(converter.Face f:ob.faces){

                    if(f.isTriangle){
                        this.canvas.addFace(f.v1.vertex,f.v2.vertex,f.v3.vertex,Color.white);
                    }
                    else{
                        this.canvas.addFace(f.v1.vertex,f.v2.vertex,f.v3.vertex,f.v4.vertex,Color.white);
                    }
                }
            //}
            //check if the settings enable the spine view and draw the spine if this.spineEnableObjViewer is true
            if(LAMS.spineEnableSolidView){
                for(int i = 0;i<ob.spine.size();i++){
                    if(ob.spine.size()>1 && i>0){
                        //this.canvas.addLine(ob.spine.get(i),ob.spine.get(i-1),this.spineColorObjViewer);
                    }
                    
                }
            }
        }
    }
    
    /*public void drawPointCloud(GL2 gl, ArrayList<CartesianCoordinate> cartesian){
        gl.glColor3f(this.pointColorPointCloudViewer.getRed()/255.0f,this.pointColorPointCloudViewer.getGreen()/255.0f,this.pointColorPointCloudViewer.getBlue()/255.0f);
        gl.glPointSize(10);
        gl.glBegin(GL2.GL_POINTS);
        for(CartesianCoordinate cc:cartesian){
            gl.glVertex3f(cc.x, cc.y, cc.z);
        }
        gl.glEnd();
    }*/
    
    public void importLamFile(File lamFile){
        if(!lamFile.equals(null)){
            //this.pointCloudFlag=true;
            this.lamConverter.convert(lamFile);
            Obj3D obj = new Obj3D();
            Map<Float,ArrayList<CartesianCoordinate>> xSlices = new HashMap<Float,ArrayList<CartesianCoordinate>>();
            for(CartesianCoordinate vertexCC:lamConverter.cartesian){
                Float xSliceRef = new Float(Math.floor(new Float(vertexCC.dx).doubleValue() * 10) / 10);
                        ArrayList<CartesianCoordinate> xSlicesVal;
                        if(xSlices.containsKey(xSliceRef)){
                            xSlicesVal = xSlices.get(xSliceRef);
                            xSlicesVal.add(vertexCC);
                            xSlices.put(xSliceRef, xSlicesVal);
                        }
                        else{
                            xSlicesVal = new ArrayList<CartesianCoordinate>();
                            xSlicesVal.add(vertexCC);
                            xSlices.put(xSliceRef, xSlicesVal);
                        }
                        if(vertexCC.dx>obj.maxX.dx){
                            obj.maxX = vertexCC;
                        }
                        else if(vertexCC.dx<obj.minX.dx){
                            obj.minX = vertexCC;
                        }
                        else if(vertexCC.dy>obj.maxY.dy){
                            obj.maxY = vertexCC;
                        }
                        else if(vertexCC.dy<obj.minY.dy){
                            obj.minY = vertexCC;
                        }
                        else if(vertexCC.dz>obj.maxZ.dz){
                            obj.maxZ = vertexCC;
                        }
                        else if(vertexCC.dz<obj.minZ.dz){
                            obj.minZ = vertexCC;
                        }
                        obj.addVertex(vertexCC);
            }
            obj.center = new CartesianCoordinate(obj.minX.dx+(obj.maxX.dx-obj.minX.dx)/2, obj.minY.dy+(obj.maxY.dy-obj.minY.dy)/2, obj.minZ.dx+(obj.maxZ.dz-obj.minZ.dz)/2);
            for(Float f:xSlices.keySet()){
                ArrayList<CartesianCoordinate> slice = xSlices.get(f);
                double maxY = slice.get(0).dy;
                double minY = slice.get(0).dy;
                double maxZ = slice.get(0).dz;
                double minZ = slice.get(0).dz;
                for(CartesianCoordinate xSlicesCC:slice){
                    if(xSlicesCC.dy>maxY){
                        maxY=xSlicesCC.dy;
                    }
                    else if(xSlicesCC.dy<minY){
                        minY=xSlicesCC.dy;
                    }
                    if(xSlicesCC.dz > maxZ){
                        maxZ = xSlicesCC.dz;
                    }
                    else if(xSlicesCC.dz < minZ){
                        minZ = xSlicesCC.dz;
                    }
                }
                CartesianCoordinate tempSpineCC = new CartesianCoordinate(f,minY+(maxY-minY)/2,minZ+(maxZ-minZ)/2);
                obj.spine.add(tempSpineCC);
            }
            this.objects.add(obj);
            this.focusedObject = obj;
            this.displayObjects();
            //JOptionPane.showMessageDialog(frame, "When point cloud review is complete, hit the Enter key to continue.");
        }
    }
    
    public void createGrid(int gridSize, int gridLineSize){
        if((LAMS.xyGridEnableVertexView && this.vertexViewEnable) || (LAMS.xyGridEnableWireframeView && this.wireframeViewEnable) || (LAMS.xyGridEnableSolidView && this.solidViewEnable)){
            for(int i = 0;i<=10;i++){
                for(int j = 0;j<10;j++){
                    int p1 = canvas.addPoint(new CartesianCoordinate(10*(i-5),0.0f,50),Color.white,1);
                    int p2 = canvas.addPoint(new CartesianCoordinate(10*(i-5),0.0f,-50),Color.white,1);
                    int p3 = canvas.addPoint(new CartesianCoordinate(50,0.0f,10*(i-5)),Color.white,1);
                    int p4 = canvas.addPoint(new CartesianCoordinate(-50,0.0f,10*(i-5)),Color.white,1);
                    if(this.vertexViewEnable){
                        canvas.addLine(p1,p2,LAMS.xyGridColorVertexView);
                        canvas.addLine(p3,p4,LAMS.xyGridColorVertexView);
                    }
                    else if(this.wireframeViewEnable){
                        canvas.addLine(p1,p2,LAMS.xyGridColorWireframeView);
                        canvas.addLine(p3,p4,LAMS.xyGridColorWireframeView);
                    }
                    else if(this.solidViewEnable){
                        canvas.addLine(p1,p2,LAMS.xyGridColorSolidView);
                        canvas.addLine(p3,p4,LAMS.xyGridColorSolidView);
                    }
                    
                }
            }
        }
        else if (LAMS.xyGridEnableSolidView){
            for(int i = 0;i<=10;i++){
                for(int j = 0;j<10;j++){
                    int p1 = canvas.addPoint(new CartesianCoordinate(10*(i-5),0.0f,50),Color.white,1);
                    int p2 = canvas.addPoint(new CartesianCoordinate(10*(i-5),0.0f,-50),Color.white,1);
                    int p3 = canvas.addPoint(new CartesianCoordinate(50,0.0f,10*(i-5)),Color.white,1);
                    int p4 = canvas.addPoint(new CartesianCoordinate(-50,0.0f,10*(i-5)),Color.white,1);
                    canvas.addLine(p1,p2,LAMS.xyGridColorVertexView);
                    canvas.addLine(p3,p4,LAMS.xyGridColorVertexView);
                }
            }
        }
        if((this.vertexViewEnable && LAMS.zAxisEnableVertexView)){
            canvas.addLine(canvas.addPoint(new CartesianCoordinate(0.0,0.0,200.0),Color.white,1),canvas.addPoint(new CartesianCoordinate(0.0,0.0,-200),Color.white,1),LAMS.zAxisColorVertexView);
        }
        else if((this.wireframeViewEnable && LAMS.zAxisEnableWireframeView)){
            canvas.addLine(canvas.addPoint(new CartesianCoordinate(0.0,0.0,200.0),Color.white,1),canvas.addPoint(new CartesianCoordinate(0.0,0.0,-200),Color.white,1),LAMS.zAxisColorWireframeView);
        }
        else if((this.solidViewEnable && LAMS.zAxisEnableSolidView)){
            canvas.addLine(canvas.addPoint(new CartesianCoordinate(0.0,0.0,200.0),Color.white,1),canvas.addPoint(new CartesianCoordinate(0.0,0.0,-200),Color.white,1),LAMS.zAxisColorSolidView);
        }
        
        if((this.vertexViewEnable && LAMS.yAxisEnableVertexView)){
            canvas.addLine(canvas.addPoint(new CartesianCoordinate(0.0,200.0,0.0),Color.white,1),canvas.addPoint(new CartesianCoordinate(0.0,-200,0),Color.white,1),LAMS.yAxisColorVertexView);
        }
        else if((this.wireframeViewEnable && LAMS.yAxisEnableWireframeView)){
            canvas.addLine(canvas.addPoint(new CartesianCoordinate(0.0,200.0,0.0),Color.white,1),canvas.addPoint(new CartesianCoordinate(0.0,-200,0),Color.white,1),LAMS.yAxisColorWireframeView);
        }
        else if((this.solidViewEnable && LAMS.yAxisEnableSolidView)){
            canvas.addLine(canvas.addPoint(new CartesianCoordinate(0.0,200.0,0.0),Color.white,1),canvas.addPoint(new CartesianCoordinate(0.0,-200,0),Color.white,1),LAMS.yAxisColorSolidView);
        }
        
        if((this.vertexViewEnable && LAMS.xAxisEnableVertexView)){
            canvas.addLine(canvas.addPoint(new CartesianCoordinate(200,0.0,0.0),Color.white,1),canvas.addPoint(new CartesianCoordinate(-200,0,0),Color.white,1),LAMS.xAxisColorVertexView);
        }
        else if((this.wireframeViewEnable && LAMS.xAxisEnableWireframeView)){
            canvas.addLine(canvas.addPoint(new CartesianCoordinate(200,0.0,0.0),Color.white,1),canvas.addPoint(new CartesianCoordinate(-200,0,0),Color.white,1),LAMS.xAxisColorWireframeView);
        }
        else if((this.solidViewEnable && LAMS.xAxisEnableSolidView)){
            canvas.addLine(canvas.addPoint(new CartesianCoordinate(200,0.0,0.0),Color.white,1),canvas.addPoint(new CartesianCoordinate(-200,0,0),Color.white,1),this.xAxisColorSolidView);
        }
    }
    
    public void calculateNormals(){
        for(Obj3D ob:this.objects){
            CartesianCoordinate cc;
            CartesianVector cv;
                for(CartesianCoordinate tempCC:ob.vertices){
                    double a = Math.sqrt((tempCC.dx*tempCC.dx) + (tempCC.dy * tempCC.dy) + (tempCC.dz * tempCC.dz));
                    cv = new CartesianVector(tempCC.dx/a,tempCC.dy/a,tempCC.dz/a);
                    tempCC.normal = cv;
                }
                
        }
    }
    
    public void importObjFile(File objFile){
        try {
            Obj3D obj = new Obj3D();
            Map<Float,ArrayList<CartesianCoordinate>> xSlices = new HashMap<Float,ArrayList<CartesianCoordinate>>();
            String str;
            String [] temp;
            Scanner s = new Scanner(objFile);
            while(s.hasNextLine()){
                str = s.nextLine();
                if(!str.equals("")){
                    str = str.replace('\t', ' ');
                    str = str.replace("  ", " ");
                    str = str.replace("  ", " ");
                    temp = str.split(" ");
                    if(temp[0].equals("v")){
                        CartesianCoordinate vertexCC = new CartesianCoordinate(new Float(temp[1]), new Float(temp[2]), new Float(temp[3]));
                        Float xSliceRef = new Float(Math.floor(new Float(vertexCC.dx).doubleValue() * 10) / 10);
                        ArrayList<CartesianCoordinate> xSlicesVal;
                        if(xSlices.containsKey(xSliceRef)){
                            xSlicesVal = xSlices.get(xSliceRef);
                            xSlicesVal.add(vertexCC);
                            xSlices.put(xSliceRef, xSlicesVal);
                        }
                        else{
                            xSlicesVal = new ArrayList<CartesianCoordinate>();
                            xSlicesVal.add(vertexCC);
                            xSlices.put(xSliceRef, xSlicesVal);
                        }
                        if(vertexCC.dx>obj.maxX.dx){
                            obj.maxX = vertexCC;
                        }
                        else if(vertexCC.dx<obj.minX.dx){
                            obj.minX = vertexCC;
                        }
                        else if(vertexCC.dy>obj.maxY.dy){
                            obj.maxY = vertexCC;
                        }
                        else if(vertexCC.dy<obj.minY.dy){
                            obj.minY = vertexCC;
                        }
                        else if(vertexCC.dz>obj.maxZ.dz){
                            obj.maxZ = vertexCC;
                        }
                        else if(vertexCC.dz<obj.minZ.dz){
                            obj.minZ = vertexCC;
                        }
                        obj.addVertex(vertexCC);
                    }
                    else if(temp[0].equals("vt")){
                        obj.addTexture(new Float(temp[1]), new Float(temp[2]));
                    }
                    else if(temp[0].equals("vn")){
                        obj.addNormalVertex(new Float(temp[1]), new Float(temp[2]), new Float(temp[3]));  
                    }
                    else if(temp[0].equals("e")){
                        obj.addEdge(new Edge(Integer.parseInt(temp[1]),Integer.parseInt(temp[2])));
                    }
                    else if(temp[0].equals("f")){
                        ArrayList<FaceVertex> fv = new ArrayList<FaceVertex>();
                        for(int i=1;i<temp.length;i++){
                            String [] verString=temp[i].split("/");
                            if(verString.length==3){
                                fv.add(new FaceVertex((Integer.parseInt(verString[0])-1),(Integer.parseInt(verString[1])-1),(Integer.parseInt(verString[2])-1))); 
                            }
                            else if(verString.length == 2){
                                fv.add(new FaceVertex((Integer.parseInt(verString[0])-1),(Integer.parseInt(verString[1])-1)));
                            }
                            else if(verString.length==1){
                                fv.add(new FaceVertex((Integer.parseInt(verString[0])-1)));
                            }
                        }
                        if(fv.size()==4){
                            converter.Face f = new converter.Face(fv.get(0), fv.get(1), fv.get(2), fv.get(3));
                            //f.calculateFaceNormal();
                            obj.addFace(f); 
                        }
                        else if(fv.size()==3){
                            converter.Face f = new converter.Face(fv.get(0), fv.get(1), fv.get(2));
                            //f.calculateFaceNormal();
                            obj.addFace(f); 
                        }
                         
                    }
                }
            }
            obj.center = new CartesianCoordinate(obj.minX.dx+(obj.maxX.dx-obj.minX.dx)/2, obj.minY.dy+(obj.maxY.dy-obj.minY.dy)/2, obj.minZ.dx+(obj.maxZ.dz-obj.minZ.dz)/2);
            s.close();
            for(Float f:xSlices.keySet()){
                ArrayList<CartesianCoordinate> slice = xSlices.get(f);
                double maxY = slice.get(0).dy;
                double minY = slice.get(0).dy;
                double maxZ = slice.get(0).dz;
                double minZ = slice.get(0).dz;
                for(CartesianCoordinate xSlicesCC:slice){
                    if(xSlicesCC.dy>maxY){
                        maxY=xSlicesCC.dy;
                    }
                    else if(xSlicesCC.dy<minY){
                        minY=xSlicesCC.dy;
                    }
                    if(xSlicesCC.dz > maxZ){
                        maxZ = xSlicesCC.dz;
                    }
                    else if(xSlicesCC.dz < minZ){
                        minZ = xSlicesCC.dz;
                    }
                }
                CartesianCoordinate tempSpineCC = new CartesianCoordinate(f,minY+(maxY-minY)/2,minZ+(maxZ-minZ)/2);
                obj.spine.add(tempSpineCC);
            }
            this.objects.add(obj);
            this.focusedObject = obj;
            this.displayObjects();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String removeSpaces(String str){
        String result = "";
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)!=' '){
                result+=str.charAt(i);
            }
        }
        return result;
    }
    
    public String getColorString(Color color){
        return color.getRed() + "," + color.getGreen() + "," + color.getBlue();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
