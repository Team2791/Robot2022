package frc.robot.subsystems;

import frc.robot.*;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax leftLeader, rightLeader, leftFollower, rightFollower;
    private RelativeEncoder rightEncoder, leftEncoder;

     public Drivetrain() {
        super.register();
        leftLeader = new CANSparkMax(RobotMap.leftLeaderID, MotorType.kBrushless);
        rightLeader = new CANSparkMax(RobotMap.rightLeaderID, MotorType.kBrushless);
        rightLeader.setInverted(true);
        leftFollower = new CANSparkMax(RobotMap.leftFollowerID, MotorType.kBrushless);
        leftFollower.follow(leftLeader, false);
        rightFollower = new CANSparkMax(RobotMap.rightFollowerID, MotorType.kBrushless);
        rightFollower.follow(rightLeader, false);

        leftEncoder = leftLeader.getEncoder();
        rightEncoder = rightLeader.getEncoder();

        leftEncoder.setPositionConversionFactor(Constants.conversionFactor);
        rightEncoder.setPositionConversionFactor(Constants.conversionFactor);
    }

    public void setMotors(double left, double right){
        leftLeader.set(left);
        rightLeader.set(right);
    }

    public void setBrakeMode() {
        leftLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
        leftFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);

    }
    public void setCoastMode() {
        leftLeader.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightLeader.setIdleMode(CANSparkMax.IdleMode.kCoast);
        leftFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);
        rightFollower.setIdleMode(CANSparkMax.IdleMode.kCoast);

    }

    public CANSparkMax getLeftMotor(){
        return leftLeader;
    }

    public CANSparkMax getRightMotor(){
        return rightLeader;
    }
    
    public RelativeEncoder getLeftEncoder(){
        return leftEncoder;
    }

    public RelativeEncoder getRightEncoder(){
        return rightEncoder;
    }

    public double getLeftPosition(){
        return leftEncoder.getPosition();
    }

    public double getRightPosition(){
        return rightEncoder.getPosition();
    }
    public void resetEncoders() {
        rightEncoder.setPosition(0);
        leftEncoder.setPosition(0);
    }

    @Override
    public void periodic() {
        // SmartDashboard.putNumber("Right Velocity", rightLeader.get());
        // SmartDashboard.putNumber("Left Velocity", leftLeader.get());
        SmartDashboard.putNumber("Right Position", getRightPosition());
        SmartDashboard.putNumber("Left Position", getLeftPosition());


    }
}