package com.backend.anthony.dao

import com.backend.anthony.model.Persona
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

//esta interfaz es la encargada de hacer
//todas las operaciones entre la base de datos y
//trabaja como un repository
@Repository
//jpaRepository debe tener el tipo de modelo que va a contener
//y segundo paramentro es que tipo de id maneja mi modelo
//en mi caso Long
interface PersonaRepository:JpaRepository<Persona,Long>