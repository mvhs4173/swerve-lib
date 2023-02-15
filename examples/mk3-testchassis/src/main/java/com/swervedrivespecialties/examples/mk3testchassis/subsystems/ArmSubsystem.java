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
  private SparkMaxPIDController m_pidController;
  private RelativeEncoder m_encoder;
  private double m_zeroPos; 
  private double m_inchPerRot = Math.PI * 0.75;

  /** Creates a new ArmSubsystem. */
  public ArmSubsystem() {
    m_motor = new CANSparkMax(Constants.CLIMBER_ID, MotorType.kBrushless);
    m_encoder = m_motor.getEncoder();
    setZero();
  }

  public void goToPositionInches(double distance) {
    m_encoder.setPosition(inchesToRotations(distance) - m_zeroPos);
  }

  public void setZero() {
    m_zeroPos = m_encoder.getPosition();
  }

  public double getPositionInches() {
    return rotationsToInches(m_encoder.getPosition());
  }

  private double rotationsToInches(double rotations) {
    return rotations * m_inchPerRot;
  }

  private double inchesToRotations(double inches) {
    return inches / m_inchPerRot;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
