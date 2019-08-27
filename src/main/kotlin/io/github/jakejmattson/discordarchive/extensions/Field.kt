package io.github.jakejmattson.discordarchive.extensions

import kotlinx.html.*
import net.dv8tion.jda.api.entities.MessageEmbed

fun MessageEmbed.Field.toHTML(div: DIV): DIV {
    val fieldName = name ?: ""
    val fieldValue = value ?: ""

    return div.apply {
        div {
            style = "color: #dcddde;font-size: 15px;padding-bottom: 10px;"
            +fieldName
        }

        div {
            style = "color: #AAA9AD;font-size: 14px;padding-bottom: 10px;Line-Height: 20px"
            +fieldValue
        }
    }
}