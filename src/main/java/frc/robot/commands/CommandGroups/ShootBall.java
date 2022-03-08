// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CommandGroups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommands.AutoHighShot;
import frc.robot.commands.IndexerCommands.RunIndexerBelts;
import frc.robot.commands.IndexerCommands.RunShooter;
import frc.robot.commands.ShooterCommands.LowerHubClose;
import frc.robot.commands.ShooterCommands.ShooterTest;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ShootBall extends SequentialCommandGroup {
  /** Creates a new ShootBall. */
  public ShootBall() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new AutoHighShot(), new RunIndexerBelts());
  }
}