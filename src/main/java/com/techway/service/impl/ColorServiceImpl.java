package com.techway.service.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.techway.entity.Color;
import com.techway.exception.ResourceNotFoundException;
import com.techway.repository.ColorRepository;
import com.techway.service.ColorService;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    public Color getColorById(Integer id) {
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) {
            return color.get();
        } else {
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        }
    }

    public Color createColor(Color color) {
        return colorRepository.save(color);
    }

    public boolean deleteColorById(Integer id) {
        Optional<Color> color = colorRepository.findById(id);
        if (color.isPresent()) {
            colorRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        }
    }

    public Color updateColorById(Integer id, Color color) {
        Optional<Color> optionalColor = colorRepository.findById(id);
        if (optionalColor.isPresent()) {
            Color existingColor = optionalColor.get();
            existingColor.setColor(color.getColor());
            return colorRepository.save(existingColor);
        } else {
            throw new ResourceNotFoundException("Product with id " + id + " not found");
        }
    }
}