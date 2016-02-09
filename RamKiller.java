package jaxzack;
import robocode.*;
import java.awt.Color;
import java.awt.geom.Point2D;

/**
 * RamKiller - a robot by Jaxon Wright and Zack Patterson
 * This robot was made to exploit RamFire. It wins around 97% of the time
 */
public class RamKiller extends AdvancedRobot
{
	private Point2D.Double[] points = new Point2D.Double[4];
	private int pCount = 0;
	private int pNum = 4; //stores the number of points in 'points'
	
	/**
	 * run: RamKiller's default behavior
	 */
	public void run() {
		setColors(Color.red,Color.cyan,Color.cyan); // body,gun,radar
		//set up array of points used for the main path used. This is a rectangle
		//bottom left
		points[0] = new Point2D.Double((1.0/4.0)*getBattleFieldWidth(), (1.0/4.0)*getBattleFieldHeight());
		//bottom right
		points[1] = new Point2D.Double((3.0/4.0)*getBattleFieldWidth(), (1.0/4.0)*getBattleFieldHeight());
		//top right
		points[2] = new Point2D.Double((3.0/4.0)*getBattleFieldWidth(), (3.0/4.0)*getBattleFieldHeight());
		//top left
		points[3] = new Point2D.Double((1.0/4.0)*getBattleFieldWidth(), (3.0/4.0)*getBattleFieldHeight());
		
		pNum = points.length;
		goToClosest(); //find and go to the point on the rectangle closest to us
		while (true) {
			System.out.println("run");
			turnGunRight(Double.POSITIVE_INFINITY);
		}
	}
	
	/**
	 * goToClosest: goes to the closest point in the predefined path to follow
	 */
	private void goToClosest() {
		Point2D.Double currPos = new Point2D.Double(getX(), getY());
		Point2D.Double closest = new Point2D.Double(2000, 2000);
		for (int i=0; i < points.length; i++) {
			if(currPos.distance(points[i]) - currPos.distance(closest) < 0.001){
				closest = points[i];
				pCount = i;
			}
		}
		goTo(closest.getX(), closest.getY());
	}
	
	/**
	 * goToFurthestFrom: go to the point furthest from the supplied value
	 */
	private void goToFurthestFrom(Point2D.Double pos) {
		//pos is position of scanned robot
		Point2D.Double furthest = pos;
		Point2D.Double[] corners = excludeOppositeCorner();
		for (int i=0; i < corners.length; i++) {
			//System.out.println("" + corners[i] + " pC:" + pCount + " i:" + i);
			if(corners[i] != null && pos.distance(corners[i]) - pos.distance(furthest) >= 0.001){
				furthest = corners[i];
				//System.out.println("IN IF " + corners[i]);
				pCount = i;
			}
		}
		goTo(furthest.getX(), furthest.getY());
	}
	
	/**
	 * excludeOppositeCorner: returns array of points, ignoring the opposite corner
	 */
	private Point2D.Double[] excludeOppositeCorner() {
		Point2D.Double[] temp = new Point2D.Double[4];
		int currPoint = pCount%pNum;
		pCount = currPoint;
		//32
		//01
		System.out.println(pCount%pNum);
		switch(currPoint) {
			case 0:
				temp[0] = points[0];
				temp[1] = points[1];
				temp[2] = null;
				temp[3] = points[3];
				break;
			case 1:
				temp[0] = points[0];
				temp[1] = points[1];
				temp[2] = points[2];
				temp[3] = null;
				break;
			case 2:
				temp[0] = null;
				temp[1] = points[1];
				temp[2] = points[2];
				temp[3] = points[3];
				break;
			case 3:
				temp[0] = points[0];
				temp[1] = null;
				temp[2] = points[2];
				temp[3] = points[3];
				break;
		}
		return temp;
	}
	
	/**
	 * calcEnemyPos: caclulates X, Y coordinate of scanned robot
	 * Used from StackOVerflow user Kris
	 */
	private Point2D.Double calcEnemyPos(double angleToEnemy, double dist) {
        // Calculate the angle to the scanned robot
        double angle = Math.toRadians((getHeading() + angleToEnemy) % 360);
        // Calculate the coordinates of the robot
        double enemyX = (getX() + Math.sin(angle) * dist);
        double enemyY = (getY() + Math.cos(angle) * dist);
		return new Point2D.Double(enemyX, enemyY);
 	}
	
	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		System.out.println("OnScannedRobot: Dist:" + e.getDistance() + " pC:" + Math.abs(pCount)%pNum);
		//They are too close to us. Get away!
		if(e.getDistance() < 50) {
			turnRight(e.getBearing()-90);
			ahead(100);
			goToFurthestFrom(calcEnemyPos(e.getBearing(), e.getDistance()));
		} else  {
			turnGunRight(getHeading() + e.getBearing() - getGunHeading());//align gun with scanned bot
			if (e.getVelocity()==0) { 
	  		  fire(Rules.MAX_BULLET_POWER); //if they aren't moving, light 'em up!
			} else {
				//System.out.println("" + points[pCount%pNum].getX() + ", " + points[pCount%pNum].getY());
				goToFurthestFrom(calcEnemyPos(e.getBearing(), e.getDistance()));
			}
		}
	}
	
    /**
	* From RoboWiki
	* GoTo coordinates.
	*/
	private void goTo(double destinationX, double destinationY) {
		destinationX -= getX();
		destinationY -= getY();
        double angle = robocode.util.Utils.normalRelativeAngle(Math.atan2(destinationX, destinationY) - Math.toRadians(getHeading()) );
		double turnAngle = Math.atan(Math.tan(angle));
                turnRight(Math.toDegrees(turnAngle));
		ahead(Math.hypot(destinationX, destinationY) * (angle == turnAngle ? 1 : -1));
	}
}