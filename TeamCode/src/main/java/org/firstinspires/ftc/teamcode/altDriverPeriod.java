package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.subsystems.strafe;
//import com.google.blocks.firstinspires.ftc.teamcode.subsystems.TelemetryLoop;
import org.firstinspires.ftc.teamcode.subsystems.hwMap;

//@Disabled
@TeleOp
public class altDriverPeriod extends OpMode {
    private hwMap robot = new hwMap();
    private Gamepad gamepad1 = new Gamepad();
    private strafe wheels = new strafe(robot, gamepad1);
    public altDriverPeriod(){}


    //private TelemetryLoop telemetryLoop = new TelemetryLoop();

    public void init(){
        robot.init(hardwareMap);

    }
    public void loop(){
        wheels.loop();
    }
    public void stop(){}
}