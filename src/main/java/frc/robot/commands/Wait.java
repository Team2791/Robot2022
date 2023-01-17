// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.TimeSpan;

public class Wait extends CommandBase {
	/** Creates a new wait2sec. */
	Timer timer;
	TimeSpan time;
	boolean finished;

	public Wait(TimeSpan duration) {
		// Use addRequirements() here to declare subsystem dependencies.
		timer = new Timer();
		time = duration;
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		timer.reset();
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		if (timer.get() >= time.toSeconds())
			finished = true;
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return finished;
	}
}