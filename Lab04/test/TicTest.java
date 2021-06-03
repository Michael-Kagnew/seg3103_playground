package test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.platform.console.shadow.picocli.CommandLine.Parameters;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import src.Tic;

@RunWith(Parameterized.class)
public class TicTest{
    

    //The object
    Tic obj;

    //The expected outcome
    String[][] expected;

    public TicTest(Object[][] ticVals, Object[][] expected){
       
        this.obj = new Tic((int)ticVals[0][0], (int)ticVals[0][1]);
        this.expected = (String[][]) expected;
    }


    @Parameters
    public static List<Object[][][]> data(){
        List<Object[][][]> params = new LinkedList<Object[][][]>();
        params.add(new Object[][][]{ {{2,3}}, {{"_|","_|", "_"}, {"_|","_"}}} );
        params.add(new Object[][][]{ {{1,2}}, {{"_|","_"}}} );
        params.add(new Object[][][]{ {{1,2}}, {{"_|","_"}, {"_|","_"}}} );
        return params;
    }



    @Test
    public void boardTest(){
        assertEquals(Arrays.deepToString(expected), Arrays.deepToString(obj.board));
    }
}