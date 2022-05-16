// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import com.ctre.phoenix.led.ColorFlowAnimation.Direction;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.TrajectoryUtil;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.Drivetrain;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public final static Drivetrain m_drive = new Drivetrain();


  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    var autoVoltageConstraint= new DifferentialDriveVoltageConstraint(
      new SimpleMotorFeedforward(
        Constants.ksVolts, 
        Constants.kvVoltSecondsPerMeter,
        Constants.kaVoltSecondsSquaredPerMeter),
      Constants.kDriveKinematics,
      10);  

      TrajectoryConfig config = 
        new TrajectoryConfig(
          Constants.kMaxSpeed, Constants.kMaxAcceleration) 
          .setKinematics(Constants.kDriveKinematics)
          .addConstraint(autoVoltageConstraint);
    
      Trajectory testTrajectory = 
        TrajectoryGenerator.generateTrajectory(
          new Pose2d(0,0, new Rotation2d(0)),
          List.of(new Translation2d(2,0)), 
          new Pose2d(4,0, new Rotation2d(0)),
           config);
      
      String t = "C:\\Users\\mdhus\\OneDrive\\Desktop\\ROBOT\\Robot2022\\src\\main\\deploy\\paths\\goBack.wpilib.json";
      Path trajPath = Filesystem.getDeployDirectory().toPath().resolve(t);
      
      try{
        testTrajectory = TrajectoryUtil.fromPathweaverJson(trajPath);

      }
      catch (IOException ex){
        System.out.println("path error");
      } 

      RamseteCommand ramseteCommand =
        new RamseteCommand(testTrajectory, 
        m_drive::getPose, 
        new RamseteController(2.0, .7), 
        new SimpleMotorFeedforward(
            Constants.ksVolts,
            Constants.kWheelDiameterMeters,
            Constants.kaVoltSecondsSquaredPerMeter),
          Constants.kDriveKinematics,
          m_drive::getWheelSpeeds,
          new PIDController(Constants.kPDrive, 0, 0),
          new PIDController(Constants.kPDrive, 0, 0),
          m_drive::tankDriveVolts,
          m_drive);

          
    m_drive.resetOdometery(testTrajectory.getInitialPose());
    return ramseteCommand.andThen(() -> m_drive.tankDriveVolts(0, 0));
  }
}
