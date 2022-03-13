// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.commands.AutoCommands.AutoHighShot;
import frc.robot.commands.AutoCommands.AutoReverseIntakeAndIndex;
import frc.robot.commands.AutoCommands.AutoRunIndexer;
import frc.robot.commands.AutoCommands.AutoRunIndexerBelts;
import frc.robot.commands.AutoCommands.DriveDistance;
import frc.robot.commands.AutoCommands.DriveDistanceBack;
import frc.robot.commands.AutoCommands.ExtendRunIntakeAuto;
import frc.robot.commands.AutoCommands.RetractStopIntake;
import frc.robot.commands.AutoCommands.Turn;
import frc.robot.commands.AutoCommands.TurnCounterClock;
import frc.robot.commands.AutoCommands.stopIndexerAuto;
import frc.robot.commands.AutoCommands.wait;
import frc.robot.commands.IndexerCommands.StopIndexer;
import frc.robot.commands.IntakeCommands.RetractIntake;
import frc.robot.commands.ShooterCommands.StopFlywheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class LeftZoneAuto extends SequentialCommandGroup {
  /** Creates a new LeftZoneAuto. */
  public LeftZoneAuto() {

    addCommands(
    new ExtendRunIntakeAuto(),
    new wait(0.5),
    new DriveDistance(0.86,0.86, 0.3),
    new wait(0.5), 
    new RetractStopIntake(),

    new AutoRunIndexer(), 
    new DriveDistanceBack(1.55,1.55,0.3), 
    new TurnCounterClock(9),
    new DriveDistanceBack(0.09,0.09,0.3),
    new AutoHighShot(), 
    new wait(1.3), 
    new AutoRunIndexerBelts(), 
    new wait(1.3),
    new stopIndexerAuto(), 
    new StopFlywheel(), 
    new stopIndexerAuto());
      
    
    
    // new ExtendRunIntakeAuto(),
    // new DriveDistance(1, 1, 0.3), 
    // new RetractStopIntake(), 
    // new AutoRunIndexer(),
    // new DriveDistanceBack(1.7, 1.7, 0.3), 
    // new TurnCounterClock(5), 
    // new AutoHighShot(),
    // new wait(1.5), 
    // new AutoRunIndexerBelts(), 
    // new wait(1.3), 
    // new StopFlywheel(), 
    // new stopIndexerAuto());
    // ball spit
    // new DriveDistance(3.05, 3.05, Constants.autoDriveSpeed);
    // new Turn(35);
    // new ExtendRunIntakeAuto();
    // new wait(1.5);
    // new Turn(-10);
    // new AutoReverseIntakeAndIndex();
    // new RetractIntake();    
  }
}
