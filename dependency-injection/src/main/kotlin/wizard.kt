interface Wizard {
    fun smoke()
}

class AdvancedSorceress : Wizard {
    private lateinit var tobacco: Tobacco
    override fun smoke() = tobacco.smoke(this)
    fun setTobacco(tobacco: Tobacco) {
        this.tobacco = tobacco
    }
}

class AdvancedWizard(private val tobacco: Tobacco) : Wizard {
    override fun smoke() = tobacco.smoke(this)
}

class GuiceWizard(private val tobacco: Tobacco) : Wizard {
    override fun smoke() = tobacco.smoke(this)
}

class SimpleWizard : Wizard {
    private val tobacco = OldTobyTobacco()
    override fun smoke() = tobacco.smoke(this)
}