// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IndexerCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class ReleaseBallManual extends CommandBase {
  /** Creates a new ReleaseBall. */
  private boolean twoballs, finish = false; 
  private Timer timer = new Timer();
  private boolean ran = false;


  public ReleaseBallManual() {
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
    //SmartDashboard.putBoolean("two?", twoballs);
    if(twoballs == false){
      Robot.indexer.setUpperMotor(Constants.topindexerSpeed);
    }
    if(twoballs==true){
      
       Robot.indexer.setUpperMotor(Constants.topindexerSpeed);    
      
      if(Robot.indexer.getLowerLimitSwitch()==false && Robot.indexer.getUpperLimitSwitch() == true) {
        Robot.indexer.setUpperMotor(Constants.topindexerSpeed);
        Robot.indexer.setLowerMotor(Constants.bottomindexerSpeed);
       
      }
      if(Robot.indexer.getLowerLimitSwitch()==true && Robot.indexer.getUpperLimitSwitch() == false){
        Robot.indexer.stopUpperMotor();
        Robot.indexer.stopLowerMotor();
        finish = true;

      }
      
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
    return finish;
  }
}
