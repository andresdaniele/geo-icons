package com.alkemy.geoicons.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Icon")
@Getter
@Setter

public class IconEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "creation_date")
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate creationDate;

	private String image;

	private String denomitantion;

	private Long lenght;

	private String history;

	@ManyToMany(mappedBy = "icons", cascade = CascadeType.ALL)  //el OWNERSHIP lo tiene pais ya que es mas practico crear un pais con su respectivos iconos que un icono con los paises
	private List<CountryEntity> countries = new ArrayList<>();

}
