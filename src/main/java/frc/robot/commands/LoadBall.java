// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class LoadBall extends CommandBase {
  /** Creates a new LoadBall. */
  public LoadBall() {
    // Use addRequirements() here to declare subsystem dependencies.
	addRequirements(Robot.indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
   	if(Robot.indexer.getUpperLimitSwitch()) {
		Robot.indexer.setLowerMotor(0.65);
		Robot.indexer.setUpperMotor(0.65);
  	}
  
}
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()  {
  	if(Robot.indexer.getLowerLimitSwitch()&&!Robot.indexer.getUpperLimitSwitch())
  	{
		Robot.indexer.setLowerMotor(0.65);
		Robot.indexer.setUpperMotor(0);
  	}
  	else if(!Robot.indexer.getUpperLimitSwitch()&&!Robot.indexer.getLowerLimitSwitch())
  	{
		Robot.indexer.setLowerMotor(0);
		Robot.indexer.setUpperMotor(0);
  	}
}
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
	Robot.indexer.setLowerMotor(0);
	Robot.indexer.setUpperMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
