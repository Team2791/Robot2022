package frc.robot.controller;

import edu.wpi.first.wpilibj2.command.button.Button;

public class MultiButton extends Button{
    private Button[] buttons;
    
    public MultiButton(Button[] buttons) {
        this.buttons = new Button[buttons.length];
        for(int i = 0; i < buttons.length; ++i) {
            this.buttons[i] = buttons[i];
        }
    }

    public boolean get() {
        for(Button b : buttons) {
            if(b.get()) return true;
        }
        
        return false;
    }
}
