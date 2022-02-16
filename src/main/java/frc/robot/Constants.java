// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
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

	public static final double kWheelDiameterMeters = Units.inchesToMeters(6); 
    private static final double kEncoderCPR = 4096; //actual value??

   
    public static final double gearRatio = 1/7.95;
    public static final double kEncoderPositionConversionFactor = kWheelDiameterMeters * gearRatio *Math.PI;
    public static final double kEncoderDistancePerPulse =
    // Assumes the encoders are directly mounted on the wheel shafts
    kEncoderPositionConversionFactor/60;;
	public static final boolean kLeftEncoderInverted = false;
    public static final boolean kRightEncoderInverted = true;

	public static final double kRamseteB = 2;
    public static final double kRamseteZeta = .7;
    
	public static final double ksVolts = 0.087396;
	public static final double kvVoltSecondsPerMeter = 2.3279;
    public static final double kaVoltSecondsSquaredPerMeter = 0.25637;

    private static final double kTrackwidthMeters =  0.81038;
    
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);
    
	public static final double kPDriveVel = 0.23188;
 
	public static final double kMaxSpeedMetersPerSecond = 0.5;
	public static final double kMaxAccelerationMetersPerSecondSquared = 0.5;
    public static final double kCreep = 0.2;
    public static final double TURN_FACTOR = 2;
    public static final int kDriverControllerPort = 0;
}
