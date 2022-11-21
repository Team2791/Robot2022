package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class Shoot extends CommandBase {
	public Shoot() {
		addRequirements(Robot.drivetrain);

	}

	@Override
	public void initialize() {
		Robot.shooter.setspeedr(0.5);
		Robot.shooter.setspeedl(0.5);
	}

	@Override
	public void execute() {

	}

	@Override
	public boolean isFinished() {
		// TODO: Make this return true when this Command no longer needs to run execute()
		return false;
	}

	@Override
	public void end(boolean interrupted) {

	}
}
