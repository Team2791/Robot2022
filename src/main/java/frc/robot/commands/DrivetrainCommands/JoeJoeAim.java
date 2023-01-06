// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.DrivetrainCommands;

import org.photonvision.PhotonCamera;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;


public class JoeJoeAim extends CommandBase {
  PIDController pid;
  Command thing;
  double output;
  /** Creates a new JoeAim. */
  PhotonCamera camera = new PhotonCamera("2791photonvision");
  public JoeJoeAim() {
    addRequirements(Robot.drivetrain);
    // addRequirements(Robot.limelight);
    pid = new PIDController(Constants.photonp, Constants.photoni, Constants.photond);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    pid.setSetpoint(0);
    pid.setTolerance(3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    var result = camera.getLatestResult();
    if(result!=null && result.hasTargets()) {
        output = pid.calculate(result.getBestTarget().getYaw());
        output = Math.min(output, 0.3);
        SmartDashboard.putNumber("YAW", result.getBestTarget().getYaw());
    }
    Robot.drivetrain.setMotors(output, -output);
    SmartDashboard.putBoolean("JOEJOE Align false", false);


  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("JOEJOE Align Complete", true);
    Robot.drivetrain.setMotors(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
    
  }
}
