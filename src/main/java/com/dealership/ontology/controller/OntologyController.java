package com.dealership.ontology.controller;

import com.dealership.ontology.service.ConsultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class OntologyController {
        @Autowired
        private ConsultService consultServiceService;

        @PostMapping("/upload")
        public ResponseEntity<String> postSparQLFile(@RequestParam("file") MultipartFile sparqlFile) throws IOException {
            return consultServiceService.retrieveResult(sparqlFile);
        }
}
