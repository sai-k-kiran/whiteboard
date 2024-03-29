package com.project.Controller;

import com.project.Models.Design.AmazonClient;
import com.project.Models.Images.ImageCreationRequest;
import com.project.Models.Images.ImageDTO;
import com.project.Models.Images.ImageServiceImpl;
import com.project.Models.Images.ImageUploadRequest;
import com.project.Models.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/v1/storage")
public class UploadController {
    private ImageServiceImpl imageService;
    private AmazonClient amazonClient;

    @Autowired
    UploadController(AmazonClient amazonClient, ImageServiceImpl imageService) {
        this.imageService = imageService;
        this.amazonClient = amazonClient;
    }

    @GetMapping("allImages")
    public List<ImageDTO> getAllImages(@RequestParam("id") Integer id){
        return imageService.getAllImages(id);
    }

    @PostMapping("uploadFile")
    public String uploadFile(@ModelAttribute ImageCreationRequest request) {
        User user = new User(request.user_id());
        String res = this.amazonClient.uploadFile(request.file());

        imageService.uploadImage(new ImageUploadRequest(res, user));
        return res;
    }

    @DeleteMapping("deleteFile")
    public void deleteFile(@RequestParam("fileUrl") String fileUrl) {
        imageService.deleteImage(fileUrl);
        amazonClient.deleteFileFromS3Bucket(fileUrl);
    }
}
