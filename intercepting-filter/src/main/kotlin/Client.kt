import java.awt.BorderLayout
import java.awt.GridLayout
import javax.swing.*

class Client : JFrame("Client System") {
    @Transient
    lateinit var filterManager: FilterManager

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(300, 300)
        layout = BorderLayout()
        val jl = JLabel("RUNNING...")
        add(jl, BorderLayout.SOUTH)
        val jtField = Array(3) { JTextField() }
        val jtAreas = Array(2) { JTextArea() }
        val processButton = JButton("Process").apply {
            addActionListener {
                jl.text = filterManager.filterRequest(Order(jtField[0].text, jtField[1].text, jtAreas[0].text, jtField[2].text, jtAreas[1].text))
            }
        }
        add(JPanel().apply {
            layout = GridLayout(6, 2)
            add(JLabel("Name"))
            add(jtField[0])
            add(JLabel("Contact Number"))
            add(jtField[1])
            add(JLabel("Address"))
            add(jtAreas[0])
            add(JLabel("Deposit Number"))
            add(jtField[2])
            add(JLabel("Order"))
            add(jtAreas[1])
            add(JButton("Clear").apply {
                addActionListener {
                    jtAreas.forEach { i -> i.text = "" }
                    jtField.forEach { i -> i.text = "" }
                }
            })
            add(processButton)
        }, BorderLayout.CENTER)
        SwingUtilities.getRootPane(processButton).defaultButton = processButton
        isVisible = true
    }
}