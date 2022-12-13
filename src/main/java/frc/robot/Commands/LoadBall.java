// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
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
    if (Robot.indexer.getUpperLimitSwitch()){
        Robot.indexer.setUpperMotor(Constants.topindexerSpeed);
        Robot.indexer.setLowerMotor(Constants.bottomindexerSpeed);
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (!Robot.indexer.getUpperLimitSwitch()){
      Robot.indexer.setUpperMotor(0);
  }
  if (!Robot.indexer.getLowerLimitSwitch()){
    Robot.indexer.setLowerMotor(0);
  }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.indexer.stopLowerMotor();
    Robot.indexer.stopUpperMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

