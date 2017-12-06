package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class hwMap {
    public DcMotor FLwheel  = null;
    public DcMotor FRwheel  = null;
    public DcMotor BLwheel  = null;
    public DcMotor BRwheel  = null;

    String visionKey = "ATbhi57/////AAAAGQRa1bE8jECdj/6icLYPmMlzH0c+5q1WUivk0mq1Nm8kOhItHlAvtT3K1jkfQN+tUw3cGYu+vtHDNUi02PE" +
            "wwDwYwQv+VzHot4Hy3P5ZM+inkT/2mRNsnUypgRdTSF8rliLAlIPU6cRROXF4Y5nMh1zudrotM+1oFVNwctJ6oJx99pyE3nNukIQYh8MNcyu0vbm" +
            "YeahHRijLt/NkW+0xUy6BVQBnLD8ZJSx4MV24HJTLDdubvlK2XjQfXFHBTGu3+BO7DRf97gJju7Gk/07RVtluSEGW1a3BceC/pKIkjvhozZGyuDmGv" +
            "uQH2QerIgQGQFtYRjw9HVpV/gUHf0twdqasWrj6oOWo5V4osxmB0eCX";

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