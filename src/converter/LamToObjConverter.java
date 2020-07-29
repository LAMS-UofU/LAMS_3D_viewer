/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author tyjensen
 */
public class LamToObjConverter {
    private File lamFile;
    private File saveFile;
    private ArrayList<String> objContents;
    public ArrayList<Face> faces;
    public ArrayList<SphericalCoordinate> spherical;
    public ArrayList<CartesianCoordinate> cartesian;
    
    public LamToObjConverter(){
        faces = new ArrayList<Face>();
        spherical = new ArrayList<SphericalCoordinate>();
        cartesian = new ArrayList<CartesianCoordinate>();
    }
    
    /**The convert method will perform the conversion between the spherical coordinates stored in the .lam file
     * to the Cartesian coordinates needed for the .obj file.
     * 
     * @param lamFile the file object that contains the .lam file to be read and converted.
     * @return a Boolean indicating success or failure of the operation.
     */
    public boolean convert(File lamFile){
        spherical = new ArrayList<SphericalCoordinate>();
        cartesian = new ArrayList<CartesianCoordinate>();
        try{
            Scanner s = new Scanner(lamFile);
            while(s.hasNextLine()){
                String temp  = s.nextLine();
                if(!temp.equals("")){
                    spherical.add(getSpherical(temp));
                }
            }
            s.close();
            for(SphericalCoordinate sc:spherical){
                cartesian.add(sc.getCartesianCoordiante());
            }
            return true;
        }
        catch(FileNotFoundException e){
            return false;
        }
    }
    
    public Obj3D getObj(){
        return new Obj3D(this.cartesian, this.faces);
    }
    
    /**This method will get the vertices text needed for the .obj file from the Cartesian ArrayList object containing
     * the CartesianCoordinate objects.
     * 
     */
    public void getVertices(){
        for(CartesianCoordinate cc:cartesian){
            objContents.add("v  " + cc.x + "  " + cc.y + "  " + cc.z);
        }
    }
    
    public void createFaces(){
        if(this.cartesian.size()>0){
            for(int i = 0;i<this.cartesian.size()-1;i++){
                //FloatPair p = new FloatPair(spherical.get(i).phi,spherical.get(i).theta);
                int v1 = -1;
                double distance1 = -1.0;
                int v2 = -1;
                double distance2 = -1.0;
                int v3 = -1;
                double distance3 = -1.0;
                int v4 = -1;
                double distance4 = -1.0;
                for(int j = 0;j<this.cartesian.size();j++){
                    if(j!= i){
                        double distance = this.cartesian.get(i).doubleDistance(this.cartesian.get(j));
                        if(distance1==-1){
                            distance1 = distance;
                            v1 = j;
                        }
                        else if(distance<=distance1){
                            distance4 = distance3;
                            v4=v3;
                            distance3 = distance2;
                            v3=v2;
                            distance2=distance1;
                            v2=v1;
                            distance1 = distance;
                            v1 = j;
                        }
                        else if(distance<=distance2){
                            distance4 = distance3;
                            v4=v3;
                            distance3 = distance2;
                            v3=v2;
                            distance2=distance;
                            v2=j;
                        }
                        else if(distance<=distance3){
                            distance4 = distance3;
                            v4=v3;
                            distance3 = distance;
                            v3=j;
                        }
                        else if(distance<=distance4){
                            distance4 = distance;
                            v4=j;
                        }
                    }
                }
                if(v1!=-1 && v2!=-1){
                    Face f;
                    if(v1!=v2){
                        f = new Face(this.cartesian.get(i+1),this.cartesian.get(v1),this.cartesian.get(v2));
                        f.calculateFaceNormal();
                        this.faces.add(f);
                    }
                    if(v3!=-1){
                        if(v2!=v3){
                            f = new Face(this.cartesian.get(i+1),this.cartesian.get(v2),this.cartesian.get(v3));
                            f.calculateFaceNormal();
                            this.faces.add(f);
                        }
                        if(v3!=v1){
                            f = new Face(this.cartesian.get(i+1),this.cartesian.get(v3),this.cartesian.get(v1));
                            f.calculateFaceNormal();
                            this.faces.add(f);
                        }
                        if(v4!=-1){
                            if(v3!=v4);
                                f = new Face(this.cartesian.get(i+1),this.cartesian.get(v3),this.cartesian.get(v4));
                                f.calculateFaceNormal();
                                this.faces.add(f);
                            if(v4!=v1);
                                f = new Face(this.cartesian.get(i+1),this.cartesian.get(v4),this.cartesian.get(v1));
                                f.calculateFaceNormal();
                                this.faces.add(f);
                            if(v4!=v2);
                                f = new Face(this.cartesian.get(i+1),this.cartesian.get(v4),this.cartesian.get(v2));
                                f.calculateFaceNormal();
                                this.faces.add(f);
                        }
                    }
                        
                }
            }
        }
    }
    
    public void getFaces(){  
        if(faces.size()>0){   
            for(Face f:faces){
                if(f.isTriangle){
                    objContents.add("f  " + f.v1 + "  " + f.v2 + "  " + f.v3);
                }
                else{
                    objContents.add("f " + f.v1 + "  " + f.v2 + "  " + f.v3 + "  " + f.v4);
                }
            }
        }
        
    }
    
    public void getVertexNormals(){
        
    }
    
    public boolean createObjFile(File objFile){
        this.objContents = new ArrayList<String>();
        this.getVertices();
        objContents.add("\r\n");
        this.createFaces();
        this.getFaces();
        try{
            FileWriter fw = new FileWriter(objFile);
            for(String s:objContents){
                fw.write(s + "\r\n");
            }
            fw.flush();
            fw.close();
            return true;
        }
        catch (IOException ex) {
            return false;
        }
        
    }
    
    public SphericalCoordinate getSpherical(String text){
        String temp[] = text.split(",");
        return new SphericalCoordinate(new Float(temp[0]),new Float(temp[1]),new Float(temp[2]));
    }
    
    private class FloatPair{
        float key;
        float value;
        
        FloatPair(float key, float value){
            this.key = key;
            this.value = value;
        }
    }
    
}
