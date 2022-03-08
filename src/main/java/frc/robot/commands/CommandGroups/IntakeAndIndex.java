// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.CommandGroups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeCommands.RunIntake;
import frc.robot.commands.IndexerCommands.RunIndexer;
import frc.robot.commands.IntakeCommands.ExtendRunIntake;
import frc.robot.commands.IntakeCommands.ReleaseIntake;
import frc.robot.commands.IntakeCommands.RetractIntake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeAndIndex extends ParallelCommandGroup {
  /** Creates a new IntakeAndIndex. */
  public IntakeAndIndex() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    
    addCommands(new ExtendRunIntake());
  }
}