package frc.robot.subsystems;

import frc.robot.*;

import com.fasterxml.jackson.databind.module.SimpleAbstractTypeResolver;
import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase{
    private CANSparkMax leftLeader, rightLeader, leftFollower, rightFollower;
    private AHRS gyro;

    DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(1.2735); 
    DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(getHeading());
    SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(Constants.ks,Constants.kv,Constants.ka);  

    PIDController leftPIDController = new PIDController(0,0,0);
    PIDController rightPIDController = new PIDController(0,0,0);


    Pose2d pose;

    public Drivetrain() {
        super.register();
        leftLeader = new CANSparkMax(RobotMap.leftLeaderID, MotorType.kBrushless);
        rightLeader = new CANSparkMax(RobotMap.rightLeaderID, MotorType.kBrushless);
        leftFollower = new CANSparkMax(RobotMap.leftFollowerID, MotorType.kBrushless);
        rightFollower = new CANSparkMax(RobotMap.rightFollowerID, MotorType.kBrushless);
        
        leftFollower.follow(leftLeader);
        rightFollower.follow(rightLeader);

        leftLeader.setInverted(true);
        rightLeader.setInverted(false);
        gyro = new AHRS(Port.kMXP);
    }
    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(-gyro.getAngle());
    }

    public DifferentialDriveWheelSpeeds getSpeeds() {
        return new DifferentialDriveWheelSpeeds(
            leftLeader.getEncoder().getVelocity() / Constants.gearRatio * Math.PI * Constants.wheelDiameterMeters / 60, 
            rightLeader.getEncoder().getVelocity() / Constants.gearRatio * Math.PI * Constants.wheelDiameterMeters / 60);
    }

    public SimpleMotorFeedforward getFeedforward() {
        return feedforward;
    }


    public PIDController getLeftPidController() {
        return leftPIDController;
    }
  
    public PIDController getRightPidController() {
        return rightPIDController;
    }

    public Pose2d getPose() {
        return pose;
    }
    public DifferentialDriveKinematics getKinematics() {
        return kinematics;
    }
    public void setOutput(double leftVolts, double rightVolts) {
        leftLeader.set(leftVolts/12);
        rightLeader.set(rightVolts/12);
    }
  
    public double getleftPos() {
        return leftLeader.getEncoder().getPosition() * Math.PI * Constants.wheelDiameterMeters;
    }
    public double getrightPos() {
        return rightLeader.getEncoder().getPosition() * Math.PI * Constants.wheelDiameterMeters;
    }
    

    @Override
    public void periodic() {
        SmartDashboard.putNumber("left pos", getleftPos());
        SmartDashboard.putNumber("Right pos", getrightPos());
        pose = odometry.update(getHeading(), getleftPos(), getrightPos());
    }
}