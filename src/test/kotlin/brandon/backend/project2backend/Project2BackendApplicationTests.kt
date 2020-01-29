package brandon.backend.project2backend

import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import java.net.HttpURLConnection
import java.net.URL

@SpringBootTest
class Project2BackendApplicationTests {

	val url = "http://localhost:8080/api"

	@Test
	fun addPasteTest(){
		val u = URL("$url/paste")
		with(u.openConnection() as HttpURLConnection){
			requestMethod = "POST"

			val jsonRequest = JSONObject(mapOf(Pair("title","Test title"),Pair("content","This is a test content block")))

			setRequestProperty("Content-Type","application/json")

			doOutput = true

			this.outputStream.write(jsonRequest.toString().toByteArray())

			val response = inputStream.reader().readText()

			println(response)

			disconnect()
		}
	}

	@Test
	fun retrievePasteTest(){
		addPasteTest()
		val u = URL("$url/recents")
		with(u.openConnection() as HttpURLConnection){
			requestMethod = "POST"

			setRequestProperty("Content-Type","application/json")

			val response = inputStream.reader().readText()

			assert(response.isNotEmpty())

			disconnect()
		}
	}

	@Test
	fun getTestId(){
		addPasteTest()
		val u = URL("$url/1")
		with(u.openConnection() as HttpURLConnection){
			requestMethod = "GET"

			setRequestProperty("Content-Type","application/json")

			val response = inputStream.reader().readText()

			assert(response.isNotEmpty())

			disconnect()
		}
	}

}
