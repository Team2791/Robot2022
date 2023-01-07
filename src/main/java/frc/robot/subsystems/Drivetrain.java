package frc.robot.subsystems;

import frc.robot.*;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax leftLeader, rightLeader, leftFollower, rightFollower;
    MotorControllerGroup left, right;
    DifferentialDrive drive;
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
        
        left = new MotorControllerGroup(leftFollower,leftLeader);
        right = new MotorControllerGroup(rightFollower, rightLeader);
        left.setInverted(true);
        drive = new DifferentialDrive(left, right);
    }
    public void resetGyro() {
        gyro.reset();    
    }
    public void setMotors(double left, double right){
        leftLeader.set(left);
        rightLeader.set(right);
    }
     public void arcadeDrive(double thurst, double turn) {
        drive.arcadeDrive(thurst, turn);
     }

    public void setBrakeMode() {
        leftLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightLeader.setIdleMode(CANSparkMax.IdleMode.kBrake);
        leftFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);
        rightFollower.setIdleMode(CANSparkMax.IdleMode.kBrake);

    }
    public double getAngle() {
        return gyro.getYaw();
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
    public void setRampUp(double time) {
        leftLeader.setOpenLoopRampRate(time);
        rightLeader.setOpenLoopRampRate(time);
    }

    @Override
    public void periodic() {
        // SmartDashboard.putNumber("Right Velocity", rightLeader.get());
        // SmartDashboard.putNumber("Left Velocity", leftLeader.get());
        SmartDashboard.putNumber("Right Position", getRightPosition());
        SmartDashboard.putNumber("Left Position", getLeftPosition());
        SmartDashboard.putNumber("angle", getAngle());



    }
}