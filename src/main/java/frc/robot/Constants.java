// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final double kCreep = 0.25;
    public static final double TURN_FACTOR = -0.55;

    public static final double kClimberTime = 0.1;
    public static final double kClimbSpeedUp = 0.5;
    public static final double kClimbSpeedDown = -0.5;

    public static final double bottomindexerSpeed = 1;
    public static final double topindexerSpeed = 1;
    public static final double intakeSpeed = 0.9;

    public static final double frontFlySpeed = 0.4;
    public static final double backFlySpeed = -0.3;
    public static final double minPressure = 60;
    public static final double maxPressure = 100;

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
    public static final double kUpperFrontCloseSpeed = .40; //45 good
    public static final double kUpperBackCloseSpeed = -.40; //65 good
    public static final double kLowerFrontCloseSpeed = 0.07;
    public static final double kLowerBackCloseSpeed = -.40
    ;

    public static final double kUpperFrontCloseVelocity = 2350;
    public static final double kUpperBackCloseVelocity = -3455;
    public static final double driveTime = 1.25;
}

