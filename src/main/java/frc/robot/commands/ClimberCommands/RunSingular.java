// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class RunSingular extends CommandBase {
  private int count;
  /** Creates a new RunSingular. */
  public RunSingular(int c) {
    addRequirements(Robot.climber);
    count = c;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.climber.setExtended();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(count==1)
    Robot.climber.setLeftMotor(Constants.climberSpeed);
    if(count==2)
    Robot.climber.setRightMotor(Constants.climberSpeed);
    if(count==-1)
    Robot.climber.setLeftMotor(-Constants.climberSpeed);
    if(count==-2)
    Robot.climber.setRightMotor(-Constants.climberSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
