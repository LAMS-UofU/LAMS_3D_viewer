/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lamsdraw3d;

/**
 *
 * @author tyjen
 */
public class CartesianCoordinate2D {
    public float floatX;
    public float floatY;
    public int intX;
    public int intY;
    
    public CartesianCoordinate2D(float x, float y){
        this.floatX = x;
        this.floatY = y;
    }
    
    public CartesianCoordinate2D(int x, int y){
        this.floatX = intX;
        this.floatY = intY;
    }
    
    public CartesianCoordinate2D(){
        
    }
    
    public String toString(){
        return this.intX + "," + this.intY;
    }
}
