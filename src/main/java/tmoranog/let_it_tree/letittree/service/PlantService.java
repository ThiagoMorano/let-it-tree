package tmoranog.let_it_tree.letittree.service;

import java.time.LocalDate;
import java.util.List;

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

    public int addPlant(Plant plant) {
        return plantDao.insertPlant(plant);
    }
}
