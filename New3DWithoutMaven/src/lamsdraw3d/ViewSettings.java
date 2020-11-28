/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package lamsdraw3d;

import java.awt.Color;
import javax.swing.JColorChooser;

public class ViewSettings extends javax.swing.JFrame {
    
    public void parseViewSettingsArgs(String args){
        String [] colors = args.split(":");
        String [] temp = colors[0].split(",");
        this.solidViewBackgroundColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[1].split(",");
        this.solidViewXAxisColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[2].split(",");
        this.solidViewYAxisColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[3].split(",");
        this.solidViewZAxisColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[4].split(",");
        this.solidViewXYGridColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[5].split(",");
        this.solidViewSpineColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[6].split(",");
        this.vertexViewBackgroundColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[7].split(",");
        this.vertexViewPointColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[8].split(",");
        this.vertexViewXAxisColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[9].split(",");
        this.vertexViewYAxisColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[10].split(",");
        this.vertexViewZAxisColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[11].split(",");
        this.vertexViewXYGridColor = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
    }
    
    public void setViewSettingsColors(Color bgcSolidView, Color xacSolidView, Color yacSolidView, Color zacSolidView, Color xygcSolidView, Color scSolidView,
        Color bgcWireframeView, Color xacWireframeView, Color yacWireframeView, Color zacWireframeView, Color xygcWireframeView,
        Color bgcVertexView, Color pcVertexView, Color xacVertexView, Color yacVertexView, Color zacVertexView, Color xygcVertexView){
        //solidView colors
        this.solidViewBackgroundColor = bgcSolidView;
        this.solidViewXAxisColor = xacSolidView;
        this.solidViewYAxisColor = yacSolidView;
        this.solidViewZAxisColor = zacSolidView;
        this.solidViewXYGridColor = xygcSolidView;
        this.solidViewSpineColor = scSolidView;
        //wireframeView colors
        this.wireframeViewBackgroundColor = bgcWireframeView;
        this.wireframeViewXAxisColor = xacWireframeView;
        this.wireframeViewYAxisColor = yacWireframeView;
        this.wireframeViewZAxisColor = zacWireframeView;
        this.wireframeViewXYGridColor = xygcWireframeView;
        //vertexView colors
        this.vertexViewBackgroundColor = bgcVertexView;
        this.vertexViewPointColor = pcVertexView;
        this.vertexViewXAxisColor = xacVertexView;
        this.vertexViewYAxisColor = yacVertexView;
        this.vertexViewZAxisColor = zacVertexView;
        this.solidViewXYGridColor = xygcSolidView;
        
        this.setLabelColors();
    }
    
    public void setViewSettingsBool(boolean xaeSolidView, boolean yaeSolidView, boolean zaeSolidView, boolean xygeSolidView, boolean seSolidView,
            boolean xaeWireframeView, boolean yaeWireframeView, boolean zaeWireframeView, boolean xygeWireframeView,
            boolean xaeVertexView, boolean yaeVertexView, boolean zaeVertexView, boolean xygeVertexView){
        this.solidViewXAxisCheckBox.setSelected(xaeSolidView);
        this.solidViewYAxisCheckBox.setSelected(yaeSolidView);
        this.solidViewZAxisCheckBox.setSelected(zaeSolidView);
        this.solidViewXYGridCheckBox.setSelected(xygeSolidView);
        this.spineCheckBox.setSelected(seSolidView);
        this.wireframeViewXAxisCheckBox.setSelected(xaeWireframeView);
        this.wireframeViewYAxisCheckBox.setSelected(yaeWireframeView);
        this.wireframeViewZAxisCheckBox.setSelected(zaeWireframeView);
        this.wireframeViewXYGridCheckBox.setSelected(xygeWireframeView);
        this.vertexViewXAxisCheckBox.setSelected(xaeVertexView);
        this.vertexViewYAxisCheckBox.setSelected(yaeVertexView);
        this.vertexViewZAxisCheckBox.setSelected(zaeVertexView);
        this.vertexViewXYGridCheckBox.setSelected(xygeVertexView);
        
       this.jTextField1.setText(Integer.toString(LAMS.pointSize));
    }
    
