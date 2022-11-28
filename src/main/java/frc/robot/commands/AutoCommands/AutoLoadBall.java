// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoLoadBall extends CommandBase {
  /** Creates a new AutoLoadBall. */
  private boolean Finished = false;
  public AutoLoadBall() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
	  Robot.indexer.setLowerMotor(0.6);
	  Robot.indexer.setUpperMotor(0.6);
	  Robot.intake.setIntake(true);
	  Robot.intake.setMotor(0.7);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
	if(Robot.indexer.getUpperLimitSwitch() == false) {
		Robot.indexer.stopLowerMotor();
		Robot.indexer.stopUpperMotor();
		Robot.intake.setIntake(false);
		Robot.intake.setMotor(0);
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
