package com.example.demo.dto.serviceDto;

import lombok.Getter;
import lombok.Setter;
import lombok.Getter;
import lombok.Setter;

/**
 * Data Transfer Object (DTO) representing brewery details for service layer.
 */
@Getter
@Setter
public class ServiceBreweryDTO {

    /**
     * The name of the brewery.
     */
    private String name;

    /**
     * Address line 1 of the brewery.
     */
    private String address1;

    /**
     * Address line 2 of the brewery.
     */
    private String address2;

    /**
     * City where the brewery is located.
     */
    private String city;

    /**
     * State or region where the brewery is located.
     */
    private String state;

    /**
     * Country where the brewery is located.
     */
    private String country;

    /**
     * Phone number associated with the brewery.
     */
    private String phone;

    /**
     * Website URL of the brewery.
     */
    private String website;

    /**
     * Description of the brewery.
     */
    private String descript;
}
