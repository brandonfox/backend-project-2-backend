package brandon.backend.project2backend

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class ApiController {

    @PostMapping("/paste")
    fun paste(@RequestBody data: String){
        println(data)
    }

    @PostMapping("/recents")
    fun recents(){

    }

    @GetMapping("/{id}")
    fun pasteId(@PathVariable id: String): String{
        return id
    }

}