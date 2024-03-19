package task3;

import lombok.Data;
import lombok.SneakyThrows;

@Data
public class Panel{
    private boolean turnOn = false;
    private Camera camera = null;
    private int maxDistance = 50;

    public void disconnection(){
        camera = null;
    }

    public void startShooting(){
        camera.startShooting();
    }

    public void stopShooting(){
        camera.stopShooting();
    }

    @SneakyThrows
    public void cameraZoom(int x){
        if (camera.getCoordinate() + x > 50) throw new Exception("Недопустимая длина перемещения!");
        camera.setCoordinate(camera.getCoordinate() + x);
    }

    @SneakyThrows
    public void cameraDeparture(int x){
        if (camera.getCoordinate() - x < 0) throw new Exception("Недопустимая длина перемещения!");
        camera.setCoordinate(camera.getCoordinate() - x);
    }
}
