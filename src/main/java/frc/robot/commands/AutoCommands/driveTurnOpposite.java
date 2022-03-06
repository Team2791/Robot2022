// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class driveTurnOpposite extends CommandBase {
  /** Creates a new driveCurved. */
  Timer timer, timer2;
  boolean finished = false , part1 = false;;
  public driveTurnOpposite() {
    // Use addRequirements() here to declare subsystem dependencies.
    timer = new Timer();
    timer2 = new Timer();
    addRequirements(Robot.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.start();
    timer.reset();

    Robot.drivetrain.setMotors(0.3,-0.3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(timer.get()>0.85) {
      Robot.drivetrain.setMotors(0,0);
      finished = true;
    }
      
  }  
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
