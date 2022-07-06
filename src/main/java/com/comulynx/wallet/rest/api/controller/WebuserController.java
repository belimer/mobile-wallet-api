package com.comulynx.wallet.rest.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comulynx.wallet.rest.api.exception.ResourceNotFoundException;
import com.comulynx.wallet.rest.api.model.Webuser;
import com.comulynx.wallet.rest.api.repository.WebuserRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/webusers")
public class WebuserController {
	private Gson gson = new Gson();
	private static final Logger logger = LoggerFactory.getLogger( WebuserController.class );
	@Autowired
	private WebuserRepository webuserRepository;

	 private boolean loginSucceeded = false;
	/**
	 * TODO : Fix Webuser Login functionality
	 * 
	 * Login
	 * 
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public ResponseEntity<?> webuserLogin(@RequestBody String request) {
		try {

			// TODO : Add Webuser login logic here. Login using employeeId and
			// password
			// NB: We are using plain text password for testing Webuser login
			// If username doesn't exists throw an error "User does not exist"
			// If password do not match throw an error "Invalid credentials"

			final JsonObject loginRequest = gson.fromJson(request, JsonObject.class);
			String employeeId = loginRequest.get("employeeId").getAsString();
			String password = loginRequest.get("password").getAsString();
			
			logger.info("em"+employeeId+ "pwd"+ password);

			Optional<Webuser> user = webuserRepository.findByEmployeeId(employeeId);
			if(user.isEmpty()) {
				throw new Exception("User does not exist");
			}

			if (employeeId.equals(user.get().getEmployeeId())
					&& password.equals(user.get().getPassword())) {
				loginSucceeded=true;

			}else {
				return ResponseEntity.status(400).body("Invalid credentials");
			}
			
			return ResponseEntity.status(200).body(HttpStatus.OK);

		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@GetMapping("/")
	public List<Webuser> getAllWebusers() {
		return webuserRepository.findAll();
	}

	@GetMapping("/{employeeId}")
	public ResponseEntity<Webuser> getWebuserByEmployeeId(@PathVariable(value = "employeeId") String employeeId)
			throws ResourceNotFoundException {
		Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(webuser);
	}

	@PostMapping("/")
	public ResponseEntity<?> createWebuser(@Valid @RequestBody Webuser webuser) {
		try {
			// TODO : Add logic to check if Webuser with provided username, or
			// email, or employeeId, or customerId exists.
			// If exists, throw a Webuser with [?] exists Exception.
 Optional <Webuser>exisitingUser=webuserRepository.findByEmployeeId(webuser.getEmployeeId());

			return ResponseEntity.ok().body(webuserRepository.save(webuser));
		} catch (Exception ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<Webuser> updateWebuser(@PathVariable(value = "employeeId") String employeeId,
			@Valid @RequestBody Webuser webuserDetails) throws ResourceNotFoundException {
		Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));

		webuser.setEmail(webuserDetails.getEmail());
		webuser.setLastName(webuserDetails.getLastName());
		webuser.setFirstName(webuserDetails.getFirstName());
		final Webuser updatedWebuser = webuserRepository.save(webuser);
		return ResponseEntity.ok(updatedWebuser);
	}

	@DeleteMapping("/{employeeId}")
	public Map<String, Boolean> deleteWebuser(@PathVariable(value = "employeeId") String employeeId)
			throws ResourceNotFoundException {
		Webuser webuser = webuserRepository.findByEmployeeId(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Webuser not found for this id :: " + employeeId));

		webuserRepository.delete(webuser);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
