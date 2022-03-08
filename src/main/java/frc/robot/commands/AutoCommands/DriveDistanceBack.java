// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class DriveDistanceBack extends CommandBase {
  /** Creates a new DriveDistance. */
  double leftDistance, rightDistance;
  boolean finished;
  public DriveDistanceBack(double left, double right) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
    leftDistance = -left;
    rightDistance = -right;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.drivetrain.resetEncoders();
    Robot.drivetrain.setMotors(-0.5,-0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.drivetrain.getLeftPosition()<leftDistance && Robot.drivetrain.getRightPosition()<rightDistance) {
      Robot.drivetrain.setMotors(0,0);
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
