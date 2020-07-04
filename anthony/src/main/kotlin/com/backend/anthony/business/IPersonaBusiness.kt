package com.backend.anthony.business

import com.backend.anthony.model.Persona


interface IPersonaBusiness {
    fun listAll(): List<Persona>
    fun loadOne(idPersona:Long):Persona
    fun save(persona: Persona):Persona
    fun delete(idPersona: Long)

}