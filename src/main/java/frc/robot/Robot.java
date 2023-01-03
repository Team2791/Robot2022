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
import frc.robot.commands.CombinedAutos.LeftZoneAuto;
import frc.robot.commands.CombinedAutos.ThreeBall;
import frc.robot.commands.CombinedAutos.TwoBallRight;
import frc.robot.commands.AutoCommands.TurnCounterClockwisePID;
import frc.robot.commands.CombinedAutos.FourBall;
import frc.robot.commands.CombinedAutos.FourBallTerminal;
import frc.robot.commands.CombinedAutos.FourBallTerminalPID;
import frc.robot.commands.CombinedAutos.oneBall;
import frc.robot.commands.CombinedAutos.FourBall;
import frc.robot.commands.DrivetrainCommands.DriveWithJoystick;
import frc.robot.commands.DrivetrainCommands.stopMotors;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Drivetrain;
 import frc.robot.subsystems.Indexer;
 import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Intake;
// import frc.robot.subsystems.Photonvision;

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
  public static Intake intake;
  public static Compressor compressor;
  public static Shooter shooter;
  public static Indexer indexer;
  public static Drivetrain drivetrain;
  public static Climber climber;
  public static PowerDistribution pdp;
  // public static Photonvision photonvision;

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
    intake = new Intake();
    shooter = new Shooter();
    indexer = new Indexer();
    // photonvision = new Photonvision();
    drivetrain = new Drivetrain();
    Robot.drivetrain.resetEncoders();

    climber = new Climber();
    pdp = new PowerDistribution(RobotMap.kPDP, ModuleType.kRev);
    oi = new OI();
    compressor = new Compressor(RobotMap.kPCM,PneumaticsModuleType.REVPH);
    //compressor.enableDigital();
    threeBallAuto = new ThreeBall();
    spitBallAuto = new LeftZoneAuto();
    oneBallAuto = new oneBall();
    fourBallAuto = new FourBallTerminal();
    twoBallRightZoneAuto = new TwoBallRight();
    fourBallPIDAuto = new FourBallTerminalPID();
    autoChooser = new SendableChooser<>();


    SmartDashboard.putData(autoChooser);
    m_autonomousCommand = autoChooser.getSelected();
    Robot.drivetrain.resetGyro();

    climber.resetClimberPosition();
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
    Constants.photonp = SmartDashboard.getNumber("PID P value", 0);
    Constants.photoni = SmartDashboard.getNumber("PID i value", 0);

    CommandScheduler.getInstance().run();

  ;

    //SmartDashboard.putData(autoChooser); //add to periodic??
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    Robot.drivetrain.setCoastMode();
  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    Robot.drivetrain.setBrakeMode();
    //Robot.drivetrain.setCoastMode();
    Robot.drivetrain.resetEncoders();
    Robot.drivetrain.resetGyro();
    
    m_autonomousCommand = autoChooser.getSelected();
    //m_autonomousCommand = new TurnCounterClockwisePID(-22.34);
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
    //Robot.drivetrain.setRampUp(0.07);
    Robot.drivetrain.setCoastMode();
    Robot.climber.resetClimberPosition();

    // SmartDashboard.putNumber("Front Shooter kP", Constants.BackFlywheelkP);
    // SmartDashboard.putNumber("Front Shooter kF", Constants.BackFlywheelkFF);
    // SmartDashboard.putNumber("Front Shooter kD", Constants.BackFlywheelkD);
    // SmartDashboard.putNumber("Front Shooter setpoint", 0);

    // SmartDashboard.putNumber("Back Shooter kP", Constants.FrontFlywheelkP);
    // SmartDashboard.putNumber("Back Shooter kF", Constants.FrontFlywheelkFF);
    // SmartDashboard.putNumber("Back Shooter kD", Constants.FrontFlywheelkD);
    // SmartDashboard.putNumber("Back Shooter setpoint", 0);

  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    //PID testing for front flywheel
    //double kpF = SmartDashboard.getNumber("Front Shooter kP", 0);
    //double kf = SmartDashboard.getNumber("Shooter kF", 0);
    //double kd = SmartDashboard.getNumber("Shooter kD", 0);

    
    //PID testing for back flywheel
    //double kpB = SmartDashboard.getNumber("Front Shooter kP", 0);
    //double kf = SmartDashboard.getNumber("Shooter kF", 0);
    //double kd = SmartDashboard.getNumber("Shooter kD", 0);
 
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
