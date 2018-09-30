package com.silveryark.rpc.matching;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GameMatching {

    private String game;
    private String uuid;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public GameMatching(@JsonProperty("game") String game, @JsonProperty("uuid") String uuid) {
        this.game = game;
        this.uuid = uuid;
    }

    public String getGame() {
        return game;
    }

    public String getUuid() {
        return uuid;
    }
}
