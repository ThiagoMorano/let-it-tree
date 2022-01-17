package tmoranog.let_it_tree.letittree.model;

import java.time.LocalDate;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Plant {
    private final UUID id;
    private final String name;
    private final LocalDate lastDateWatered;
    private final int daysBetweenWatering;

    public Plant(@JsonProperty("id") UUID id,
                @JsonProperty("name") String name,
                @JsonProperty("lastDateWatered") LocalDate lastDateWatered,
                @JsonProperty("daysBetweenWatering") int daysBetweenWatering) {
        this.id = id;
        this.name = name;
        this.lastDateWatered = lastDateWatered;
        this.daysBetweenWatering = daysBetweenWatering;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getLastDateWatered() {
        return lastDateWatered;
    }

    public int getDaysBetweenWatering() {
        return daysBetweenWatering;
    }
}
