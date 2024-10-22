/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * This is NOT an opmode.
 *
 * This class can be used to define all the specific hardware for a single robot.
 * In this case that robot is a Pushbot.
 * See PushbotTeleopTank_Iterative and others classes starting with "Pushbot" for usage examples.
 *
 * This hardware class assumes the following device names have been configured on the robot:
 * Note:  All names are lower case and some have single spaces between words.
 *
 * Motor channel:  Left Front drive motor:          "leftFrontDrive"
 * Motor channel:  Right Front drive motor:         "rightFrontDrive"
 * Motor channel:  Left Rear drive motor:           "leftRearDrive"
 * Motor channel:  Right Rear drive motor:          "leftRearDrive"
 * Motor channel:  Lift Arm:                        "liftMotor"
 * Servo channel:  Servo to intake Left:            "intakeL"
 * Servo channel:  Servo to intake Right:           "intakeR"
 * Servo channel:  Servo to drop pixel:             "drop"
 * Servo channel:  Servo paperairplane:             "paperairplane"
 *
 */
public class Hardware11241{
    /* Public OpMode members. */
    public DcMotor  leftDrive         = null;
    public DcMotor  rightDrive        = null;
    public DcMotor  liftMotor         = null;
    public DcMotor  hangMotor         = null;
    public Servo    intake            = null;
    public Servo    orient            = null;

    public static final double MID_SERVO       =  0.50 ;

    /* local OpMode members. */
    private HardwareMap hwMap           = null;
    private ElapsedTime period  = new ElapsedTime();

    /* Constructor */
    public Hardware11241(){

    }

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        leftDrive = hwMap.get(DcMotor.class, "leftDrive");
        rightDrive = hwMap.get(DcMotor.class, "rightDrive");
        liftMotor = hwMap.get(DcMotor.class, "liftMotor");
        hangMotor = hwMap.get(DcMotor.class, "hangMotor");

        leftDrive.setDirection(DcMotor.Direction.FORWARD);  // Set to FORWARD FOR goBILDA motors
        rightDrive.setDirection(DcMotor.Direction.REVERSE); //// Set to "" goBILDA motors
        liftMotor.setDirection(DcMotor.Direction.REVERSE);       // Set to "" goBILDA motors
        hangMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        // Set all motors to zero power
        leftDrive.setPower(0);
        rightDrive.setPower(0);
        liftMotor.setPower(0);
        hangMotor.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        leftDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        hangMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Define and initialize ALL installed servos.
        intake = hwMap.get(Servo.class, "intake");
        orient = hwMap.get(Servo.class, "orient");

        intake.setPosition(1.0);
        orient.setPosition(0.5);

    }
    public void driveForward(int time){
        leftDrive.setPower(-0.55);
        rightDrive.setPower(-0.55);

        try {
            Thread.sleep(time, 400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
    public void turnLeft(){
        leftDrive.setPower(0.55);
        rightDrive.setPower(-0.55);

        try {
            Thread.sleep(355, 400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
    public void turnRight(){
        leftDrive.setPower(-0.55);
        rightDrive.setPower(0.55);
        try {
            Thread.sleep(355, 400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
    public void driveLeft(int time){
        leftDrive.setPower(-0.55);
        rightDrive.setPower(0);
        try {
            Thread.sleep(time, 400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
    public void driveRight(int time){
        leftDrive.setPower(0);
        rightDrive.setPower(-0.55);
        try {
            Thread.sleep(time, 400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
    public void driveBackwards(int time){
        leftDrive.setPower(0.55);
        rightDrive.setPower(0.55);        try {
            Thread.sleep(time, 400);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        leftDrive.setPower(0);
        rightDrive.setPower(0);
    }
}