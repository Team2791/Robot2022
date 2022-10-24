// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.AutoCommands.AutoHighShot;
import frc.robot.commands.AutoCommands.AutoMidShot;
import frc.robot.commands.AutoCommands.AutoRunIndexer;
import frc.robot.commands.AutoCommands.AutoRunIndexerBelts;
import frc.robot.commands.AutoCommands.AutoRunIndexerSingleBall;
import frc.robot.commands.AutoCommands.DriveAndIndex;
import frc.robot.commands.AutoCommands.DriveDistance;
import frc.robot.commands.AutoCommands.DriveDistanceBack;
import frc.robot.commands.AutoCommands.ExtendRunIntakeAuto;

import frc.robot.commands.AutoCommands.RetractStopIntake;
import frc.robot.commands.AutoCommands.TurnCounterClock;
import frc.robot.commands.AutoCommands.stopIndexerAuto;
import frc.robot.commands.AutoCommands.Turn;
import frc.robot.commands.AutoCommands.wait;
import frc.robot.commands.IndexerCommands.StopIndexer;
import frc.robot.commands.IntakeCommands.ExtendRunIntake;
import frc.robot.commands.ShooterCommands.StopFlywheel;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TwoBallRight extends SequentialCommandGroup {
  /** Creates a new TwoBallRight. */
  public TwoBallRight() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new ExtendRunIntakeAuto(),
      new wait(0.6),
      new DriveDistance(0.9,0.9, Constants.autoDriveSpeed),
      new wait(0.6), 
      new RetractStopIntake(),

      new AutoRunIndexer(), 
      new DriveDistanceBack(1.56,1.56,Constants.autoDriveSpeed), 
      new Turn(9),
      new DriveDistanceBack(0.06,0.06,Constants.autoDriveSpeed),
      new AutoHighShot(), 
      new wait(2.3), 
      new AutoRunIndexerBelts(), 
      new wait(2),
      new stopIndexerAuto(), 
      new StopFlywheel(), 
      new stopIndexerAuto());
  }
}
