import java.awt.*
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
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
        this.addKeyListener(GameKeyAdapter())
        background = Color.BLACK
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
        this.nOfDots = 3
        for (z in 0 until this.nOfDots) {
            x[z] = 50 - z * 10
            y[z] = 50
        }
        this.locateApple()

        this.timer = Timer(delay, this)
        this.timer!!.start()

    }

    //apple 随机出现
    private fun locateApple() {
        var randomPos = (Math.random() * this.randPos).toInt()
        this.appleX = randomPos * this.dotSize
        randomPos = (Math.random() * this.randPos).toInt()
        this.appleY = randomPos * this.dotSize

    }

    //游戏开始
    override fun actionPerformed(e: ActionEvent?) {
        if (this.inGame) {
            this.checkApple()
            this.checkCollision()
            this.move()
        }
        this.repaint()
    }

    override fun paintComponents(g: Graphics?) {
        super.paintComponents(g)
        this.doDrawing(g)
    }

    private fun doDrawing(g: Graphics?) {
        assert(g != null) { "g:Graphics cant be null" }
        if (this.inGame) {
            g!!.drawImage(this.appleImage, this.appleX, this.appleY, this)
            for (z in 0..this.nOfDots) {
                if (z == 0) {
                    g.drawImage(this.snakeHeadImage, this.x[z], this.y[z], this)
                } else {
                    g.drawImage(this.dotImage, this.x[z], this.y[z], this)
                }
            }
            Toolkit.getDefaultToolkit().sync()
        } else {
            this.gameOver(g)
        }

    }

    private fun gameOver(g: Graphics?) {
        assert(g != null) { "g cant be null" }

        val msg: String = "Game Over"
        val smallFont = Font("Helvetica", Font.BOLD, 14)
        val fontMetrics = this.getFontMetrics(smallFont)

        val renderHint = RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        renderHint[RenderingHints.KEY_RENDERING] = RenderingHints.VALUE_RENDER_QUALITY

        (g!! as Graphics2D).setRenderingHints(renderHint)

        g.color = Color.WHITE
        g.font = smallFont
        //居中显示
        g.drawString(msg, this.boardWith - fontMetrics.stringWidth(msg) / 2, this.boardHeight / 2)

    }

    // apple 不能出现在原点
    private fun checkApple() {
        if (x[0] == this.appleX && y[0] == this.appleY) {
            this.nOfDots++
            this.locateApple()
        }
    }

    // 移动逻辑
    private fun move() {
        for (z in this.nOfDots downTo 1) {
            x[z] = x[z - 1]
            y[z] = y[z - 1]
        }
        if (this.leftDirection) {
            x[0] -= this.dotSize
        }
        if (this.rightDirection) {
            x[0] += this.dotSize
        }
        if (this.upDirection) {
            y[0] -= this.dotSize
        }
        if (this.downDirection) {
            y[0] += this.dotSize
        }
    }

    //碰撞检测
    private fun checkCollision() {
        for (z in this.nOfDots downTo 1) {
            if (z > 4 && x[0] == y[z] && y[0] == y[z]) {
                this.inGame = false
            }
        }
        if (y[0] >= this.boardHeight) {
            this.inGame = false
        }
        if (y[0] < 0) {
            this.inGame = false
        }
        if (x[0] >= this.boardWith) {
            this.inGame = false
        }
        if (x[0] < 0) {
            this.inGame = false
        }
        if (!this.inGame) {
            this.timer!!.stop()
        }
    }

    private inner class GameKeyAdapter : KeyAdapter() {
        override fun keyPressed(e: KeyEvent?) {
            val key = e!!.keyCode
            //如果向左
            if (key == KeyEvent.VK_LEFT && !rightDirection) {
                println("按键向左")
                leftDirection = true
                upDirection = false
                downDirection = false
            }
            // 如果向右
            if (key == KeyEvent.VK_RIGHT && !leftDirection) {
                println("按键向右")
                rightDirection = true
                upDirection = false
                downDirection = false
            }
            //如果向下
            if (key == KeyEvent.VK_DOWN && !upDirection) {
                println("按键向下")
                downDirection = true
                leftDirection = false
                rightDirection = false
            }
            // 如果向上
            if (key == KeyEvent.VK_UP && !downDirection) {
                println("按键向上")
                upDirection = true
                leftDirection = false
                rightDirection = false
            }

        }
    }

}