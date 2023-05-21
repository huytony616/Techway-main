package com.techway.service;

import java.util.List;

import com.techway.entity.Color;

public interface ColorService {

    List<Color> getAllColors();

    Color getColorById(Integer id);

    Color createColor(Color color);

    boolean deleteColorById(Integer id);

    Color updateColorById(Integer id, Color color);
}