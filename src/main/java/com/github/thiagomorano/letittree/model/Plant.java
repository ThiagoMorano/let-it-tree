package com.github.thiagomorano.letittree.model;

import java.time.LocalDate;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="PLANT")
public class Plant {
	@Id
	@Column(name="PLANT_ID")
	private final UUID id;
	@Column(name="NAME")
	private String name;
	@Column(name="LAST_WATERING_DATE")
	private LocalDate lastWateringDate = LocalDate.MIN;
	@Column(name="DAYS_BETWEEN_WATERING")
	private int daysBetweenWatering;

	public Plant(UUID id) {
		this.id = id;
	}

	public Plant(
			UUID id,
			int daysBetweenWatering) {
		this.id = id;
		this.daysBetweenWatering = daysBetweenWatering;
	}

	public Plant(UUID id, int daysBetweenWatering, LocalDate lastWateringDate) {
		this.id = id;
		this.daysBetweenWatering = daysBetweenWatering;
		this.lastWateringDate = lastWateringDate;
	}

	@Autowired
	public Plant(
			@JsonProperty("id") UUID id,
			@JsonProperty("name") String name,
			@JsonProperty("daysBetweenWatering") int daysBetweenWatering,
			@JsonProperty("lastWateringDate") LocalDate lastWateringDate) {
		this.id = id;
		this.name = name;
		this.daysBetweenWatering = daysBetweenWatering;
		this.lastWateringDate = lastWateringDate;
	}

	public UUID getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getLastWateringDate() {
		return lastWateringDate;
	}

	public void setLastWateringDate(LocalDate lastWateringDate) {
		this.lastWateringDate = lastWateringDate;
	}

	public int getDaysBetweenWatering() {
		return daysBetweenWatering;
	}

	public void setDaysBetweenWatering(int daysBetweenWatering) {
		this.daysBetweenWatering = daysBetweenWatering;
	}
}
