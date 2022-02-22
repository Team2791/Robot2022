// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IndexerCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ReleaseBallManual extends CommandBase {
  /** Creates a new ReleaseBall. */
  private boolean twoballs, temp = false; 
  private Timer timer = new Timer();
  


  public ReleaseBallManual() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    twoballs = !Robot.indexer.getLowerLimitSwitch();
    timer.reset();
    

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(!twoballs) {
        Robot.shooter.setBackFlywheel(Constants.backFlySpeed);
        Robot.shooter.setFrontFlywheel(Constants.frontFlySpeed);
        timer.start();

        if(timer.get()>3)
          Robot.indexer.setUpperMotor(Constants.topindexerSpeed);
    }
    else {
      Robot.shooter.setBackFlywheel(Constants.backFlySpeed);
      Robot.shooter.setFrontFlywheel(Constants.frontFlySpeed);
      timer.start();

      if(timer.get()>3) {
        Robot.indexer.setUpperMotor(Constants.topindexerSpeed);
        //Robot.indexer.setLowerMotor(Constants.bottomindexerSpeed);
        timer.reset();
    
      }
      if(Robot.indexer.getLowerLimitSwitch()==false && Robot.indexer.getUpperLimitSwitch() == true) {
        Robot.indexer.setUpperMotor(Constants.topindexerSpeed);
        Robot.indexer.setLowerMotor(Constants.bottomindexerSpeed);
       
      }
      if(Robot.indexer.getLowerLimitSwitch()==true && Robot.indexer.getUpperLimitSwitch() == false){
        Robot.indexer.stopUpperMotor();
        Robot.indexer.stopLowerMotor();
      }
      
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.shooter.setBackFlywheel(0);
    Robot.shooter.setFrontFlywheel(0);
    Robot.indexer.stopLowerMotor();
    Robot.indexer.stopUpperMotor();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
