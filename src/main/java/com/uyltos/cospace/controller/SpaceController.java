package com.uyltos.cospace.controller;

import com.uyltos.cospace.model.Space;
import com.uyltos.cospace.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spaces")
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @PostMapping
    public ResponseEntity<Space> createSpace(@RequestBody Space space) {
        Space savedSpace = spaceService.createSpace(space);
        return ResponseEntity.ok(savedSpace);
    }

    @GetMapping
    public ResponseEntity<List<Space>> getAllSpaces() {
        List<Space> spaces = spaceService.getAllSpaces();
        return ResponseEntity.ok(spaces);
    }
}
