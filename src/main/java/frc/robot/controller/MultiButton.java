package frc.robot.controller;

import edu.wpi.first.wpilibj2.command.button.Button;

public class MultiButton extends Button {
	private final Button[] buttons;

	public MultiButton(Button[] buttons) {
		this.buttons = new Button[buttons.length];
		System.arraycopy(buttons, 0, this.buttons, 0, buttons.length);
	}

	public boolean get() {
		for (Button b : buttons) {
			if (b.get()) return true;
		}

		return false;
	}
}
