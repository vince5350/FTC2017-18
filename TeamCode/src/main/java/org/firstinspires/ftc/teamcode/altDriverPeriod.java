package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.hardwareMap;
import org.firstinspires.ftc.teamcode.subsystems.strafe;

@Disabled
@TeleOp(name="Code Test v.2")

public class altDriverPeriod extends OpMode {

    public altDriverPeriod(){}

    protected hardwareMap robot = new hardwareMap();
    private strafe drive = new strafe();

    public void init(){
        robot.init(hardwareMap);
    }
    public void loop(){
        drive.loop();
    }
    public void stop(){}
}