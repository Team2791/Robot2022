// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.commands.DrivetrainCommands;

// import org.photonvision.PhotonCamera;

// import edu.wpi.first.math.controller.PIDController;
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.Command;
// import edu.wpi.first.wpilibj2.command.CommandBase;
// import frc.robot.Constants;
// import frc.robot.Robot;


// public class SimpleAlign extends CommandBase {
//   PIDController simpid;
//   private Timer timer;
//   private Timer override;
//   double angle;
//   /** Creates a new JoeAim. */
//   PhotonCamera camera = new PhotonCamera("2791photonvision");
//   public SimpleAlign() {
//     addRequirements(Robot.drivetrain);
//     simpid = new PIDController(0.01, 0, 0);
//     timer = new Timer();
//     override = new Timer();
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     var result = camera.getLatestResult();
//     if(result!=null && result.hasTargets()) {
//        angle = result.getYaw();
//        SmartDashboard.putNumber("Simple Target Angle", angle);
//     }
//     simpid.setSetpoint(angle);
//     simpid.setTolerance(2);
//     timer.reset();
//     override.start();

//     SmartDashboard.putBoolean("Turn start", true);
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     double output = simpid.calculate(Robot.drivetrain.getAngle());
//     output = Math.min(output, 0.3);
//     Robot.drivetrain.setMotors(-output, output);
//     if(simpid.atSetpoint()){
//       timer.start();
//     }
//     else{
//       timer.reset();
//     }

//   }


//   // Called once the command ends or is interrupted.
//   @Override
//   public void end(boolean interrupted) {
//     SmartDashboard.putBoolean("Turn complete", true);
//     Robot.drivetrain.setMotors(0, 0);
//   }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     return simpid.atSetpoint() && timer.get()>.25 || override.get()>1;
    
//   }
// }
