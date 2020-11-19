/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LAMS;

import java.util.ArrayList;

/**
 *
 * @author tyjensen
 */
public class Perimeter {
    ArrayList<Integer> vertices = new ArrayList<Integer>();
    int x1;
    int y1;
    int x2;
    int y2;
    
    public Perimeter(int x1,int y1){
        this.x1 = x1;
        this.y1 = y1;
    }
    
    public Perimeter(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public Perimeter(){
        
    }
    
    public void setVertex1(int x1, int y1){
        this.x1 = x1;
        this.y1 = y1;
    }
    
    public void setVertex2(int x2, int y2){
        this.x2 = x2;
        this.y2 = y2;
    }
    
    
}
