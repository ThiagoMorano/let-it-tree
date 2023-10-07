package com.github.thiagomorano.letittree.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plant {
	private final Long id;
	private String name;
	private LocalDate lastWateringDate = LocalDate.MIN;
	private int daysBetweenWatering;

	public Plant(Long id) {
		this.id = id;
	}

	public Plant(
			Long id,
			int daysBetweenWatering) {
		this.id = id;
		this.daysBetweenWatering = daysBetweenWatering;
	}

	public Plant(Long id, int daysBetweenWatering, LocalDate lastWateringDate) {
		this.id = id;
		this.daysBetweenWatering = daysBetweenWatering;
		this.lastWateringDate = lastWateringDate;
	}

	public Plant(
			@JsonProperty("id") Long id,
			@JsonProperty("name") String name,
			@JsonProperty("daysBetweenWatering") int daysBetweenWatering,
			@JsonProperty("lastWateringDate") LocalDate lastWateringDate) {
		this.id = id;
		this.name = name;
		this.daysBetweenWatering = daysBetweenWatering;
		this.lastWateringDate = lastWateringDate;
	}

	public Long getId() {
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
