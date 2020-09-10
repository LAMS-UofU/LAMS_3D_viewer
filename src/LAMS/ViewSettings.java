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

package LAMS;

import java.awt.Color;
import javax.swing.JColorChooser;

public class ViewSettings extends javax.swing.JFrame {
    
    public void parseViewSettingsArgs(String args){
        String [] colors = args.split(":");
        String [] temp = colors[0].split(",");
        this.backgroundColorObjViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[1].split(",");
        this.xAxisColorObjViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[2].split(",");
        this.yAxisColorObjViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[3].split(",");
        this.zAxisColorObjViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[4].split(",");
        this.gridColorObjViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[5].split(",");
        this.spineColorObjViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[6].split(",");
        this.backgroundColorPointCloudViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[7].split(",");
        this.pointColorPointCloudViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[8].split(",");
        this.xAxisColorPointCloudViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[9].split(",");
        this.yAxisColorPointCloudViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[10].split(",");
        this.zAxisColorPointCloudViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
        temp = colors[11].split(",");
        this.gridColorPointCloudViewer = new Color(Integer.parseInt(temp[0]),Integer.parseInt(temp[1]),Integer.parseInt(temp[2]));
    }
    
    public void setLabelColors(){
        backgroundColorLabel.setBackground(this.backgroundColorObjViewer);
        xAxisColorLabel.setBackground(xAxisColorObjViewer);
        yAxisColorLabel.setBackground(yAxisColorObjViewer);
        zAxisColorLabel.setBackground(zAxisColorObjViewer);
        xyGridColorLabel.setBackground(gridColorObjViewer);
        spineColorLabel.setBackground(spineColorObjViewer);
        backgroundColorLabelPointCloud.setBackground(this.backgroundColorPointCloudViewer);
        xAxisColorLabelPointCloud.setBackground(xAxisColorPointCloudViewer);
        yAxisColorLabelPointCloud.setBackground(yAxisColorPointCloudViewer);
        zAxisColorLabelPointCloud.setBackground(zAxisColorPointCloudViewer);
        xyGridColorLabelPointCloud.setBackground(gridColorPointCloudViewer);
        verticesColorPointCloudColorLabel.setBackground(pointColorPointCloudViewer);
    }
    
