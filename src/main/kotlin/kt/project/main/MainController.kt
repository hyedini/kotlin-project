package kt.project.main

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import java.sql.DriverManager

@Controller
class MainController(val mainService:MainService) {

    @GetMapping("/")
    fun main(model: Model):String {
        var count = mainService.getMemberCount()
        model.addAttribute("count",count)

        var memberList = mainService.getMemberList()
        model.addAttribute("memberList",memberList)

        val memberDto = MemberDto()
        memberDto.name="hyedini"
        memberDto.limit=1
        var getMemberParam = mainService.getMemberParam(memberDto)
        model.addAttribute("getMemberParam",getMemberParam)

        // update
        var message = updateMember()
        model.addAttribute("message",message)

        return "index"
    }

    fun updateMember() : String{
        val memberDto = MemberDto()
        memberDto.id=3
        memberDto.name="하늘보리"
        memberDto.age=23

        var message = ""
        try {
            val result = mainService.updateMember(memberDto)
            message = when(result){
                1 -> "update success"
                else -> "update failed"
            }
        } catch (e:Exception){
            message = "server error"
        }
        return message
    }
}

