// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double kCreep = 0.225;
    public static final double TURN_FACTOR = -0.40;
    public static final double autoRampUp = 0.09;
    public static final double kClimberTime = 0.1;
    public static final double kClimbSpeedUp = 0.25;
    public static final double kClimbSpeedDown = -0.25;

    public static final double bottomindexerSpeed = 0.75;
    public static final double topindexerSpeed = 1;
    public static final double intakeSpeed = 0.9;

    public static final double frontFlySpeed = 0.4;
    public static final double backFlySpeed = -0.3;
    public static final double minPressure = 60;//110
    public static final double maxPressure = 120;
    public static final double darren = 1.0;

    //PID CONSTANTS
    //feedforward (ff): reduces error in PID system (prediction technique); probably won't use this maybe
    //kP: increasing this increases the speed of the control system response aka reduces oscillations
    //kP values that are too high will result in more oscillations
    //kD: increasing this will help decrease the amplitude of the oscillations (prevents overshooting)
    //to determine values, start with kP; increase until oscillations start, then decrease back down to where there were no oscillations
    //then, if needed, increase kD slightly
    public static final double BackFlywheelkP = 0;//.001;//.09;
    public static final double BackFlywheelkFF = 0;
    public static final double BackFlywheelkD = 0;
    public static final double FrontFlywheelkFF = 0;
    public static final double FrontFlywheelkP = 0;//.01;//.135;
    public static final double FrontFlywheelkD = 0;

//P values tried 10,5,1 , .1,, .01, .001
    public static final double kUpperFrontCloseSpeed = .34; //0.38 practice day orig //40
    public static final double kUpperBackCloseSpeed = -.39; //-.38 practice day orig //.33 and .38 sometimes hitting lip

    public static final double kLowerFrontCloseSpeed = .1; //0.111;//0.1; //.07 with firm balls was backdriving the motor.
    public static final double kLowerBackCloseSpeed = -.40;//-0.42; //-.40;

    public static final double longShotFrontWheel=0.3;
    public static final double longShotBackWheel=-.6;

    public static final double GachShotFront = 0.2;
    public static final double GachShotBack = -0.88;

    public static final double autoHighShotFront=0.34;
    public static final double autoHighShotBack=-0.38;
    public static final double autoMidShotFront=0.38;
    public static final double autoMidShotBack=-0.50;
    public static final double autoDriveSpeed = 0.4;
    public static final double wheelDiameterMeters = Units.inchesToMeters(6);
    public static double photonp = 0.5;
    public static double photond = 0;
    public static double photoni = 0;



    public static final double kUpperFrontCloseVelocity = 2350;
    public static final double kUpperBackCloseVelocity = -3455;
    public static final double driveTime = 1.4;
    public static final double climberSpeed = 0.7;

    //auto constants
    public static final double waitTime = 1.35;
    //max and min should be based on right falcon encoder
    public static final double maxClimbHeight = 270000; //240,000
    public static final double minClimbHeight = 0; //-400000;
    public static final double conversionFactor = wheelDiameterMeters * 0.1099989 * Math.PI;
    //previous gear ratio 0.091659

    public static final double kClimbDelay = 0.25;

    
    public static final double GyrokP = 0.01; //.01
    public static final double GyrokI = 0;
    public static final double GyrokD = 0;

}
