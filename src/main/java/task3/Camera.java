package task3;

import lombok.Data;

@Data
public class Camera {
    private long startTime;
    private long timeShooting;
    private int coordinate = 0;


    public void startShooting() {
        startTime = System.currentTimeMillis();
    }

    public void stopShooting() {
        timeShooting =  System.currentTimeMillis() - startTime;
    }

    public int getCoordinate() {
        return coordinate;
    }
    public void setCoordinate(int x){
        coordinate = x;
    }
}
