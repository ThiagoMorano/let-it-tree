package tmoranog.let_it_tree.letittree.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import tmoranog.let_it_tree.letittree.model.Plant;

public interface PlantDao {
    int insertPlant(UUID id, Plant plant);

    default int insertPlant(Plant plant) {
        UUID id = UUID.randomUUID();
        return insertPlant(id, plant);
    }

    List<Plant> selectAllPlants();

    List<Plant> selectPlantsToBeWatered();
}
