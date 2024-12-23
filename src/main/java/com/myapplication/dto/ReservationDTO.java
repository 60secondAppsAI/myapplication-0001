package com.myapplication.dto;

import java.util.List;
import java.util.Date;
import java.sql.Timestamp;
import java.time.Year;

import lombok.Getter;
import lombok.Setter;


@Getter @Setter
public class ReservationDTO {

	private Integer reservationId;

	private int passengerCount;

	private String contactName;

	private String contactEmail;






}