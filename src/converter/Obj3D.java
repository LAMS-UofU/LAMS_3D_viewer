/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import converter.CartesianCoordinate;
import converter.Face;
import java.util.ArrayList;

/**
 *
 * @author tyjensen
 */
public class Obj3D {
    public ArrayList<CartesianCoordinate> vertices;
    public ArrayList<Face> faces;
    
    public Obj3D(){
        vertices = new ArrayList<CartesianCoordinate>();
        faces = new ArrayList<Face>();
    }
    
    public Obj3D(ArrayList<CartesianCoordinate> vertices, ArrayList<Face> faces){
        this.vertices = vertices;
        this.faces = faces;
    }
}
