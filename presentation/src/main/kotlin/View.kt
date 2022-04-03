import org.slf4j.LoggerFactory
import java.awt.TextField
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.*

class View {
    private val log = LoggerFactory.getLogger(javaClass)
    val model = PresentationModel(PresentationModel.albumDataSet())
    val txtTitle = TextField()
    val txtArtist = TextField()
    val chkClassical = JCheckBox()
    val txtComposer = TextField()

    fun saveToPMod() {
        log.info("Save data to PresentationModel")
        model.apply {
            artist = txtArtist.text
            title = txtTitle.text
            isClassical = chkClassical.isSelected
            composer = txtComposer.text
        }
    }

    fun loadFromPMod() {
        log.info("Load data from PresentationModel")
        txtArtist.text = model.artist
        txtTitle.text = model.title
        chkClassical.isSelected = model.isClassical
        txtComposer.isEditable = model.isClassical
        txtComposer.text = model.composer
    }

    fun createView() {
        JFrame("Album").apply {
            add(Box.createHorizontalBox().apply {
                add(JList(model.albumList).apply {
                    addMouseListener(object : MouseAdapter() {
                        override fun mouseClicked(e: MouseEvent) {
                            this@View.model.setSelectedAlbumNumber(selectedIndex + 1)
                            loadFromPMod()
                        }
                    })
                })
                add(Box.createVerticalBox().apply {
                    add(txtArtist.apply { setSize(WIDTH_TXT, HEIGHT_TXT) })
                    add(txtTitle.apply { setSize(WIDTH_TXT, HEIGHT_TXT) })
                    add(chkClassical.apply {
                        addActionListener {
                            txtComposer.isEditable = chkClassical.isSelected
                            if (!chkClassical.isSelected) txtComposer.text = ""
                        }
                    })
                    add(txtComposer.apply {
                        setSize(WIDTH_TXT, HEIGHT_TXT)
                        isEditable = model.isClassical
                    })
                    add(JButton("Apply").apply {
                        addMouseListener(object : MouseAdapter() {
                            override fun mouseClicked(e: MouseEvent) {
                                saveToPMod()
                                loadFromPMod()
                            }
                        })
                    })
                    add(JButton("Cancel").apply {
                        addMouseListener(object : MouseAdapter() {
                            override fun mouseClicked(e: MouseEvent) = loadFromPMod()
                        })
                    })
                })
            })
            defaultCloseOperation = WindowConstants.EXIT_ON_CLOSE
            setBounds(200, 200, 500, 300)
            isVisible = true
        }
    }

    companion object {
        private const val WIDTH_TXT = 200
        private const val HEIGHT_TXT = 50
    }
}