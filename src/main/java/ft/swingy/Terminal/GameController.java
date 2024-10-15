package ft.swingy.Terminal;

import java.util.Scanner;

import ft.swingy.Game.Direction;
import ft.swingy.Game.GameMapModel;
import ft.swingy.Game.GameModel;
import ft.swingy.Game.LoaderModel;
import ft.swingy.Game.MoveResult;
import ft.swingy.Hero.Hero;
import ft.swingy.Hero.HeroDirectorModel;

public class GameController {

    GameView gameView;
    GameModel gameModel;
    HeroDirectorController heroDirectorController;
    Scanner read;

    private void outputCombatLogs(){
        for (int i = 0; i < gameModel.getCombatLogs().size(); i++){
            System.out.println(gameModel.getCombatLogs().get(i));
        }
    }

    public void gameStart() {
        LoaderController loader;
        Hero hero;
        Direction input;

        input = null;
        read = new Scanner(System.in);
        heroDirectorController = new HeroDirectorController(new HeroDirectorView(), new HeroDirectorModel(), read);
        gameView = new GameView();
        loader = new LoaderController(new LoaderView(read), new LoaderModel());
        hero = loader.loadHero();
        if (hero == null){
            hero = heroDirectorController.createHero();
        }
        gameModel = new GameModel(hero, new GameMapModel(hero.getLevel()));
        gameView.startGame(hero);
        while (true) {
            GameMapView.MapWithHeroStats(gameModel.getMap(), gameModel.getTurn(), gameModel.getHero());
            while (input == null) {
                input = gameView.playerMoveInput(read);
                if (input == null){
                    System.out.println("Invalid input, please enter a correct move !");
                }
            }
            gameModel.movePlayer(input);
            while (gameModel.getMoveStatus() == MoveResult.INVALID_MOVE) {
                input = gameView.playerMoveInput(read);
                gameModel.movePlayer(input);
            }
            input = null;
            gameModel.incrementTurn();
            if (gameModel.getMoveStatus() == MoveResult.ENEMY_ENCOUNTER){
                if (gameView.enemyEncounter(read) == true){
                    if (gameModel.playerFight() == false){
                        outputCombatLogs();
                        gameModel.gameOver();
                        return ;
                    }
                    else{
                        outputCombatLogs();
                        gameModel.dropArtifact();
                        if (gameModel.getDropArtifact() != null){
                            gameModel.getDropArtifact().printArtifact();
                            if (gameView.takeArtifactsInput(read) == true){
                                gameModel.getHero().equipArtifact(gameModel.getDropArtifact());
                            }
                        }
                    };
                }
                else{
                    if (gameModel.playerTryToRun() == false){
                        outputCombatLogs();
                        if (gameModel.playerFight() == false){
                            outputCombatLogs();
                            gameModel.gameOver();
                            return ;
                        }
                        else{
                            gameModel.dropArtifact();
                            if (gameModel.getDropArtifact() != null){
                                gameModel.getDropArtifact().printArtifact();
                                if (gameView.takeArtifactsInput(read) == true){
                                    gameModel.getHero().equipArtifact(gameModel.getDropArtifact());
                                }
                                gameModel.resetArtifact();
                            }
                        };
                    }
                    else{
                        System.out.println("You've successfully ran away !");
                    }
                }
            }
            if (gameModel.playerWon()){
                gameModel.saveGame();
                GameMapView.MapWithHeroStats(gameModel.getMap(), gameModel.getTurn(), gameModel.getHero());
                if (gameView.continuePlaying(read) == true){
                    gameModel.createaNewMap();
                }
                else{
                    System.out.println("Goodbye !");
                    return ;
                }
            }
        }
    }
}
