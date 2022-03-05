// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class driveBack extends CommandBase {
  /** Creates a new driveBack. */
  boolean finished=false;
  Timer timer;
  double speed1 = 0, speed2 = -0.05;
  public driveBack() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
     //.5.5
     Robot.drivetrain.setMotors(-0.47,-0.47);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(timer.get()>1.0) {
      Robot.drivetrain.setMotors(0,0);
      finished = true;
    }
   

     
    
  }
  // btw joes phone password is 830216
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  } 
}
