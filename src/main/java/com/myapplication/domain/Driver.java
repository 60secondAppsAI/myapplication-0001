package com.myapplication.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Year;
import jakarta.persistence.Transient;



@Entity
@Table(name="drivers")
@Getter @Setter @NoArgsConstructor
public class Driver {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
  	@Column(name="driver_id")
	private Integer driverId;
    
  	@Column(name="name")
	private String name;
    
  	@Column(name="license_number")
	private String licenseNumber;
    
  	@Column(name="contact_information")
	private String contactInformation;
    
	




}
