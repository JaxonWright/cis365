package jaxzack;
import robocode.*;
import java.awt.Color;

/**
 * RamKiller - a robot by Jaxon Wright and Zack Patterson
 */
public class RamKiller extends AdvancedRobot
{
	int backCount = 0; // used to change direction after a certain amount of back() calls.
					   // This helps from getting jammed between wall and RamFire.
	final double MAX_BACKUP = 110; //max amount to back up
	final double FIELD_PADDING = 50; //padding to sat within (mainly to avoid walls entirely)
	
	/**
	 * run: RamKiller's default behavior
	 */
	public void run() {
		setColors(Color.red,Color.cyan,Color.cyan); // body,gun,radar
		while (true) {
			turnGunRight(Double.POSITIVE_INFINITY);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		turnRight(e.getBearing()); //face the scanned bot
		turnGunRight(getHeading() + e.getBearing() - getGunHeading()); //align gun with canned bot
		
		if (e.getVelocity()==0) {
		  fire(Rules.MAX_BULLET_POWER); //if they aren't moving, light 'em up!
		} else {
		  if (backCount<2) {
		  	backCount++; //if we've backed up a small amoutn of times
			safeBack();
		  } else {
		  	turnRight(90); //otherwise, change direction
			safeBack();
		  	backCount = 0;
		  }
		  fire(3);
		}	
	}
	
	/**
	 * safeBack: safely back up within boundaries set to avoid walls entirely
	 */
	void safeBack() {
		double backAmount = MAX_BACKUP;
		double desiredX;
		double desiredY;
		double rightBound = getBattleFieldWidth() - FIELD_PADDING;
		double topBound = getBattleFieldHeight() - FIELD_PADDING;
		double heading = getHeading()-180; //we are backing up
		double hRad = Math.toRadians(heading);
		//Figure out desired X and Y values
		desiredX = Math.asin(hRad)*backAmount;
		desiredY = Math.acos(hRad)*backAmount;
		
		System.out.println("Back Heading: " + heading);
		System.out.println("Current X: " + getX());
		System.out.println("Current Y: " + getY());
		
		System.out.println("Desired X: " + desiredX);
		System.out.println("Desired Y: " + desiredY);
		
		//adjust desiredX
		if (desiredX <= FIELD_PADDING){ //too colse to adjusted left wall
			desiredX = FIELD_PADDING;
		} else if ( desiredX >= rightBound){ //too colse to adjusted right wall
			desiredX = rightBound;
		}
		//adjust desiredY
		if (desiredY <= FIELD_PADDING){ //too close to adjusted bottom wall
			desiredY = FIELD_PADDING;
		} else if (desiredY >= topBound){ //too close to adjusted top wall
			desiredY = topBound;
		}
		
		System.out.println("New Desired X: " + desiredX);
		System.out.println("New Desired Y: " + desiredY);
		//backAmount to the distance between current point and new point
		//										          ______________________
		//This is simply the standard distance formula, -/ (y2-y1)^2 + (x2-x1)^2
		backAmount = Math.sqrt(Math.pow(desiredY-getY(), 2) + Math.pow(desiredX-getX(), 2));
		System.out.println("Back Amount = " + backAmount);
		back(backAmount);
	}
	
	/**
	 * onHitRobot: What to do when you hit a robot
	 */
	void OnHitRobot(HitRobotEvent e) {
		/*if(e.isMyFault() == false) {
	 	   if (e.getBearing() > -90 && e.getBearing() <= 90) {	
		        back(110);
		    }
		    else {
		        ahead(100);
		    }
		} else {
			ahead(1);
			fire(Rules.MAX_BULLET_POWER);
		}*/
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		//back(100);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 * THIS SHOULD NEVER HAPPEN
	 */
	public void onHitWall(HitWallEvent e) {
		ahead(10); //move up so rotating doesn't cause another wall hit
		if (e.getBearing() >= 180)
			turnRight(90); //back right corner..ish hit wall. 
		else
			turnLeft(90); //back left corner..ish hit wall.
		back(250);
		backCount=0;
	}	
}
