package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.subsystems.Wheels;
import org.firstinspires.ftc.teamcode.subsystems.TelemetryLoop;
import org.firstinspires.ftc.teamcode.subsystems.hardwareMap;

//@Disabled
@TeleOp(name="Code Test v.2")

public class altDriverPeriod extends OpMode {

    public altDriverPeriod(){}

    protected hardwareMap robot = new hardwareMap();
    protected Wheels drive = new Wheels();
    private TelemetryLoop telemetryLoop = new TelemetryLoop();

    public void init(){
        robot.init(hardwareMap);
        telemetry.addData ("Say", "Robot Initiated");
    }
    public void loop(){
        drive.loop();
        telemetryLoop.loop();
    }
    public void stop(){}
}