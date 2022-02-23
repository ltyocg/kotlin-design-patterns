import java.awt.BorderLayout
import java.awt.Dimension
import javax.swing.*
import javax.swing.table.DefaultTableModel

class Target : JFrame("Order System") {
    private val dtm = DefaultTableModel(arrayOf("Name", "Contact Number", "Address", "Deposit Number", "Order"), 0)
    private val jt = JTable(dtm)
    private val del = JButton("Delete")

    init {
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(640, 480)
        layout = BorderLayout()
        add(jt.tableHeader, BorderLayout.NORTH)
        add(JPanel().apply {
            layout = BorderLayout()
            add(del, BorderLayout.EAST)
        }, BorderLayout.SOUTH)
        add(JScrollPane(jt).apply {
            preferredSize = Dimension(500, 250)
        }, BorderLayout.CENTER)
        del.addActionListener {
            val temp = jt.selectedRow
            if (temp != -1) (0 until jt.selectedRowCount).forEach { _ -> dtm.removeRow(temp) }
        }
        SwingUtilities.getRootPane(del).defaultButton = del
        isVisible = true
    }

    fun execute(request: Array<String>) {
        dtm.addRow(request.copyOf())
    }
}