package furhatos.app.vaccinationreceptionist.nlu

import furhatos.util.Language
import furhatos.nlu.Intent
import furhatos.nlu.TextGenerator
import java.util.*

// FAQ
class RequestVaccin: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "What's the vaccin that you have?",
                "What is your vaccin?",
                "Which vaccin do you have?"
        )
    }
}

class RequestSideEffects: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "What will happen after the vaccination?",
                "What is the symptom after the vaccination?",
                "What should I do after the vaccination?"
        )
    }
}

class RequestWaitingTime: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "How long do I have to wait?",
                "When will I get the vaccination?",
                "How long will it take?",
                "How long should I wait?",
                "What's the waiting time?"
        )
    }
}

// main form with slots
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