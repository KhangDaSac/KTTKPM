package iuh.fit.se.performancetest.controller;

import iuh.fit.se.performancetest.service.SlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private SlowService slowService;

    @GetMapping("/no-cache")
    public ResponseEntity<Map<String, Object>> noCache() {
        String result = slowService.processWithoutCache();

        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("cache", "NO");

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/with-cache")
    public ResponseEntity<Map<String, Object>> withCache() {
        String result = slowService.processWithCache();

        Map<String, Object> resp = new HashMap<>();
        resp.put("result", result);
        resp.put("cache", "YES");

        return ResponseEntity.ok(resp);
    }
}
