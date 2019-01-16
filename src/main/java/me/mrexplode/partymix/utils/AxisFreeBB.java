package me.mrexplode.partymix.utils;

import org.bukkit.Location;
import org.bukkit.util.Vector;

public class AxisFreeBB {
    
    private Side front;
    private Side back;
    
    public AxisFreeBB() {
        front = new Side();
        back = new Side();
    }
    
    /*
     * dont try to understand this.
     * loc is the player's location, the width and height is self descriptive.
     * the depth is the cubiod's depth.
     */
    public AxisFreeBB(Location loc, double width, double height, double depth) {
        applyPitch(loc.getPitch(), front, width, height, 0);
        applyYaw(loc.getYaw(), front, width, height, 0);
        
        applyPitch(loc.getPitch(), back, width, height, depth);
        applyYaw(loc.getYaw(), back, width, height, depth);
    }
    
    public boolean isIn(Vector p) {
        boolean in = isInAA(p);
        return in;
    }

    private boolean isInAA(Vector p) {
        boolean x = p.getX() >= front.leftBottom.getX() && p.getX() <= back.rightTop.getX();
        boolean y = p.getY() >= front.leftBottom.getY() && p.getY() <= back.rightTop.getY();
        boolean z = p.getZ() >= front.leftBottom.getZ() && p.getZ() <= back.rightTop.getZ();
        return x && y && z;
    }
    
    /*
     * returns the new corner2 value. 
     */
    private void transformToAxis() {
        
    }
    
    private void applyYaw(float yaw, Side side, double width, double height, double depth) {
        
    }
    
    private void applyPitch(float pitch, Side side, double width, double height, double depth) {
        
    }
    
    public static void rotate(Angle angle, double degree, Vector vector) {
        double sin = Math.sin(Math.toRadians(degree));
        double cos = Math.cos(Math.toRadians(degree));
        switch (angle) {
        //this is the pitch
        case X:
            vector.setX(vector.getX() * cos - vector.getY() * sin);
            vector.setY(vector.getX() * sin + vector.getY() * cos);
            break;
        //this is the yaw
        case Y:
            vector.setX(vector.getX() * cos - vector.getZ() * sin);
            vector.setZ(vector.getX() * sin + vector.getZ() * cos);
            break;
        case Z://FIXME: this is just the copypaste from x, complete
            vector.setX(vector.getX() * cos - vector.getY() * sin);
            vector.setY(vector.getX() * sin + vector.getY() * cos);
            break;
        }
    }

}
class Side {
    
    public Vector leftBottom = new Vector();
    public Vector leftTop = new Vector();
    public Vector rightBottom = new Vector();
    public Vector rightTop = new Vector();
}
