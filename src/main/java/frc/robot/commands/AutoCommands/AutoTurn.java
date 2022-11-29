// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoTurn extends CommandBase {
  /** Creates a new AutoCommands. */
  private boolean Finished = false;
  public AutoTurn() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
	Robot.drivetrain.setMotors(.1,-.1);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
	if(Robot.drivetrain.getAngle()>90){
		Robot.drivetrain.setMotors(0,0);
		Finished = true;
	}

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Finished;
  }
}
