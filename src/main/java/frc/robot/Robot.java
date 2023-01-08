// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.Arrays;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.CombinedAutos.ParkReturn;

import frc.robot.subsystems.Drivetrain;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Thread m_visionThread;
  Thread m_visionThread2;
  private Command m_autonomousCommand;

  // private RobotContainer m_robotContainer;
  public static OI oi;
  public static Compressor compressor;

  public static Drivetrain drivetrain;
  public static PowerDistribution pdp;

  public static Trajectory parkGo;
  public static Trajectory parkReturn;


  private Command parkReturnAuto;
 
  private SendableChooser<Command> autoChooser;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
  
    CameraServer.startAutomaticCapture(0);
    CameraServer.startAutomaticCapture(1);
 
    drivetrain = new Drivetrain();
    Robot.drivetrain.resetEncoders();

    pdp = new PowerDistribution(RobotMap.kPDP, ModuleType.kRev);
    oi = new OI();
    compressor = new Compressor(RobotMap.kPCM, PneumaticsModuleType.REVPH);
    // compressor.enableDigital();

    // a forward trajectory config
    TrajectoryConfig fwdConfig = new TrajectoryConfig(1.0, 1.0);
    fwdConfig.setKinematics(drivetrain.getKinematics());

    // move out to the right, swing into a "parking space" on the left
    parkGo = TrajectoryGenerator.generateTrajectory(
        Arrays.asList(
            new Pose2d(),
            poseFeetCompass(10.0, -2.0, 0),
            poseFeetCompass(13.0, 2.0, 270)),
        fwdConfig);

    // a reverse trajectory config (robot moves backward)
    TrajectoryConfig revConfig = new TrajectoryConfig(0.7, 0.7);
    revConfig.setKinematics(drivetrain.getKinematics());
    revConfig.setReversed(true);

    // reverse of the parkGo trajectory, robot should return to starting pose
    parkReturn = TrajectoryGenerator.generateTrajectory(
        Arrays.asList(
            poseFeetCompass(13.0, 2.0, 270),
            poseFeetCompass(10.0, -2.0, 0),
            new Pose2d()),
        revConfig);

    parkReturnAuto = new ParkReturn();
    
    autoChooser = new SendableChooser<>();
    autoChooser.setDefaultOption("Park & Return (Ramsete)", parkReturnAuto);
 

    SmartDashboard.putData(autoChooser);
    m_autonomousCommand = autoChooser.getSelected();
    Robot.drivetrain.resetGyro();

  }

  /**
   * This is a utility function for simplified creation of a Pose
   * Converts feet to meters and converts from integer compass heading:
   * 0 to 179, 180 to 360
   * to internal heading representation (-180 to 180):
   * 0 to -179, 180 to 0
   * and converts to Rotation2d
   * 
   * @param xFeet   The x coordinate of position in feet
   * @param yFeet   The y coordinate of positoin in feet
   * @param compass The heading as a on a compass, 0 - 360 degrees
   * @return A Pose2d object in internal units
   */
  Pose2d poseFeetCompass(double xFeet, double yFeet, int compass) {
    int angle = (compass + 180) % 360;
    if (angle < 0) {
      angle += 360;
    }
    angle = 180 - angle;
    Rotation2d rot = Rotation2d.fromDegrees((double) angle);

    return new Pose2d(Units.feetToMeters(xFeet), Units.feetToMeters(yFeet), rot);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    compressor.enableAnalog(Constants.minPressure, Constants.maxPressure);
    CommandScheduler.getInstance().run();
    SmartDashboard.putData(CommandScheduler.getInstance());
    SmartDashboard.putBoolean("Compressor enabled", compressor.enabled());
    SmartDashboard.putNumber("Compressor Pressure", compressor.getPressure());
    // SmartDashboard.putNumber("Intake Current", pdp.getCurrent(14));
    // SmartDashboard.putNumber("Front Shooter Current", pdp.getCurrent(13));
    // SmartDashboard.putNumber("Back Shooter Current", pdp.getCurrent(12));
    // SmartDashboard.putNumber("(Left) Drivetrain Current", pdp.getCurrent(0));
    // SmartDashboard.putNumber("Climber Current", pdp.getCurrent(1));
    SmartDashboard.putNumber("Upper Indexer Current", pdp.getCurrent(8));
    SmartDashboard.putNumber("Lower Indexer Current", pdp.getCurrent(7));

    // SmartDashboard.putData(autoChooser); //add to periodic??
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    Robot.drivetrain.setCoastMode();
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    // m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    Robot.drivetrain.setBrakeMode();
    // Robot.drivetrain.setCoastMode();
    Robot.drivetrain.resetEncoders();
    Robot.drivetrain.resetGyro();

    m_autonomousCommand = autoChooser.getSelected();
    // m_autonomousCommand = new TurnCounterClockwisePID(-22.34);
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    // Robot.drivetrain.setRampUp(0.07);
    Robot.drivetrain.setCoastMode();

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
 
  
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
