package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerId implements Pojo{
    @JsonProperty("playerId")
    private String playerId;
    @JsonProperty("id")
    private String id;

    public PlayerId(String id){
        this.id = id;
        this.playerId = id;
    }

    public String getPlayerId(){
        return playerId;
    }

    public String getId(){
        return id;
    }
}
