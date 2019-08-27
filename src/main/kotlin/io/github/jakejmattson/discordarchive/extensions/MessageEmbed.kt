package io.github.jakejmattson.discordarchive.extensions

import kotlinx.html.*
import net.dv8tion.jda.api.entities.MessageEmbed
import java.awt.Color

private fun Color.toHex() = String.format("#%02x%02x%02x", red, green, blue).toUpperCase()
private const val defaultEmbedColor = "#1FFFFF"

fun MessageEmbed.toHTML(div: DIV): DIV {

    val embedAuthor = author
    val embedTitle = title
    val embedDescription = description
    val embedThumbnail = thumbnail?.url
    val embedImage = image?.url
    val embedFooterIcon = footer?.iconUrl
    val embedFooterText = footer?.text
    val embedColor = color?.toHex() ?: defaultEmbedColor
    val embedTimeStamp = timestamp
    val embedFields = fields

    return div.apply {
        div {
            style =
                "width: 100%;" +
                    "max-width: 500px;" +
                    "min-width: 300px;" +
                    "height: auto;" +
                    "overflow: visible;" +
                    "display: flex;" +
                    "flex-direction: column;" +
                    "border-radius: 5px;" +
                    "border-left-style: solid;" +
                    "border-left-width: 5px;" +
                    "border-left-color: $embedColor;" +
                    "background-color: #34363C;"

            div {
                style =
                    "padding-left: 10px;" +
                        "padding-top: 15px;" +
                        "line-height: 10px;"

                if (embedTitle != null) {
                    div {
                        style = "color: #F1F1F1;font-size: 15px;"
                        + embedTitle
                    }
                }

                if (embedDescription != null) {
                    div {
                        style = "color: #AAA9AD;font-size: 14px;Line-Height: 20px"
                        + embedDescription
                    }
                }

                if (embedThumbnail != null) {
                    img {
                        style =
                            "height: 80px;" +
                                "width: 80px;" +
                                "position: relative;" +
                                "float: right;" +
                                "bottom: 8px;" +
                                "margin-right: 7px;" +
                                "border-radius: 5px;" +
                                "border-style: solid;" +
                                "border-color: transparent;"

                        src = embedThumbnail
                    }
                }

                embedFields.forEach {
                    it.toHTML(this)
                }
            }
        }
    }
}