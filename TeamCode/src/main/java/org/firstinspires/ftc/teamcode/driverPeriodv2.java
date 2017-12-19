package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.clawArm1;
import org.firstinspires.ftc.teamcode.subsystems.clawArm2;
import org.firstinspires.ftc.teamcode.subsystems.strafe1;
//import com.google.blocks.firstinspires.ftc.teamcode.subsystems.TelemetryLoop;
import org.firstinspires.ftc.teamcode.subsystems.hwMap;
import org.firstinspires.ftc.teamcode.subsystems.strafe2;

//@Disabled
@TeleOp
public class driverPeriodv2 extends OpMode {
    private hwMap robot = new hwMap();
    private Gamepad gamepad1 = new Gamepad();
    private Gamepad gamepad2 = new Gamepad();

    private strafe1 wheels1 = new strafe1(robot, gamepad1);
    private strafe2 wheels2 = new strafe2(robot, gamepad2);
    private clawArm1 arm1   = new clawArm1(robot, gamepad1);
    private clawArm2 arm2   = new clawArm2(robot, gamepad2);

    public driverPeriodv2(){}


    public void init(){
        robot.init(hardwareMap);

    }

    public void loop(){
        boolean ownership = true;

        if(gamepad1.start){
            ownership =!ownership;
        }
        if(ownership){
            wheels1.loop();
            arm2.loop();
        } else if(!ownership){
            wheels2.loop();
            arm1.loop();
        }

    }
    public void stop(){}
}