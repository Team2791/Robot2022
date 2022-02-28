package frc.robot.controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Button;

public class DPadButton extends Button{
    public static final int kDPadUp = 0;
    public static final int kDPadRight = 1;
    public static final int kDPadDown = 2;
    public static final int kDPadLeft = 3;
    private XboxController stick;
    private int direction;

    public DPadButton(XboxController driverStick, int direction) {
        this.direction = direction;
        this.stick = driverStick;
    }

    public boolean get() {
        if(stick.getPOV() == -1) {
            return false;
        }
        return stick.getPOV() == direction * 90 ||
            stick.getPOV() == (direction * 90 - 45) % 360 ||
            stick.getPOV() == (direction * 90 + 45) % 360;
    }
}
