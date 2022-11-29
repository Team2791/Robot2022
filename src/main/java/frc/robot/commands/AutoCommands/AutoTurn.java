// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class AutoCommands extends CommandBase {
  /** Creates a new AutoCommands. */
  public AutoCommands() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
	Robot.drivetrain.setMotors(-.2,.2);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
	if(drivetrain.getAngle()>90){
		Robot.setMotors(0,0);
	}

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
