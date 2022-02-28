package frc.robot.controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;

public class AnalogButton extends Button{
    XboxController stick;
    int positive;
    int negative;
    int alternate;
    double dead;

    public AnalogButton(XboxController driverStick, int positiveAxis) {
        stick = driverStick;
        positive = positiveAxis;
        negative = -1;
        alternate = -1;
        dead = 0.2;
    }

    public AnalogButton(XboxController joystick, int positiveAxis, int negativeAxis) {
        stick = joystick;
        positive = positiveAxis;
        negative = negativeAxis;
        alternate = -1;
        dead = 0.2;
    }

    public AnalogButton(XboxController joystick, int positiveAxis, int negativeAxis, double deadZone) {
        stick = joystick;
        positive = positiveAxis;
        negative = negativeAxis;
        alternate = -1;
        dead = Math.abs(deadZone);
    }
    public AnalogButton(XboxController joystick, int positiveAxis, int negativeAxis, int alternateAxis, double deadZone) {
        stick = joystick;
        positive = positiveAxis;
        negative = negativeAxis;
        alternate = alternateAxis;
        dead = Math.abs(deadZone);
    }

    @Override public boolean get() {
        double total = 0;
        if(alternate >= 0 && Math.abs(stick.getRawAxis(alternate)) > dead) {
            return true;
        }

        if(positive >= 0) {
            total += stick.getRawAxis(positive);
        }

        if(negative >= 0) {
            total -= stick.getRawAxis(negative);
        }

        return Math.abs(total) > dead;
    }
}

