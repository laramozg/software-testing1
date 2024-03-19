import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import task3.*;
import task3.enam.HairCondition;
import task3.enam.TypeOfMovement;

import java.util.HashSet;


import static org.junit.jupiter.api.Assertions.*;

public class TextModelTest {
    @Nested
    class HairTest{
        Human human1;
        Human human2;

        @BeforeEach
        void init() {
            human1 = new Human(
                    "Артур",
                    5, true
            );
            human2 = new Human(
                    "Лара",
                    10, false
            );
        }

        @Test
        @DisplayName("Check the hair moved")
        void checkTheHairMoved() {
            assertAll(
                    () -> {
                        human1.startToMove(TypeOfMovement.QUICKLY);
                        assertEquals(HairCondition.MOVES, human1.getFeel());
                    },
                    () -> {
                        human2.startToMove(TypeOfMovement.SLOWLY);
                        assertEquals(HairCondition.CALM, human2.getFeel());
                    },
                    () -> {
                        human1.endToMove();
                        assertEquals(HairCondition.CALM, human1.getFeel());
                    },
                    () -> {
                        human2.changeThePace(TypeOfMovement.INTENTLY);
                        assertEquals(HairCondition.MOVES, human2.getFeel());
                    }
            );
        }
    }

    @Nested
    class ShootingTest{
        Human human1;
        Human human2;
        Shooting shooting;
        @BeforeEach
        void init() {
            human1 = new Human(
                    "Артур",
                    5, true
            );
            human2 = new Human(
                    "Лара",
                    10, false
            );
        }

        @Test
        @DisplayName("Check shooting without camera or panel")
        void checkWithoutCameraOrPanel(){
            Throwable exception = assertThrows(Exception.class, () -> shooting = new Shooting(null,null,null));
            assertEquals("Съемка не может начаться без камеры или пульта!", exception.getMessage());
        }

        @Test
        @DisplayName("Check add hero")
        void checkAddHero(){
            shooting = new Shooting(new Camera(),new Panel(),null);
            shooting.addHero(human1);
            Throwable exception = assertThrows(Exception.class, () ->  shooting.addHero(human1));
            assertEquals("Этот человек уже снимается!", exception.getMessage());
        }

        @Test
        @DisplayName("Check remove hero")
        void checkRemoveHero(){
            shooting = new Shooting(new Camera(),new Panel(),null);
            Throwable exception = assertThrows(Exception.class, () -> shooting.removeHero(human2));
            assertEquals("Этот человек не снимается!", exception.getMessage());
        }

        @Test
        @DisplayName("Check big distance camera")
        void checkBigCameraDistance(){
            shooting = new Shooting(new Camera(),new Panel(),null);
            Throwable exception = assertThrows(Exception.class, () -> shooting.cameraZoom(70));
            assertEquals("Недопустимая длина перемещения!", exception.getMessage());
            Throwable exception2 = assertThrows(Exception.class, () -> shooting.cameraDeparture(70));
            assertEquals("Недопустимая длина перемещения!", exception2.getMessage());
        }

        @Test
        @DisplayName("Check normal distance camera")
        void checkNormalCameraDistance(){
            shooting = new Shooting(new Camera(),new Panel(),null);
            shooting.cameraZoom(20);
            assertEquals(20, shooting.getCamera().getCoordinate());
        }

        @Test
        @DisplayName("Check hair moves")
        void checkHairMoves(){
            shooting = new Shooting(new Camera(),new Panel(),new HashSet<>());
            shooting.addHero(human1);
            shooting.addHero(human2);
            assertAll(
                    () -> {
                        shooting.cameraZoom(25);
                        assertEquals(HairCondition.CALM, human1.getFeel());
                        assertEquals(HairCondition.CALM, human2.getFeel());
                    },
                    () -> {
                        shooting.cameraZoom(20);
                        assertEquals(HairCondition.MOVES, human1.getFeel());
                        assertEquals(HairCondition.MOVES, human2.getFeel());
                    }
            );
        }

    }
}
