package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="AutoParkEasy", group="Robot")
public class Autosuperbasic extends LinearOpMode {
    Hardware11241 robot = new Hardware11241();   // Use Team 11241's hardware
    /* Declare OpMode members. */
    private ElapsedTime     runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException{
        // Initialize the hardware variables.
        // * The init() method of the hardware class does all the work here
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "skibidi sigma is ready to rock and rizz");    //
        telemetry.update();

        // Wait for the game to start (driver presses START)
        waitForStart();
        robot.leftDrive.setPower(-0.25);
        robot.rightDrive.setPower(-0.25);

        while (opModeIsActive()) {
            if (runtime.seconds() > 3) {
                robot.leftDrive.setPower(0);
                robot.rightDrive.setPower(0);
                break;
            }
        }
    }
}