    public void setLabelColors(){
        solidViewBackgroundColorLabel.setBackground(this.solidViewBackgroundColor);
        solidViewXAxisColorLabel.setBackground(solidViewXAxisColor);
        solidViewYAxisColorLabel.setBackground(solidViewYAxisColor);
        solidViewZAxisColorLabel.setBackground(solidViewZAxisColor);
        solidViewXYGridColorLabel.setBackground(solidViewXYGridColor);
        spineColorLabel.setBackground(solidViewSpineColor);
        wireframeViewBackgroundColorLabel.setBackground(wireframeViewBackgroundColor);
        wireframeViewXAxisColorLabel.setBackground(wireframeViewXAxisColor);
        wireframeViewYAxisColorLabel.setBackground(wireframeViewYAxisColor);
        wireframeViewZAxisColorLabel.setBackground(wireframeViewZAxisColor);
        wireframeViewXYGridColorLabel.setBackground(wireframeViewXYGridColor);
        vertexViewBackgroundColorLabel.setBackground(this.vertexViewBackgroundColor);
        vertexViewXAxisColorLabel.setBackground(vertexViewXAxisColor);
        vertexViewYAxisColorLabel.setBackground(vertexViewYAxisColor);
        vertexViewZAxisColorLabel.setBackground(vertexViewZAxisColor);
        vertexViewXYGridColorLabel.setBackground(vertexViewXYGridColor);
        vertexViewVerticesColorLabel.setBackground(vertexViewPointColor);
    }
    
