package com.example.javaee_backend.mapper;

import com.example.javaee_backend.pojo.CompetitionDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompetitionMapper {
    @Select("SELECT competition_id as competitionId, competition_name as competitionName, competition_start_time as competitionStartTime, competition_end_time as competitionEndTime, registration_start_time as registrationStartTime, registration_end_time as registrationEndTime, competition_publish_time as competitionPublishTime, website_url as websiteUrl, competition_info as competitionInfo, competition_type as competitionType, competition_level as competitionLevel, visitor_number as visitorNumber, collect_number as collectNumber, sponsor_name as sponsorName, user_id as userId " +
            "FROM competition_info")
    List<CompetitionDTO> getAllCompetitions();

    @Insert("INSERT INTO competition_info(competition_name, competition_start_time, competition_end_time, registration_start_time, registration_end_time, competition_publish_time, website_url, competition_info, competition_type, competition_level, visitor_number, collect_number, sponsor_name, user_id) " +
            "VALUES(#{competition.competitionName}, #{competition.competitionStartTime}, #{competition.competitionEndTime}, #{competition.registrationStartTime}, #{competition.registrationEndTime}, #{competition.competitionPublishTime}, #{competition.websiteUrl}, #{competition.competitionInfo}, #{competition.competitionType}, #{competition.competitionLevel}, #{competition.visitorNumber}, #{competition.collectNumber}, #{competition.sponsorName}, #{competition.userId})")
    int addCompetition(@Param("competition") CompetitionDTO competitionDTO);

    @Select("SELECT competition_id as competitionId, competition_name as competitionName, competition_start_time as competitionStartTime, competition_end_time as competitionEndTime, registration_start_time as registrationStartTime, registration_end_time as registrationEndTime, competition_publish_time as competitionPublishTime, website_url as websiteUrl, competition_info as competitionInfo, competition_type as competitionType, competition_level as competitionLevel, visitor_number as visitorNumber, collect_number as collectNumber, sponsor_name as sponsorName, user_id as userId " +
            "FROM competition_info WHERE competition_name LIKE CONCAT ('%', #{name}, '%')")
    List<CompetitionDTO> getCompetitionByName(@Param("name") String competitionName);

    @Select("SELECT competition_id as competitionId, competition_name as competitionName, competition_start_time as competitionStartTime, competition_end_time as competitionEndTime, registration_start_time as registrationStartTime, registration_end_time as registrationEndTime, competition_publish_time as competitionPublishTime, website_url as websiteUrl, competition_info as competitionInfo, competition_type as competitionType, competition_level as competitionLevel, visitor_number as visitorNumber, collect_number as collectNumber, sponsor_name as sponsorName, user_id as userId " +
            "FROM competition_info WHERE user_id=#{userId}")
    List<CompetitionDTO> getCompetitionByUserId(@Param("userId") int userId);

    @Select("SELECT competition_id as competitionId, competition_name as competitionName, competition_start_time as competitionStartTime, competition_end_time as competitionEndTime, registration_start_time as registrationStartTime, registration_end_time as registrationEndTime, competition_publish_time as competitionPublishTime, website_url as websiteUrl, competition_info as competitionInfo, competition_type as competitionType, competition_level as competitionLevel, visitor_number as visitorNumber, collect_number as collectNumber, sponsor_name as sponsorName, user_id as userId " +
            "FROM competition_info WHERE competition_id = #{competitionId}")
    CompetitionDTO getCompetition(@Param("competitionId") int competitionId);

    @Update("UPDATE competition_info SET competition_name = #{competition.competitionName}, competition_start_time = #{competition.competitionStartTime}, competition_end_time = #{competition.competitionEndTime}, registration_start_time = #{competition.registrationStartTime}, registration_end_time = #{competition.registrationEndTime}, competition_publish_time = #{competition.competitionPublishTime}, website_url = #{competition.websiteUrl}, competition_info = #{competition.competitionInfo}, competition_type = #{competition.competitionType}, competition_level = #{competition.competitionLevel}, visitor_number = #{competition.visitorNumber}, collect_number = #{competition.collectNumber}, sponsor_name = #{competition.sponsorName}, user_id = #{competition.userId} WHERE competition_id = #{competition.competitionId}")
    int updateCompetition(@Param("competition") CompetitionDTO competition);

    @Delete("DELETE FROM competition_info WHERE competition_id = #{competitionId}")
    int deleteCompetition(@Param("competitionId") int competitionId);
}
