// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class Turn extends CommandBase {
	final int angle;
  /** Creates a new Turn. */
  public Turn(int angle1) {
	addRequirements(Robot.drivetrain);
   Robot.drivetrain.setMotors(0.1,-0.1);
	angle=angle1;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  	Robot.drivetrain.setMotors(0,0);
}
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.drivetrain.getAngle()>angle;
  }
}
