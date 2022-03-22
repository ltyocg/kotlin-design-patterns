import java.awt.Color
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.*

class FileSelectorJFrame : JFrame("File Loader"), FileSelectorView, ActionListener {
    private val ok = JButton("OK").apply { setBounds(250, 50, 100, 25) }
    private val cancel = JButton("Cancel").apply { setBounds(380, 50, 100, 25) }
    private val input = JTextField(100).apply { setBounds(150, 15, 200, 20) }
    private val area = JTextArea(100, 100).apply { isEditable = false }
    override var presenter: FileSelectorPresenter? = null
    override var fileName: String? = null

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        layout = null
        setBounds(100, 100, 500, 200)
        add(JPanel().apply {
            layout = null
            setBounds(0, 0, 500, 200)
            background = Color.LIGHT_GRAY
            add(JLabel("File Name :").apply { setBounds(30, 10, 100, 30) })
            add(JLabel("File contents :").apply { setBounds(30, 100, 120, 30) })
            add(input)
            add(JScrollPane(area).apply {
                horizontalScrollBarPolicy = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED
                verticalScrollBarPolicy = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED
                setBounds(150, 100, 250, 80)
            })
            add(ok.apply { addActionListener(this@FileSelectorJFrame) })
            add(cancel.apply { addActionListener(this@FileSelectorJFrame) })
        })
    }

    override fun actionPerformed(e: ActionEvent) {
        if (ok == e.source) {
            fileName = input.text
            presenter!!.fileNameChanged()
            presenter!!.confirmed()
        } else if (cancel == e.source) presenter!!.cancelled()
    }

    override fun open() {
        isVisible = true
    }

    override fun close() = dispose()
    override val opened: Boolean
        get() = isVisible

    override fun showMessage(message: String?) = JOptionPane.showMessageDialog(null, message)
    override fun displayData(data: String?) {
        area.text = data
    }
}