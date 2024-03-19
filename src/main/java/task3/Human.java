package task3;


import lombok.Data;

import task3.enam.HairCondition;
import task3.enam.TypeOfMovement;

@Data
public class Human {
    private String name;
    private Head head;

    public Human(String name, int size, boolean ethereal) {
        this.name = name;
        this.head = new Head(new Hair(size,HairCondition.CALM),ethereal);
    }

    public HairCondition getFeel(){
        return head.getHair().getHairCondition();
    };


    public void startToMove(TypeOfMovement type ){
        if (type == TypeOfMovement.INTENTLY || type == TypeOfMovement.QUICKLY){
            head.getHair().setHairCondition(HairCondition.MOVES);
        }
    }
    public void endToMove(){
        head.getHair().setHairCondition(HairCondition.CALM);
    }

    public void changeThePace(TypeOfMovement type){
        if (type == TypeOfMovement.INTENTLY || type == TypeOfMovement.QUICKLY){
            head.getHair().setHairCondition(HairCondition.MOVES);
        }else head.getHair().setHairCondition(HairCondition.CALM);
    }
}
