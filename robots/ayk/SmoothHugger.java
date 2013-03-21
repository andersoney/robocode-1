package ayk;

import java.awt.geom.Rectangle2D;

import robocode.*;
import robocode.util.Utils;
public class SmoothHugger extends AdvancedRobot{
	// created by Ali Khan
	// nano sized wallhugging robot that uses wallsmoothing
	// movement - wallhugging with a VelocityTrick 
	// gun - uses a random linear gun
	
	static double prevEnergy = 100; //Energy enemy has in beginning of round
	public void run() {
		
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		
		while (true) {
			turnRadarRightRadians(Double.POSITIVE_INFINITY);
		}
	}
	public void onScannedRobot(ScannedRobotEvent e) {

		//***********************SET UP *****************************
			double v1;
			
		//***********************MOVEMENT************************
			
			
			//velocityTrick (idea from Python)
			if(prevEnergy > (prevEnergy = e.getEnergy())){
				setMaxVelocity(Math.random() * 8 + 3); // minimum velocity I want is 3
			}
			
			//Pez's wall smoothing code with adaptation to make it hug walls clockwise
			//original wallhugging code is at http://robowiki.net/wiki/Talk:Wall_Smoothing/Implementations
			 v1 = getHeadingRadians() + Math.PI/2 + Math.PI/2; //I am trying to maintain my current heading
			while (!new Rectangle2D.Double(20,20,760,560).contains(getX() + Math.sin(v1) * 160, getY() + Math.cos(v1) * 160)) v1 += .1;	
			 v1 -= getHeadingRadians();
			
			if (v1 > Math.PI/2) {
				v1 += Math.PI;
				setBack(100);
			} else setAhead(100);
			
			setTurnRightRadians(Utils.normalRelativeAngle(v1)); //normalization happens only once
			
			
			
	    //********************GUN**********************
			//random linear gun from RaikoNano
			
			setTurnGunRightRadians(Utils.normalRelativeAngle((v1 = getHeadingRadians() + e.getBearingRadians()) + 
					Math.random() * e.getVelocity() / 
					13 * Math.sin(e.getHeadingRadians() - v1) - getGunHeadingRadians()));
			
	     		setFire(2);
	       
		//*****************RADAR***********************
	       //Basic radar used in nearly every NanoBot
			setTurnRadarLeftRadians(getRadarTurnRemaining());
	}
	
}
