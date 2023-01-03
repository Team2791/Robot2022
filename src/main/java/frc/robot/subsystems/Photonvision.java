// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import java.util.List;

// import org.photonvision.PhotonCamera;
// import org.photonvision.targeting.PhotonTrackedTarget;

// import edu.wpi.first.math.geometry.Transform2d;
// import edu.wpi.first.math.geometry.Transform3d;
// import edu.wpi.first.networktables.NetworkTable;
// import edu.wpi.first.networktables.NetworkTableEntry;
// import edu.wpi.first.networktables.NetworkTableInstance;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class Photonvision extends SubsystemBase {
//   /** Creates a new Photonv
//    * Photonision. */
//   PhotonTrackedTarget target;
//   PhotonCamera camera; 
//   public Photonvision() {
//     camera = new PhotonCamera("photonvision");
//     var result = camera.getLatestResult();

//     // if(result.hasTargets()) {
//     //   List<PhotonTrackedTarget> targets = result.getTargets();
//     //   target = result.getBestTarget();
//     // }
//   }


//   public double getSkew() {
    
//     return target.getSkew();
//   }

//   // public double getYaw() {
//   //   if(result.hasTargets()) {
//   //     List<PhotonTrackedTarget> targets = result.getTargets();
//   //     target = result.getBestTarget();
//   //   }
//   //   return target.getYaw();
//   // }

//   // public double getArea() {
//   //   return target.getArea();
//   // }

//   // public double getPitch() {
//   //   return target.getPitch();
//   // }
//   // public Transform3d getPose(){
//   //   return target.getBestCameraToTarget();
//   }


//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//     // SmartDashboard.putNumber("Yaw", getYaw()); //
//   }
// }
