package furhatos.app.vaccinationreceptionist.nlu

import furhatos.util.Language
import furhatos.nlu.Intent
import furhatos.nlu.TextGenerator
import java.util.*



// main form with slots
// TODO: complete the slot form
class ReceiveVaccination: Intent(), TextGenerator {
    var name: String? = null
    var birthday: Date? = null

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I want to take a vaccine.",
                "I want to receive a vaccine.",
                "I would like to receive a vaccine."
        )
    }

    override fun toText(lang: Language): String {
        return generate(lang, "[You are $name] [born on $birthday]")
    }

    override fun toString(): String {
        return toText()
    }
}