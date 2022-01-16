package kt.project.main

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MainService(@Autowired val mainMapper:MainMapper) {
    // select
    fun getMemberCount() = mainMapper.getMemberCount()
    fun getMemberList() = mainMapper.getMemberList()
    fun getMemberParam(memberDto:MemberDto) = mainMapper.getMemberParam(memberDto.name!!,memberDto.limit!!)
    // update
    @Transactional
    fun updateMember(memberDto:MemberDto) = mainMapper.updateMember(memberDto)
    @Transactional
    fun selectKeyInsertMember(memberDto:MemberDto) = mainMapper.selectKeyMember(memberDto)
}