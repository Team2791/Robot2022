package frc.robot.subsystem;

import frc.robot.*;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax leftLeader, rightLeader, leftFollower, rightFollower;
    private RelativeEncoder rightEncoder, leftEncoder;
    private final AHRS gyro;
     public Drivetrain() {
        super.register();
        leftLeader = new CANSparkMax(RobotMap.leftLeaderID, MotorType.kBrushless);
        rightLeader = new CANSparkMax(RobotMap.rightLeaderID, MotorType.kBrushless);
        rightLeader.setInverted(true);
        
        leftFollower = new CANSparkMax(RobotMap.leftFollowerID, MotorType.kBrushless);
        leftFollower.follow(leftLeader, false);
        rightFollower = new CANSparkMax(RobotMap.rightFollowerID, MotorType.kBrushless);
        rightFollower.follow(rightLeader, false);

        leftLeader.setSmartCurrentLimit(80);
        leftFollower.setSmartCurrentLimit(80);
        rightLeader.setSmartCurrentLimit(80);
        rightFollower.setSmartCurrentLimit(80);

        leftEncoder = leftLeader.getEncoder();
        rightEncoder = rightLeader.getEncoder();

        leftEncoder.setPositionConversionFactor(Constants.conversionFactor);
        rightEncoder.setPositionConversionFactor(Constants.conversionFactor);
        gyro = new AHRS(Port.kMXP);
    }
    public void resetGyro() {
        gyro.reset();    
    }
    public void setMotors(double left, double right){
        leftLeader.set(left);
        rightLeader.set(right);
    }

 
    public double getAngle() {
        return gyro.getYaw();
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

    }
}