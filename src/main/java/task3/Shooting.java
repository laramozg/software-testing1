package task3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import task3.enam.HairCondition;

import java.util.HashSet;
import java.util.Set;

@Data
public class Shooting {
    private Camera camera;
    private Panel panel;
    private Set<Human> heroes;

    @SneakyThrows
    public Shooting(Camera camera, Panel panel, Set<Human> heroes) {
        if  (panel==null ) throw new Exception("Съемка не может начаться без камеры или пульта!");
        this.camera = camera;
        this.panel = panel;
        if (heroes == null) this.heroes = new HashSet<>();
        else this.heroes = heroes;
        init();
    }

    public void cameraZoom(int x){
        panel.cameraZoom(x);
        if (panel.getCamera().getCoordinate()>40) {
            for (Human hero: heroes) hero.getHead().getHair().setHairCondition(HairCondition.MOVES);
        }else for (Human hero: heroes) hero.getHead().getHair().setHairCondition(HairCondition.CALM);
    }

    public void cameraDeparture(int x){
        panel.cameraDeparture(x);
        for (Human hero: heroes) hero.getHead().getHair().setHairCondition(HairCondition.CALM);
    }

    public void init(){
        panel.setTurnOn(true);
        panel.setCamera(camera);
    }

    public void start(){
        panel.startShooting();
    }

    public void stop(){
        panel.stopShooting();
    }

    public void finish() {
        panel.disconnection();
        panel.setTurnOn(false);
    }

    @SneakyThrows
    public void addHero(Human human){
        if (this.heroes.contains(human)) throw new Exception("Этот человек уже снимается!");
        heroes.add(human);
    }

    @SneakyThrows
    public void removeHero(Human human){
        if (!this.heroes.contains(human)) throw new Exception("Этот человек не снимается!");
        heroes.remove(human);
    }

}
