package com.backend.anthony.model

import javax.persistence.*

//debnoto que es una entidad con el anotador
@Entity
//denoto el nombre de la tabla con el nombre que le pase como prm
@Table(name = "persona")
data class Persona(val nombre:String="def",val cedula:String="def",val apellido:String="def") {
    //generar in id
    @Id
    //generar un id de tipo autoincremental lo hace el param
    @GeneratedValue(strategy = GenerationType.AUTO)
    //los ids siempre deben ser de tipo long
    var id:Long=0
}