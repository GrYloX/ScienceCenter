package ftn.upp.sc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ftn.upp.sc.controller.base.UserDetailsControllerBase;
import ftn.upp.sc.converter.UserDetailsConverter;
import ftn.upp.sc.dto.UserDetailsDTO;
import ftn.upp.sc.model.UserDetails;
import ftn.upp.sc.service.UserDetailsService;

@Controller
public class UserDetailsController extends UserDetailsControllerBase {

}
