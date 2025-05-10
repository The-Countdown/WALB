package org.firstinspires.ftc.teamcode.subsystems;

public class Limelight extends Robot.HardwareDevices{
    private final Robot walB;

    public Limelight(Robot walB ) {
        this.walB = walB;
    }
    public void setPipeline(int pipeline) {
        Robot.HardwareDevices.limelight.pipelineSwitch(pipeline);
    }
}
