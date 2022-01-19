package tmoranog.let_it_tree.letittree.api;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tmoranog.let_it_tree.letittree.model.Plant;
import tmoranog.let_it_tree.letittree.service.PlantService;

@RequestMapping("api/v1/plant")
@RestController
public class PlantController {

    private PlantService plantService;

    @Autowired
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    @PostMapping
    public int addPlant(@RequestBody Plant plant) {
        return plantService.addPlant(plant);
    }

    @GetMapping
    public List<Plant> getAllPlants() {
        return plantService.getAllPlants();
    }

    @GetMapping("/{id}")
    public Optional<Plant> getPlantById(@PathVariable UUID id) {
        return plantService.getPlantById(id);
    }

    @GetMapping("/needWater")
    public List<Plant> getPlantsToBeWatered() {
        return plantService.getPlantsToBeWatered();
    }

    @PutMapping("/{id}")
    public int updatePlantOfId(@PathVariable UUID id, @RequestBody Plant plant) {
        return plantService.updatePlantById(id, plant);
    }
}
