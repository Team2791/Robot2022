// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IndexerCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class RunIndexer extends CommandBase {
  /** Creates a new RunIndexer. */
  public RunIndexer() {
    addRequirements(Robot.indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      if(Robot.indexer.getUpperLimitSwitch()==false && Robot.indexer.getLowerLimitSwitch()==false) {
        Robot.indexer.setUpperMotor(Constants.indexerSpeed);
        Robot.indexer.setLowerMotor(Constants.indexerSpeed);
      }
      if(Robot.indexer.getUpperLimitSwitch()==false && Robot.indexer.getLowerLimitSwitch()==true) {
        Robot.indexer.setUpperMotor(Constants.indexerSpeed);
        Robot.indexer.setLowerMotor(Constants.indexerSpeed);
      }
      if(Robot.indexer.getUpperLimitSwitch()==true && Robot.indexer.getLowerLimitSwitch()==false) {
        Robot.indexer.setLowerMotor(Constants.indexerSpeed);
      }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if(Robot.indexer.getUpperLimitSwitch()==true) {
        Robot.indexer.stopUpperMotor();
      }
      if(Robot.indexer.getUpperLimitSwitch()==true && Robot.indexer.getLowerLimitSwitch()==true) {
        Robot.indexer.stopUpperMotor();
        Robot.indexer.stopLowerMotor();
      }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      Robot.indexer.stopUpperMotor();
      Robot.indexer.stopLowerMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
