package me.joebass;
import robocode.*;
import robocode.util.Utils;

import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Robot1 - a robot by (your name here)
 */
public class Robot1 extends AdvancedRobot
{
	/**
	 * run: Robot1's default behavior
	 */
	public void run() {
		// Initialization of the robot should be put here

		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		setColors(Color.red,Color.blue,Color.green); // body,gun,radar

		setAdjustRadarForGunTurn(false);
		// Robot main loop

		while(true) {
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
			// Replace the next 4 lines with any behavior you would like
//			ahead(100);
//			turnGunRight(360);
//			back(100);
//			turnGunRight(360);


			scan();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
//		System.out.println("Turning radar to: " + Utils.normalRelativeAngle(radarTurn) * (180 / Math.PI));
//		System.out.println("Radar currently is: " + this.getRadarHeadingRadians() * (180 / Math.PI));
//		System.out.println("Gun currently is: " + this.getGunHeadingRadians() * (180 / Math.PI));

		setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
//		setTurnGunRightRadians(getHeadingRadians() + (getRadarHeadingRadians() - getGunHeadingRadians()));
//		setTurnGunRightRadians(Utils.normalRelativeAngle(radarTurn));
//		System.out.println("Found a robot!");

//		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
//		back(10);
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}


}
								