    /** Creates new form Find */
    public ViewSettings(String args) {
        this.parseViewSettingsArgs(args);
        initComponents();
        this.setLabelColors();
        this.xAxisCheckBox.setSelected(LAMS.xAxisEnableObjViewer);
        this.yAxisCheckBox.setSelected(LAMS.yAxisEnableObjViewer);
        this.zAxisCheckBox.setSelected(LAMS.zAxisEnableObjViewer);
        this.xyGridCheckBox.setSelected(LAMS.xyGridEnableObjViewer);
        this.spineCheckBox.setSelected(LAMS.spineEnableObjViewer);
        this.xAxisCheckBoxPointCloud.setSelected(LAMS.xAxisEnablePointCloudViewer);
        this.yAxisCheckBoxPointCloud.setSelected(LAMS.yAxisEnablePointCloudViewer);
        this.zAxisCheckBoxPointCloud.setSelected(LAMS.zAxisEnablePointCloudViewer);
        this.xyGridCheckBoxPointCloud.setSelected(LAMS.xyGridEnablePointCloudViewer);
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xAxisCheckBox = new javax.swing.JCheckBox();
        yAxisCheckBox = new javax.swing.JCheckBox();
        zAxisCheckBox = new javax.swing.JCheckBox();
        xyGridCheckBox = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        xAxisColorLabel = new javax.swing.JLabel();
        yAxisColorLabel = new javax.swing.JLabel();
        zAxisColorLabel = new javax.swing.JLabel();
        xyGridColorLabel = new javax.swing.JLabel();
        backgroundColorLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        xAxisCheckBoxPointCloud = new javax.swing.JCheckBox();
        yAxisCheckBoxPointCloud = new javax.swing.JCheckBox();
        zAxisCheckBoxPointCloud = new javax.swing.JCheckBox();
        xyGridCheckBoxPointCloud = new javax.swing.JCheckBox();
        xAxisColorLabelPointCloud = new javax.swing.JLabel();
        yAxisColorLabelPointCloud = new javax.swing.JLabel();
        zAxisColorLabelPointCloud = new javax.swing.JLabel();
        xyGridColorLabelPointCloud = new javax.swing.JLabel();
        verticesColorPointCloudColorLabel = new javax.swing.JLabel();
        backgroundColorLabelPointCloud = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        spineCheckBox = new javax.swing.JCheckBox();
        spineColorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("View Settings");

        xAxisCheckBox.setText("Show X Axis");
        xAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xAxisCheckBoxActionPerformed(evt);
            }
        });

        yAxisCheckBox.setText("Show Y Axis");
        yAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yAxisCheckBoxActionPerformed(evt);
            }
        });

        zAxisCheckBox.setText("Show Z Axis");
        zAxisCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zAxisCheckBoxActionPerformed(evt);
            }
        });

        xyGridCheckBox.setText("Show XY Grid");
        xyGridCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xyGridCheckBoxActionPerformed(evt);
            }
        });

        jLabel2.setText("Object Viewer Orientation Guides");

        xAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        xAxisColorLabel.setOpaque(true);
        xAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xAxisColorLabelMouseClicked(evt);
            }
        });

        yAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        yAxisColorLabel.setOpaque(true);
        yAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yAxisColorLabelMouseClicked(evt);
            }
        });

        zAxisColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        zAxisColorLabel.setOpaque(true);
        zAxisColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zAxisColorLabelMouseClicked(evt);
            }
        });

        xyGridColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        xyGridColorLabel.setOpaque(true);
        xyGridColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xyGridColorLabelMouseClicked(evt);
            }
        });

        backgroundColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        backgroundColorLabel.setOpaque(true);
        backgroundColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backgroundColorLabelMouseClicked(evt);
            }
        });

        jLabel1.setText("Background");

        jLabel3.setText("Point Cloud View Settings ");

        jLabel4.setText("Background");

        xAxisCheckBoxPointCloud.setText("Show X Axis");
        xAxisCheckBoxPointCloud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xAxisCheckBoxPointCloudActionPerformed(evt);
            }
        });

        yAxisCheckBoxPointCloud.setText("Show Y Axis");
        yAxisCheckBoxPointCloud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yAxisCheckBoxPointCloudActionPerformed(evt);
            }
        });

        zAxisCheckBoxPointCloud.setText("Show Z Axis");
        zAxisCheckBoxPointCloud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zAxisCheckBoxPointCloudActionPerformed(evt);
            }
        });

        xyGridCheckBoxPointCloud.setText("Show XY Grid");
        xyGridCheckBoxPointCloud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xyGridCheckBoxPointCloudActionPerformed(evt);
            }
        });

        xAxisColorLabelPointCloud.setBackground(new java.awt.Color(255, 255, 255));
        xAxisColorLabelPointCloud.setOpaque(true);
        xAxisColorLabelPointCloud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xAxisColorLabelPointCloudMouseClicked(evt);
            }
        });

        yAxisColorLabelPointCloud.setBackground(new java.awt.Color(255, 255, 255));
        yAxisColorLabelPointCloud.setOpaque(true);
        yAxisColorLabelPointCloud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yAxisColorLabelPointCloudMouseClicked(evt);
            }
        });

        zAxisColorLabelPointCloud.setBackground(new java.awt.Color(255, 255, 255));
        zAxisColorLabelPointCloud.setOpaque(true);
        zAxisColorLabelPointCloud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                zAxisColorLabelPointCloudMouseClicked(evt);
            }
        });

        xyGridColorLabelPointCloud.setBackground(new java.awt.Color(255, 255, 255));
        xyGridColorLabelPointCloud.setOpaque(true);
        xyGridColorLabelPointCloud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xyGridColorLabelPointCloudMouseClicked(evt);
            }
        });

        verticesColorPointCloudColorLabel.setBackground(new java.awt.Color(255, 255, 255));
        verticesColorPointCloudColorLabel.setOpaque(true);
        verticesColorPointCloudColorLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                verticesColorPointCloudColorLabelMouseClicked(evt);
            }
        });

        backgroundColorLabelPointCloud.setBackground(new java.awt.Color(255, 255, 255));
        backgroundColorLabelPointCloud.setOpaque(true);
        backgroundColorLabelPointCloud.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backgroundColorLabelPointCloudMouseClicked(evt);
            }
        });

        jLabel9.setText("PointCloud Vertices");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(zAxisCheckBox)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(zAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(yAxisCheckBox)
                                    .addGap(23, 23, 23)
                                    .addComponent(yAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(xAxisCheckBox)
                                    .addGap(22, 22, 22)
                                    .addComponent(xAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(backgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(spineCheckBox)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(spineColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(xyGridCheckBox)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(xyGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(zAxisCheckBoxPointCloud)
                                    .addComponent(xyGridCheckBoxPointCloud)
                                    .addComponent(yAxisCheckBoxPointCloud)
                                    .addComponent(jLabel4)
                                    .addComponent(xAxisCheckBoxPointCloud)
                                    .addComponent(jLabel9))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(yAxisColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(xAxisColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(verticesColorPointCloudColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(xyGridColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(zAxisColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(backgroundColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jLabel2)
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(xAxisCheckBox)
                    .addComponent(xAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(yAxisCheckBox)
                    .addComponent(yAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(zAxisCheckBox)
                    .addComponent(zAxisColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(xyGridCheckBox)
                    .addComponent(xyGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spineCheckBox)
                    .addComponent(spineColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(backgroundColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xAxisCheckBoxPointCloud)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yAxisCheckBoxPointCloud)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(zAxisCheckBoxPointCloud))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(verticesColorPointCloudColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(xAxisColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(yAxisColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(zAxisColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(xyGridCheckBoxPointCloud))
                    .addComponent(xyGridColorLabelPointCloud, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backgroundColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundColorLabelMouseClicked
        this.backgroundColorObjViewer = JColorChooser.showDialog(this, "Choose Background Color", this.backgroundColorObjViewer);
        LAMS.backgroundColorObjViewer = this.backgroundColorObjViewer;
        this.setLabelColors();
    }//GEN-LAST:event_backgroundColorLabelMouseClicked

    private void xAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xAxisColorLabelMouseClicked
        this.xAxisColorObjViewer = JColorChooser.showDialog(this, "Choose X Axis Color", this.xAxisColorObjViewer);
        LAMS.xAxisColorObjViewer = this.xAxisColorObjViewer;
        this.setLabelColors();
        this.xAxisCheckBox.setSelected(true);
        LAMS.xAxisEnableObjViewer = true;
    }//GEN-LAST:event_xAxisColorLabelMouseClicked

    private void yAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yAxisColorLabelMouseClicked
        this.yAxisColorObjViewer = JColorChooser.showDialog(this, "Choose Y Axis Color", this.yAxisColorObjViewer);
        LAMS.yAxisColorObjViewer = this.yAxisColorObjViewer;
        this.setLabelColors();
        this.yAxisCheckBox.setSelected(true);
        LAMS.yAxisEnableObjViewer = true;
    }//GEN-LAST:event_yAxisColorLabelMouseClicked

    private void zAxisColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zAxisColorLabelMouseClicked
        this.zAxisColorObjViewer = JColorChooser.showDialog(this, "Choose Z Axis Color", this.zAxisColorObjViewer);
        LAMS.zAxisColorObjViewer = this.zAxisColorObjViewer;
        this.setLabelColors();
        this.zAxisCheckBox.setSelected(true);
        LAMS.zAxisEnableObjViewer = true;
    }//GEN-LAST:event_zAxisColorLabelMouseClicked

    private void xyGridColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xyGridColorLabelMouseClicked
        this.gridColorObjViewer = JColorChooser.showDialog(this, "Choose Z Axis Color", this.gridColorObjViewer);
        LAMS.xyGridColorObjViewer = this.gridColorObjViewer;
        this.setLabelColors();
        this.xyGridCheckBox.setSelected(true);
        LAMS.xyGridEnableObjViewer = true;
    }//GEN-LAST:event_xyGridColorLabelMouseClicked

    private void xAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xAxisCheckBoxActionPerformed
        LAMS.xAxisEnableObjViewer = xAxisCheckBox.isSelected();
    }//GEN-LAST:event_xAxisCheckBoxActionPerformed

    private void yAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yAxisCheckBoxActionPerformed
        LAMS.yAxisEnableObjViewer = yAxisCheckBox.isSelected();
    }//GEN-LAST:event_yAxisCheckBoxActionPerformed

    private void zAxisCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zAxisCheckBoxActionPerformed
        LAMS.zAxisEnableObjViewer = zAxisCheckBox.isSelected();
    }//GEN-LAST:event_zAxisCheckBoxActionPerformed

    private void xyGridCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xyGridCheckBoxActionPerformed
        LAMS.xyGridEnableObjViewer = xyGridCheckBox.isSelected();
    }//GEN-LAST:event_xyGridCheckBoxActionPerformed

    private void backgroundColorLabelPointCloudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundColorLabelPointCloudMouseClicked
        this.backgroundColorPointCloudViewer = JColorChooser.showDialog(this, "Point Cloud Background Color Chooser", this.backgroundColorPointCloudViewer);
        LAMS.backgroundColorPointCloudViewer = this.backgroundColorPointCloudViewer;
        this.setLabelColors();
    }//GEN-LAST:event_backgroundColorLabelPointCloudMouseClicked

    private void verticesColorPointCloudColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_verticesColorPointCloudColorLabelMouseClicked
        this.pointColorPointCloudViewer = JColorChooser.showDialog(this, "Point Cloud Color Chooser", this.pointColorPointCloudViewer);
        LAMS.pointColorPointCloudViewer = this.pointColorPointCloudViewer;
        this.setLabelColors();
    }//GEN-LAST:event_verticesColorPointCloudColorLabelMouseClicked

    private void xAxisColorLabelPointCloudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xAxisColorLabelPointCloudMouseClicked
        this.xAxisColorPointCloudViewer = JColorChooser.showDialog(this, "Choose X Axis Color", this.xAxisColorPointCloudViewer);
        LAMS.xAxisColorPointCloudViewer = this.xAxisColorPointCloudViewer;
        this.setLabelColors();
        this.xAxisCheckBoxPointCloud.setSelected(true);
        LAMS.xAxisEnablePointCloudViewer = true;
    }//GEN-LAST:event_xAxisColorLabelPointCloudMouseClicked

    private void yAxisColorLabelPointCloudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yAxisColorLabelPointCloudMouseClicked
        this.yAxisColorPointCloudViewer = JColorChooser.showDialog(this, "Choose Y Axis Color", this.yAxisColorPointCloudViewer);
        LAMS.yAxisColorPointCloudViewer = this.yAxisColorPointCloudViewer;
        this.setLabelColors();
        this.yAxisCheckBoxPointCloud.setSelected(true);
        LAMS.yAxisEnablePointCloudViewer = true;
    }//GEN-LAST:event_yAxisColorLabelPointCloudMouseClicked

    private void zAxisColorLabelPointCloudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_zAxisColorLabelPointCloudMouseClicked
        this.zAxisColorPointCloudViewer = JColorChooser.showDialog(this, "Choose Z Axis Color", this.zAxisColorPointCloudViewer);
        LAMS.zAxisColorPointCloudViewer = this.zAxisColorPointCloudViewer;
        this.setLabelColors();
        this.zAxisCheckBoxPointCloud.setSelected(true);
        LAMS.zAxisEnablePointCloudViewer = true;
    }//GEN-LAST:event_zAxisColorLabelPointCloudMouseClicked

    private void xyGridColorLabelPointCloudMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xyGridColorLabelPointCloudMouseClicked
        this.gridColorPointCloudViewer = JColorChooser.showDialog(this, "Choose XY Grid Color", this.gridColorPointCloudViewer);
        LAMS.xyGridColorPointCloudViewer = this.gridColorPointCloudViewer;
        this.setLabelColors();
        this.xyGridCheckBoxPointCloud.setSelected(true);
        LAMS.xyGridEnablePointCloudViewer = true;
    }//GEN-LAST:event_xyGridColorLabelPointCloudMouseClicked

    private void xAxisCheckBoxPointCloudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xAxisCheckBoxPointCloudActionPerformed
        LAMS.xAxisEnablePointCloudViewer = this.xAxisCheckBoxPointCloud.isSelected();
    }//GEN-LAST:event_xAxisCheckBoxPointCloudActionPerformed

    private void yAxisCheckBoxPointCloudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yAxisCheckBoxPointCloudActionPerformed
        LAMS.yAxisEnablePointCloudViewer = this.yAxisCheckBoxPointCloud.isSelected();
    }//GEN-LAST:event_yAxisCheckBoxPointCloudActionPerformed

    private void zAxisCheckBoxPointCloudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zAxisCheckBoxPointCloudActionPerformed
        LAMS.zAxisEnablePointCloudViewer = this.zAxisCheckBoxPointCloud.isSelected();
    }//GEN-LAST:event_zAxisCheckBoxPointCloudActionPerformed

    private void xyGridCheckBoxPointCloudActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xyGridCheckBoxPointCloudActionPerformed
        LAMS.xyGridEnablePointCloudViewer = this.xyGridCheckBoxPointCloud.isSelected();
    }//GEN-LAST:event_xyGridCheckBoxPointCloudActionPerformed

    private void spineCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_spineCheckBoxActionPerformed
        LAMS.spineEnableObjViewer = this.spineCheckBox.isSelected();
    }//GEN-LAST:event_spineCheckBoxActionPerformed

    private void spineColorLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_spineColorLabelMouseClicked
        this.spineColorObjViewer = JColorChooser.showDialog(this, "Choose Object Spine Color", this.spineColorObjViewer);
        LAMS.spineColorObjViewer = this.spineColorObjViewer;
        this.setLabelColors();
        this.spineCheckBox.setSelected(true);
        LAMS.spineEnableObjViewer = true;
    }//GEN-LAST:event_spineColorLabelMouseClicked
    
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
                new ViewSettings(settingArgs).setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundColorLabel;
    private javax.swing.JLabel backgroundColorLabelPointCloud;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JCheckBox spineCheckBox;
    private javax.swing.JLabel spineColorLabel;
    private javax.swing.JLabel verticesColorPointCloudColorLabel;
    private javax.swing.JCheckBox xAxisCheckBox;
    private javax.swing.JCheckBox xAxisCheckBoxPointCloud;
    private javax.swing.JLabel xAxisColorLabel;
    private javax.swing.JLabel xAxisColorLabelPointCloud;
    private javax.swing.JCheckBox xyGridCheckBox;
    private javax.swing.JCheckBox xyGridCheckBoxPointCloud;
    private javax.swing.JLabel xyGridColorLabel;
    private javax.swing.JLabel xyGridColorLabelPointCloud;
    private javax.swing.JCheckBox yAxisCheckBox;
    private javax.swing.JCheckBox yAxisCheckBoxPointCloud;
    private javax.swing.JLabel yAxisColorLabel;
    private javax.swing.JLabel yAxisColorLabelPointCloud;
    private javax.swing.JCheckBox zAxisCheckBox;
    private javax.swing.JCheckBox zAxisCheckBoxPointCloud;
    private javax.swing.JLabel zAxisColorLabel;
    private javax.swing.JLabel zAxisColorLabelPointCloud;
    // End of variables declaration//GEN-END:variables
    private Color xAxisColorObjViewer= Color.WHITE;
    private Color yAxisColorObjViewer= Color.WHITE;
    private Color zAxisColorObjViewer= Color.WHITE;
    private Color gridColorObjViewer= Color.WHITE;
    private Color backgroundColorObjViewer= Color.WHITE;
    private Color spineColorObjViewer= Color.WHITE;
    private Color xAxisColorPointCloudViewer= Color.WHITE;
    private Color yAxisColorPointCloudViewer= Color.WHITE;
    private Color zAxisColorPointCloudViewer= Color.WHITE;
    private Color gridColorPointCloudViewer= Color.WHITE;
    private Color pointColorPointCloudViewer= Color.WHITE;
    private Color backgroundColorPointCloudViewer= Color.WHITE;
    private JColorChooser jcc= new JColorChooser();
}
