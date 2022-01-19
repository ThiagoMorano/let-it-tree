package tmoranog.let_it_tree.letittree.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import tmoranog.let_it_tree.letittree.dao.PlantDao;
import tmoranog.let_it_tree.letittree.model.Plant;

@Service
public class PlantService {
    private final PlantDao plantDao;

    @Autowired
    public PlantService(@Qualifier("fakeDao") PlantDao plantDao) {
        this.plantDao = plantDao;
    }

    public List<Plant> getAllPlants() {
        return plantDao.selectAllPlants();
    }

    public Optional<Plant> getPlantById(UUID id) {
        return plantDao.selectPlantById(id);
    }

    public int addPlant(Plant plant) {
        return plantDao.insertPlant(plant);
    }

    public List<Plant> getPlantsToBeWatered() {
        return plantDao.selectPlantsToBeWatered();
    }

    public int updatePlantById(UUID id, Plant plant) {
        return plantDao.updatePlantById(id, plant);
    }
}
