package com.github.thiagomorano.letittree.model;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plant {
	private final UUID id;
	private String name;
	private LocalDate lastWateringDate = LocalDate.MIN;
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
