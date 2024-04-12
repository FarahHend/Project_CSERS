package Hend.BackendSpringboot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import Hend.BackendSpringboot.entity.Incident;
import Hend.BackendSpringboot.service.IncidentService;

@RestController
@RequestMapping("/incidents")
public class IncidentController {


}
