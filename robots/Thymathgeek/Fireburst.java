package Thymathgeek;
import robocode.*;
import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Fireburst - a robot by (Daniel Kostovetsky)
 */
public class Fireburst extends Robot
{
	/**
	 * run: Fireburst's default behavior
	 */
	public void run() {
		turnRight(360);


		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setColors(Color.orange,Color.red,Color.blue); // body,gun,radar

		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			turnRight(360);
			turnGunLeft(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(20);
		fire(15);
		ahead(200);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(10);
		turnLeft(150);
	}	
}
