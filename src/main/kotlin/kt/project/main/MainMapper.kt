package kt.project.main

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select

@Mapper
interface MainMapper {

    @Select("""
        select count(*) from member
    """)
    fun getMemberCount():Int

    @Select("""
        select * from member
    """)
    fun getMemberList():List<MemberDto>


    @Select("""
        select * 
        from member 
        where name = #{name}
        limit #{limit}
    """)
    fun getMemberParam(@Param("name") name:String, limit:Int):MemberDto
}