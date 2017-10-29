package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.util.Range;
import org.firstinspires.ftc.teamcode.altDriverPeriod;

public class Wheels extends altDriverPeriod {

    public void loop() {
        //Wheels
        float lStickX, lStickY, lTrigger, rTrigger;
        boolean bButton;

        bButton = gamepad1.b;

        lStickX = gamepad1.left_stick_x;
        lStickY = gamepad1.left_stick_y;
        lTrigger = gamepad1.left_trigger;
        rTrigger = gamepad1.right_trigger;

        lStickX = Range.clip(lStickX, -1, 1);
        lStickY = Range.clip(lStickY, -1, 1);
        lTrigger = Range.clip(lTrigger, 0, 1);
        rTrigger = Range.clip(rTrigger, 0, 1);


        //Forward
        if (lStickY > 0) {
            robot.FLwheel.setPower(-(lStickY + lStickX));
            robot.FRwheel.setPower(lStickY + lStickX);
            robot.BLwheel.setPower(-(lStickY + lStickX));
            robot.BRwheel.setPower(lStickY + lStickX);
        }

        //Backward
        if (lStickY < 0) {
            robot.FLwheel.setPower(lStickY + lStickX);
            robot.FRwheel.setPower(-(lStickY + lStickX));
            robot.BLwheel.setPower(lStickY + lStickX);
            robot.BRwheel.setPower(-(lStickY + lStickX));
        }

        //Left
        if (lStickX < 0) {
            robot.FLwheel.setPower(lStickY + lStickX);
            robot.FRwheel.setPower(lStickY + lStickX);
            robot.BLwheel.setPower(-(lStickY + lStickX));
            robot.BRwheel.setPower(-(lStickY + lStickX));
        }

        //Right
        if (lStickX > 0) {
            robot.FLwheel.setPower(-(lStickY + lStickX));
            robot.FRwheel.setPower(-(lStickY + lStickX));
            robot.BLwheel.setPower(lStickY + lStickX);
            robot.BRwheel.setPower(lStickY + lStickX);
        }

        //Turn Left
        if (lTrigger > 0) {
            robot.FLwheel.setPower(-0.6);
            robot.FRwheel.setPower(-0.6);
            robot.BLwheel.setPower(-0.6);
            robot.BRwheel.setPower(-0.6);
        }

        //Turn Right
        if (rTrigger > 0) {
            robot.FLwheel.setPower(0.6);
            robot.FRwheel.setPower(0.6);
            robot.BLwheel.setPower(0.6);
            robot.BRwheel.setPower(0.6);
        }

        //Boost
        if (bButton){
            robot.FLwheel.setPower((-(lStickY+lStickX))*2);
            robot.FRwheel.setPower((lStickY+lStickX)*2);
            robot.BLwheel.setPower((-(lStickY+lStickX))*2);
            robot.BRwheel.setPower((lStickY+lStickX)*2);
        }
    }
}
