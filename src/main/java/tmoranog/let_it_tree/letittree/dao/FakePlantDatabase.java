package tmoranog.let_it_tree.letittree.dao;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import tmoranog.let_it_tree.letittree.model.Plant;

@Repository("fakeDao")
public class FakePlantDatabase implements PlantDao {
    private static List<Plant> plantDatabase = new ArrayList<Plant>();

    @Override
    public int insertPlant(UUID id, Plant plant) {
        Plant newPlant = new Plant(id, plant.getName(), plant.getLastDateWatered(), plant.getDaysBetweenWatering());
        plantDatabase.add(newPlant);
        return 1;
    }

    @Override
    public List<Plant> selectAllPlants() {
        return plantDatabase;
    }

    @Override
    public List<Plant> selectPlantsToBeWatered() {
        List<Plant> plantsToBeWatered = plantDatabase.stream()
                .filter(plant -> {
                    int daysSinceLastWatering = (int) ChronoUnit.DAYS.between(plant.getLastDateWatered(),
                            LocalDate.now());
                    return daysSinceLastWatering > plant.getDaysBetweenWatering();
                })
                .collect(Collectors.toList());
        return plantsToBeWatered;
    }

    @Override
    public int updatePlantById(UUID id, Plant plant) {
        Optional<Plant> plantMaybe = selectPlantById(id);

        if (plantMaybe.isEmpty()) {
            return 0;
        }

        int indexOfPlantToUpdate = plantDatabase.indexOf(plantMaybe.get());
        if (indexOfPlantToUpdate >= 0) {
            plantDatabase.set(
                    indexOfPlantToUpdate,
                    new Plant(id, plant.getName(), plant.getLastDateWatered(), plant.getDaysBetweenWatering()));
        }

        return 1;
    }

    @Override
    public Optional<Plant> selectPlantById(UUID id) {
        Optional<Plant> plantMaybe = plantDatabase.stream()
            .filter(plant -> plant.getId().equals(id))
            .findFirst();
        return plantMaybe;
    }
}
