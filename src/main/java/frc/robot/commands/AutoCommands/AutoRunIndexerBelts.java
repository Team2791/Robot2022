// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class AutoRunIndexerBelts extends CommandBase {
  Timer shooterTimer,indexerTimer;
  private boolean finished= false;
  /** Creates a new RunIndexer. */
  public AutoRunIndexerBelts() {
    // Use addRequirements() here to declare subsystem dependencies.
    shooterTimer = new Timer();
    indexerTimer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooterTimer.reset();
    shooterTimer.start();
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(shooterTimer.get()>1.1) {
      Robot.indexer.setUpperMotor(Constants.topindexerSpeed);
      Robot.indexer.setLowerMotor(Constants.bottomindexerSpeed);
      shooterTimer.reset();
      indexerTimer.start();
    }
    if(indexerTimer.get()>2) {
      Robot.indexer.stopLowerMotor();
      Robot.indexer.stopUpperMotor();
      finished= true;
      
    }
    
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
