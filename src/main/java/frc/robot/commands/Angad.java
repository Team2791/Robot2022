// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class Angad extends CommandBase {
	/**
	 * Creates a new ongod.
	 */
	public Angad() {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(Robot.intake);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		Robot.intake.setMotor(-0.5);
		Robot.indexer.setLowerMotor(-0.5);
		Robot.indexer.setUpperMotor(-0.5);
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		Robot.intake.setMotor(0);
		Robot.indexer.setLowerMotor(0);
		Robot.indexer.setUpperMotor(0);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}