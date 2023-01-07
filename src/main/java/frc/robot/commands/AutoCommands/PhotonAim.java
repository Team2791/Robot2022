// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import org.photonvision.PhotonCamera;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class PhotonAim extends CommandBase {
  /** Creates an aiming command using protonVision data (yaw). */
  private double motorSpeed = 0.1;
  private double yawTolerance = 1.0;
  private PhotonCamera camera = new PhotonCamera("2791photonvision");
  private Drivetrain drive = Robot.drivetrain;

  public PhotonAim() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    var result = camera.getLatestResult();
    if (result == null || !result.hasTargets()) {
      drive.setMotors(0.0, 0.0);
      return;
    }

    double yaw = result.getBestTarget().getYaw();
    if (-yawTolerance < yaw && yaw < yawTolerance) {
      drive.setMotors(0.0, 0.0);
      return;
    }

    if (yaw > 0) {
      drive.setMotors(+motorSpeed, -motorSpeed);
    } else {
      drive.setMotors(-motorSpeed, +motorSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.setMotors(0.0, 0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}