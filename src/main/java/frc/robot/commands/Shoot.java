package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class Shoot extends CommandBase {
	public Shoot() {
		addRequirements(Robot.drivetrain);

	}

	@Override
	public void initialize() {
		Robot.indexer.setLowerMotor(0.6);
		Robot.indexer.setUpperMotor(0.6);

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
		Robot.indexer.stopLowerMotor();
		Robot.indexer.stopUpperMotor();
	}
}
