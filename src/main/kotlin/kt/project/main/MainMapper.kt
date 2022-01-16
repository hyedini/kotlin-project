package kt.project.main

import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.annotations.Update

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

    @Update("""
        update member
        set name = #{name} , age = #{age}
        where id = #{id}
    """)
    fun updateMember(member:MemberDto):Int

}