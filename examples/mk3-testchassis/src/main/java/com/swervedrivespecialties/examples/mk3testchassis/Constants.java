package com.swervedrivespecialties.examples.mk3testchassis;

public class Constants {
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.53; //this is in meter 
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.53;

    public static final int DRIVETRAIN_PIGEON_ID = 39;

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 36;
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 35;
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 45;
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = 0; // -Math.toRadians(261.73);

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 38;
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 37;
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 47;
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = 0; // -Math.toRadians(140.62);

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 31;
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 32;
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 41;
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = 0; // -Math.toRadians(325.63);

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 33;
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 34;
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 43;
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = 0; // -Math.toRadians(266.48);
}
