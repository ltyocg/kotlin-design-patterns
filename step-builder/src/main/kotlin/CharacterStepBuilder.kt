object CharacterStepBuilder {
    fun newBuilder(): NameStep = CharacterSteps()
    interface NameStep {
        fun name(name: String?): ClassStep
    }

    interface ClassStep {
        fun fighterClass(fighterClass: String?): WeaponStep
        fun wizardClass(wizardClass: String?): SpellStep
    }

    interface WeaponStep {
        fun withWeapon(weapon: String?): AbilityStep
        fun noWeapon(): BuildStep
    }

    interface SpellStep {
        fun withSpell(spell: String?): AbilityStep
        fun noSpell(): BuildStep
    }

    interface AbilityStep {
        fun withAbility(ability: String): AbilityStep
        fun noMoreAbilities(): BuildStep
        fun noAbilities(): BuildStep
    }

    interface BuildStep {
        fun build(): Character
    }

    private class CharacterSteps : NameStep, ClassStep, WeaponStep, SpellStep, AbilityStep, BuildStep {
        private var name: String? = null
        private var fighterClass: String? = null
        private var wizardClass: String? = null
        private var weapon: String? = null
        private var spell: String? = null
        private val abilities = mutableListOf<String>()
        override fun name(name: String?): ClassStep = apply { this.name = name }
        override fun fighterClass(fighterClass: String?): WeaponStep = apply { this.fighterClass = fighterClass }
        override fun wizardClass(wizardClass: String?): SpellStep = apply { this.wizardClass = wizardClass }
        override fun withWeapon(weapon: String?): AbilityStep = apply { this.weapon = weapon }
        override fun noWeapon(): BuildStep = this
        override fun withSpell(spell: String?): AbilityStep = apply { this.spell = spell }
        override fun noSpell(): BuildStep = this
        override fun withAbility(ability: String): AbilityStep = apply { abilities.add(ability) }
        override fun noMoreAbilities(): BuildStep = this
        override fun noAbilities(): BuildStep = this
        override fun build(): Character {
            val character = Character(name)
            if (fighterClass != null) character.fighterClass = fighterClass
            else character.wizardClass = wizardClass
            if (weapon != null) character.weapon = weapon
            else character.spell = spell
            if (abilities.isNotEmpty()) character.abilities = abilities
            return character
        }
    }
}
