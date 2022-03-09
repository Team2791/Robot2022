// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;
import frc.robot.commands.AutoCommands.AutoHighShot;
import frc.robot.commands.AutoCommands.AutoMidShot;
import frc.robot.commands.AutoCommands.AutoRunIndexer;
import frc.robot.commands.AutoCommands.AutoRunIndexerBelts;
import frc.robot.commands.AutoCommands.AutoRunIndexerSingleBall;
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
public class FourBall extends SequentialCommandGroup {
  /** Creates a new RightZoneThreeBallAuto. */
  public FourBall() {
 
    addCommands(
    new ExtendRunIntakeAuto(),
    new wait(0.5),
    new DriveDistance(1.05,1.05),
    new wait(0.5), 
    new RetractStopIntake(),

    new AutoRunIndexer(), 
    new DriveDistanceBack(1.5,1.5), 
    new Turn(9),
    new DriveDistanceBack(0.2,0.2),
    new AutoHighShot(), 
    new wait(1.3), 
    new AutoRunIndexerBelts(), 
    new wait(1.3),
    new stopIndexerAuto(),
    new wait(1.3), 
    new StopFlywheel(), 
    new stopIndexerAuto(),
   
    new DriveDistance(0.2,0.2),
    new Turn(36),
    new ExtendRunIntakeAuto(),
    //new IndexDrive(3,3),
    new RetractStopIntake(),
    new wait(0.4),
    new ExtendRunIntakeAuto(),
    //new IndexDriveDouble(3,3),
    new RetractStopIntake(),
    new AutoRunIndexer(),
    new DriveDistanceBack(5.5,5.5), 
    new TurnCounterClock(36), 

    new AutoHighShot(), 
    new wait(1.3),
    new AutoRunIndexerBelts(), 
    new wait(1), 
    new StopFlywheel(), 
    new stopIndexerAuto());
  }
}
