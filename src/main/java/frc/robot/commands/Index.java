// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class Index extends CommandBase {
	/** Creates a new Index. */
	public Index() {
		// Use addRequirements() here to declare subsystem dependencies.

		addRequirements(Robot.indexer);
		addRequirements(Robot.drivetrain);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		Robot.indexer.setMotors(-0.4, 0.4);
		Robot.drivetrain.setFlag(0.4);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		Robot.indexer.setMotors(0, 0);
		Robot.drivetrain.setFlag(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
