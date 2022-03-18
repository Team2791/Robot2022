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
import frc.robot.commands.AutoCommands.DriveDistance;
import frc.robot.commands.AutoCommands.DriveDistanceBack;
import frc.robot.commands.AutoCommands.DriveIndex;
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
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      //First Intake
      new ExtendRunIntake(), 
      new Turn(8), 
      new DriveDistance(0.86, 0.86, 0.4), 
      new wait(0.5), 
      new RetractStopIntake(), 
      new AutoRunIndexer(), 

      //DriveBack, shoot 2
      new DriveDistanceBack(0.4, 0.4, 0.3), //Might not need

      new AutoGachShot(),
      new TurnCounterClock(5),
      new wait(1),
      new AutoRunIndexerBelts(),
      new wait(1.3),
      new StopFlywheel(),
      new stopIndexerAuto(),
  
      //Drive back to terminal
      new Turn(5),
      new ExtendRunIntake(),
      new DriveDistance(4, 4, 0.5),
      
      //Drive back and shoot 3rd and 4th 
      new DriveAndIndex(4.5, 4.5, 0.5),
      new AutoGachShot(),
      new TurnCounterClock(5),
      new wait(0.5),
      new AutoRunIndexerBelts(),
      new wait(1.3),
      new StopFlywheel(),
      new stopIndexerAuto());
  }
}
