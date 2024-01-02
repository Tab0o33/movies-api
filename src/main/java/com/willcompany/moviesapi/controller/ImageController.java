package com.willcompany.moviesapi.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class ImageController {

	@GetMapping("/affiches_movies/{imageName}")
	public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
		// Chargez l'image depuis les ressources de l'application
		Resource image = new ClassPathResource("affiches_movies/" + imageName);

		// Vérifiez si l'image existe
		if (image.exists()) {
			return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(image);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}