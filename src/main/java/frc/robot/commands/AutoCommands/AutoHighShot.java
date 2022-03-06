// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class AutoHighShot extends CommandBase {
  /** Creates a new AutoLowShot. */
  public AutoHighShot() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.shooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.shooter.setFrontFlywheel(Constants.autoHighShotFront);
    Robot.shooter.setBackFlywheel(Constants.autoHighShotBack);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
