package frc.robot.Subsystem;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.RobotMap;
import frc.robot.Commands.DriveWithJoystick;


public class DriveTrain extends SubsystemBase {
  

  private CANSparkMax leftLeader = new CANSparkMax(RobotMap.leftLeaderID, MotorType.kBrushless);
  private CANSparkMax leftFollower = new CANSparkMax(RobotMap.leftFollowerID, MotorType.kBrushless);
  private CANSparkMax rightLeader = new CANSparkMax(RobotMap.rightLeaderID, MotorType.kBrushless);
  private CANSparkMax rightFollower = new CANSparkMax(RobotMap.rightFollowerID, MotorType.kBrushless);

  private final AHRS m_gyro;

  // The motors on the left side of the drive.
  private final MotorControllerGroup m_leftMotors =
      new MotorControllerGroup(leftLeader,leftFollower);

  // The motors on the right side of the drive.
  private final MotorControllerGroup m_rightMotors =
      new MotorControllerGroup(rightLeader,rightFollower);
         

  // The robot's drive
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  // The left-side drive encoder
  private final RelativeEncoder m_leftEncoder = leftLeader.getEncoder();

  // The right-side drive encoder
  private final RelativeEncoder m_rightEncoder = rightLeader.getEncoder();
     

  // The gyro sensor


  // Odometry class for tracking robot pose
  private final DifferentialDriveOdometry m_odometry;

  /** Creates a new DriveSubsystem. */
  public DriveTrain() {
    super.register();
    m_gyro = new AHRS(Port.kMXP);
    // We need to invert one side of the drivetrain so that positive voltages
    // result in both sides moving forward. Depending on how your robot's
    // gearbox is constructed, you might have to invert the left side instead.
    m_rightMotors.setInverted(true);

    // Sets the distance per pulse for the encoders
    m_leftEncoder.setPositionConversionFactor(Constants.kEncoderPositionConversionFactor);
    m_rightEncoder.setPositionConversionFactor(Constants.kEncoderPositionConversionFactor);

    //different from source code; sets velocity conversion for the getRate()
    m_leftEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);
    m_rightEncoder.setVelocityConversionFactor(Constants.kEncoderDistancePerPulse);

    //sets encoder inversions
    //m_leftEncoder.setInverted(Constants.kLeftEncoderInverted);
    //m_rightEncoder.setInverted(Constants.kRightEncoderInverted);

    resetEncoders();
    m_odometry = new DifferentialDriveOdometry(m_gyro.getRotation2d());
  }

  @Override
  public void periodic() {
    var translation = m_odometry.getPoseMeters().getTranslation();
    m_odometry.update(
        m_gyro.getRotation2d(), m_leftEncoder.getPosition(), -m_rightEncoder.getPosition());
    // Update the odometry in the periodic block
  
    

    SmartDashboard.putNumber("Left position", -m_leftEncoder.getPosition());
    SmartDashboard.putNumber("Right position", m_rightEncoder.getPosition());

    SmartDashboard.putNumber("X Translation", translation.getX());
    SmartDashboard.putNumber("Y Translation", translation.getY());;

    SmartDashboard.putNumber("Left Velocity", leftLeader.get());
    SmartDashboard.putNumber("Right Velocity", rightLeader.get());
    SmartDashboard.putNumber("Rotation 2d", m_gyro.getRotation2d().getDegrees());
    SmartDashboard.putNumber("Gyro angle", -m_gyro.getYaw());
    SmartDashboard.putBoolean("Gyro calibrating", m_gyro.isCalibrating());
   



    
  }

  /**
   * Returns the currently-estimated pose of the robot.
   *
   * @return The pose.
   */
  public Pose2d getPose() {
    return m_odometry.getPoseMeters();
  }

  /**
   * Returns the current wheel speeds of the robot.
   *
   * @return The current wheel speeds.
   */
  public DifferentialDriveWheelSpeeds getWheelSpeeds() {
    return new DifferentialDriveWheelSpeeds(m_leftEncoder.getVelocity(), m_rightEncoder.getVelocity());
  }

  /**
   * Resets the odometry to the specified pose.
   *
   * @param pose The pose to which to set the odometry.
   */
  public void resetOdometry(Pose2d pose) {
    resetEncoders();
    m_gyro.zeroYaw();
    m_odometry.resetPosition(pose, m_gyro.getRotation2d());
  }

  /**
   * Drives the robot using arcade controls.
   *
   * @param fwd the commanded forward movement
   * @param rot the commanded rotation
   */
  public void arcadeDrive(double fwd, double rot) {
    m_drive.arcadeDrive(fwd, rot);
  }

  /**
   * Controls the left and right sides of the drive directly with voltages.
   *
   * @param leftVolts the commanded left output
   * @param rightVolts the commanded right output
   */
  public void tankDriveVolts(double leftVolts, double rightVolts) {
    m_leftMotors.setVoltage(leftVolts);
    m_rightMotors.setVoltage(rightVolts);
    m_drive.feed();
  }

  /** Resets the drive encoders to currently read a position of 0. */
  public void resetEncoders() {
    //reset encoders (position 0 = reset?)
    m_leftEncoder.setPosition(0);
    m_rightEncoder.setPosition(0);
  }

  /**
   * Gets the average distance of the two encoders.
   *
   * @return the average of the two encoder readings
   */
  public double getAverageEncoderDistance() {
    return (m_leftEncoder.getPosition() + m_rightEncoder.getPosition()) / 2.0;
  }

  /**
   * Gets the left drive encoder.
   *
   * @return the left drive encoder
   */
  public RelativeEncoder getLeftEncoder() {
    return m_leftEncoder;
  }

  /**
   * Gets the right drive encoder.
   *
   * @return the right drive encoder
   */
  public RelativeEncoder getRightEncoder() {
    return m_rightEncoder;
  }

  /**
   * Sets the max output of the drive. Useful for scaling the drive to drive more slowly.
   *
   * @param maxOutput the maximum output to which the drive will be constrained
   */
  public void setMaxOutput(double maxOutput) {
    m_drive.setMaxOutput(maxOutput);
  }

  /** Zeroes the heading of the robot. */
  public void zeroHeading() {
    m_gyro.reset();
  }

  /**
   * Returns the heading of the robot.
   *
   * @return the robot's heading in degrees, from -180 to 180
   */
  public double getHeading() {
    return -m_gyro.getYaw();
  }

  /**
   * Returns the turn rate of the robot.
   *
   * @return The turn rate of the robot, in degrees per second
   */
  public double getTurnRate() {
    return -m_gyro.getRate();
  }

  public void setMotors(double left, double right) {
    leftLeader.set(left);
    rightLeader.set(right);
  }

public void calibrateGyro() {
  m_gyro.calibrate();
}
  
}