package com.example.agent.service;


import com.example.agent.controller.dto.MediaFileDto;

import java.io.IOException;

public interface ImageService {
    MediaFileDto getImage(String name) throws IOException;
}
