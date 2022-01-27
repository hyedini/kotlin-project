package kt.project.main

import com.fasterxml.jackson.databind.ObjectMapper
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

        val memberDto = MemberDto()
        memberDto.name="hyedini"
        memberDto.limit=1
        var getMemberParam = mainService.getMemberParam(memberDto)
        model.addAttribute("getMemberParam",getMemberParam)

//        DTO value 값으로 key 찾기
        val objectMapper = ObjectMapper()
        val mapdto = objectMapper.convertValue(memberDto,Map::class.java)
        var key = mapdto.filterValues {
            it.toString().contentEquals("hyedini")
        }.keys.firstOrNull()
        print(key)

        // update
        var message = ""
//        message = updateMember()
        message = selectKeyInsertMember()
        model.addAttribute("message",message)

        return "index"
    }

    fun updateMember() : String{
        val memberDto = MemberDto()
        memberDto.id=3
        memberDto.name="하늘보리"
        memberDto.age=23

        var message = try {
            val result = mainService.updateMember(memberDto)
            when(result){
                1 -> "update success"
                else -> "update failed"
            }
        } catch (e:Exception){
            "server error"
        }
        return message
    }

    fun selectKeyInsertMember() : String{
        val memberDto = MemberDto()
        memberDto.name="insert!"
        memberDto.age=24

        var message = try {
            val result = mainService.selectKeyInsertMember(memberDto)
            if(result>0){ "insert success" }else{ "insert failed" }
        } catch (e:Exception){
            "server error"
        }
        return message
    }
}

