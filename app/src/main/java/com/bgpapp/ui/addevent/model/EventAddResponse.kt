package com.bgpapp.ui.addevent.model

import com.bgpapp.ui.pubs.model.Game
import java.util.*

data class EventAddResponse (
    val id: Int, val meetingDate: String, val organizer: Organizer, val placeId: Int, val medals: String, val meetingType: String, val availableGames: List<Game>
)

/*
public class MeetingDto {
    private Integer id;
    private Date meetingDate;
    private User organizer;
    private Integer placeId;
    private String medals;
    private MeetingType meetingType;
    private List<Integer> availableGames;
}
 */