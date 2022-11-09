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
import frc.robot.commands.DrivetrainCommands.DriveWithJoystick;
import frc.robot.commands.DrivetrainCommands.stopMotors;
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
  public static PowerDistribution pdp;

  private static double setpointFront = 0; //for PID testing
  private static double setpointBack = 0; //for PID testing
  private Timer timer;
  
  private Command move;
  
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    //m_robotContainer = new RobotContainer();
    CameraServer.startAutomaticCapture();
    m_visionThread = 
    new Thread ( 
      () -> {
        UsbCamera camera = CameraServer.startAutomaticCapture();
        camera.setResolution(432, 324); //480 360
        CvSink cvSink = CameraServer.getVideo();
        CvSource outputStream = CameraServer.putVideo("Rectangle", 480, 360);

        Mat mat = new Mat();
        while(!Thread.interrupted()) {
          if(cvSink.grabFrame(mat) == 0) {
            outputStream.notifyError(cvSink.getError());
            continue;
          }
          Imgproc.rectangle(mat, new Point(100,100), new Point(400,400), new Scalar(255,255,255),5);
          outputStream.putFrame(mat);
        }
      });
      m_visionThread.setDaemon(true);
      m_visionThread.start();
    CameraServer.startAutomaticCapture();

    m_visionThread2 = 
    new Thread ( 
      () -> {
        UsbCamera camera2 = CameraServer.startAutomaticCapture();
        camera2.setResolution(432, 324); 
        CvSink cvSink2 = CameraServer.getVideo();
        CvSource outputStream2 = CameraServer.putVideo("Rectangle", 432, 324);

        Mat mat2 = new Mat();
        while(!Thread.interrupted()) {
          if(cvSink2.grabFrame(mat2) == 0) {
            outputStream2.notifyError(cvSink2.getError());
            continue;
          }
          Imgproc.rectangle(mat2, new Point(100,100), new Point(400,400), new Scalar(255,255,255),5);
          outputStream2.putFrame(mat2);
        }
      });
      m_visionThread2.setDaemon(true);
      m_visionThread2.start();
      CameraServer.startAutomaticCapture();

    timer = new Timer();
    drivetrain = new Drivetrain();
    pdp = new PowerDistribution(RobotMap.kPDP, ModuleType.kRev);
    oi = new OI();
    compressor = new Compressor(RobotMap.kPCM,PneumaticsModuleType.REVPH);
    //compressor.enableDigital();
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
    SmartDashboard.putData(CommandScheduler.getInstance());
    SmartDashboard.putBoolean("Compressor enabled", compressor.enabled());
    SmartDashboard.putNumber("Compressor Pressure", compressor.getPressure());
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
    //m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    
    timer.start();
    Robot.drivetrain.setMotors(0.3, 0.3);
    
    //m_autonomousCommand = new TurnCounterClockwisePID(-22.34);
    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }  

  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    if(timer.get()>1.4) {
      Robot.drivetrain.setMotors(0, 0);
    }
  }

  @Override
  public void teleopInit() {
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
