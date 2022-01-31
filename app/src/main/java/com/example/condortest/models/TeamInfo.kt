package com.example.condortest.models

import java.io.Serializable

data class TeamInfo(
    val idTeam: String,
    val strTeam: String,
    val strTeamBadge: String,
    val strStadium: String,
    val strDescriptionEN: String,
    val intFormedYear: String,
    val strTeamJersey: String,
    val strWebsite: String,
    val strFacebook: String,
    val strTwitter: String,
    val strInstagram: String,
): Serializable
