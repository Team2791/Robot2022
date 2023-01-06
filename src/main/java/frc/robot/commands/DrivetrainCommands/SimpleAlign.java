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
  double angle;
  /** Creates a new JoeAim. */
  PhotonCamera camera = new PhotonCamera("2791photonvision");
  public JoeJoeAim() {
    addRequirements(Robot.drivetrain);
    pid = new PIDController(0.01, 0, 0);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    var result = camera.getLatestResult();
    if(result!=null && result.hasTargets()) {
       angle = result.getYaw();
    }
    pid.setSetpoint(0);
    pid.setTolerance(0.02);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if(result == null) {
      output = 0;
    }
    Robot.drivetrain.setMotors(output, -output);
    SmartDashboard.putBoolean("Drivetrain Align false", false);


  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Drivetrain Align Complete", true);
    Robot.drivetrain.setMotors(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return pid.atSetpoint();
    
  }
}
