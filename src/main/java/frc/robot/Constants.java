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
    public static final double kCreep = 0.1;
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
  

    
    public static final double wheelDiameterMeters = Units.inchesToMeters(6);
    


    public static final double conversionFactor = wheelDiameterMeters * 0.1099989 * Math.PI;
    //previous gear ratio 0.091659

    public static final double kClimbDelay = 0.25;

    
    
    public static final double ka = 0;
    public static final double kv = 0;
    public static final double ks = 0;
    public static final double gearRatio = 0;

}
