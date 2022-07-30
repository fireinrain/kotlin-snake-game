import java.awt.Color
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import javax.swing.ImageIcon
import javax.swing.JPanel
import javax.swing.Timer

/**
@Description:
@Author  : fireinrain
@Site    : https://github.com/fireinrain
@File    : GameBoard
@Software: IntelliJ IDEA
@Time    : 2022/7/30 2:35 PM
 */

class GameBoard : JPanel(), ActionListener {

    private val boardWith = 300
    private val boardHeight = 300
    private val dotSize = 10
    private val allDots = 900
    private val randPos = 29
    private val delay = 140

    private val x = IntArray(allDots)
    private val y = IntArray(allDots)

    private var nOfDots: Int = 0
    private var appleX: Int = 0
    private var appleY: Int = 0

    private var leftDirection: Boolean = false
    private var rightDirection: Boolean = true
    private var upDirection: Boolean = false
    private var downDirection: Boolean = false
    private var inGame = true

    private var timer: Timer? = null
    private var dotImage: Image? = null
    private var appleImage: Image? = null
    private var snakeHeadImage: Image? = null

    // 初始化

    init {
        addKeyListener(TAdapter())
        background = Color.PINK
        isFocusable = true

        preferredSize = Dimension(boardWith, boardHeight)
        // 装载图片
        loadGameImages()
        //初始化游戏
        initGames()
    }


    private fun loadGameImages() {
        val dot = ImageIcon("src/main/resources/dot.png")
        this.dotImage = dot.image

        val apple = ImageIcon("src/main/resources/apple.png")
        this.appleImage = apple.image

        val head = ImageIcon("src/main/resources/sname_head.png")
        this.snakeHeadImage = head.image

    }

    private fun initGames() {


    }

    override fun actionPerformed(e: ActionEvent?) {

    }

    override fun paintComponents(g: Graphics?) {
        super.paintComponents(g)
        doDrawing(g)
    }

    private fun doDrawing(g: Graphics?) {
        assert(g != null) { "g:Graphics cant be null" }


    }
}