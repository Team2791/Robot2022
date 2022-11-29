// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoLoadBall2 extends CommandBase {
  /** Creates a new AutoLoadBall. */
  private boolean finished = false;
  public AutoLoadBall2() {
    // Use addRequirements() here to declare subsystem dependencies.
	addRequirements(Robot.indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
	Robot.indexer.setLowerMotor(0.6);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

	if(!Robot.indexer.getLowerLimitSwitch()) {
		finished = true;
	}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
	  Robot.indexer.setLowerMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
