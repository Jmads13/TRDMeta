/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dk.sdu.mmmi.cbse.basicpathmovement;

import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author SoA
 */
public class Path {
    
    private static Path instance;
    ArrayList<Point> pointPath;
    
    
    private Path(){
        pointPath = new ArrayList<>();
        pointPath.add(new Point(32,32)); 
        pointPath.add(new Point(96,32));
        pointPath.add(new Point(96,160));
        pointPath.add(new Point(288,160));
        pointPath.add(new Point(288,352));
        pointPath.add(new Point(416,352));
        pointPath.add(new Point(416,96));
    }
    
    public static Path getInstance(){
        if(instance != null){
            return instance;
        }
        return (instance = new Path());
    }
    
    public ArrayList<Point> getPoints(){
        return pointPath;
    }
//        32  96  160  224  288   352
//      32{1, 0,   0,   0,   2,   2},
//      96{1, 1,   1,   0,   0,   2},
//     160{2, 0,   1,   0,   0,   0},
//     224{2, 0,   1,   0,   1,   1},
//     288{2, 2,   1,   1,   1,   1},
//     352{2, 2,   0,   0,   0,   1},
//     416{2, 1,   1,   1,   1,   1},
//     480{0, 1,   0,   0,   2,   2},
//     {2, 1,   1,   1,   1,   1},
//     {0, 1,   0,   0,   2,   2},
    
}
