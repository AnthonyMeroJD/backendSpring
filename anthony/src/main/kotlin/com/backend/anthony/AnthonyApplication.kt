package com.backend.anthony

import com.backend.anthony.dao.PersonaRepository
import com.backend.anthony.model.Persona
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.stereotype.Component
import java.net.URI
import javax.websocket.*
import javax.websocket.server.ServerEndpoint


@SpringBootApplication
class AnthonyApplication:CommandLineRunner{
	@Autowired
	val personaRepository:PersonaRepository?=null
	override fun run(vararg args: String?) {
		var persona=Persona("antho","1727028134","mero")
		personaRepository!!.save(persona)
	}
}


fun main(args: Array<String>) {
	runApplication<AnthonyApplication>(*args)

}

@ServerEndpoint("/mensaje")
class MiWebSocket {

    @OnMessage
    fun mensaje(mensaje: String): String {
        return "hola desde el servidor el mensaje es :$mensaje"

    }

}