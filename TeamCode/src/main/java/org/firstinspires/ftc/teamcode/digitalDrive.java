package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.subsystems.hwMap;

//@Disabled
@TeleOp
public class digitalDrive extends OpMode {

    public digitalDrive() {
    }

    private hwMap robot = new hwMap();

    public void init() {
        //Initiates variables if not put in hardware map
        robot.init(hardwareMap);
    }

    public void loop() {
        //strafe

        boolean dpadDown, dpadUp, dpadRight, dpadLeft, lBumper, rBumper;

        dpadDown = gamepad1.dpad_down;
        dpadUp = gamepad1.dpad_up;
        dpadLeft = gamepad1.dpad_left;
        dpadRight = gamepad1.dpad_right;
        lBumper = gamepad1.left_bumper;
        rBumper = gamepad1.right_bumper;

        //float yStick = Range.clip(gamepad1.left_stick_y, -1 , 1);
        //float xStick = Range.clip(gamepad1.left_stick_x, -1, 1);

        //Forward
        if (dpadUp) {
            robot.FLwheel.setPower(-1.3);
            robot.FRwheel.setPower(1.3);
            robot.BLwheel.setPower(-1.3);
            robot.BRwheel.setPower(1.3);
        }

        //Backward
        if (dpadDown) {
            robot.FLwheel.setPower(1.3);
            robot.FRwheel.setPower(-1.3);
            robot.BLwheel.setPower(1.3);
            robot.BRwheel.setPower(-1.3);
        }

        //Left
        if (dpadLeft) {
            robot.FLwheel.setPower(1.3);
            robot.FRwheel.setPower(1.3);
            robot.BLwheel.setPower(-1.3);
            robot.BRwheel.setPower(-1.3);
        }

        //Right
        if (dpadRight) {
            robot.FLwheel.setPower(-1.3);
            robot.FRwheel.setPower(-1.3);
            robot.BLwheel.setPower(1.3);
            robot.BRwheel.setPower(1.3);
        }

        //Turn Left
        if (lBumper) {
            robot.FLwheel.setPower(0.8);
            robot.FRwheel.setPower(0.8);
            robot.BLwheel.setPower(0.8);
            robot.BRwheel.setPower(0.8);
        }

        //Turn Right
        if (rBumper) {
            robot.FLwheel.setPower(-0.8);
            robot.FRwheel.setPower(-0.8);
            robot.BLwheel.setPower(-0.8);
            robot.BRwheel.setPower(-0.8);
        }

        //Stop robot
        if (!dpadDown && !dpadUp && !dpadLeft && !dpadRight && !rBumper && !lBumper) {
            robot.FLwheel.setPower(0);
            robot.FRwheel.setPower(0);
            robot.BLwheel.setPower(0);
            robot.BRwheel.setPower(0);
        }

        //STOP MOTORS PERMANENTLY AKA EMERGENCY STOP
       /* if (xButton) {
            robot.FLwheel.setPower(0);
            robot.FLwheel.setPower(0);
            robot.FLwheel.setPower(0);
            robot.FLwheel.setPower(0);
            }


        robot.FLwheel.setPower(yStick + xStick);
        robot.FRwheel.setPower(yStick - xStick);
        robot.BLwheel.setPower(yStick + xStick);
        robot.BRwheel.setPower(yStick - xStick);
        */
    }

    public void stop(){}
}