package io.github.jakejmattson.discordarchive

import io.github.jakejmattson.discordarchive.extensions.toHTML
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import net.dv8tion.jda.api.entities.*
import java.io.*
import net.dv8tion.jda.api.JDABuilder
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    require(args.size == 2) { "Expected bot token and channel ID as program arguments." }

    val token = args.component1()
    val channelId = args.component2()

    val jda = JDABuilder(token).build()

    Thread.sleep(1000)

    val channel = jda.getTextChannelById(channelId) as MessageChannel?
        ?: throw IllegalArgumentException("Could not retrieve channel from provided ID.")

    val outputFile = File("archive.html")
    val writer = FileWriter(outputFile)

    channel.toHTML(writer)
    writer.close()

    jda.shutdownNow()

    exitProcess(0)
}

fun MessageChannel.toHTML(output: Writer) {
    val history = getHistoryFromBeginning(100).complete().retrievedHistory

    require(history.isNotEmpty()) { "No messages in channel." }

    output.appendHTML().html {
        body {
            style = "background-color: #36393F; font-family: Whitney,Helvetica Neue,Helvetica,Arial,sans-serif;"

            div {
                style = "color: #949494"

                h1 {
                    +name
                }
            }

            div {
                history.reversed().forEach {
                    it.toHTML(this)
                }
            }
        }
    }
}
