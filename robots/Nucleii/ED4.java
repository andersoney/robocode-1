package Nucleii;
import robocode.*;
import robocode.util.*;
import java.awt.Color;

public class ED4 extends AdvancedRobot
{
	
 
 

	static StringBuffer enemyLog = new StringBuffer("000000000000000000000000000000");
	static final int PATTERN_DEPTH = 30;
	
	double DnM;
	double mov2 = 15000;
	static double movCon = 12600;
	static double eBotEnergy = 100;
	static double angH = 30, angL = -30;
	static double movH = 2, movL = -2;
	static double fPH = 2, fPL = 0.15;
	static double rGfH = 0.2, rGfL = -0.2;
	static int deathCount;
	
	public void run() {
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:
		setColors(Color.black,Color.orange,Color.gray,Color.yellow,Color.blue);
		
		setAdjustRadarForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustGunForRobotTurn(true);
		
		setTurnRadarRight(Double.POSITIVE_INFINITY);
		do{
			turnRadarRightRadians(1);
			scan();
		}while(true);
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		//radar
		double radarTurn = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();	
		setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
		//
		//
		//
		//
		//move var
		double absoluteBearing = getHeadingRadians() + e.getBearingRadians();
		double movAmt = movCon/e.getDistance();
		if(movAmt<50){
			movAmt = 50;
		}
		double dir = movAmt;
		double aCon = Math.random()*(1+angH-angL)+angL;
		double dCon = Math.random()*(1+movH-movL)+movL;
		if(dCon> -0.3 && dCon<0){
			dCon = -0.6;
		}
		if(dCon<0.3 && dCon>0){
			dCon = 0.6;
		}
		DnM = dir*dCon;
		
		
		//enemy properties
		double eY = getY()+e.getDistance()*Math.sin(absoluteBearing);
		double eX = getX()+e.getDistance()*Math.cos(absoluteBearing);
		//my properties
		double myVel = getVelocity();
		double fH = 2.2;
		double fL = 1.6;
		double fCon = Math.random()*(1+fH-fL)+fL;
		double firePower = fCon;
		double bVel = 20-3*firePower;
		double fx, fy;
		double hyp = (dir*dCon);
		double Ang = aCon + e.getBearing()+90;
		double x = getX();
		double y = getY();
		fx = x + hyp*Math.cos(Ang);
		fy = y + hyp*Math.sin(Ang);
		boolean fSafe;
		
		//battlefield properties
		double wallAvoidF = 160;
		double cornerAvoidF = 230;
		double bfH = getBattleFieldHeight();
		double bfW = getBattleFieldWidth();
		double cX = bfW/2, cY = bfH/2;
		double xD = x-cX, yD = y-cY;
		
		if(fx<wallAvoidF && (bfW-fx)<wallAvoidF && fy<wallAvoidF && (bfH-fy)<wallAvoidF){
			fSafe = true;
		} else {
			fSafe = false;
		}
		double eDifX = eX - fx;
		double eDifY = eY - fy;
		double DifX = x - fx;
		double DifY = y - fy;
		
		
		//find intersection
		
		double fpConInt = 3500;
		double fpCon = fpConInt;
		
		if(dCon>0){
			fpCon = fpConInt;
		}
		if(dCon<0){
			fpCon = -fpConInt;
		}
		double fireAngleDeg = fpCon/e.getDistance();//edit when finished with intersection
		//movement
		//firing
		if(fSafe == false){
			
			fSafe = true;
		}
		if(eBotEnergy>(eBotEnergy = e.getEnergy())){

			if(deathCount<5 && fSafe == true){
				setTurnRight(e.getBearing() + 90 + aCon);
				setAhead(DnM);
			}
		}
		if(deathCount>=5){
			setTurnRight(e.getBearing() + 90);
			setAhead(mov2);
		}
		if(e.getEnergy() == 0){
			turnRight(e.getBearing());
			ahead(e.getDistance()+10);
		}
		
		if(e.getDistance()<100){
			firePower = 3;
			setTurnRight(e.getBearing()+90);
			setAhead(500);
			
		}
		double absB; //absolute bearing
		int index; //index of the match of the pm gun
		int matchLenght = PATTERN_DEPTH; //the number of data a pattern contains
		
		enemyLog.insert(0, (char)((int)(Math.sin(e.getHeadingRadians() - (absB=e.getBearingRadians()+getHeadingRadians()))*e.getVelocity())));
		while ((index = enemyLog.toString().indexOf(enemyLog.substring(0, matchLenght--), 1)) < 0);
		
		matchLenght = index - (int)(e.getDistance()/bVel);
		
		do{
			absB += Math.asin(((byte)enemyLog.codePointAt(index--))/e.getDistance());
		}while(index >= Math.max(0, matchLenght));
		
		setTurnGunRightRadians(Utils.normalRelativeAngle(absB-getGunHeadingRadians()));
		setFire(firePower);
	}



	public void onHitWall(HitWallEvent e){
		DnM = -DnM;
		setTurnRight(60);
		setAhead(DnM*5);
		mov2 = -mov2;
    }
	public void onDeath(DeathEvent e){
        deathCount++;
    }
//	public void onScannedObstacle(ScannedObstacleEvent e){
//		double obsDist = e.getDistance();
//		double obsB = e.getBearing();
//		double obsBearing = getHeadingRadians() + e.getBearingRadians();
//		double oY = getY()+e.getDistance()*Math.sin(obsBearing);
//		double oX = getX()+e.getDistance()*Math.cos(obsBearing);
//		double oW = e.getWidth();
//		double oH = e.getHeight();
//		double oR = (Math.max(oW,oH))/2 + 50;
//		if(obsDist < oR){
//			setTurnRight(e.getBearing() + 120);
//			setAhead(100);
//		}
//	}
}
