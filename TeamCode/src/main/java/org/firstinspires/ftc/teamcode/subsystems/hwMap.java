package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class hwMap {
    public DcMotor FLwheel  = null;
    public DcMotor FRwheel  = null;
    public DcMotor BLwheel  = null;
    public DcMotor BRwheel  = null;


    private com.qualcomm.robotcore.hardware.HardwareMap hwMap       = null;

    public hwMap(){}

    public void init(com.qualcomm.robotcore.hardware.HardwareMap ahwMap){
        hwMap = ahwMap;

        //Define and init motors
        FLwheel  = hwMap.dcMotor.get("FLwheel");
        FRwheel  = hwMap.dcMotor.get("FRwheel");
        BLwheel  = hwMap.dcMotor.get("BLwheel");
        BRwheel  = hwMap.dcMotor.get("BRwheel");


        //Set all motors to zero power
        FLwheel.setPower(0);
        FRwheel.setPower(0);
        BLwheel.setPower(0);
        BRwheel.setPower(0);

        //Set motors to run w/o encoders
        FLwheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FRwheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BLwheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BRwheel.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        //FRwheel.setDirection(DcMotor.Direction.FORWARD);
        //FLwheel.setDirection(DcMotor.Direction.REVERSE);
        //BRwheel.setDirection(DcMotor.Direction.REVERSE);
        //BLwheel.setDirection(DcMotor.Direction.FORWARD);


    }

}