// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.AutoCommands.AutoLoadBall;
import frc.robot.commands.AutoCommands.RealAuto;
import frc.robot.subsystem.Drivetrain;
import frc.robot.subsystem.Indexer;
import frc.robot.subsystem.Intake;
import frc.robot.subsystem.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
	//private RobotContainer m_robotContainer;
	public static OI oi;
	public static Intake intake;
	public static Compressor compressor;
	public static Shooter shooter;
	public static Indexer indexer;
	public static Drivetrain drivetrain;
	// public static Climber climber;
	public static PowerDistribution pdp;
	private static final double setpointFront = 0; //for PID testing
	private static final double setpointBack = 0; //for PID testing
	Thread m_visionThread;
	Thread m_visionThread2;
	private Command m_autonomousCommand;
	private Timer timer;
	private boolean firstBallShot;

	private Command Auto;

  private Command AutoLoadBall;

	/**
	 * This function is run when the robot is first started up and should be used for any
	 * initialization code.
	 */
	@Override
	public void robotInit() {
		CameraServer.startAutomaticCapture(0);
		CameraServer.startAutomaticCapture(1);
		timer = new Timer();
		intake = new Intake();
		shooter = new Shooter();
		indexer = new Indexer();
		drivetrain = new Drivetrain();
		oi = new OI();

    compressor = new Compressor(RobotMap.kPCM,PneumaticsModuleType.REVPH);
    pdp = new PowerDistribution(RobotMap.kPDP, ModuleType.kRev);
    compressor.enableDigital();
    Robot.drivetrain.resetGyro();
    Robot.drivetrain.resetEncoders();
	Auto = new RealAuto();
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
		SmartDashboard.putNumber("Upper Indexer Current", pdp.getCurrent(8));
		SmartDashboard.putNumber("Lower Indexer Current", pdp.getCurrent(7));

		//SmartDashboard.putData(autoChooser); //add to periodic??
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 */
	@Override
	public void disabledInit() {
	}

	@Override
	public void disabledPeriodic() {

	}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    Robot.drivetrain.resetEncoders();
    Robot.drivetrain.resetGyro();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }  
	Auto.schedule();
  }

	/**
	 * This function is called periodically during autonomous.
	 */
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

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {

	}


	@Override
	public void testInit() {
		// Cancels all running commands at the start of test mode.
		CommandScheduler.getInstance().cancelAll();
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {

	}
}
