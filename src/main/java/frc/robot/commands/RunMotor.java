package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.*;
public class RunMotor extends CommandBase {
    public RunMotor()
    {

    }
    public void initialize()
    {

    }
    public void execute()
    {
        Robot.prototypes.setMotor(Constants.motorspeed);
        
        
    }
    
}
