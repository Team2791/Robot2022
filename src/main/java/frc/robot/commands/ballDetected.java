// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ballDetected extends CommandBase {
  /** Creates a new ballDetected. */
  boolean temp = true;

  public ballDetected() {
    // Use addRequirements() here to declare subsystem dependencies.
  }
  //Runs lower motor until true, then runs lower until false
  //FALSE   TRUE   FALSE 


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      Robot.index.setLowerMotor(0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.index.getLimitSwitch() == true){
        Robot.index.setLowerMotor(0.5);
        temp = false; 
    }
    if(temp == false && Robot.index.getLimitSwitch() == false){
        Robot.index.setLowerMotor(0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
      Robot.index.setLowerMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
