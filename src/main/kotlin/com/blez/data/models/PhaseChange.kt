package com.blez.data.models

import com.blez.data.Room
import com.blez.other.Constants.TYPE_PHASE_CHANGE

data class PhaseChange(
    var phase : Room.Phase?,
    var time : Long,
    val drawingPlayer : String?=null
): BaseModel(TYPE_PHASE_CHANGE)
