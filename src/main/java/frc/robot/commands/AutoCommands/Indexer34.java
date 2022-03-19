// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Indexer34 extends CommandBase {
  /** Creates a new Indexer34. */
  Timer timer;
  boolean finished; 
  public Indexer34() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.indexer);
    timer = new Timer();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timer.reset();
    timer.start();
    Robot.indexer.setLowerMotor(Constants.bottomindexerSpeed);
    Robot.indexer.setUpperMotor(Constants.topindexerSpeed);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(Robot.indexer.getUpperLimitSwitch()== false) {
      Robot.indexer.setUpperMotor(0);
    }
    if(Robot.indexer.getLowerLimitSwitch()== false && Robot.indexer.getUpperLimitSwitch()== false) {
      Robot.indexer.setLowerMotor(0);
      finished = true;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.indexer.setUpperMotor(0);
    Robot.indexer.setLowerMotor(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return finished || timer.get()>3;
  }
}
