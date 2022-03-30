package uz.coder.uzmovie.models.credits

import uz.adkhamjon.promovie.models.Credits.Crew

data class CreditsModel(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)