import com.google.inject.Guice

fun main() {
    SimpleWizard().smoke()
    AdvancedWizard(SecondBreakfastTobacco()).smoke()
    AdvancedSorceress().apply { setTobacco(SecondBreakfastTobacco()) }.smoke()
    Guice.createInjector(TobaccoModule()).getInstance(GuiceWizard::class.java).smoke()
}