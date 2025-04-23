package org.firstinspires.ftc.teamcode.subsystems;

public class Head extends Robot.HardwareDevices {
    private Robot walB;

    public Head(Robot walB) {
        this.walB = walB;
    }


    public void servoTurn(double servoDegree){
        if (servoDegree > 270) {
            servoDegree = 270;
        } else if (servoDegree < 0) {
            servoDegree = 0;
        }
        Robot.HardwareDevices.headRotation.setPosition(servoDegree / 270);
    }
    //0-1
    public void servoRoatation(double turn){
        if (turn > 0.7) {
            turn = 0.7;
        } else if (turn < 0.3) {
            turn = 0.3;
        }
        Robot.HardwareDevices.headRotation.setPosition(turn);
    }
    //0-1
    public void eyeRoatation(double eyeTurn) {
        if (eyeTurn > 1) {
            eyeTurn = 1;
        } else if (eyeTurn < 0) {
            eyeTurn = 0;
        }
        Robot.HardwareDevices.eyeRotation.setPosition(eyeTurn);
    }
}
