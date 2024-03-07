package com.dealership.ontology.service;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ConsultService {

    private String ontologyFilePath = "/home/javadev/Documents/dealership.rdf";
    private String result = "";

    public ResponseEntity<String> retrieveResult(MultipartFile sparqlFile) throws IOException {
        Model model = ModelFactory.createDefaultModel();

        model.read(ontologyFilePath);

        String sparqlText = new String(sparqlFile.getBytes());

        Query query = QueryFactory.create(sparqlText);

        try (QueryExecution qe = QueryExecutionFactory.create(query, model)) {
            ResultSet results = qe.execSelect();
            QuerySolution solution;

            while (results.hasNext()) {
                solution = results.nextSolution();

                result = result.concat(solution.toString());
                result = result.concat("\n");
            }

            results.close();
        }

        return ResponseEntity.ok(result);
    }
}
