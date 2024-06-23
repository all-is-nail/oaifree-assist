package org.example.oaifreeassist.controller;

import org.example.oaifreeassist.entity.OaiTokenManagement;
import org.example.oaifreeassist.service.OaiTokenManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tokens")
public class OaiTokenController {

    @Autowired
    private OaiTokenManagementService oaiTokenManagementService;

    @GetMapping("/{id}")
    public ResponseEntity<OaiTokenManagement> getToken(@PathVariable Long id) {
        OaiTokenManagement token = oaiTokenManagementService.getById(id);
        return ResponseEntity.ok(token);
    }

    @PostMapping
    public ResponseEntity<String> createToken(@RequestBody OaiTokenManagement token) {
        boolean result = oaiTokenManagementService.save(token);
        return ResponseEntity.ok("");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateToken(@RequestBody OaiTokenManagement token) {
        boolean update = oaiTokenManagementService.updateById(token);
        String result;
        if (update) {
            result = "更新成功！";
        } else {
            result = "更新失败！";
        }
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteToken(@PathVariable Long id) {
        oaiTokenManagementService.removeById(id);
        return ResponseEntity.noContent().build();
    }
}
