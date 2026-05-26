package com.uyltos.cospace.service;

import com.uyltos.cospace.model.Space;
import com.uyltos.cospace.repository.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;

    public Space createSpace(Space space) {
        return spaceRepository.save(space);
    }

    public List<Space> getAllSpaces() {
        return spaceRepository.findAll();
    }
}