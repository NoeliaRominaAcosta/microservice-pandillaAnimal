package com.pandilla.adoptions.controllers;

import com.pandilla.adoptions.services.AdopterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adopter")
public class adopterController {
    @Autowired
    AdopterService adopterService;
}
