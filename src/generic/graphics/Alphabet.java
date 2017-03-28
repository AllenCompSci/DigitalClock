package generic.graphics;

import java.awt.*;

/**
 * Created by taylor hudson on 3/28/2017.
 */
public class Alphabet {
    boolean LEDAlpha[][][];
    float unit;
    public Alphabet(int unit){
        this.unit = unit;
        LEDAlpha = new boolean[26][][];
        for(int z = 0; z < 26; z++){
            LEDAlpha[z] = new boolean[7][];
            for(int i = 0; i < 7; i++){
                LEDAlpha[z][i] = new boolean[5];
                for(int j = 0; j < 5; j++){
                    LEDAlpha[z][i][j] = false;
                }
            }
        }
       init();

    }
    private void init(){
        A();
        B();
        C();
        D();
        E();
        F();
        G();
        H();
        I();
        J();
        K();
        L();
        M();
        N();
        O();
        P();
        Q();
        R();
        S();
        T();
        U();
        V();
        W();
        X();
        Y();
        Z();
    }
    private void set(int z, int i, int j, int bool){
        LEDAlpha[z][i][j] = (bool == 1);
    }
    private void corners(int z, int bool){
        topCorners(z,bool);
        bottomCorners(z, bool);
    }
    private void outside(int z, int bool){
        leftSide(z,bool);
        bottom(z,bool);
        top(z,bool);
        rightSide(z,bool);
    }
    private void topCorners(int z, int bool){
        LEDAlpha[z][0][0] = (bool == 1);
        LEDAlpha[z][0][4] = (bool == 1);
    }
    private void bottomCorners(int z, int bool){
        LEDAlpha[z][6][0] = (bool == 1);
        LEDAlpha[z][6][4] = (bool == 1);
    }
    private void leftSide(int z, int bool){
        for(int i = 0; i < 7; i++){
            set(z, i, 0, bool);
        }
    }
    private void rightSide(int z, int bool){
        for(int i = 0; i < 7; i++){
            set(z, i, 4, bool);
        }
    }
    private void top(int z, int bool){
        for(int i = 0; i < 5; i++){
            set(z, 0, i, bool);
        }
    }
    private void bottom(int z, int bool){
        for(int i = 0; i < 5; i++){
            set(z, 6, i, bool);
        }
    }
    private void A(){
        //A
        LEDAlpha[0][0][0] = LEDAlpha[0][1][0] = LEDAlpha[0][0][4] = LEDAlpha[0][1][4] = false;
        LEDAlpha[0][1][1] = LEDAlpha[0][1][3] = true;
        set(0,0,2,1);
        for(int t = 2; t < 7; t++){
            set(0, t, 0, 1);
            set(0, t, 4, 1);
        }
        for(int t = 0; t < 5; t++)
            set(0,3,t,1);
    }
    private void B(){
        // B
        for(int t = 0; t < 7; t++){
            set(1, t, 0, 1);
            set(1, t, 4, 1);
        }
        for(int t = 0; t < 5; t++){
            set(1, 3, t, 1);
            set(1, 0, t, 1);
            set(1, 6, t, 1);
        }
        set(1,0,4,0);
        set(1,3,4,0);
        set(1,6,4,0);
    }
    private void C(){
        // C
        top(2,1);
        leftSide(2,1);
        bottom(2,1);
        corners(2,0);
        set(2,1,4,1);
        set(2,5,4,1);
    }
    private void D(){
        // 3
        outside(3,1);
        set(3,0,4,0);
        set(3,6,4,0);
    }
    private void E(){
        // 4
        outside(4,1);
        rightSide(4,0);
        set(4,0,4,1);
        set(4,6,4,1);
        set(4,3,1,1);
        set(4,3,2,1);
    }
    private void F(){
        //5
        top(5,1);
        leftSide(5,1);
        set(5,3,1,1);
        set(5,3,2,1);
    }
    private void G(){
        //6
        outside(6,1);
        corners(6,0);
        set(6,4,3,1);
        set(6,3,4,0);
        set(6,2,4,0);
    }
    private void H(){
        // 7
        leftSide(7,1);
        rightSide(7,1);
        for(int t = 0; t < 5; t++){
            set(7,3,t,1);
        }
    }
    private void I(){
        // 8
        for(int t = 1; t < 4; t++){
            set(8,0,t,1);
            set(8,6,t,1);
        }
        for(int t = 0; t < 7; t++){
            set(8, t, 2, 1);
        }
    }
    private void J(){
        //9
        bottom(9,1);
        rightSide(9,1);
        bottomCorners(9,0);
        for(int t = 4; t < 6; t++){
            set(9,t,0,1);
        }
    }
    private void K(){
        // 10
        leftSide(10,1);
        for(int t = 0; t < 4; t++){
            set(10, 3+t, 1 + t, 1);
            set(10, 3-t, 1 + t, 1);
        }
    }
    private void L(){
        // 11
        leftSide(11,1);
        bottom(11,1);
    }
    private void M(){
        // 12
        leftSide(12,1);
        rightSide(12,1);
        set(12,1,1,1);
        set(12,1,3,1);
        set(12,2,2,1);
    }
    private void N(){
        // 13
        leftSide(13,1);
        rightSide(13,1);
        set(13,2,1,1);
        set(13,3,2,1);
        set(13,4,3,1);
    }
    private void O(){
        // 14
        outside(14,1);
        corners(14,0);
    }
    private void P(){
        // 15
        leftSide(15,1);
        for(int t = 1; t < 5; t++){
            set(15,0,t,1);
            set(15,3,t,1);
        }
        set(15,1,4,1);
        set(15,2,4,1);
        set(15,0,4,0);
        set(15,3,4,0);
    }
    private void Q(){
        // 16
        outside(16,1);
        corners(16,0);
        for(int t = 0; t < 3; t++){
            set(16,4+t,2+t,1);
        }
    }
    private void R(){
        // 17
        leftSide(17,1);
        for(int t = 1; t < 4; t++){
            set(17,0,t,1);
            set(17,3,t,1);
        }
        set(17,1,4,1);
        set(17,2,4,1);
        set(17,4,2,1);
        set(17,5,3,1);
        set(17,6,4,1);
    }
    private void S(){
        // 18
        for (int t = 1; t < 4; t++) {
            set(18, 0, t, 1);
            set(18, 3, t, 1);
            set(18, 6, t, 1);
        }
        for (int t = 0; t < 2; t++) {
            set(18, 1 + t, 0, 1);
            set(18, 4 + t, 4, 1);
        }
        set(18,1,4,1);
        set(18,5,0,1);
    }
    private void T(){
        // 19
        top(19,1);
        for(int t= 0; t < 7; t++)
            set(19,t,2,1);
    }
    private void U(){
        // 20
        leftSide(20,1);
        rightSide(20,1);
        bottom(20,1);
        bottomCorners(20,0);
    }
    private void V(){
        // 21
        leftSide(21,1);
        rightSide(21,1);
        for(int t = 0; t < 5; t++){
            set(21,5,t,1);
            set(21,6,t,1);
        }
        bottomCorners(21,0);
        set(21,5,0,0);
        set(21,5,4,0);
        set(21,6,1,0);
        set(21,6,3,0);
        set(21,5,2,0);
    }
    private void W(){
        // 22
        leftSide(22,1);
        rightSide(22,1);
        set(22,5,1,1);
        set(22,5,3,1);
        set(22,4,2,1);
    }
    private void X(){
        // 23
        corners(23,1);
        for(int t = 0; t < 5; t++){
            set(23, 1 + t, t, 1);
            set(23, 1 + t, 4-t, 1);
        }
    }
    private void Y(){
        // 24
        topCorners(24,1);
        for(int t = 0; t < 3; t++){
            set(24,1 + t, t, 1);
            set(24,1 + t, 4-t, 1);
        }
        for(int t = 0; t < 4; t++){
            set(24, 3+t, 2, 1);
        }
    }
    private void Z(){
        // 25
        top(25,1);
        bottom(25,1);
        for(int t = 0; t < 5; t++){
            set(25,5-t,t,1);
        }
    }
    public void draw(Graphics2D g2d, char t, int x, int y){
        int val = (int)t - 65;
        if(val > 26){
            val -= (97-65);
        }
        int XShift = x + (int)(unit/2);
        int YShift = y + (int)(unit/2);
        for(int i = 0; i < 7; i++ )
            for(int j = 0 ; j < 5; j++){
                if(LEDAlpha[val][i][j]){
                    g2d.setColor(DigitalTimer.clockColor);
                    g2d.fillOval(XShift + j*(int)unit, YShift + i * (int)unit, (int)unit, (int)unit);
                }
                else{
                    g2d.setColor(DigitalTimer.transparent);
                    g2d.fillOval(XShift + j*(int)unit, YShift + i * (int)unit, (int)unit, (int)unit);
                }
            }
    }
}
