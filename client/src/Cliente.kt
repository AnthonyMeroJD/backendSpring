import java.io.BufferedReader
import java.io.DataInputStream
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.Socket


class Cliente

fun main(args: Array<String>) {
    try {
        val socket = Socket("localhost", 8080)

        val inStream = DataInputStream(socket.getInputStream())
        val outStream = DataOutputStream(socket.getOutputStream())
        val br = BufferedReader(InputStreamReader(System.`in`))
        var clientMessage = ""
        var serverMessage = ""
        while (clientMessage != "bye") {
            clientMessage = br.readLine()
            outStream.writeUTF(clientMessage)
            outStream.flush()
            serverMessage = inStream.readUTF()
            println("From Server: $serverMessage")
        }
        outStream.close()
        outStream.close()
        socket.close()
    } catch (e: Exception) {
        println("soy el error $e")
    }
}