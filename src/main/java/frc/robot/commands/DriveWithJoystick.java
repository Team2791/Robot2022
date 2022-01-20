package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class DriveWithJoystick extends CommandBase {
    private Joystick stick;
    private boolean isSquaredTurn;
    private double deadzone;

    public DriveWithJoystick(Joystick stick, double deadzone) {
       
        this.stick = stick;
        this.deadzone = deadzone;
    }

    public void execute() {
        // if(stick.getRawButton(5) && stick.getRawButton(6)){
        //     Robot.drivetrain.setMotors(Constants.kCreep, Constants.kCreep);
        // } else if (stick.getRawButton(5)){
        //     Robot.drivetrain.setMotors(-Constants.kCreep,  Constants.kCreep);
        // } else if (stick.getRawButton(6)){
        //     Robot.drivetrain.setMotors( Constants.kCreep, -Constants.kCreep);
        // } else {
        //     Robot.drivetrain.setMotors(0, 0);
        // }
        double thrust = 0;
        if(stick.getRawButton(6)) {
            thrust = Constants.kCreep;
        }
        else if(stick.getRawButton(5)) {
            thrust = -Constants.kCreep;
        } else {
            thrust = stick.getRawAxis(3) - stick.getRawAxis(2);
            if(Math.abs(thrust) < deadzone) {
                thrust = 0;
            }
        }
        
        double turn = stick.getRawAxis(0) * Constants.TURN_FACTOR;

        if(turn < deadzone && turn > -deadzone) {
            turn = 0;
        }

        if(isSquaredTurn) {
            turn *= Math.abs(turn);
        }

        if(stick.getRawButton(2)) {
            turn /= 3;
        }

        // This causes the robot to crash.  It is the ideal solution to wheelies.
        // if(Math.abs(turn) > 0.05) {
        //     Robot.drivetrain.setLimit(Constants.kNeoAmpLimitTurn);
        // }
        // else {
        //     Robot.drivetrain.setLimit(Constants.kNeoAmpLimit);
        // }

        double left = Math.max(Math.min(thrust + turn, 1), -1);
        double right = Math.max(Math.min(thrust - turn, 1), -1);
        
        // Robot.drivetrain.setMotors(left, right);
        Robot.drivetrain.setMotors(left, right);
    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}