// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class MoveClimb extends CommandBase {
  /** Creates a new ClimbUp. */
  public MoveClimb() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.climber);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.climber.setExtended();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(-Robot.climber.getLeftClimbPosition()<Constants.maxClimbHeight && Robot.climber.getRightClimbPosition()<Constants.maxClimbHeight && Robot.climber.getClimbSolenoid()) {
      Robot.climber.setMotors(Constants.climberSpeed);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.climber.setMotors(0);
    Robot.climber.setRetracted();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return !(Robot.climber.getLeftClimbPosition() < Constants.maxClimbHeight);
  }
}
