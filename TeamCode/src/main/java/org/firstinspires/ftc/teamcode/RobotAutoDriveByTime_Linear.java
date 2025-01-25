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

import com.qualcomm.hardware.sparkfun.SparkFunOTOS;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * This OpMode illustrates the concept of driving a path based on time.
 * The code is structured as a LinearOpMode
 *
 * The code assumes that you do NOT have encoders on the wheels,
 *   otherwise you would use: RobotAutoDriveByEncoder;
 *
 *   The desired path in this example is:
 *   - Drive forward for 2 seconds or bump touch sensor (if available)
 *   - Drive Backward for 1 Second to hook specimen
 *   - Spin right 90 degrees or more, for 1.3 seconds, Park
 *   - Plan more moves to get behind samples
 *   - Park robot (or get behind samples to park and push sample into zone)
 *
 *  The code is written in a simple form with no optimizations.
 *  However, there are several ways that this type of sequence could be streamlined,
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this OpMode to the Driver Station OpMode list
 */

@Autonomous(name="Autonomous[WIP]", group="Robot")

public class RobotAutoDriveByTime_Linear extends LinearOpMode {
    Hardware11241 robot = new Hardware11241();   // Use Team 11241's hardware
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {
        // Initialize the hardware variables.
        // * The init() method of the hardware class does all the work here
        robot.init(hardwareMap);
        robot.intake.setPosition(0.3);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Ready to run");
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();
        robot.liftMotor.setPower(-0.75);
        Thread.sleep(650);
        robot.liftMotor.setPower(0);
        robot.hangMotor.setPower(-0.75);
        Thread.sleep(3100);
        robot.hangMotor.setPower(0);

        robot.leftBackDrive.setPower(-0.4);
        robot.rightBackDrive.setPower(-0.4);
        robot.leftFrontDrive.setPower(-0.4);
        robot.rightFrontDrive.setPower(-0.4);
        Thread.sleep(1000);
        robot.leftBackDrive.setPower(0);
        robot.rightBackDrive.setPower(0);
        robot.leftFrontDrive.setPower(0);
        robot.rightFrontDrive.setPower(0);
        Thread.sleep(100);
        /*
        robot.driveRight(500);
        robot.driveForward(200);
        robot.driveRight(700);
        robot.leftBackDrive.setPower(-0.55);
        robot.rightBackDrive.setPower(0.55);
        robot.leftFrontDrive.setPower(-0.55);
        robot.rightFrontDrive.setPower(0.55);
        while(true){
            SparkFunOTOS.Pose2D pos = robot.mouseSensor.getPosition();
            if(pos.h==180){
                robot.leftBackDrive.setPower(0);
                robot.rightBackDrive.setPower(0);
                robot.leftFrontDrive.setPower(0);
                robot.rightFrontDrive.setPower(0);
                break;
            }
        }
        robot.driveForward(800);
        robot.driveBackwards(800);
        robot.driveLeft(300);
        robot.driveForward(800);
        robot.driveBackwards(800);
        robot.driveLeft(300);
        robot.driveForward(800);
        robot.intake.setPosition(0.8);
        Thread.sleep(1000);
        robot.intake.setPosition(0.2);
        robot.driveBackwards(800);

         */
    }
}