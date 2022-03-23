import org.springframework.beans.factory.getBean
import org.springframework.context.support.StaticApplicationContext
import org.springframework.context.support.beans

fun main() {
    SimpleWizard().smoke()
    AdvancedWizard(SecondBreakfastTobacco()).smoke()
    AdvancedSorceress().apply { setTobacco(SecondBreakfastTobacco()) }.smoke()
    StaticApplicationContext().apply {
        beans {
            bean<RivendellTobacco>()
            bean<GuiceWizard>()
        }.initialize(this)
    }.getBean<GuiceWizard>().smoke()
}