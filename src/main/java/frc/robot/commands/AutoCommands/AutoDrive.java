// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;

public class AutoDrive extends CommandBase {
	private PIDController controller;
	private double output;
	private Timer timer;

	/** Creates a new AutoDrive. */
	public AutoDrive() {
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(Robot.drivetrain);
		controller = new PIDController(Constants.pid, 0, 0);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
		controller.setSetpoint(2);
		controller.setTolerance(0.5);
		timer.start();
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		if(timer.get() >= 5)
		{
			end(interrupted);
		}
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
		output = controller.calculate((Robot.drivetrain.getLeftPosition()+Robot.drivetrain.getRightPosition())/2);
		Robot.drivetrain.setMotors(output, output);
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return controller.atSetpoint();
	}
}
