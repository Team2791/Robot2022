// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class turn extends CommandBase {
  /** Creates a new driveCurved. */
  boolean finished = false;
  Timer timer;
  public turn() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
    timer.reset();
    timer.start();
    Robot.drivetrain.setMotors(-0.2,0.2);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(timer.get()>1.05) {
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
