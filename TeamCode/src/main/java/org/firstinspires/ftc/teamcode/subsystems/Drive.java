package org.firstinspires.ftc.teamcode.subsystems;

public class Drive extends Robot.HardwareDevices {
    private Robot walB;

    public Drive(Robot walB){
        this.walB = walB;
    }

    //robot.drive.linear(0-100)

    public void setPower(double leftPower, double rightPower) {
        Robot.HardwareDevices.leftTrack.setPower(leftPower);
        Robot.HardwareDevices.rightTrack.setPower(rightPower);
    }
    public void percentPower(double power) {
        if (power > 100) {
            power = 100;
        } else if (power < -100) {
            power = -100;
        }
            Robot.HardwareDevices.leftTrack.setPower(power / 100);
            Robot.HardwareDevices.rightTrack.setPower(power / 100);
        }
    //robot.drive.percentPower(150)
    //robot.drive.percentPower(-200)

}
