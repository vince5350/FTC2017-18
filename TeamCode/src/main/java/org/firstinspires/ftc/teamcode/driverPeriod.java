package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.Range;

@Disabled
@TeleOp(name="Competition Driver Period")
public class driverPeriod extends OpMode {

    public driverPeriod(){}

    hardwareMap robot = new hardwareMap();

    public void init(){
        //Initiates variables if not put in hardware map
        robot.init(hardwareMap);
    }

    public void loop(){
        //Wheels
        float lStickX, lStickY;
        lStickX   = gamepad1.left_stick_x;
        lStickY   = gamepad1.left_stick_y;

        lStickX = Range.clip(lStickX, -1,1);
        lStickY = Range.clip(lStickY, -1,1);

        /* The dead zone is -0.2 - +0.2
           In case the stick isn't centred
        */

        //Forward
        if(lStickY < 0.2){
            robot.FLwheel.setPower(-(lStickY+lStickX));
            robot.FRwheel.setPower(lStickY+lStickX);
            robot.BLwheel.setPower(-(lStickY+lStickX));
            robot.BRwheel.setPower(lStickY+lStickX);
        }

        //Backward
        if (lStickY > -0.2){
            robot.FLwheel.setPower(lStickY+lStickX);
            robot.FRwheel.setPower(-(lStickY+lStickX));
            robot.BLwheel.setPower(lStickY+lStickX);
            robot.BRwheel.setPower(-(lStickY+lStickX));
        }

        //Left
        if (lStickX < -0.2){
            robot.FLwheel.setPower(lStickY+lStickX);
            robot.FRwheel.setPower(lStickY+lStickX);
            robot.BLwheel.setPower(-(lStickY+lStickX));
            robot.BRwheel.setPower(-(lStickY+lStickX));
        }

        //Right
        if (lStickX < 0.2){
            robot.FLwheel.setPower(-(lStickY+lStickX));
            robot.FRwheel.setPower(-(lStickY+lStickX));
            robot.BLwheel.setPower(lStickY+lStickX);
            robot.BRwheel.setPower(lStickY+lStickX);
        }


    }

    public void stop(){}
}