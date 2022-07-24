package test.java;

import java.util.Random;
import main.java.*;


/**
 * Test Class to run basic Unit testing on the Battleship Model
 * 
 * @author Aaron Zachariah
 * 
 */
public class BattleshipTest {
    
    Battleship b = null;

    BattleshipTest(Battleship ship){
        this.b = ship; 
    }

    public void TestConstructor(){

        if(!(this.b == null)){
            System.out.println("Constructor Test Passed!");
        } else {
            System.out.println("Constructor Test Failed!");
        }

    }

    public void TestMyInitialBoard(){
        int ctr = 0;
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(this.b.myBoard[i][j] == Battleship.SHIP){
                    ctr++;
                }
            }
        }

        if(ctr == 18){
            System.out.println("MyInitialBoard Test Passed!");
        } else {
            System.out.println("MyInitialBoard Test Failed!");
        }

    }

    public void TestEnemyInitialBoard(){
        int ctr = 0;
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(this.b.enemyBoard[i][j] == Battleship.NONE){
                    ctr++;
                }
            }
        }

        if(ctr == 100){
            System.out.println("MyInitialBoard Test Passed!");
        } else {
            System.out.println("MyInitialBoard Test Failed!");
        }
        
    }

    public void TestInitialState(){

        if(this.b.state == BattleshipState.INIT){
            System.out.println("InitialState Test Passed!");
        } else {
            System.out.println("InitialState Test Failed!");
        }

    }

    public void TestRandomize(){

        this.b.randomize();
        int ctr = 0;
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(this.b.myBoard[i][j] == Battleship.SHIP){
                    ctr++;
                }
            }
        }
        
        if(ctr == 18){
            System.out.println("Randomize Test Passed!");
        } else {
            System.out.println("Randomize Test Failed!");
        }

    }

    public void TestPlaceShip4(){
        Random r = new Random();
        this.b.placeShip4(r);

        int ctr = 0;
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(this.b.myBoard[i][j] == Battleship.SHIP){
                    ctr++;
                }
            }
        }

        if(ctr == 22){
            System.out.println("PlaceShip4 Test Passed!");
        } else {
            System.out.println("PlaceShip4 Test Failed!");
        }

    }

    public void TestPlaceShip3(){
        Random r = new Random();
        this.b.placeShip3(r);

        int ctr = 0;
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(this.b.myBoard[i][j] == Battleship.SHIP){
                    ctr++;
                }
            }
        }

        if(ctr == 25){
            System.out.println("PlaceShip3 Test Passed!");
        } else {
            System.out.println("PlaceShip3 Test Failed!");
        }

    }
    
    public void TestPlaceShip2(){
        Random r = new Random();
        this.b.placeShip2(r);

        int ctr = 0;
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(this.b.myBoard[i][j] == Battleship.SHIP){
                    ctr++;
                }
            }
        }

        if(ctr == 27){
            System.out.println("PlaceShip2 Test Passed!");
        } else {
            System.out.println("PlaceShip2 Test Failed!");
        }

    }

    public void TestPlaceShip1(){
        Random r = new Random();
        this.b.placeShip1(r);

        int ctr = 0;
        for(int i = 0;i < 10;i++){
            for(int j = 0;j < 10;j++){
                if(this.b.myBoard[i][j] == Battleship.SHIP){
                    ctr++;
                }
            }
        }

        if(ctr == 28){
            System.out.println("PlaceShip1 Test Passed!");
        } else {
            System.out.println("PlaceShip1 Test Failed!");
        }

    }

    public void TestFireAt(){

        int state = this.b.myBoard[0][0];
        boolean r = this.b.fireAt(0, 0);

        if(state == Battleship.SHIP){
            if(r){
                System.out.println("FireAt Test Passed!");
            } else {
                System.out.println("FireAt Test Failed!");
            }
        } else {
            if(r){
                System.out.println("FireAt Test Failed!");
            } else {
                System.out.println("FireAt Test Passed!");
            }
        }

    }

    public void TestFireResult(){

        this.b.fireResult(0, 0, Battleship.HIT);
        if(this.b.enemyBoard[0][0] == Battleship.HIT){
            System.out.println("FireResult Test Passed!");
        } else {
            System.out.println("FireResult Test Failed!");
        }

    }

    public void TestIsOver(){

        boolean r = this.b.isOver();

        if(r){
            System.out.println("IsOver Test Failed!");
        } else {
            System.out.println("IsOver Test Passed!");
        }

    }

    public void TestGetState(){

        BattleshipState s = this.b.getState();
        if(s == BattleshipState.INIT){
            System.out.println("GetState Test Passed!");
        } else {
            System.out.println("GetState Test Failed!");
        }

    }

    public void TestSetState() {

        this.b.setState(BattleshipState.P1TURN);
        
        if(this.b.getState() == BattleshipState.P1TURN){
            System.out.println("SetState Test Passed!");
        } else {
            System.out.println("SetState Test Failed!");
        }

    }



    public static void main(String[] args){

        Peer p = null;
        Battleship battle = new Battleship(p);
        BattleshipTest tester = new BattleshipTest(battle);

        System.out.println("\n-------- RUNNING TESTS --------\n");

        tester.TestConstructor();
        tester.TestMyInitialBoard();
        tester.TestEnemyInitialBoard();
        tester.TestInitialState();
        tester.TestRandomize();
        tester.TestPlaceShip4();
        tester.TestPlaceShip3();
        tester.TestPlaceShip2();
        tester.TestPlaceShip1();
        tester.TestFireAt();
        tester.TestFireResult();
        tester.TestIsOver();
        tester.TestGetState();
        tester.TestSetState();

        System.out.println("\n-------- FINISHED TESTS --------\n");

    }

}
