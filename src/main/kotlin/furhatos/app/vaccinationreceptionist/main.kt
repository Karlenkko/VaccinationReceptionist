package furhatos.app.vaccinationreceptionist

import furhatos.app.vaccinationreceptionist.flow.*
import furhatos.skills.Skill
import furhatos.flow.kotlin.*

class VaccinationreceptionistSkill : Skill() {
    override fun start() {
        Flow().run(Idle)
    }
}

fun main(args: Array<String>) {
    Skill.main(args)

}
