// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class RunIndexer extends CommandBase {
  /** Creates a new RunIndexer. */
  public RunIndexer() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      if(Robot.index.getUpperLimitSwitch()==false && Robot.index.getLowerLimitSwitch()==false) {
        Robot.index.setUpperMotor(Constants.indexerSpeed);
        Robot.index.setLowerMotor(Constants.indexerSpeed);
      }
      if(Robot.index.getUpperLimitSwitch()==false && Robot.index.getLowerLimitSwitch()==true) {
        Robot.index.setUpperMotor(Constants.indexerSpeed);
        Robot.index.setLowerMotor(Constants.indexerSpeed);
      }
      if(Robot.index.getUpperLimitSwitch()==true && Robot.index.getLowerLimitSwitch()==false) {
        Robot.index.setLowerMotor(Constants.indexerSpeed);
      }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      if(Robot.index.getUpperLimitSwitch()==true) {
        Robot.index.stopUpperMotor();
      }
      if(Robot.index.getUpperLimitSwitch()==true && Robot.index.getLowerLimitSwitch()==true) {
        Robot.index.stopUpperMotor();
        Robot.index.stopLowerMotor();
      }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      Robot.index.stopUpperMotor();
      Robot.index.stopLowerMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
