package kt.project.main

import org.apache.ibatis.annotations.*

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

    @Insert("""
        insert into member (id,name,age) values (#{id},#{name},#{age}) 
    """)
    @SelectKey(statement = ["SELECT MAX(id)+1 FROM member"], keyProperty = "id", before = true, resultType = Int::class)
    fun selectKeyMember(member:MemberDto):Int
}