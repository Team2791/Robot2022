// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ClimberCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class PitMoveClimbDown extends CommandBase {
  /** Creates a new RunClimbDown. */
  Timer timer;
  public PitMoveClimbDown() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.climber);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.climber.setExtended();
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.climber.getClimbSolenoid() && timer.get()>Constants.kClimbDelay) {
      Robot.climber.setMotors(-Constants.climberSpeed);
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
    return false;
  }
}
