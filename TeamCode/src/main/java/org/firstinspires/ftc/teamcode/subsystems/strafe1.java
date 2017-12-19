package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.Range;

public class strafe1 {
    private hwMap robot = null;
    private Gamepad gamepad1 = null;
    public strafe1(hwMap mainMap, Gamepad gamepad1){
        this.robot = mainMap;
        this.gamepad1 = gamepad1;
    }

    public void loop() {

            float lStickX, lStickY;
            boolean bButton, lBumper, rBumper;

            bButton = gamepad1.b;


            lStickX = gamepad1.left_stick_x;
            lStickY = gamepad1.left_stick_y;
            lBumper = gamepad1.left_bumper;
            rBumper = gamepad1.right_bumper;

            lStickX = Range.clip(lStickX, -1, 1);
            lStickY = Range.clip(lStickY, -1, 1);

            robot.FLwheel.setPower(lStickY + lStickX);
            robot.FRwheel.setPower(lStickY - lStickX);
            robot.BLwheel.setPower(lStickY - lStickX);
            robot.BRwheel.setPower(lStickY + lStickX);

            //Turn Left
            if (lBumper) {
                robot.FLwheel.setPower(0.6);
                robot.FRwheel.setPower(0.6);
                robot.BLwheel.setPower(0.6);
                robot.BRwheel.setPower(0.6);
            }

            //Turn Right
            if (rBumper) {
                robot.FLwheel.setPower(-0.6);
                robot.FRwheel.setPower(-0.6);
                robot.BLwheel.setPower(-0.6);
                robot.BRwheel.setPower(-0.6);
            }

            //Boost
            if (bButton) {
                robot.FLwheel.setPower((lStickY + lStickX) * 2);
                robot.FRwheel.setPower((lStickY - lStickX) * 2);
                robot.BLwheel.setPower((lStickY - lStickX) * 2);
                robot.BRwheel.setPower((lStickY + lStickX) * 2);

            }
        }
    }

