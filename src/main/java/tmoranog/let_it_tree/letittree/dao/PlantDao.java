package tmoranog.let_it_tree.letittree.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import tmoranog.let_it_tree.letittree.model.Plant;

public interface PlantDao {
    int insertPlant(UUID id, Plant plant);

    default int insertPlant(Plant plant) {
        UUID id = UUID.randomUUID();
        return insertPlant(id, plant);
    }

    List<Plant> selectAllPlants();

    Optional<Plant> selectPlantById(UUID id);

    List<Plant> selectPlantsToBeWatered();

    int updatePlantById(UUID id, Plant plant);

}
