// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class AutoRunIndexerSingleBall extends CommandBase {
  /** Creates a new RunIndexer2. */
  boolean finished=false;
  boolean pastUpper=false;
  public AutoRunIndexerSingleBall() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.indexer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.indexer.getUpperLimitSwitch()==true && !pastUpper) {
      Robot.indexer.setUpperMotor(Constants.topindexerSpeed);
      Robot.indexer.setLowerMotor(Constants.bottomindexerSpeed);
    }
   
    if(Robot.indexer.getUpperLimitSwitch()==false && Robot.indexer.getLowerLimitSwitch()==true) {
      Robot.indexer.setLowerMotor(Constants.bottomindexerSpeed);
      Robot.indexer.stopUpperMotor();
      //finished = true;
    }
    if(Robot.indexer.getUpperLimitSwitch()==false) {
      Robot.indexer.stopUpperMotor();
      pastUpper = true;
      finished=true;
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
    return finished;
  }
}
