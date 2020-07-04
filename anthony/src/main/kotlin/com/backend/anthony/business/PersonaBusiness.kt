package com.backend.anthony.business

import com.backend.anthony.dao.PersonaRepository
import com.backend.anthony.exeptions.BussinessExeption
import com.backend.anthony.exeptions.NotFoundException
import com.backend.anthony.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.lang.Exception
import java.util.*

//se denota como servicio ya que esta es la encargada
//de implementar los metodos que me establece IPERSONABUSINESS
//trabaja junto al daop que es el que provee las operacionex de la base de datos

@Service
class PersonaBusiness :IPersonaBusiness{
    //inyecta la dependencia de un repository en este caso Pers
    //ona repository
@Autowired
val personaRepository:PersonaRepository?=null
    //ojo aqui en las operaciones de las bases de datos
    //debo tener en cuenta que puede haber errores
    //asi que debo colocar los metodos try catch
    //aqui debo colocar todas las exepciones quye va a manejar
    @Throws(BussinessExeption::class)
    override fun listAll(): List<Persona> {
        try {
            return personaRepository!!.findAll()
        }catch (e:Exception){
            throw BussinessExeption(e.message)
        }
    }
    @Throws(BussinessExeption::class,NotFoundException::class)
    override fun loadOne(idPersona: Long): Persona {
        val opcional:Optional<Persona>

        try {
            opcional=personaRepository!!.findById(idPersona)
        }catch (e:Exception){
        //este error es por parte del server
            throw BussinessExeption(e.message)
        }
        //verifica si contiene una persona si no es porque no
        //encontro la persona
        if (!opcional.isPresent){
            //este error es si no existe la persona en DB
            throw NotFoundException("No se encontro la persona")
        }
        return opcional.get()
    }
    @Throws(BussinessExeption::class)
    override fun save(persona: Persona): Persona {
        try {
            return personaRepository!!.save(persona)
        }catch (e:Exception){
            throw BussinessExeption(e.message)
        }
    }
    @Throws(BussinessExeption::class,NotFoundException::class)
    override fun delete(idPersona: Long) {
        val optional:Optional<Persona>
        try {
            optional=personaRepository!!.findById(idPersona)
        }catch (e:Exception){
            throw BussinessExeption(e.message)
        }
        if (!optional.isPresent){
            throw NotFoundException("No se encuentra persona")
        }else{
            try {
                personaRepository!!.deleteById(idPersona)
            }catch (e:Exception){
                throw BussinessExeption(e.message)
            }
        }
    }

}