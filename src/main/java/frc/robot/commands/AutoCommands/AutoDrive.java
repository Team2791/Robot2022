// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class AutoDrive extends CommandBase {
	private boolean finished = false;

	/*
	 * Only use the left to check because
	 * they will be moving at the same pace.
	 */
	private double preLeftPos;

	/** Creates a new AutoDrive. */
	public AutoDrive() {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(Robot.drivetrain);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		preLeftPos = Robot.drivetrain.getLeftPosition();
		Robot.drivetrain.setMotors(0.5, 0.5);

	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		if (Robot.drivetrain.getLeftPosition() >= preLeftPos+3) {
			finished = true;

		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		Robot.drivetrain.setMotors(0, 0);

	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return finished;
	}
}
