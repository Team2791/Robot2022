// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.CommandTime;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ShootTime extends CommandBase {
  /** Creates a new ShootTime. */
  public ShootTime() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.Shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.Shooter.kill(.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.Shooter.kill(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
