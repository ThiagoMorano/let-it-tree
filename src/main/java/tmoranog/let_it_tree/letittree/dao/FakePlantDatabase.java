package tmoranog.let_it_tree.letittree.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import tmoranog.let_it_tree.letittree.model.Plant;

@Repository("fakeDao")
public class FakePlantDatabase implements PlantDao {
    private static List<Plant> plantDatabase = new ArrayList<Plant>();

    @Override
    public List<Plant> selectAllPlants() {
        return plantDatabase;
    }

    @Override
    public int insertPlant(UUID id, Plant plant) {
        Plant newPlant = new Plant(id, plant.getName(), plant.getLastDateWatered(), plant.getDaysBetweenWatering());
        plantDatabase.add(newPlant);
        return 1;
    }
}
