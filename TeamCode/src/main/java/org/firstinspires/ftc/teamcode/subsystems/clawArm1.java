package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

public class clawArm1 {
    private hwMap robot = null;
    private Gamepad gamepad1 = null;
    public clawArm1(hwMap mainMap, Gamepad gamepad1){
        this.robot = mainMap;
        this.gamepad1 = gamepad1;
    }

    public void loop() {

        boolean xButton, yButton, aButton, bButton;

        //Arm
        xButton = gamepad1.x;
        yButton = gamepad1.y;

        //Claw
        aButton = gamepad1.a;
        bButton = gamepad1.b;

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
