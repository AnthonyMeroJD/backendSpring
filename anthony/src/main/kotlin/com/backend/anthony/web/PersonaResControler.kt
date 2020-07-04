package com.backend.anthony.web

import com.backend.anthony.business.IPersonaBusiness
import com.backend.anthony.exeptions.BussinessExeption
import com.backend.anthony.exeptions.NotFoundException
import com.backend.anthony.model.Persona
import com.backend.anthony.utils.Const
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

//Esta clase es la encargada de devolver la respuesta en json
//esta clase mapea con los diferentes metosd de http request
@RestController
//aqui es donde debo pasar la url que se va a consultar
//para poder acceder a mi api
@RequestMapping(Const.URL_BASE_PERSONAS)
class PersonaResControler {
    //aqui inyecto los metodos dentro de mi resControler ya
    //con la logica
    @Autowired
    val personaBusiness: IPersonaBusiness? = null

    //con estos anotadores amnejo las request http(get)
    @GetMapping("")
    fun list(): ResponseEntity<List<Persona>> {
        return try {
            ResponseEntity(personaBusiness!!.listAll(),HttpStatus.OK)
        }catch (e:Exception){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @GetMapping("/{id}")
    //pathvariable pilla el path de la url
    fun loadById(@PathVariable("id") idPersona:Long):ResponseEntity<Persona>{
        return try {
            ResponseEntity(personaBusiness!!.loadOne(idPersona),HttpStatus.OK)
        }catch (e:BussinessExeption){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        } catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    //requestbody pilla el body que le pase por metodo Post
    @PostMapping("")
    fun save(@RequestBody persona: Persona):ResponseEntity<Any>{
        return try {
            personaBusiness!!.save(persona)
            val responseh=HttpHeaders()
            //lo que hace este header es escribir en la url del navegador url/id
            //lo que hace esto es que llama al metodo loadById y retorna una persona
            responseh.set("location",Const.URL_BASE_PERSONAS+"/"+persona.id)
           ResponseEntity(responseh,HttpStatus.CREATED)
        }catch (e:BussinessExeption){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
    @PutMapping("")
    fun update(@RequestBody persona: Persona):ResponseEntity<Any>{
        return try {
            personaBusiness!!.save(persona)
            val header=HttpHeaders()
            header.set("location",Const.URL_BASE_PERSONAS+"/"+persona.id)
            ResponseEntity(header,HttpStatus.OK)
        }catch (e:BussinessExeption){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
           ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
    @DeleteMapping("{id}")
    fun delete(@PathVariable("id") idPersona: Long ):ResponseEntity<Any>{
        return try {
            personaBusiness!!.delete(idPersona)
            val header=HttpHeaders()
            header.set("location",Const.URL_BASE_PERSONAS)
            ResponseEntity(header,HttpStatus.OK)
        }catch (e:BussinessExeption){
            ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        }catch (e:NotFoundException){
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
