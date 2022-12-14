package com.tvz.skypark.dto;

import java.io.Serial;
import java.io.Serializable;

import com.tvz.skypark.enums.VehicleManufacturerEnum;
import com.tvz.skypark.enums.VehicleTypeEnum;
import com.tvz.skypark.model.Vehicle;

public class VehicleDetailsDto implements Serializable {

    @Serial
    private static final long serialVersionUID = -5508284222212034988L;

    private Integer id;
    private String model;
    private VehicleManufacturerEnum manufacturer;
    private VehicleTypeEnum type;
    private Integer mileage;
    private Integer productionYear;
    private Integer price;
    private String imagePath;
    private boolean available;

    public VehicleDetailsDto() {
    }

    public VehicleDetailsDto(Vehicle vehicle) {
    	this.id = vehicle.getId();
        this.model = vehicle.getModel();
        this.manufacturer = vehicle.getManufacturer();
        this.type = vehicle.getType();
        this.mileage = vehicle.getMileage();
        this.productionYear = vehicle.getProductionYear();
        this.price = vehicle.getPrice();
        this.imagePath = vehicle.getImagePath();
        this.available = vehicle.isAvailable();
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public VehicleManufacturerEnum getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(VehicleManufacturerEnum manufacturer) {
        this.manufacturer = manufacturer;
    }

    public VehicleTypeEnum getType() {
        return type;
    }

    public void setType(VehicleTypeEnum type) {
        this.type = type;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(Integer productionYear) {
        this.productionYear = productionYear;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
