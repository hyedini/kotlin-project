package kt.project.main

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController(val mainService:MainService) {

    @GetMapping("/")
    fun main(model: Model):String {
        var count = mainService.getMemberCount()
        model.addAttribute("count",count)

        var memberList = mainService.getMemberList()
        model.addAttribute("memberList",memberList)

        val memberDto:MemberDto = MemberDto()
        memberDto.name="hyedini"
        memberDto.limit=1
        var getMemberParam = mainService.getMemberParam(memberDto)
        model.addAttribute("getMemberParam",getMemberParam)

        return "index"
    }
}
