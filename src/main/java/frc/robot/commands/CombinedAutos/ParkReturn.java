// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.commands.AutoCommands.wait;
import frc.robot.commands.DrivetrainCommands.stopMotors;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ParkReturn extends SequentialCommandGroup {
  /** Creates a new RightZoneFourBallAuto. */
  public ParkReturn() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        Robot.drivetrain.followTrajectory(Robot.parkGo),
        new stopMotors(),
        new wait(2.0),
        Robot.drivetrain.followTrajectory(Robot.parkReturn),
        new stopMotors());
  }
}