    /** Creates new form Find */
    public ViewSettings() {
        //this.parseViewSettingsArgs(args);
        //this.setViewSettings();
        initComponents();
        
        /*this.solidViewXAxisCheckBox.setSelected(LAMS.xAxisEnableSolidView);
        this.solidViewYAxisCheckBox.setSelected(LAMS.yAxisEnableSolidView);
        this.solidViewZAxisCheckBox.setSelected(LAMS.zAxisEnableSolidView);
        this.solidViewXYGridCheckBox.setSelected(LAMS.xyGridEnableSolidView);
        this.spineCheckBox.setSelected(LAMS.spineEnableSolidView);
        this.wireframeViewXAxisCheckBox.setSelected(LAMS.xAxisEnableWireframeView);
        this.wireframeViewYAxisCheckBox.setSelected(LAMS.yAxisEnableWireframeView);
        this.wireframeViewZAxisCheckBox.setSelected(LAMS.zAxisEnableWireframeView);
        this.wireframeViewXYGridCheckBox.setSelected(LAMS.xyGridEnableWireframeView);
        this.vertexViewXAxisCheckBox.setSelected(LAMS.xAxisEnableVertexView);
        this.vertexViewYAxisCheckBox.setSelected(LAMS.yAxisEnableVertexView);
        this.vertexViewZAxisCheckBox.setSelected(LAMS.zAxisEnableVertexView);
        this.vertexViewXYGridCheckBox.setSelected(LAMS.xyGridEnableVertexView);*/
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        solidViewXAxisCheckBox = new javax.swing.JCheckBox();
        solidViewYAxisCheckBox = new javax.swing.JCheckBox();
        solidViewZAxisCheckBox = new javax.swing.JCheckBox();
        solidViewXYGridCheckBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        solidViewXAxisColorLabel = new javax.swing.JLabel();
        solidViewYAxisColorLabel = new javax.swing.JLabel();
        solidViewZAxisColorLabel = new javax.swing.JLabel();
        solidViewXYGridColorLabel = new javax.swing.JLabel();
        solidViewBackgroundColorLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        vertexViewXAxisCheckBox = new javax.swing.JCheckBox();
        vertexViewYAxisCheckBox = new javax.swing.JCheckBox();
        vertexViewZAxisCheckBox = new javax.swing.JCheckBox();
        vertexViewXYGridCheckBox = new javax.swing.JCheckBox();
        vertexViewXAxisColorLabel = new javax.swing.JLabel();
        vertexViewYAxisColorLabel = new javax.swing.JLabel();
        vertexViewZAxisColorLabel = new javax.swing.JLabel();
        vertexViewXYGridColorLabel = new javax.swing.JLabel();
        vertexViewVerticesColorLabel = new javax.swing.JLabel();
        vertexViewBackgroundColorLabel = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        spineCheckBox = new javax.swing.JCheckBox();
        spineColorLabel = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        wireframeViewBackgroundColorLabel = new javax.swing.JLabel();
        wireframeViewXAxisCheckBox = new javax.swing.JCheckBox();
        wireframeViewXAxisColorLabel = new javax.swing.JLabel();
        wireframeViewYAxisCheckBox = new javax.swing.JCheckBox();
        wireframeViewYAxisColorLabel = new javax.swing.JLabel();
        wireframeViewZAxisCheckBox = new javax.swing.JCheckBox();
        wireframeViewZAxisColorLabel = new javax.swing.JLabel();
        wireframeViewXYGridCheckBox = new javax.swing.JCheckBox();
        wireframeViewXYGridColorLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Settings");

        solidViewXAxisCheckBox.setText("Show X Axis");
        solidViewXAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solidViewXAxisCheckBoxActionPerformed(evt);
            }
        });

        solidViewYAxisCheckBox.setText("Show Y Axis");
        solidViewYAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solidViewYAxisCheckBoxActionPerformed(evt);
            }
        });

        solidViewZAxisCheckBox.setText("Show Z Axis");
        solidViewZAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solidViewZAxisCheckBoxActionPerformed(evt);
            }
        });

        solidViewXYGridCheckBox.setText("Show XY Grid");
        solidViewXYGridCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                solidViewXYGridCheckBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Solid Object Viewer Settings");

        solidViewXAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        solidViewXAxisColorLabel.setOpaque(true);
        solidViewXAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solidViewXAxisColorLabelMouseClicked(evt);
            }
        });

        solidViewYAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        solidViewYAxisColorLabel.setOpaque(true);
        solidViewYAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solidViewYAxisColorLabelMouseClicked(evt);
            }
        });

        solidViewZAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        solidViewZAxisColorLabel.setOpaque(true);
        solidViewZAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solidViewZAxisColorLabelMouseClicked(evt);
            }
        });

        solidViewXYGridColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        solidViewXYGridColorLabel.setOpaque(true);
        solidViewXYGridColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solidViewXYGridColorLabelMouseClicked(evt);
            }
        });

        solidViewBackgroundColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        solidViewBackgroundColorLabel.setOpaque(true);
        solidViewBackgroundColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                solidViewBackgroundColorLabelMouseClicked(evt);
            }
        });

        jLabel1.setText("Background");

        jLabel3.setText("Vertex View Settings ");

        jLabel4.setText("Background");

        vertexViewXAxisCheckBox.setText("Show X Axis");
        vertexViewXAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertexViewXAxisCheckBoxActionPerformed(evt);
            }
        });

        vertexViewYAxisCheckBox.setText("Show Y Axis");
        vertexViewYAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertexViewYAxisCheckBoxActionPerformed(evt);
            }
        });

        vertexViewZAxisCheckBox.setText("Show Z Axis");
        vertexViewZAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertexViewZAxisCheckBoxActionPerformed(evt);
            }
        });

        vertexViewXYGridCheckBox.setText("Show XY Grid");
        vertexViewXYGridCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vertexViewXYGridCheckBoxActionPerformed(evt);
            }
        });

        vertexViewXAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        vertexViewXAxisColorLabel.setOpaque(true);
        vertexViewXAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vertexViewXAxisColorLabelMouseClicked(evt);
            }
        });

        vertexViewYAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        vertexViewYAxisColorLabel.setOpaque(true);
        vertexViewYAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vertexViewYAxisColorLabelMouseClicked(evt);
            }
        });

        vertexViewZAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        vertexViewZAxisColorLabel.setOpaque(true);
        vertexViewZAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vertexViewZAxisColorLabelMouseClicked(evt);
            }
        });

        vertexViewXYGridColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        vertexViewXYGridColorLabel.setOpaque(true);
        vertexViewXYGridColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vertexViewXYGridColorLabelMouseClicked(evt);
            }
        });

        vertexViewVerticesColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        vertexViewVerticesColorLabel.setOpaque(true);
        vertexViewVerticesColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vertexViewVerticesColorLabelMouseClicked(evt);
            }
        });

        vertexViewBackgroundColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        vertexViewBackgroundColorLabel.setOpaque(true);
        vertexViewBackgroundColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vertexViewBackgroundColorLabelMouseClicked(evt);
            }
        });

        jLabel9.setText("Point Cloud Vertices");

        spineCheckBox.setText("Show Spine");
        spineCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                spineCheckBoxActionPerformed(evt);
            }
        });

        spineColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        spineColorLabel.setOpaque(true);
        spineColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                spineColorLabelMouseClicked(evt);
            }
        });

        jLabel5.setText("Wireframe Viewer Settings");

        jLabel6.setText("Background");

        wireframeViewBackgroundColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        wireframeViewBackgroundColorLabel.setOpaque(true);
        wireframeViewBackgroundColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wireframeViewBackgroundColorLabelMouseClicked(evt);
            }
        });

        wireframeViewXAxisCheckBox.setText("Show X Axis");
        wireframeViewXAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wireframeViewXAxisCheckBoxActionPerformed(evt);
            }
        });

        wireframeViewXAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        wireframeViewXAxisColorLabel.setOpaque(true);
        wireframeViewXAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wireframeViewXAxisColorLabelMouseClicked(evt);
            }
        });

        wireframeViewYAxisCheckBox.setText("Show Y Axis");
        wireframeViewYAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wireframeViewYAxisCheckBoxActionPerformed(evt);
            }
        });

        wireframeViewYAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        wireframeViewYAxisColorLabel.setOpaque(true);
        wireframeViewYAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wireframeViewYAxisColorLabelMouseClicked(evt);
            }
        });

        wireframeViewZAxisCheckBox.setText("Show Z Axis");
        wireframeViewZAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wireframeViewZAxisCheckBoxActionPerformed(evt);
            }
        });

        wireframeViewZAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        wireframeViewZAxisColorLabel.setOpaque(true);
        wireframeViewZAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wireframeViewZAxisColorLabelMouseClicked(evt);
            }
        });

        wireframeViewXYGridCheckBox.setText("Show XY Grid");
        wireframeViewXYGridCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                wireframeViewXYGridCheckBoxActionPerformed(evt);
            }
        });

        wireframeViewXYGridColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        wireframeViewXYGridColorLabel.setOpaque(true);
        wireframeViewXYGridColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                wireframeViewXYGridColorLabelMouseClicked(evt);
            }
        });

        jLabel10.setText("Point Cloud Size");

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2))
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(solidViewZAxisCheckBox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(solidViewZAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(solidViewYAxisCheckBox)
                            .addGap(23, 23, 23)
                            .addComponent(solidViewYAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(solidViewXAxisCheckBox)
                            .addGap(22, 22, 22)
                            .addComponent(solidViewXAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(solidViewBackgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(spineCheckBox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(spineColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(solidViewXYGridCheckBox)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(solidViewXYGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(wireframeViewZAxisCheckBox)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(wireframeViewZAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(wireframeViewYAxisCheckBox)
                                    .addGap(23, 23, 23)
                                    .addComponent(wireframeViewYAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(wireframeViewXAxisCheckBox)
                                    .addGap(22, 22, 22)
                                    .addComponent(wireframeViewXAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(wireframeViewBackgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(wireframeViewXYGridCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(wireframeViewXYGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))))))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(vertexViewVerticesColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vertexViewBackgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(vertexViewZAxisCheckBox)
                                    .addComponent(vertexViewXYGridCheckBox)
                                    .addComponent(vertexViewYAxisCheckBox)
                                    .addComponent(vertexViewXAxisCheckBox))
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(vertexViewYAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vertexViewXAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vertexViewXYGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vertexViewZAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jLabel3)))
            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(solidViewBackgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(solidViewXAxisCheckBox)
                    .addComponent(solidViewXAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(solidViewYAxisCheckBox)
                    .addComponent(solidViewYAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(solidViewZAxisCheckBox)
                    .addComponent(solidViewZAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(solidViewXYGridCheckBox)
                    .addComponent(solidViewXYGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spineCheckBox)
                    .addComponent(spineColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel5)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(wireframeViewBackgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wireframeViewXAxisCheckBox)
                    .addComponent(wireframeViewXAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wireframeViewYAxisCheckBox)
                    .addComponent(wireframeViewYAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wireframeViewZAxisCheckBox)
                    .addComponent(wireframeViewZAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(wireframeViewXYGridCheckBox)
                    .addComponent(wireframeViewXYGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(vertexViewBackgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(vertexViewVerticesColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(vertexViewXAxisCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vertexViewYAxisCheckBox)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vertexViewZAxisCheckBox))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(vertexViewXAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(vertexViewYAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(vertexViewZAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addComponent(vertexViewXYGridCheckBox))
                    .addComponent(vertexViewXYGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(185, Short.MAX_VALUE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(372, 372, 372)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void solidViewBackgroundColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solidViewBackgroundColorLabelMouseClicked
        this.solidViewBackgroundColor = JColorChooser.showDialog(this, "Choose Background Color", this.solidViewBackgroundColor);
        LAMS.backgroundColorSolidView = this.solidViewBackgroundColor;
        //LAMS.setBackgroundColorSolidView(this.solidViewBackgroundColor);
        this.setLabelColors();
        LAMS.refresh();
    }//GEN-LAST:event_solidViewBackgroundColorLabelMouseClicked

    private void solidViewXAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solidViewXAxisColorLabelMouseClicked
        this.solidViewXAxisColor = JColorChooser.showDialog(this, "Choose X Axis Color", this.solidViewXAxisColor);
        LAMS.xAxisColorSolidView = this.solidViewXAxisColor;
        //LAMS.setXAxisSolidView(this.solidViewXAxisColor,true);
        this.setLabelColors();
        this.solidViewXAxisCheckBox.setSelected(true);
        LAMS.xAxisEnableSolidView = true;
        LAMS.refresh();
    }//GEN-LAST:event_solidViewXAxisColorLabelMouseClicked

    private void solidViewYAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solidViewYAxisColorLabelMouseClicked
        this.solidViewYAxisColor = JColorChooser.showDialog(this, "Choose Y Axis Color", this.solidViewYAxisColor);
        LAMS.yAxisColorSolidView = this.solidViewYAxisColor;
        this.setLabelColors();
        this.solidViewYAxisCheckBox.setSelected(true);
        LAMS.yAxisEnableSolidView = true;
        LAMS.refresh();
    }//GEN-LAST:event_solidViewYAxisColorLabelMouseClicked

    private void solidViewZAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solidViewZAxisColorLabelMouseClicked
        this.solidViewZAxisColor = JColorChooser.showDialog(this, "Choose Z Axis Color", this.solidViewZAxisColor);
        LAMS.zAxisColorSolidView = this.solidViewZAxisColor;
        this.setLabelColors();
        this.solidViewZAxisCheckBox.setSelected(true);
        LAMS.zAxisEnableSolidView = true;
        LAMS.refresh();
    }//GEN-LAST:event_solidViewZAxisColorLabelMouseClicked

    private void solidViewXYGridColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_solidViewXYGridColorLabelMouseClicked
        this.solidViewXYGridColor = JColorChooser.showDialog(this, "Choose Z Axis Color", this.solidViewXYGridColor);
        LAMS.xyGridColorSolidView = this.solidViewXYGridColor;
        this.setLabelColors();
        this.solidViewXYGridCheckBox.setSelected(true);
        LAMS.xyGridEnableSolidView = true;
        LAMS.refresh();
    }//GEN-LAST:event_solidViewXYGridColorLabelMouseClicked

    private void solidViewXAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solidViewXAxisCheckBoxActionPerformed
        LAMS.xAxisEnableSolidView = solidViewXAxisCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_solidViewXAxisCheckBoxActionPerformed

    private void solidViewYAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solidViewYAxisCheckBoxActionPerformed
        LAMS.yAxisEnableSolidView = solidViewYAxisCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_solidViewYAxisCheckBoxActionPerformed

    private void solidViewZAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solidViewZAxisCheckBoxActionPerformed
        LAMS.zAxisEnableSolidView = solidViewZAxisCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_solidViewZAxisCheckBoxActionPerformed

    private void solidViewXYGridCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_solidViewXYGridCheckBoxActionPerformed
        LAMS.xyGridEnableSolidView = solidViewXYGridCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_solidViewXYGridCheckBoxActionPerformed

    private void vertexViewBackgroundColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vertexViewBackgroundColorLabelMouseClicked
        this.vertexViewBackgroundColor = JColorChooser.showDialog(this, "Point Cloud Background Color Chooser", this.vertexViewBackgroundColor);
        LAMS.backgroundColorVertexView = this.vertexViewBackgroundColor;
        this.setLabelColors();
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewBackgroundColorLabelMouseClicked

    private void vertexViewVerticesColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vertexViewVerticesColorLabelMouseClicked
        this.vertexViewPointColor = JColorChooser.showDialog(this, "Point Cloud Color Chooser", this.vertexViewPointColor);
        LAMS.pointColorVertexView = this.vertexViewPointColor;
        this.setLabelColors();
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewVerticesColorLabelMouseClicked

    private void vertexViewXAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vertexViewXAxisColorLabelMouseClicked
        this.vertexViewXAxisColor = JColorChooser.showDialog(this, "Choose X Axis Color", this.vertexViewXAxisColor);
        LAMS.xAxisColorVertexView = this.vertexViewXAxisColor;
        this.setLabelColors();
        this.vertexViewXAxisCheckBox.setSelected(true);
        LAMS.xAxisEnableVertexView = true;
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewXAxisColorLabelMouseClicked

    private void vertexViewYAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vertexViewYAxisColorLabelMouseClicked
        this.vertexViewYAxisColor = JColorChooser.showDialog(this, "Choose Y Axis Color", this.vertexViewYAxisColor);
        LAMS.yAxisColorVertexView = this.vertexViewYAxisColor;
        this.setLabelColors();
        this.vertexViewYAxisCheckBox.setSelected(true);
        LAMS.yAxisEnableVertexView = true;
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewYAxisColorLabelMouseClicked

    private void vertexViewZAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vertexViewZAxisColorLabelMouseClicked
        this.vertexViewZAxisColor = JColorChooser.showDialog(this, "Choose Z Axis Color", this.vertexViewZAxisColor);
        LAMS.zAxisColorVertexView = this.vertexViewZAxisColor;
        this.setLabelColors();
        this.vertexViewZAxisCheckBox.setSelected(true);
        LAMS.zAxisEnableVertexView = true;
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewZAxisColorLabelMouseClicked

    private void vertexViewXYGridColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vertexViewXYGridColorLabelMouseClicked
        this.vertexViewXYGridColor = JColorChooser.showDialog(this, "Choose XY Grid Color", this.vertexViewXYGridColor);
        LAMS.xyGridColorVertexView = this.vertexViewXYGridColor;
        this.setLabelColors();
        this.vertexViewXYGridCheckBox.setSelected(true);
        LAMS.xyGridEnableVertexView = true;
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewXYGridColorLabelMouseClicked

    private void vertexViewXAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertexViewXAxisCheckBoxActionPerformed
        LAMS.xAxisEnableVertexView = this.vertexViewXAxisCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewXAxisCheckBoxActionPerformed

    private void vertexViewYAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertexViewYAxisCheckBoxActionPerformed
        LAMS.yAxisEnableVertexView = this.vertexViewYAxisCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewYAxisCheckBoxActionPerformed

    private void vertexViewZAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertexViewZAxisCheckBoxActionPerformed
        LAMS.zAxisEnableVertexView = this.vertexViewZAxisCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewZAxisCheckBoxActionPerformed

    private void vertexViewXYGridCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vertexViewXYGridCheckBoxActionPerformed
        LAMS.xyGridEnableVertexView = this.vertexViewXYGridCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_vertexViewXYGridCheckBoxActionPerformed

    private void spineCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spineCheckBoxActionPerformed
        LAMS.spineEnableSolidView = this.spineCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_spineCheckBoxActionPerformed

    private void spineColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spineColorLabelMouseClicked
        this.solidViewSpineColor = JColorChooser.showDialog(this, "Choose Object Spine Color", this.solidViewSpineColor);
        LAMS.spineColorSolidView = this.solidViewSpineColor;
        this.setLabelColors();
        this.spineCheckBox.setSelected(true);
        LAMS.spineEnableSolidView = true;
        LAMS.refresh();
    }//GEN-LAST:event_spineColorLabelMouseClicked

    private void wireframeViewBackgroundColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wireframeViewBackgroundColorLabelMouseClicked
        this.wireframeViewBackgroundColor = JColorChooser.showDialog(this, "Wireframe View Background Color Chooser", this.wireframeViewBackgroundColor);
        LAMS.backgroundColorWireframeView = this.wireframeViewBackgroundColor;
        this.setLabelColors();
        LAMS.refresh();
    }//GEN-LAST:event_wireframeViewBackgroundColorLabelMouseClicked

    private void wireframeViewXAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wireframeViewXAxisCheckBoxActionPerformed
        LAMS.xAxisEnableWireframeView = this.wireframeViewXAxisCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_wireframeViewXAxisCheckBoxActionPerformed

    private void wireframeViewXAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wireframeViewXAxisColorLabelMouseClicked
        this.wireframeViewXAxisColor = JColorChooser.showDialog(this, "Choose X Axis Color", this.wireframeViewXAxisColor);
        LAMS.xAxisColorWireframeView = this.wireframeViewXAxisColor;
        this.setLabelColors();
        this.wireframeViewXAxisCheckBox.setSelected(true);
        LAMS.xAxisEnableWireframeView = true;
        LAMS.refresh();
    }//GEN-LAST:event_wireframeViewXAxisColorLabelMouseClicked

    private void wireframeViewYAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wireframeViewYAxisCheckBoxActionPerformed
        LAMS.yAxisEnableWireframeView = this.wireframeViewYAxisCheckBox.isSelected();
    }//GEN-LAST:event_wireframeViewYAxisCheckBoxActionPerformed

    private void wireframeViewYAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wireframeViewYAxisColorLabelMouseClicked
        this.wireframeViewYAxisColor = JColorChooser.showDialog(this, "Choose Y Axis Color", this.wireframeViewYAxisColor);
        LAMS.yAxisColorWireframeView = this.wireframeViewYAxisColor;
        this.setLabelColors();
        this.wireframeViewYAxisCheckBox.setSelected(true);
        LAMS.yAxisEnableWireframeView = true;
        LAMS.refresh();
    }//GEN-LAST:event_wireframeViewYAxisColorLabelMouseClicked

    private void wireframeViewZAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wireframeViewZAxisCheckBoxActionPerformed
        LAMS.zAxisEnableWireframeView = this.wireframeViewZAxisCheckBox.isSelected();
        LAMS.refresh();
    }//GEN-LAST:event_wireframeViewZAxisCheckBoxActionPerformed

    private void wireframeViewZAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wireframeViewZAxisColorLabelMouseClicked
        this.wireframeViewZAxisColor = JColorChooser.showDialog(this, "Choose Z Axis Color", this.wireframeViewZAxisColor);
        LAMS.zAxisColorWireframeView = this.wireframeViewZAxisColor;
        this.setLabelColors();
        this.wireframeViewZAxisCheckBox.setSelected(true);
        LAMS.zAxisEnableWireframeView = true;
        LAMS.refresh();
    }//GEN-LAST:event_wireframeViewZAxisColorLabelMouseClicked

    private void wireframeViewXYGridCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_wireframeViewXYGridCheckBoxActionPerformed
        LAMS.xyGridEnableWireframeView = this.wireframeViewXYGridCheckBox.isSelected();
    }//GEN-LAST:event_wireframeViewXYGridCheckBoxActionPerformed

    private void wireframeViewXYGridColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_wireframeViewXYGridColorLabelMouseClicked
        this.wireframeViewXYGridColor = JColorChooser.showDialog(this, "Choose XY Grid Color", this.wireframeViewXYGridColor);
        LAMS.xyGridColorWireframeView = this.wireframeViewXYGridColor;
        this.setLabelColors();
        this.wireframeViewXYGridCheckBox.setSelected(true);
        LAMS.xyGridEnableWireframeView = true;
        LAMS.refresh();
    }//GEN-LAST:event_wireframeViewXYGridColorLabelMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        int pointSize = new Integer(jTextField1.getText());
        if(pointSize>0 && pointSize<20){
            LAMS.pointSize = pointSize;
            LAMS.refresh();
        }
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if(evt.getKeyCode() == 8 || evt.getKeyChar()>='0' && evt.getKeyChar()<='9'){
            jTextField1.setEditable(true);
        }
        else{
            jTextField1.setEditable(false);
        }
    }//GEN-LAST:event_jTextField1KeyPressed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        final String settingArgs = args[1];
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            javax.swing.UIManager.LookAndFeelInfo[] installedLookAndFeels=javax.swing.UIManager.getInstalledLookAndFeels();
            for (int idx=0; idx<installedLookAndFeels.length; idx++)
                if ("Nimbus".equals(installedLookAndFeels[idx].getName())) {
                    javax.swing.UIManager.setLookAndFeel(installedLookAndFeels[idx].getClassName());
                    break;
                }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewSettings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewSettings().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel solidViewBackgroundColorLabel;
    private javax.swing.JCheckBox solidViewXAxisCheckBox;
    private javax.swing.JLabel solidViewXAxisColorLabel;
    private javax.swing.JCheckBox solidViewXYGridCheckBox;
    private javax.swing.JLabel solidViewXYGridColorLabel;
    private javax.swing.JCheckBox solidViewYAxisCheckBox;
    private javax.swing.JLabel solidViewYAxisColorLabel;
    private javax.swing.JCheckBox solidViewZAxisCheckBox;
    private javax.swing.JLabel solidViewZAxisColorLabel;
    private javax.swing.JCheckBox spineCheckBox;
    private javax.swing.JLabel spineColorLabel;
    private javax.swing.JLabel vertexViewBackgroundColorLabel;
    private javax.swing.JLabel vertexViewVerticesColorLabel;
    private javax.swing.JCheckBox vertexViewXAxisCheckBox;
    private javax.swing.JLabel vertexViewXAxisColorLabel;
    private javax.swing.JCheckBox vertexViewXYGridCheckBox;
    private javax.swing.JLabel vertexViewXYGridColorLabel;
    private javax.swing.JCheckBox vertexViewYAxisCheckBox;
    private javax.swing.JLabel vertexViewYAxisColorLabel;
    private javax.swing.JCheckBox vertexViewZAxisCheckBox;
    private javax.swing.JLabel vertexViewZAxisColorLabel;
    private javax.swing.JLabel wireframeViewBackgroundColorLabel;
    private javax.swing.JCheckBox wireframeViewXAxisCheckBox;
    private javax.swing.JLabel wireframeViewXAxisColorLabel;
    private javax.swing.JCheckBox wireframeViewXYGridCheckBox;
    private javax.swing.JLabel wireframeViewXYGridColorLabel;
    private javax.swing.JCheckBox wireframeViewYAxisCheckBox;
    private javax.swing.JLabel wireframeViewYAxisColorLabel;
    private javax.swing.JCheckBox wireframeViewZAxisCheckBox;
    private javax.swing.JLabel wireframeViewZAxisColorLabel;
    // End of variables declaration//GEN-END:variables
    //solidView default colors
    private Color solidViewXAxisColor= Color.WHITE;
    private Color solidViewYAxisColor= Color.WHITE;
    private Color solidViewZAxisColor= Color.WHITE;
    private Color solidViewXYGridColor= Color.WHITE;
    private Color solidViewBackgroundColor= Color.WHITE;
    private Color solidViewSpineColor= Color.WHITE;
    //wireframeView default colors
    private Color wireframeViewXAxisColor= Color.WHITE;
    private Color wireframeViewYAxisColor= Color.WHITE;
    private Color wireframeViewZAxisColor= Color.WHITE;
    private Color wireframeViewXYGridColor= Color.WHITE;
    private Color wireframeViewBackgroundColor= Color.WHITE;
    //solidView default colors
    private Color vertexViewXAxisColor= Color.WHITE;
    private Color vertexViewYAxisColor= Color.WHITE;
    private Color vertexViewZAxisColor= Color.WHITE;
    private Color vertexViewXYGridColor= Color.WHITE;
    private Color vertexViewPointColor= Color.WHITE;
    private Color vertexViewBackgroundColor= Color.WHITE;
    private JColorChooser jcc= new JColorChooser();
}
