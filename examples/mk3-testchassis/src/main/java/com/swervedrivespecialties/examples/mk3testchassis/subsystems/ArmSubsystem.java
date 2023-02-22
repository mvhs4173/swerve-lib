// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package com.swervedrivespecialties.examples.mk3testchassis.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.swervedrivespecialties.examples.mk3testchassis.Constants;

public class ArmSubsystem extends SubsystemBase {
  private CANSparkMax m_motor;
  private CANSparkMax pivot_motor;
  private SparkMaxPIDController m_pidController;
  private SparkMaxPIDController pivot_pidController;
  private RelativeEncoder m_encoder;
  private RelativeEncoder pivot_encoder;
  private double m_zeroPos; 
  private double pivot_zeroPos;
  private final int gearRatio = 36;
  private double rotationsToGo;
  private double m_inchPerRot = Math.PI * 0.75;
  public double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput;

  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {
    m_motor = new CANSparkMax(Constants.CLIMBER_ID, MotorType.kBrushless);
    pivot_motor = new CANSparkMax(Constants.CLIMBER_PIVOT_ID, MotorType.kBrushless);
    m_pidController = m_motor.getPIDController();
    pivot_pidController = pivot_motor.getPIDController();
    kP = 0.01; kI = 0; kD = 1; kIz = 0; kFF = 0; kMaxOutput = 1; kMinOutput = -1; // PID parameters
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);  
    m_encoder = m_motor.getEncoder();
    pivot_pidController.setP(kP);
    pivot_pidController.setI(kI);
    pivot_pidController.setD(kD);
    pivot_pidController.setIZone(kIz);
    pivot_pidController.setFF(kFF);
    pivot_pidController.setOutputRange(kMinOutput, kMaxOutput);
    pivot_encoder = pivot_motor.getEncoder(); // might need to change to alternate encoder
    setZero();
  }

  public void goToPositionInches(double distance) {
    // m_encoder.setPosition(inchesToRotations(distance) - m_zeroPos);
    rotationsToGo = inchesToRotations(distance) - m_zeroPos;
    m_pidController.setReference(rotationsToGo, CANSparkMax.ControlType.kPosition);
    System.out.println("////////// zeroPos: " + m_zeroPos + "rotationsToGo: " + rotationsToGo);
  }

  public void goToPositionRotations(double distance) {
    pivot_pidController.setReference(distance, CANSparkMax.ControlType.kPosition);
  }

  public void setZero() {
    m_zeroPos = m_encoder.getPosition();
  }

  public double getPositionInches() {
    return rotationsToInches(m_encoder.getPosition());
  }

  public double getPositionRotations() {
    return pivot_encoder.getPosition();
  }

  private double rotationsToInches(double rotations) {
    return (rotations * m_inchPerRot) / gearRatio;
  }

  private double inchesToRotations(double inches) {
    return inches / m_inchPerRot * gearRatio;
  }

  public double getCurrentDrawArm(){
    return m_motor.getOutputCurrent();
  }

  public double getCurrentDrawPivot(){
    return pivot_motor.getOutputCurrent();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
