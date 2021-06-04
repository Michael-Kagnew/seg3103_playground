package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


import org.junit.runners.Parameterized.Parameters;
import src.Tic;

@RunWith(Parameterized.class)
public class TicNextTest {
    

    //The object
    Tic obj;
    int nextTicX, nextTicY;

    //The expected outcome
    Object[][] expected;

    public TicNextTest(Object[][] ticVals, Object[][] expected){
        
        this.obj = new Tic((Integer)(ticVals[0][0]), (Integer)(ticVals[0][1]));
        nextTicX = (Integer)ticVals[0][2];
        nextTicY = (Integer)ticVals[0][3];
        
        this.expected = expected;
    }


    @Parameters
    public static List<Object[][][]> data(){
        List<Object[][][]> params = new LinkedList<Object[][][]>();
        params.add(new Object[][][]{ {{2,3,0,0}}, {{"X|","_|", "_"}, {"_|","_|","_"}}} );
        params.add(new Object[][][]{ {{1,2,1,0}}, {{"_|","X"}}} );
        params.add(new Object[][][]{ {{2,2,1,1}}, {{"_|","_"}, {"_|","X"}}} );
        params.add(new Object[][][]{ {{2,2,1,0}}, {{"_|","X"}, {"_|","_"}}} );
        params.add(new Object[][][]{ {{3,3,1,2}}, {{"_|","_|", "_"}, {"_|","_|","_"}, {"_|","X|", "_"}}} );

        return params;
    }

    @Test
    public void tickBoardTest() {
        assertEquals(expected, obj.tickBoard(nextTicX, nextTicY, Tic.Player));
    }
}
