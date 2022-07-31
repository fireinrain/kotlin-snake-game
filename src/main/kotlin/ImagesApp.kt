import java.awt.BorderLayout
import java.awt.Graphics
import javax.swing.ImageIcon
import javax.swing.JFrame
import javax.swing.JPanel

/**
@Description:
@Author  : fireinrain
@Site    : https://github.com/fireinrain
@File    : ImagesApp
@Software: IntelliJ IDEA
@Time    : 2022/7/31 11:44 AM
 */

class ImagesApp : JFrame() {

    init {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        this.setLocationRelativeTo(null)
        this.setSize(400, 300)
        this.isResizable = false
        this.getContentPane().layout = null
        val imagesPanel = ImagesPanel(this)
        imagesPanel.setBounds(0, 0, 400, 300)
        this.getContentPane().add(imagesPanel)
        this.isVisible = true

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            ImagesApp()
        }
    }

    inner class ImagesPanel(val jframe: ImagesApp) : JPanel() {
        override fun paint(g: Graphics?) {
            super.paint(g)
            val imageIcon = ImageIcon("src/main/resources/apple.png")
            g!!.drawImage(imageIcon.image, 0, 0, 400, 300, jframe)
        }
    }

}
