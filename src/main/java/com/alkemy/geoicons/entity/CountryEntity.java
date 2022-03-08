package com.alkemy.geoicons.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Country")
@Getter
@Setter

public class CountryEntity {

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String image;
	
	private String denomination;
	
	private Long population;
	
	private Long area; //m2
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "continent_id", insertable = false, updatable = false )    //Hago un join con la tabla continente. Al momento de la creacion o actualizacion de un pais este atributo es irrelevante
	private ContinentEntity continent;                                            //Se utiliza para buscar informacion y que traiga el objeto de tipo continente y no solo el ID
	
	@Column(name = "continent_id", nullable= false)
	private Long continentId;  //defino mi columna en la base de datos referida al continente donde esta ubicado el pais. Se utiliza para crear y actualizar paises
	
	@ManyToMany(
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
					})                                              //Country toma el OWNERSHIP (se hace cargo) de la relacion con iconos.
	@JoinTable(                                                     //Al crear un pais, este se crea con los iconos
			name = "icon_country",
			joinColumns = @JoinColumn(name = "country_id"),
			inverseJoinColumns = @JoinColumn(name = "icon_id") )
	
	private Set<IconEntity> icons = new HashSet<>();
	
	
	
}
