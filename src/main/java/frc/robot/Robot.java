// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.cscore.VideoSource;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.subsystems.Drivetrain;
 

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  Thread m_visionThread;
  Thread m_visionThread2;
  private Command m_autonomousCommand;
  
  //private RobotContainer m_robotContainer;
  public static OI oi;
  public static Compressor compressor;

  public static Drivetrain drivetrain;
  public static RobotContainer container;
  public static PowerDistribution pdp;
 

  private static double setpointFront = 0; //for PID testing
  private static double setpointBack = 0; //for PID testing
  private Timer timer, shooterTimer1,shooterTimer2;
  private boolean firstBallShot;

  private Command threeBallAuto;
  private Command spitBallAuto;
  private Command oneBallAuto;
  private Command fourBallAuto;
  private Command twoBallRightZoneAuto;
  private Command fourBallPIDAuto;
  private SendableChooser<Command> autoChooser;
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
  

    
    CameraServer.startAutomaticCapture(0);
    CameraServer.startAutomaticCapture(1);
    timer = new Timer();
    shooterTimer1 = new Timer();
    shooterTimer2 = new Timer(); 
    
    container = new RobotContainer();
    drivetrain = new Drivetrain();

    pdp = new PowerDistribution(RobotMap.kPDP, ModuleType.kRev);
    oi = new OI();
    compressor = new Compressor(RobotMap.kPCM,PneumaticsModuleType.REVPH);
    //compressor.enableDigital();
  


    SmartDashboard.putData(autoChooser);
    m_autonomousCommand = autoChooser.getSelected();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    compressor.enableAnalog(Constants.minPressure, Constants.maxPressure);
    CommandScheduler.getInstance().run();
   

    //SmartDashboard.putData(autoChooser); //add to periodic??
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = container.getAutonomousCommand();

    m_autonomousCommand = autoChooser.getSelected();
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
  public void testPeriodic() {}
}
