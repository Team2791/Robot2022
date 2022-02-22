// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IndexerCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ReleaseBall extends CommandBase {
  /** Creates a new ReleaseBall. */
  private boolean twoballs; 
  public ReleaseBall() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    twoballs = !Robot.indexer.getLowerLimitSwitch();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!twoballs) {
        Robot.shooter.setBackFlywheel(Constants.backFlySpeed);
        Robot.shooter.setFrontFlywheel(Constants.frontFlySpeed);
        Robot.indexer.setUpperMotor(Constants.indexerSpeed);
    }
    else{
      if(!(Robot.indexer.getUpperLimitSwitch() && ))
      Robot.shooter.setBackFlywheel(Constants.backFlySpeed);
      Robot.shooter.setFrontFlywheel(Constants.frontFlySpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.shooter.setBackFlywheel(0);
    Robot.shooter.setFrontFlywheel(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
