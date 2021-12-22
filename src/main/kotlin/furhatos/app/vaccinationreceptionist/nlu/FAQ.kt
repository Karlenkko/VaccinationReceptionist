package furhatos.app.vaccinationreceptionist.nlu

import furhatos.event.Event
import furhatos.nlu.Intent
import furhatos.util.Language

// FAQ
class RequestSideEffects: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "What are the side effects of the vaccination?",
                "What is the symptom after the vaccination?",
                "How I might feel after the vaccination?",
                "How will I feel after the vaccination?"
        )
    }
}

class RequestCOVIDSymptoms: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "What are frequent symptoms?",
                "What are possible symptoms?",
                "What are symptoms of Covid 19?",
                "What are symptoms if I'm infected?"
        )
    }
}

class RequestBooster: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Why do I need the booster dose?",
                "Why is there a need for a booster dose?",
                "Why do I need the third dose?",
                "Why do I need the third vaccination?"
        )
    }
}

class RequestBreastFeeding(): Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Are the vaccines safe for breastfeeding women?",
                "Is the vaccine safe for breastfeeding women?",
                "Is the vaccine safe for breastfeeding?"
        )
    }
}

class RequestPregnancy: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Are the vaccines safe for pregnant women?",
                "Is the vaccine safe for pregnant women?",
                "Can pregnant woman get vaccinated?",
                "Can pregnant woman receive vaccination?"
        )
    }
}

class RequestVaccineProtection: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Can the vaccines protect me against all variants?",
                "Can the vaccines protect me against Omicron variant?",
                "Can the vaccines protect me against Delta variant?"
        )
    }
}

class RequestVaccine: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "What's the vaccine that you have?",
                "What is your vaccine?",
                "Which vaccine do you have?",
                "What are the vaccines that you offer?",
                "What are the vaccines that you have?"
        )
    }
}

class RequestVaccineSafety: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Are the approved vaccines safe?",
                "Are the vaccines safe?",
                "Are the vaccines safe for injection?",
                "Is the vaccine safe?"
        )
    }
}

class RequestNationality: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "I’m not a Swedish citizen, can I get vaccinated?",
                "Can I get vaccinated if I'm not Swedish?",
                "I don’t have Swedish ID, can I get vaccinated?",
                "Can I get vaccinated if I don’t have Swedish ID?",
                "I don’t have personal number, can I get vaccinated?",
                "Can I get vaccinated if I don’t have personal number?"
        )
    }
}

class RequestVaccinationFee: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Is the vaccination free?",
                "Is the vaccine free?",
                "How much should I pay?",
                "How much is the vaccine?"
        )
    }
}

class RequestAfterVaccinationRegulation: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Do I have to continue following the general guidelines after the vaccination?",
                "Do I have to follow the general guidelines after the vaccination?",
                "Do I need to follow the general rules after the vaccination?"
        )
    }
}

class RequestVaccinationCertificate: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "How do I get a vaccination certificate?",
                "How do I get a vaccination pass?",
                "When will I get a vaccination pass?"
        )
    }
}

class RequestVaccineType: Intent() {
    override fun getExamples(lang: Language): List<String> {
        return listOf(
                "Can I choose the type of the vaccine?",
                "Can I choose the vaccine?",
                "Can I select the type of the vaccine?",
                "Can I select the vaccine?"
        )
    }
}

//class RequestWaitingTime: Intent() {
//    override fun getExamples(lang: Language): List<String> {
//        return listOf(
//                "How long do I have to wait?",
//                "When will I get the vaccination?",
//                "How long will it take?",
//                "How long should I wait?",
//                "What's the waiting time?"
//        )
//    }
//}

class TellSideEffects: Event() {
    var resp = "You might experience some tenderness, swelling or redness at the injection site, or your upper arm might itch. Some people develop a short-term fever, headaches or might feel generally unwell. This is to be expected and is a sign that the immune system is responding. The symptoms are often mild and will clear up after a few days. Allergic reactions are very rare."
}
class TellCOVIDSymptoms: Event() {
    var resp = "Frequent symptoms of COVID-19 include dry cough, fever, shortness of breath, as well as a temporary loss of smell and taste. A general feeling of being unwell accompanied by headaches and aching limbs, sore throat, and sniffles are also depicted."
}
class TellBooster: Event() {
    var resp = "While vaccinated persons still have strong protection against severe disease currently, it could decrease in the months ahead, particularly for those who are at higher risk.  A booster dose will increase their level of immunity and provide greater protection from severe disease for a longer period."
}
class TellBreastFeeding: Event() {
    var resp = "The breast milk of vaccinated mothers may help to protect their babies from COVID-19 due to antibodies in breast milk. There have also been no vaccine-related side effects reported in the babies who were breastfed by mothers who received the vaccine while breastfeeding."
}
class TellPregnancy: Event() {
    var resp = "Studies were done on women at different trimesters of pregnancy and there is no evidence to suggest that the Pfizer-BioNTech or Comirnaty or Moderna COVID-19 vaccine may cause harm to pregnant women or their babies."
}
class TellVaccineProtection: Event() {
    var resp = "Current evidence suggests that the Pfizer-BioNTech or Comirnaty, and Moderna’s vaccines continue to offer good protection against the various COVID-19 variants. And it is important to keep two things separate: protection from serious illness and protection from becoming infected. None of the vaccines provide 100 percent protection against infection, but they all provide good protection against serious illness in Covid-19."
}
class TellVaccine: Event() {
    var resp = "We offer two mRNA COVID-19 vaccines here: BioNTech or Pfizer's Comirnaty and Moderna’s Spikevax. They are gene-based vaccines that are predicated on the same new type of technology."
}
class TellVaccineSafety: Event() {
    var resp = "Yes, the vaccines that are approved for Covid 19 have undergone all the studies required. The results of the studies have been evaluated by the European medical product agencies and the vaccines have been approved as safe and effective."
}
class TellNationality: Event() {
    var resp = "You do not need to be a Swedish citizen to get the vaccine. If you live or have been in Sweden for a long time, you will be offered vaccination. People seeking asylum or who are in Sweden without a permit will also be offered free vaccination."
}
class TellVaccinationFee: Event() {
    var resp = "If you live in Sweden and are 12 years or older, you will be offered free Covid-19 vaccination."
}
class TellAfterVaccinationRegulation: Event() {
    var resp = "Yes, it is important that people who have been vaccinated against Covid-19 continue to follow the general guidelines even after the vaccine has started to work. Covid-19 is still spreading. But the vaccine protects you from becoming seriously ill so recommendations are adapted somewhat for those who have been vaccinated."
}
class TellVaccinationCertificate: Event() {
    var resp = "You do not get a vaccination certificate automatically after your vaccination. You always need to request a certificate at www.covidbevis.se by following the instruction there. You have to wait 14 days after being fully vaccinated."
}
class TellVaccineType: Event() {
    var resp = "No, due to current situations, you cannot choose the type of the vaccine. We will give you a recommendation according to your conditions"
}