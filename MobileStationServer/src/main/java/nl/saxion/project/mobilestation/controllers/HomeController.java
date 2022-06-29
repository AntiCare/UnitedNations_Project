package nl.saxion.project.mobilestation.controllers;


import nl.saxion.project.mobilestation.model.Measurement;
import nl.saxion.project.mobilestation.repositories.MeasurementRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping("")
public class HomeController {
    private MeasurementRepository repository;

    public HomeController(MeasurementRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public String getHomePage(Model model) {

        ArrayList<Integer> radiationTable = new ArrayList<>();
        ArrayList<Integer> waterLevelTable = new ArrayList<>();
        ArrayList<Integer> oilTable = new ArrayList<>();
        ArrayList<String> objectTable = new ArrayList<>();

        for(Measurement c : repository.findAll()){
            radiationTable.add(c.getRadiation());
            waterLevelTable.add(c.getLevel());
            oilTable.add(c.getOilDetection());

            for (int i = 0; i <c.getObjectsDetected().size() ; i++) {
                objectTable.add(c.getObjectsDetected().get(i));
            }
        }

        model.addAttribute("measurements", repository.findAll());
        model.addAttribute("radiationTable",radiationTable);
        model.addAttribute("waterLevelTable",waterLevelTable);
        model.addAttribute("oilTable",oilTable);
        model.addAttribute("objectTable",objectTable);

        return "homepage";
    }

    @GetMapping("/search")
    public String searchRestaurant(int name, Model model) {

        ArrayList<Integer> radiationTable = new ArrayList<>();
        ArrayList<Integer> waterLevelTable = new ArrayList<>();
        ArrayList<Integer> oilTable = new ArrayList<>();
        ArrayList<String> objectTable = new ArrayList<>();
        ArrayList<Measurement> search = new ArrayList<>();

        for (int i = 0; i < repository.findAll().size(); i++) {
            if ( repository.findAll().get(i).getDroneId() == name || repository.findAll().get(i).getDayId() == name|| repository.findAll().get(i).getId() == name) {
                Measurement foundItem = repository.findAll().get(i);
                search.add(foundItem);
                radiationTable.add(foundItem.getRadiation());
                waterLevelTable.add(foundItem.getLevel());
                oilTable.add(foundItem.getOilDetection());

                for (int j = 0; j <foundItem.getObjectsDetected().size() ; j++) {
                    objectTable.add(foundItem.getObjectsDetected().get(j));
                }

            }
        }
        model.addAttribute("radiationTable",radiationTable);
        model.addAttribute("waterLevelTable",waterLevelTable);
        model.addAttribute("oilTable",oilTable);
        model.addAttribute("objectTable",objectTable);
        model.addAttribute("measurements", search);
        return "homepage";
    }




}
