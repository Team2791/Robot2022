// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ShooterCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class ShooterTest extends CommandBase {
  double flyOneSpeed, flyTwoSpeed;
  /** Creates a new setLeftMotor. */
  public ShooterTest() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.shooter);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    Robot.shooter.setBackFlywheel(Constants.backFlySpeed);
    Robot.shooter.setFrontFlywheel(Constants.frontFlySpeed);

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
