package ft.swingy.Game;

import java.util.ArrayList;

import ft.swingy.Artifacts.Artifact;
import ft.swingy.Artifacts.ArtifactDirector;
import ft.swingy.Hero.Hero;

public class GameModel {
    private Hero hero = null;
    private GameMapModel gameMapModel = null;
    private int turn;
    private MoveResult moveStatus;
    private Artifact dropArtifact;

    public GameModel(Hero hero, GameMapModel gameMapModel) {
        this.hero = hero;
        this.gameMapModel = gameMapModel;
        this.turn = 0;
    }

    public void movePlayer(Direction direction) {
        moveStatus = gameMapModel.movePlayer(direction);
    }

	public void revertPlayerMove(){
		gameMapModel.revertPlayerMove();
	}

    public boolean playerFight() {
        return hero.fightSimulation();
    }

    public boolean playerTryToRun() {
        return hero.tryToRun();
    }

    public void gameOver() {
        hero.deleteSave();
    }

    public void saveGame() {
        hero.save();
    }

    public void dropArtifact() {
        dropArtifact = ArtifactDirector.dropRandomArtifact(hero.getLevel());
    }

    public void incrementTurn() {
        turn++;
    }

    public boolean playerWon() {
        return gameMapModel.playerReachedEdge();
    }

    public void createNewMap() {
        gameMapModel = new GameMapModel(hero.getLevel());
        turn = 0;
        hero.regenerate();
    }

    public void resetArtifact() {
        dropArtifact = null;
    }

    public ArrayList<String> getCombatLogs() {
        return hero.getCombatLogs();
    }

    public Artifact getDropArtifact() {
        return dropArtifact;
    }

    public int getTurn() {
        return turn;
    }

    public Hero getHero() {
        return hero;
    }

    public MoveResult getMoveStatus() {
        return moveStatus;
    }

    public Direction getLastMove() {
        return gameMapModel.getLastMove();
    }

    public void setMoveStatus(MoveResult moveStatus) {
        this.moveStatus = moveStatus;
    }

    public GameMapModel getMap() {
        return gameMapModel;
    }
}
