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
		setColors(Color.red,Color.blue,Color.green);
		setAdjustRadarForGunTurn(true);
		while(true) {
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
			scan();
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
		System.out.println("Turning radar to: " + Utils.normalRelativeAngle(radarTurn) * (180 / Math.PI));
		System.out.println("Radar currently is: " + this.getRadarHeadingRadians() * (180 / Math.PI));

		setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
		setTurnGunRightRadians((getRadarHeadingRadians() - getGunHeadingRadians()));
		fire(1);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
	}
	
	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		back(20);
	}
}