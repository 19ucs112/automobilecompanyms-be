package com.automobileCompany.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="registeredcars")
public class RegisteredCar {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="company")
	private String company;
	@Column(name="car_name")
	private String carName;
	@Column(name="version")
	private String car_version;
	@Column(name="model")
	private Integer model;
	@Column(name="seater")
	private Integer seater;
	@Column(name="engine")
	private Integer engine;
	@Column(name="color")
	private String color;
	@Column(name="fuelType")
	private String fuelType;
	@Column(name="gear")
	private String gearType;
	@Column(name="registerredDate")
	@Temporal(TemporalType.DATE)
	private Date registeredDate;
	@Column(name="customer_id")
	private String customerId;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getModel() {
		return model;
	}
	public void setModel(int model) {
		this.model = model;
	}
	public int getEngine() {
		return engine;
	}
	public void setEngine(int engine) {
		this.engine = engine;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getFuelType() {
		return fuelType;
	}
	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}
	public String getGearType() {
		return gearType;
	}
	public void setGearType(String gearType) {
		this.gearType = gearType;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
	public String getCarName() {
		return carName;
	}
	public void setCarName(String carName) {
		this.carName = carName;
	}
	public String getCar_version() {
		return car_version;
	}
	public void setCar_version(String car_version) {
		this.car_version = car_version;
	}
	public int getSeater() {
		return seater;
	}
	public void setSeater(int seats) {
		this.seater = seats;
	}
	
	
}

