/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lams;

import java.awt.Color;

/**
 *
 * @author tyjen
 */
public class Face {
    public Integer a;
    public Integer b;
    public Integer c;
    public Integer d;
    public boolean isTriangle;
    public Color e;
    
    public Face(Integer a,Integer b, Integer c, Integer d, Color e){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.isTriangle = false;
        this.e = e;
    }
    
    public Face(Integer a,Integer b, Integer c, Color e){
        this.a = a;
        this.b = b;
        this.c = c;
        this.isTriangle = true;
        this.e = e;
    }
}
