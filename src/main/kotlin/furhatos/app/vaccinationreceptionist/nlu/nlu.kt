package furhatos.app.vaccinationreceptionist.nlu

import com.google.gson.annotations.SerializedName
import furhatos.nlu.EnumEntity
import furhatos.nlu.Intent
import furhatos.nlu.ListEntity
import furhatos.nlu.TextGenerator
import furhatos.nlu.common.Number
import furhatos.nlu.common.PersonName
import furhatos.util.Language

// vaccine types
class Vaccine : EnumEntity(speechRecPhrases = true) {

    override fun getEnum(lang: Language): List<String> {
        return listOf("Moderna:Moderna,Spikevax", "BioNTech:BioNTech,Pfizer,Tozinameran,Comirnaty,Biotech,Bio tech,Bio,Tech",
                "Janssen:Janssen,Johnson,Johnson Johnson", "AstraZeneca:AstraZeneca,Oxford,Vaxzevria,Astra,Zeneca",
                "CoronaVac:CoronaVac,Sinovac", "Sinopharm:Sinopharm,Sino"
                )
    }

    override fun toText(lang: Language): String {
        return generate(lang, "$value")
    }
}

// main form with slots
class ReceiveVaccination: Intent(), TextGenerator {
    @SerializedName("fever") var fever: Boolean? = null                     //Do you currently have an acute illness with fever?
    @SerializedName("recent_vaccination") var recent_vaccination: Boolean? = null        //Have your received any other vaccine in the past 7 days?
    @SerializedName("age") var age: Number = Number(-1)             //How old are you?
    @SerializedName("parent_consent") var parent_consent: Boolean? = null            //Do you have the consent of your parents or a legal guardian?
    @SerializedName("count_dose") var count_dose: Number = Number(-1)      //How many doses have you ever received till now?
    @SerializedName("last_dose_date") var last_dose_date: furhatos.nlu.common.Date? = null               //When was your last dose? Please tell me the exact date.
    @SerializedName("last_dose_type") var last_dose_type: Vaccine? = null            //What type of vaccine did you get last time?
    @SerializedName("last_dose_reaction") var last_dose_reaction: Boolean? = null        //Did you develop an allergic reaction thereafter? Have you had any other unusual reactions after vaccination?
    @SerializedName("infection") var infection: Boolean? = null                 //Has it been reliably proven that you were infected with the Covid-19 in the past?
    @SerializedName("recovery") var recovery: Boolean? = null                  //Did you recover from that?
    @SerializedName("short_interval") var short_interval: Boolean? = null            //When was your last dose?
    @SerializedName("six_months_after_recovery") var six_months_after_recovery: Boolean? = null //Have you waited at least 6 months since recovery?
    @SerializedName("immunodeficiency") var immunodeficiency: Boolean? = null          //Do you have chronic diseases or do you suffer from immunodeficiency (e.g., due to chemotherapy, immunosuppressive therapy or other medications)?
    @SerializedName("allergy") var allergy: Boolean? = null                   //Do you have any allergies that at some point have caused such severe reactions that you needed hospital care?
    @SerializedName("severe_reaction") var severe_reaction: Boolean? = null           //Have you ever had a severe reaction to previous vaccinations that needed hospital care?
    @SerializedName("bleeding") var bleeding: Boolean? = null                  //Do you have an increased bleeding tendency due to disease or medicine?
    @SerializedName("pregnant") var pregnant: Boolean? = null                  //Are you pregnant?
    @SerializedName("count_pregnancy") var count_pregnancy: Number = Number(-1) //For how many months have you been pregnant?
    @SerializedName("known_disease") var known_disease: Boolean? = null             //Do you have any known diseases?
    @SerializedName("confirm_medical_info") var confirm_medical_info: Boolean? = null      //Are you sure that you have correctly answered all the questions?
//    @SerializedName("personal_num") var personal_num: String? = null
    @SerializedName("name") var name: String?=null
//    @SerializedName("first_name") var first_name: PersonName? = null
//    @SerializedName("last_name") var last_name: PersonName? = null
    @SerializedName("recommended_type") var recommended_type: Vaccine? = null
    @SerializedName("consent") var consent: Boolean? = null                   //Would you like to get vaccinated according to our recommendation?

    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I want to take a vaccine.",
                "I want to receive a vaccine.",
                "I want to get vaccinated.",
                "I would like to receive a vaccine."
        )
    }

    override fun toText(lang: Language): String {
        return generate(lang, "[You are $name]")
    }

    override fun toString(): String {
        return toText()
    }
}

