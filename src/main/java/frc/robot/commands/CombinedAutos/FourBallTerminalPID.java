// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.*;
import frc.robot.commands.IntakeCommands.ExtendRunIntake;
import frc.robot.commands.ShooterCommands.StopFlywheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class FourBallTerminalPID extends SequentialCommandGroup {
  /** Creates a new FourBallTerminal. */
  public FourBallTerminalPID() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      //First Intake
      new ExtendRunIntakeAuto(), 
      new wait(0.7),
      new DriveDistance(1.3, 1.3, 0.4), 
      new wait(0.5), 
      new RetractStopIntake(), 
      
      //DriveBack, shoot 2
     //new DriveDistanceBack(1, 1, 0.4), //Might not need
      new DriveAndIndex(1.1, 1.1, 0.4), //1
      new AutoLongShot(),
      new TurnCounterClockwisePID(-22.34), //TurnCounterClock(5)
      //new TurnCounterClockwisePID(-22.34),
      //new TurnCounterClockwisePID(-22.34),
      new wait(.5), //1.25
      new AutoRunIndexerBelts(),
      new wait(1.3),
      new StopFlywheel(),
      new stopIndexerAuto(),
  
      //Drive back to terminal
      //new TurnPID(10.17),//7 //Turn(5)
      //new TurnPID(10.17),
      new TurnCounterClockwisePID(8.875), //10.17 //8.375
      new ExtendRunIntakeAuto(),

      
      //Drive back and shoot 3rd and 4th 
      new DriveIndex(4.6, 4.6, 0.5), //4.7
      new wait(.25),
      new DriveAndIndex34(4.1, 4.1, 0.52), //4.2
      new AutoLongShot(),
      new TurnCounterClockwisePID(-20.34),//-22.34 //10 //TurnCounterClock(13)
      //new TurnCounterClockwisePID(-22.34),
      //new TurnCounterClockwisePID(-22.34),
      new wait(1),
      new AutoRunIndexerBelts(),
      new wait(3),
      new StopFlywheel(),
      new stopIndexerAuto());
  }
}