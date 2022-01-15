package kt.project.main

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MainService(@Autowired val mainMapper:MainMapper) {
    fun getMemberCount() = mainMapper.getMemberCount()
    fun getMemberList() = mainMapper.getMemberList()
    fun getMemberParam(memberDto:MemberDto) = mainMapper.getMemberParam(memberDto.name!!,memberDto.limit!!)
}