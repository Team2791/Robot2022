// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommandGroups;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.AutoRunIndexer;
import frc.robot.commands.AutoCommands.AutoRunIndexerBelts;
import frc.robot.commands.AutoCommands.driveBack;
import frc.robot.commands.AutoCommands.wait2sec;
import frc.robot.commands.CommandGroups.ShootBall;
import frc.robot.commands.IndexerCommands.RunIndexer;
import frc.robot.commands.IndexerCommands.RunIndexerBelts;
import frc.robot.commands.IndexerCommands.TwoBallManuel;
import frc.robot.commands.IntakeCommands.RetractIntake;
import frc.robot.commands.ShooterCommands.StopFlywheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootIntakeShoot extends SequentialCommandGroup {
  /** Creates a new Shootbal. */
  public ShootIntakeShoot() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    //addCommands(new ShootBall(), new StopFlywheel(), new driveIntakeIndexAuto(), new RunIndexer(), new driveBack(), new ShootBall(), new StopFlywheel() );
    //addCommands(new driveIntakeIndexAuto(), new AutoRunIndexer(),new driveBack(), new AutoLowShot(), new wait2sec(), new TwoBallManuel(true),new TwoBallManuel(true), new StopFlywheel() );
  }
}
