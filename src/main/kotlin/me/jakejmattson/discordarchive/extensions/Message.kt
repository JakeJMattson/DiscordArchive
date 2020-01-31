package me.jakejmattson.discordarchive.extensions

import kotlinx.html.*
import net.dv8tion.jda.api.entities.Message

fun Message.toHTML(div: DIV) = div.apply {
    div {
        style = "padding-bottom: 20px; padding-tp: 20px"

        if (embeds.isNotEmpty() && author.isBot) {
            embeds.first().toHTML(this)
        } else {
            p {
                style = "color: #D7DAD4"
                +contentRaw
            }
        }
    }
}