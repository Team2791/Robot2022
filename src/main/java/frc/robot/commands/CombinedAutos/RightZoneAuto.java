// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CombinedAutos;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoCommandGroups.driveIntakeIndexAuto;
import frc.robot.commands.AutoCommands.AutoHighShot;
import frc.robot.commands.AutoCommands.AutoRunIndexer;
import frc.robot.commands.AutoCommands.AutoRunIndexerBelts;
import frc.robot.commands.AutoCommands.driveBack;
import frc.robot.commands.AutoCommands.driveBackRightZone;
import frc.robot.commands.AutoCommands.wait2sec;
import frc.robot.commands.IndexerCommands.StopIndexer;
import frc.robot.commands.ShooterCommands.StopFlywheel;
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class RightZoneAuto extends SequentialCommandGroup {
  /** Creates a new RightZoneAuto. */
  public RightZoneAuto() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(new driveIntakeIndexAuto(), new AutoRunIndexer(), new driveBackRightZone(), new AutoHighShot(), new wait2sec(), new AutoRunIndexerBelts(), new wait2sec(),new StopFlywheel(), new StopIndexer());
  }
}
