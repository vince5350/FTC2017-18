package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

public class clawArm2 {
    private hwMap robot = null;
    private Gamepad gamepad2 = null;
    public clawArm2(hwMap mainMap, Gamepad gamepad2){
        this.robot = mainMap;
        this.gamepad2 = gamepad2;
    }

    public void loop() {

        boolean xButton, yButton, aButton, bButton;

        //Arm
        xButton = gamepad2.x;
        yButton = gamepad2.y;

        //Claw
        aButton = gamepad2.a;
        bButton = gamepad2.b;

        //Raise arm
        if(xButton){
            robot.Arm.setPower(-0.6);
        }

        //Lower arm
        if(yButton){
            robot.Arm.setPower(0.6);
        }

        // Open(/close) arm
        if(aButton){
            robot.clawLeft.setPosition(1);
            robot.clawRight.setPosition(1);
        }

        //[Reverse of previous statement]
        if(bButton){
            robot.clawLeft.setPosition(0);
        }
    }
}
