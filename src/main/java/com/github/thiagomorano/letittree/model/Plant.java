package com.github.thiagomorano.letittree.model;

import java.time.LocalDate;

public class Plant {
	private final Long id;
	private LocalDate lastWateringDate = LocalDate.MIN;
	private int daysBetweenWatering;

	public Plant(Long id) {
		this.id = id;
	}

	public Plant(Long id, int daysBetweenWatering) {
		this.id = id;
		this.daysBetweenWatering = daysBetweenWatering;
	}

	public Plant(Long id, int daysBetweenWatering, LocalDate lastWateringDate) {
		this.id = id;
		this.daysBetweenWatering = daysBetweenWatering;
		this.lastWateringDate = lastWateringDate;
	}

	public Long getId() {
		return id;
	}

	public LocalDate getLastWateringDate() {
		return lastWateringDate;
	}

	public int getDaysBetweenWatering() {
		return daysBetweenWatering;
	}

	public void setLastWateringDate(LocalDate lastWateringDate) {
		this.lastWateringDate = lastWateringDate;
	}

	public void setDaysBetweenWatering(int daysBetweenWatering) {
		this.daysBetweenWatering = daysBetweenWatering;
	}
}
