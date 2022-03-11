// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class AutoReverseIntakeAndIndex extends CommandBase {
  public boolean finished=false;
  private Timer timer;
  /** Creates a new AutoReverseIntake. */
  public AutoReverseIntakeAndIndex() {
    
    addRequirements(Robot.intake);
    addRequirements(Robot.indexer);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.indexer.setUpperMotor(-Constants.topindexerSpeed);
    Robot.indexer.setLowerMotor(-Constants.bottomindexerSpeed);
    Robot.intake.setMotor(-Constants.intakeSpeed);
    finished = false;
    timer = new Timer();
    timer.reset();
    timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(timer.get()>1)
      finished = true;

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.indexer.stopLowerMotor();
    Robot.indexer.stopUpperMotor();
    Robot.intake.setMotor(-Constants.intakeSpeed);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished;
  }
}
