// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.AutoCommands.AutoGachShot;
import frc.robot.commands.AutoCommands.AutoHighShot;
import frc.robot.commands.AutoCommands.AutoLongShot;
import frc.robot.commands.AutoCommands.AutoReverseIntakeAndIndex;
import frc.robot.commands.AutoCommands.AutoRunIndexer;
import frc.robot.commands.AutoCommands.AutoRunIndexerBelts;
import frc.robot.commands.AutoCommands.AutoRunIndexerSingleBall;
import frc.robot.commands.AutoCommands.DriveDistance;
import frc.robot.commands.AutoCommands.DriveDistanceBack;
import frc.robot.commands.AutoCommands.ExtendRunIntakeAuto;
import frc.robot.commands.AutoCommands.ExtendRunIntakeReverse;
import frc.robot.commands.AutoCommands.RetractStopIntake;
import frc.robot.commands.AutoCommands.ReverseIndexerAuto;
import frc.robot.commands.AutoCommands.Turn;
import frc.robot.commands.AutoCommands.TurnCounterClock;
import frc.robot.commands.AutoCommands.stopIndexerAuto;
import frc.robot.commands.AutoCommands.wait;
import frc.robot.commands.IndexerCommands.RunIndexerBelts;
import frc.robot.commands.IndexerCommands.StopIndexer;
import frc.robot.commands.IntakeCommands.RetractIntake;
import frc.robot.commands.IntakeCommands.ReverseIntake;
import frc.robot.commands.ShooterCommands.StopFlywheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LeftZoneAuto extends SequentialCommandGroup {
  /** Creates a new LeftZoneAuto. */
  public LeftZoneAuto() {

    // addCommands(
    // new ExtendRunIntakeAuto(),
    // new wait(0.7),
    // new DriveDistance(0.9,0.9, Constants.autoDriveSpeed), //.86, .3
    // new wait(0.6), 
    // new RetractStopIntake(),

    // new AutoRunIndexer(), 
    // new DriveDistanceBack(1.59,1.59,Constants.autoDriveSpeed), //1.55, .3
    // new TurnCounterClock(18),
    // new DriveDistanceBack(1,1, Constants.autoDriveSpeed), //.3
    // new AutoHighShot(), 
    // new wait(4), 
    // new AutoRunIndexerBelts(), 
    // new wait(1.3),
    // new stopIndexerAuto(), 
    // new StopFlywheel(), 
    // //new stopIndexerAuto(),

    // new ExtendRunIntakeAuto(),
    // new DriveDistance(0.13,0.13, 0.1),
    // new Turn(15), 
    // new DriveDistance(1.8,1.8,0.3),
    // new wait(.5),
    // new RetractStopIntake(),
    // new AutoRunIndexerSingleBall(),
    // new TurnCounterClock(18),
    // new ExtendRunIntakeReverse(),
    // new ReverseIndexerAuto());

    addCommands(
    new ExtendRunIntakeAuto(),
    new wait(0.7),
    new DriveDistance(0.9,0.9, Constants.autoDriveSpeed), //.86, .3
    new wait(0.6), 
    new RetractStopIntake(),

    new AutoRunIndexer(), 
    new DriveDistanceBack(0.5,0.5,Constants.autoDriveSpeed), //1.55, .3
    new TurnCounterClock(8),
    new AutoLongShot(), 
    new wait(2), 
    new AutoRunIndexerBelts(), 
    new wait(1.3),
    new stopIndexerAuto(), 
    new StopFlywheel(), 

    new ExtendRunIntakeAuto(),
    new DriveDistance(0.13,0.13, 0.1),
    new Turn(50), 
    new DriveDistance(1.8,1.8,0.3),
    new wait(.5),
    new RetractStopIntake(),
    new AutoRunIndexerSingleBall(),
    new Turn(90),
    // new Turn(90),
    // new Turn(90),
    new AutoGachShot(),
    new wait(1),
    new RunIndexerBelts()
    );
    //new ExtendRunIntakeReverse(),
    //new ReverseIndexerAuto());
  }
}
    //ball spit (Eli's Version)
    // new DriveDistance(3.05, 3.05, Constants.autoDriveSpeed);
    // new Turn(35);
    // new ExtendRunIntakeAuto();
    // new wait(1.5);
    // new Turn(-10);
    // new AutoReverseIntakeAndIndex();
    // new RetractIntake();   