package com.Hotel.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Booking {

	@Id
	@Column(name="U_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long U_id;
	private String roomNo;
	private String type;
	private String totalPrice;
	private String date;
}
