package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Prototypes extends SubsystemBase {
    private CANSparkMax left;
    private CANSparkMax right;

    public Prototypes() {
        right = new CANSparkMax(RobotMap.rightID, MotorType.kBrushless);
        left = new CANSparkMax(RobotMap.leftID, MotorType.kBrushless);
    }
    public void setMotor(double leftSpeed, double rightSpeed) {
        right.set(rightSpeed);
        left.set(leftSpeed);
    }
}
