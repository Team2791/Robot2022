// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands.PWCommandGroups;

// import edu.wpi.first.math.trajectory.Trajectory;
// import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
// import frc.robot.Robot;
// import frc.robot.commands.AutoCommands.drive;

// // NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// // information, see:
// // https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
// public class FourBallTest extends SequentialCommandGroup {  
//   /** Creates a new FourBallTest. */
//   public FourBallTest() {
//     // Add your commands in the addCommands() call, e.g.
//     // addCommands(new FooCommand(), new BarCommand());
//     Trajectory trajectory1 = Robot.drivetrain.loadTrajectoryFromFile("get First");

//     addCommands(
//       // Resets odometery which is what defines where the robot is.  This isn't generally nessecary for a match auto because the
//       // robot will start on the field at the right spot (hopefully) but it is useful for testing / simulation.
//      // Robot.drivetrain.createCommandForTrajectory(trajectory1, false).withTimeout(50).withName("Center1")
//   } 
// }
