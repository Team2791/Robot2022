// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;
import frc.robot.commands.AutoCommands.AutoHighShot;
import frc.robot.commands.AutoCommands.AutoRunIndexer;
import frc.robot.commands.AutoCommands.AutoRunIndexerBelts;
import frc.robot.commands.AutoCommands.AutoRunIndexerSingleBall;
import frc.robot.commands.AutoCommands.DriveDistance;
import frc.robot.commands.AutoCommands.DriveDistanceBack;
import frc.robot.commands.AutoCommands.ExtendRunIntakeAuto;
import frc.robot.commands.AutoCommands.RetractStopIntake;
import frc.robot.commands.AutoCommands.TurnCounterClock;
import frc.robot.commands.AutoCommands.stopIndexerAuto;
import frc.robot.commands.AutoCommands.TurnClockwise;
import frc.robot.commands.AutoCommands.wait;
import frc.robot.commands.IndexerCommands.StopIndexer;
import frc.robot.commands.IntakeCommands.ExtendRunIntake;
import frc.robot.commands.ShooterCommands.StopFlywheel;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RightZoneThreeBallAuto extends SequentialCommandGroup {
  /** Creates a new RightZoneThreeBallAuto. */
  public RightZoneThreeBallAuto() {
 
    addCommands(
    new ExtendRunIntakeAuto(),
    new DriveDistance(2,2), 
    new AutoRunIndexer(), 
    new DriveDistanceBack(2.5,2), 
    new AutoHighShot(), 
    new RetractStopIntake(),
    new wait(1.3), 
    new AutoRunIndexerBelts(), 
    new wait(1.3), 
    new StopFlywheel(), 
    new stopIndexerAuto(),

    new DriveDistance(1.7,1.7),
    new TurnClockwise(0.8),
    new ExtendRunIntakeAuto(),
    new DriveDistance(6,6), 
    new AutoRunIndexerSingleBall(),
    new DriveDistanceBack(6,6), 
    new TurnCounterClock(0.8), 
    new DriveDistanceBack(1.5,1.5),

    new AutoHighShot(), 
    new wait(1.3),
    new AutoRunIndexerBelts(), 
    new wait(1), 
    new StopFlywheel(), 
    new stopIndexerAuto());
  }
}
