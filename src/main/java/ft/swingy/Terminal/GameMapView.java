package ft.swingy.Terminal;

import ft.swingy.Game.ANSI;
import ft.swingy.Game.GameMapModel;
import ft.swingy.Game.ID;
import ft.swingy.Hero.Hero;

public class GameMapView {
    private static void StatsAtIndex(int index, Hero hero){
        String[] stats = new String[7];
        stats[0] = "    Name: " + hero.getName();
        stats[1] = "    Type: " + hero.getType();
        stats[2] = "    Level: " + hero.getLevel();
        stats[3] = "    Experience: " + hero.getExperience();
        stats[4] = "    Attack: " + hero.getAttack();
        stats[5] = "    Defense: " + hero.getDefense();
        stats[6] = "    HP: " + hero.getHitPoints();
        System.out.print(stats[index]);
    }

    public static void MapWithHeroStats(GameMapModel map, int turn, Hero hero){
        final int renderDistance = 13;
        int startX = map.getPlayerX() - renderDistance;
        int startY = map.getPlayerY() - renderDistance;
        int statsLine = 0;
        if (startX < 0)
            startX = 0;
        if (startY < 0)
            startY = 0;
        for(int i = startY; i < map.getPlayerY() + renderDistance && i < map.size; i++){
            for(int j = startX; j < map.getPlayerX() + renderDistance + 1 && j < map.size; j++){
                if (map.map[j][i] == ID.Player.getId()){
                    System.out.print(ANSI.BLACK_BOLD_BRIGHT + ANSI.GREEN_BACKGROUND + "P" + ANSI.RESET);
                }
                else if (map.map[j][i] == ID.Enemy.getId()){
                    System.out.print(ANSI.RED_TEXT + ANSI.GREEN_BACKGROUND + "X" + ANSI.RESET);
                }
                else{
                    System.out.print(ANSI.GREEN_BACKGROUND + " " + ANSI.RESET);
                }
            }
            if (statsLine < 7){
                StatsAtIndex(statsLine, hero);
                statsLine++;
            }
            else if (statsLine == 7){
                System.out.print("    turn: " + turn);
                statsLine++;
            }
            System.out.println(ANSI.RESET);
        }
    }
}
