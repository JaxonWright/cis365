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
	double backupDistance = 110; //default amount to back up
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
			back(backupDistance);
		  } else {
		  	turnRight(90); //otherwires, change direction
			back(20);
		  	backCount = 0;
		  }
		  
		  fire(3);
		}	
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
		back(100);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
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