class TellNoProblem: Intent() {
    var disease: String = ""

    override fun getExamples(lang: Language): List<String> {
        return listOf("I haven't @disease",
                "I don't have @disease",
                "I didn't have @disease",
                "I haven't had @disease")
    }
}

// tell disease
class TellHaveProblem: Intent() {
    var disease: String = ""

    override fun getExamples(lang: Language): List<String> {
        return listOf("I have @disease",
                    "I had @disease",
                    "I have had @disease")
    }
}

class TellNoPersonalNumber: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I don't have a personal number.",
                "I have no personal number.",
                "I don't have one.")
    }
}

class TellPersonalNumber(var personal_number: String?= null): Intent() {


    override fun getExamples(lang: Language): List<String> {
        return listOf("My personal number is @personal_number",
                "My number is @personal_number",
                "@personal_number")
    }
}


class NameList: ListEntity<PersonName>()

class TellName(var names: NameList?= null): Intent() {

    override fun getExamples(lang: Language): List<String> {
        return listOf("My name is @names.",
                        "The name is @names.",
                        "I'm @names",
                        "@names.")
    }
}

// tell the number of months of pregnancy
class TellCountPregnancy : Intent() {
    var count_pregnancy: Number = Number(-1)

    override fun getExamples(lang: Language): List<String> {
        return listOf("I have been pregnant for @count_pregnancy months", "I'm pregnant for @count_pregnancy months", "I'm @count_pregnancy months pregnant",
                "@count_pregnancy months", "@count_pregnancy")
    }
}

// tell the age
class TellAge : Intent() {
    var age: Number = Number(-1)

    override fun getExamples(lang: Language): List<String> {
        return listOf("I'm @age years old.", "I'm @age", "@age")
    }
}

class TellAgeFormally : Intent() {
    var age: Number = Number(-1)

    override fun getExamples(lang: Language): List<String> {
        return listOf("I'm @age years old.", "I'm @age")
    }
}

class TellNumberDose: Intent() {
    var dose: Number = Number(-1)

    override fun getExamples(lang: Language): List<String> {
        return listOf("I have received @dose doses.",
                "I have received @dose injections.",
                "I have received @dose vaccinations.",
                "I have taken @dose injections.",
                "I have taken @dose vaccinations.",
                "I have taken @dose doses.",
                "I had @dose injections.",
                "I had @dose vaccinations.",
                "I had @dose doses.",
                "@dose doses.",
                "@dose injections.",
                "@dose vaccinations.",
                "@dose")
    }
}

class TellNumberDoseFormally: Intent() {
    var dose: Number = Number(-1)

    override fun getExamples(lang: Language): List<String> {
        return listOf("I have received @dose doses.",
                "I have received @dose injections.",
                "I have received @dose vaccinations.",
                "I have taken @dose injections.",
                "I have taken @dose vaccinations.",
                "I have taken @dose doses.",
                "I had @dose injections.",
                "I had @dose vaccinations.",
                "I had @dose doses."
                )
    }
}

class TellNotAnyDose: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I have never received any dose.",
                    "I never received any dose.",
                    "I have never received any vaccination.",
                    "I never received any vaccination.",
                    "I haven't received any dose.",
                    "I haven't received any vaccination")
    }
}

class TellNotPregnant: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I'm not pregnant."
                )
    }
}

class TellIsPregnant: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I'm pregnant.",
                      "I have been pregnant since months ago"
        )
    }
}

class TellIsInfected: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I'm infected with Covid.",
                "I'm infected with Covid 19.",
                "I'm infected with coronavirus."
        )
    }
}

class TellWasInfected: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf("I was infected with Covid.",
                "I have been infected with Covid.",
                "I was infected with Covid 19.",
                "I have been infected with Covid 19.",
                "I was infected with coronavirus.",
                "I have been infected with coronavirus."
        )
    }
}