package ftn.upp.sc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.controller.base.MagazineControllerBase;
import ftn.upp.sc.converter.MagazineConverter;
import ftn.upp.sc.dto.MagazineDTO;
import ftn.upp.sc.model.Magazine;
import ftn.upp.sc.service.MagazineService;


@Controller
@CrossOrigin(origins = "http://localhost:4200", maxAge = 13200)
public class MagazineController extends MagazineControllerBase {

}
