package com.tvz.skypark.service;


import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.tvz.skypark.dto.VehicleDetailsDto;
import com.tvz.skypark.exceptions.VehicleNotFoundException;
import com.tvz.skypark.model.Vehicle;
import com.tvz.skypark.repository.VehicleRepository;

@Component
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleDetailsDto createNewVehicle(VehicleDetailsDto newVehicle) {
    	newVehicle.setAvailable(true);
        return new VehicleDetailsDto(vehicleRepository.save(new Vehicle(newVehicle)));
    }

    @Override
    public VehicleDetailsDto getById(Integer id) {
        var vehicle = vehicleRepository.findById(id).orElse(null);
        if (vehicle != null)
            return new VehicleDetailsDto(vehicle);
        else
            return null;

    }

    @Override
    public void delete(Integer id) throws VehicleNotFoundException {
        var vehicle = vehicleRepository.findById(id).orElse(null);
        if(vehicle!=null){
            vehicleRepository.delete(vehicle);
        } else {
            throw new VehicleNotFoundException("Zatraženo vozilo ne postoji");
        }
    }


    @Override
    public List<VehicleDetailsDto> getAllCars() {
        return vehicleRepository.findAll().stream().map(VehicleDetailsDto::new).collect(Collectors.toList());
    }

	@Override
	public List<VehicleDetailsDto> getAvailableCars() {
		return vehicleRepository.findByAvailableTrue().stream().map(VehicleDetailsDto::new).collect(Collectors.toList());
	}

	@Override
	public void setAvailable(int id, boolean isAvailable) {
		var vehicle = vehicleRepository.findById(id).orElse(null);
		if(vehicle!=null) {
		vehicle.setAvailable(isAvailable);
		vehicleRepository.save(vehicle);}
		
	}

	@Override
	public void changeImage(Integer id, MultipartFile image) {
		var vehicle = vehicleRepository.findById(id).orElse(null);
		String filePath = "C:\\Users\\Bruno\\Documents\\faks\\rent-a-car-projekt\\rent-a-car-frontend\\public\\carImages\\" + id + ".jpg";
		var dest = new File(filePath);
		if(!dest.exists())
	    {
	       new File(filePath).mkdir();
	    }
	       try {
			image.transferTo(dest);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	       if(vehicle!=null) {
	       vehicle.setImagePath("\\carImages\\" + id + ".jpg");
	       vehicleRepository.save(vehicle);}
		
	}

}
