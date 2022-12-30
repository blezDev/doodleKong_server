package com.blez.data.models

import com.blez.data.Player
import com.blez.other.Constants.TYPE_PLAYERS_LIST

data class PlayersList(
    val players : List<PlayerData>
):BaseModel(TYPE_PLAYERS_LIST)
