// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;
import frc.robot.Constants;
import frc.robot.commands.AutoCommands.AutoHighShot;
import frc.robot.commands.AutoCommands.AutoMidShot;
import frc.robot.commands.AutoCommands.AutoRunIndexer;
import frc.robot.commands.AutoCommands.AutoRunIndexerBelts;
import frc.robot.commands.AutoCommands.AutoRunIndexerSingleBall;
import frc.robot.commands.AutoCommands.DriveDistance;
import frc.robot.commands.AutoCommands.DriveDistanceBack;
import frc.robot.commands.AutoCommands.DriveGetting34ball;
import frc.robot.commands.AutoCommands.DriveIndex;
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
      //2 balls shot (this part should work ) 
      new ExtendRunIntakeAuto(),
      new wait(0.5),
      new DriveDistance(0.86,0.86, 0.55),
      new wait(0.5), 
      new RetractStopIntake(),
  
      new AutoRunIndexer(), 
      new DriveDistanceBack(1.55,1.55, 0.55), 
      new Turn(9),
      new DriveDistanceBack(0.09,0.09, 0.4),
      new AutoHighShot(), 
      new wait(1), 
      new AutoRunIndexerBelts(), 
      new wait(1.3),
      new stopIndexerAuto(),
      new wait(1.3), 
      new StopFlywheel(), 
      new stopIndexerAuto(),
      new DriveDistance(0.25,0.25,0.4),
      new Turn(33),


      //Pick up 3rd nad 4th ball
      new ExtendRunIntakeAuto(),
      new DriveIndex(5.5,5.5,0.5), 

      //Drive Back, Line up and shoot
      new DriveDistanceBack(5,5, 0.5), 
      new TurnCounterClock(36), 
      new DriveDistanceBack(0.34,0.34,Constants.autoDriveSpeed),
      new AutoHighShot(), 
      new wait(1),
      new AutoRunIndexerBelts(), 
      new wait(1), 
      new StopFlywheel(), 
      new stopIndexerAuto()); 
  }
}
