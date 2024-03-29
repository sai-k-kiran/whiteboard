package com.project.Controller;

import com.project.Models.Design.DesignCreationRequest;
import com.project.Models.Design.DesignDTO;
import com.project.Models.Design.DesignServiceImpl;
import com.project.Models.User.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/designs")
public class DesignController {
    private final DesignServiceImpl designService;

    public DesignController(DesignServiceImpl designService) {
        this.designService = designService;
    }

    @GetMapping
    public List<DesignDTO> getAllDesigns(@RequestParam("id") Integer id){
        return designService.getAllDesigns(id);
    }

    @PostMapping("editor")
    public void createDesign(@RequestBody DesignCreationRequest request){
        designService.addDesign(request);
    }

    @DeleteMapping()
    public void deleteDesign(@RequestParam("id") Integer id){
        designService.removeDesign(id);
    }
}
