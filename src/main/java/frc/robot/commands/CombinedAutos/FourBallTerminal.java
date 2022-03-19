// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.AutoGachShot;
import frc.robot.commands.AutoCommands.AutoIndex;
import frc.robot.commands.AutoCommands.AutoLongShot;
import frc.robot.commands.AutoCommands.AutoRunIndexer;
import frc.robot.commands.AutoCommands.AutoRunIndexerBelts;
import frc.robot.commands.AutoCommands.DriveAndIndex;
import frc.robot.commands.AutoCommands.DriveAndIndex34;
import frc.robot.commands.AutoCommands.DriveDistance;
import frc.robot.commands.AutoCommands.DriveDistanceBack;
import frc.robot.commands.AutoCommands.DriveIndex;
import frc.robot.commands.AutoCommands.ExtendRunIntakeAuto;
import frc.robot.commands.AutoCommands.RetractStopIntake;
import frc.robot.commands.AutoCommands.Turn;
import frc.robot.commands.AutoCommands.TurnCounterClock;
import frc.robot.commands.AutoCommands.stopIndexerAuto;
import frc.robot.commands.AutoCommands.wait;
import frc.robot.commands.IntakeCommands.ExtendRunIntake;
import frc.robot.commands.ShooterCommands.StopFlywheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FourBallTerminal extends SequentialCommandGroup {
  /** Creates a new FourBallTerminal. */
  public FourBallTerminal() {
    // Add your commands in the addCommands() call, e.g.
    // addPCommands(new FooCommand(), new BarCommand());
    addCommands(
      //First Intake
      new ExtendRunIntakeAuto(), 
      new wait(0.7),
      new DriveDistance(1.3, 1.3, 0.4), 
      new wait(0.5), 
      new RetractStopIntake(), 
      
      //DriveBack, shoot 2
     //new DriveDistanceBack(1, 1, 0.4), //Might not need
      new DriveAndIndex(1, 1, 0.4),

      new AutoLongShot(),
      new TurnCounterClock(5),
      new wait(1.3),
      new AutoRunIndexerBelts(),
      new wait(1.3),
      new StopFlywheel(),
      new stopIndexerAuto(),
  
      //Drive back to terminal
      new Turn(5),//7
      new ExtendRunIntakeAuto(),

      
      //Drive back and shoot 3rd and 4th 
      new DriveIndex(4.7, 4.7, 0.5),
      new DriveAndIndex34(4.2, 4.2, 0.5),
      new AutoLongShot(),
      new TurnCounterClock(10), //10
      new wait(1),
      new AutoRunIndexerBelts(),
      new wait(3),
      new StopFlywheel(),
      new stopIndexerAuto());
  }
}
