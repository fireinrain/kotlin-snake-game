import java.awt.EventQueue
import javax.swing.JFrame

/**
@Description:
@Author  : fireinrain
@Site    : https://github.com/fireinrain
@File    : GameSnake
@Software: IntelliJ IDEA
@Time    : 2022/7/31 10:54 AM
 */

class GameSnakeContainer : JFrame() {
    init {
        this.initSnake()
    }

    private fun initSnake() {
        this.add(GameBoard())
        this.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        this.title = "Snake Game"
        this.isResizable = false
        this.pack()
        this.setLocationRelativeTo(null)
        this.isVisible = true

    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            EventQueue.invokeLater {
                GameSnakeContainer()
            }
        }
    }
}