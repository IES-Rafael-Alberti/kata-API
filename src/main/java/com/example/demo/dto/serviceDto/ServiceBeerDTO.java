package com.example.demo.dto.serviceDto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;



/**
 * Data Transfer Object (DTO) representing beer details for service layer.
 */
@Getter
@Setter
public class ServiceBeerDTO {

    /**
     * The name of the beer.
     */
    @NotBlank
    private String name;

    /**
     * Universal Product Code (UPC) of the beer.
     */
    private Long upc;

    /**
     * Alcohol By Volume (ABV) of the beer.
     */
    @Min(0)
    private Float abv;

    /**
     * International Bitterness Units (IBU) of the beer.
     */
    @Min(0)
    private Float ibu;

    /**
     * Standard Reference Method (SRM) of the beer.
     */
    @Min(0)
    private Float srm;

    /**
     * Date of the last modification of the beer.
     */
    private LocalDate last_mod;

    /**
     * Description of the beer.
     */
    private String descript;

    /**
     * Filepath associated with the beer.
     */
    private String filepath;

    /**
     * User ID of the user who added the beer.
     */
    private Integer addUser;
}
