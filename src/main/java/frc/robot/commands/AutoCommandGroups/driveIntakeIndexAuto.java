// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.AutoCommandGroups;

import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import frc.robot.commands.AutoCommands.drive;
import frc.robot.commands.IndexerCommands.RunIndexer;
import frc.robot.commands.IntakeCommands.ExtendRunIntake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class driveIntakeIndexAuto extends ParallelRaceGroup {
  /** Creates a new Auto. */
  public driveIntakeIndexAuto() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    //PARALLEL RACE GROUP
    addCommands(new drive(), new ExtendRunIntake(), new RunIndexer());
  }
    // btw joes phone password is 830216

  
}
