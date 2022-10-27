// // Copyright (c) FIRST and other WPILib contributors.
// // Open Source Software; you can modify and/or share it under the terms of
// // the WPILib BSD license file in the root directory of this project.

// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
// import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
// import com.ctre.phoenix.motorcontrol.can.TalonFX;
// import com.ctre.phoenix.sensors.CANCoder;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.wpilibj.PneumaticsModuleType;
// import edu.wpi.first.wpilibj.Solenoid;
// import edu.wpi.first.wpilibj.motorcontrol.Talon;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import frc.robot.RobotMap;

// public class Climber extends SubsystemBase {
//   /** Creates a new Climber. */
//   private TalonFX climbLeft, climbRight;
//   private Solenoid climbSol;

//   public Climber() {
//       //starts always retracted
//       //when going up or down, extend pistons 
//       //Whenever climber stops, retract pistons 
      
//     super.register();
//     climbLeft = new TalonFX(RobotMap.leftClimbID);
//     climbRight = new TalonFX(RobotMap.rightClimbID);
//     climbSol = new Solenoid(PneumaticsModuleType.REVPH, RobotMap.climb);

//     climbLeft.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
//     climbRight.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 0);
//     climbLeft.setNeutralMode(NeutralMode.Brake);
//     climbRight.setNeutralMode(NeutralMode.Brake);
//     resetClimberPosition(); 

//   }
//   public void setLeftMotor(double speed)
//   {
//     climbLeft.set(TalonFXControlMode.PercentOutput,-speed);
//   }
//   public void setRightMotor(double speed)
//   {
//     climbRight.set(TalonFXControlMode.PercentOutput,-speed);
//   }
//   public void setMotors(double speed) {
//     climbLeft.set(TalonFXControlMode.PercentOutput,-speed);
//     climbRight.set(TalonFXControlMode.PercentOutput,-speed);
//   }
//   public void setExtended() {
//     //pins in (motors can run)
//     climbSol.set(true);
//   }
//   public void setRetracted() {
//     //pins out (motors cannot run)
//     climbSol.set(false);
//   }
//   public double getLeftClimbPosition() {
//       return climbLeft.getSelectedSensorPosition();
//   }
//   public double getRightClimbPosition() {
//     return climbRight.getSelectedSensorPosition();

//   }
//   public void resetClimberPosition() {
//     climbLeft.setSelectedSensorPosition(0);
//     climbRight.setSelectedSensorPosition(0);
//   }
//   public boolean getClimbSolenoid(){
//     return climbSol.get();
//   }

//   @Override
//   public void periodic() {
//     SmartDashboard.putNumber("Right Climb", climbRight.getSelectedSensorPosition());
//     SmartDashboard.putNumber("Left Climb", climbLeft.getSelectedSensorPosition());
//     // // This method will be called once per scheduler run
//   }
// }