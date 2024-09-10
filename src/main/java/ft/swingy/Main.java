package ft.swingy;

import ft.swingy.Hero.HeroBuilder;
import ft.swingy.Hero.HeroDirector;
import ft.swingy.Artifacts.ArtifactBuilder;
import ft.swingy.Artifacts.Artifact;
import ft.swingy.Artifacts.ArtifactDirector;
import ft.swingy.GUI.GUI;
import ft.swingy.Hero.Hero;

public class Main
{
    public static void main( String[] args )
    {
        // HeroDirector director = new HeroDirector();
        // HeroBuilder builder = new HeroBuilder();
        // Hero war = director.makeWarrior(builder, "Loufisse");
        // war.printStats();

        // ArtifactDirector artifactDirector = new ArtifactDirector();
        // ArtifactBuilder artifactBuilder = new ArtifactBuilder();
        // Artifact art;
        // for (int i = 0; i < 10; i++) {
        //     art = artifactDirector.makeT1(artifactBuilder);
        //     art.printStats();
        // }

        GUI gui = new GUI();
        GUI.createGui();

    }
}
