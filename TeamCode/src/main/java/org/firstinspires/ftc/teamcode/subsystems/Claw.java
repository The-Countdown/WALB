package org.firstinspires.ftc.teamcode.subsystems;

public class Claw extends Robot.HardwareDevices {
    private Robot walB;
    public Claw(Robot walB) {
        this.walB = walB;
    }
    public void clawPosition(double position) {
        Robot.HardwareDevices.leftClaw.setPosition(position);
        Robot.HardwareDevices.rightClaw.setPosition(position);
    }
}
