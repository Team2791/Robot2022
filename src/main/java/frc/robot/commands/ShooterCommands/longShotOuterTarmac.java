// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class longShotOuterTarmac extends CommandBase {
  /** Creates a new longShotOuterTarmac. */
  public longShotOuterTarmac() {
    addRequirements(Robot.shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.shooter.setFrontFlywheel(Constants.longShotFrontWheel);
    Robot.shooter.setBackFlywheel(Constants.longShotBackWheel);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.shooter.setFrontFlywheel(0);
    Robot.shooter.setBackFlywheel(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
