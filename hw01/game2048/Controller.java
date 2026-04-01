package hw01.game2048;

public class Controller {
    private Model model;
    private final View view;
    private boolean running = true;
    private boolean hasBasicWin = false;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    // 最主要的部分，控制整个流程
    public void start() {
        view.showWelcome();
        initGame();

        while (running) {
            view.showBoardAndScore(model.getBoard(), model.getScore());

            // 这里注意：是判断基础胜利的if语句
            if (!hasBasicWin && model.gameWin()) {
                handleWin();
                continue;
            }
            if (model.gameOver()) {
                handleGameOver();
                continue;
            }

            // 执行用户操作
            Command command = view.readCommand();
            handleCommand(command);
        }

        // 象征性的表示并给出最终分数
        view.showBye(model.getScore());
    }

    // 初始化游戏
    private void initGame() {
        model.generateNumber();
        model.generateNumber();
    }

    // 执行用户操作的方法
    private void handleCommand(Command command) {
        switch(command) {
            case UP, DOWN, LEFT, RIGHT -> applyMove(command);
            case RESTART -> restartGame();
            case QUIT -> running = false;
            default -> view.showInvalidInput();
        }
    }

    // 用于执行移动操作以及判断该次移动是否有效，并在空方格产生随机数字
    private void applyMove(Command command) {
        boolean moved = switch(command) {
            case UP -> model.moveUp();
            case DOWN -> model.moveDown();
            case LEFT -> model.moveLeft();
            case RIGHT -> model.moveRight();
            default -> false;
        };
        if (moved) model.generateNumber();
        else view.showNoMove();
    }

    // 重开游戏
    private void restartGame() {
        model = new Model();
        hasBasicWin = false;
        initGame();
    }

    // 第一次基础胜利询问用户是否继续的逻辑操作
    private void handleWin() {
        view.showWin();
        boolean toContinue = view.askContinue();
        if (toContinue) {
            hasBasicWin = true;
        } else {
            running = false;
        }
    }

    // 游戏结束时询问用户做法的逻辑操作
    private void handleGameOver() {
        view.showGameOver();
        boolean toRestart = view.askRestart();
        if (toRestart) {
            restartGame();
        } else {
            running = false;
        }
    }
}
